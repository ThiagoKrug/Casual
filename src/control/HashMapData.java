package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import servlet.Configuration;
import servlet.SearchType;

import jdbc.ConnectionFactory;
import dao.CategoryDAO;
import dao.PersonCategoryDAO;
import dao.PersonDAO;

import model.Category;
import model.Person;
import model.PersonCategory;
import model.Relationship;
import model.rank.Judge;
import model.rank.Popularity;
import model.rank.RelationshipLinksNumber;
import model.rank.RelationshipOccurrenceNumber;

public class HashMapData implements Search {

	private HashMap<String, Person> indexedData = null;
	private Carla carla = null;
	private Connection connection = null;

	public HashMapData(Carla carla, int popularityDispersion) throws SQLException {
		this(carla, new ConnectionFactory().getConnection(), popularityDispersion);
	}

	public HashMapData(Carla carla, Connection connection, int popularityDispersion) throws SQLException {
		this.carla = carla;
		this.connection = connection;
		this.indexedData = new HashMap<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		System.out.println("Getting persons...");
		List<Person> persons = pdao.getAllPersons();
		CategoryDAO cdao = new CategoryDAO(connection);
		System.out.println("Getting categories...");
		List<Category> categories = cdao.getAllCategories();
		PersonCategoryDAO pcdao = new PersonCategoryDAO(connection);
		System.out.println("Getting personCategories...");
		List<PersonCategory> personCategories = pcdao.getAllPersonsCategories();

		System.out.println("Creating graph...");
		// this.createGraphByIteration(persons, categories, personCategories);
		this.createGraphByHashMap(persons, categories, personCategories);

		System.out.println("Inserting persons into HashMap...");
		for (Iterator itPerson = persons.iterator(); itPerson.hasNext();) {
			Person person = (Person) itPerson.next();
			this.indexedData.put(person.getName(), person);
		}
		
		System.out.println("Calculating Popularity...");
		for (int i = 0; i < popularityDispersion; i++) {
			this.calculatePopularityDispersion();
		}

		this.connection.close();
	}
	
	public void resetPopularity() {
		for (Iterator itPerson = this.indexedData.values().iterator(); itPerson.hasNext();) {
			Person person = (Person) itPerson.next();
			person.setPopularity(1);
			person.setTemporaryPopularity(0);
		}
	}

	public void calculatePopularityDispersion() {
		for (Iterator itPerson = this.indexedData.values().iterator(); itPerson.hasNext();) {
			Person person = (Person) itPerson.next();
			person.calculateTemporaryPopularity();
		}
		for (Iterator itPerson = this.indexedData.values().iterator(); itPerson.hasNext();) {
			Person person = (Person) itPerson.next();
			person.calculatePopularity();
		}
	}

	private void createGraphByHashMap(List<Person> persons, List<Category> categories, List<PersonCategory> personCategories) {
		HashMap<Integer, Category> hmCategory = new HashMap<>();
		for (Iterator itCategory = categories.iterator(); itCategory.hasNext();) {
			Category category = (Category) itCategory.next();
			hmCategory.put(category.getId(), category);
		}

		HashMap<Integer, Person> hmPerson = new HashMap<>();
		for (Iterator itPerson = persons.iterator(); itPerson.hasNext();) {
			Person person = (Person) itPerson.next();
			hmPerson.put(person.getId(), person);
		}
		
		for (Iterator itPersonCategory = personCategories.iterator(); itPersonCategory.hasNext();) {
			PersonCategory personCategory = (PersonCategory) itPersonCategory.next();

			Category category = hmCategory.get(personCategory.getIdCategory());
			Person person = hmPerson.get(personCategory.getIdPerson());
			
			person.addCategory(category);
			category.addPerson(person);
		}
	}

	@Override
	public String didYouMean(String search, double minScore) {
		Iterator<String> itr = indexedData.keySet().iterator();
		String didYouMean = null;
		double score, similar = Double.MIN_NORMAL;
		while (itr.hasNext()) {
			String personName = (String) itr.next();
			score = this.carla.getScore(personName, search, "1");
			if (similar < score && score >= minScore) {
				similar = score;
				didYouMean = personName;
			}
		}
		return didYouMean;
	}

	@Override
	public List<Relationship> searchBy(String search, Judge calculator) throws ServletException {

		boolean computePopularity = true;
		Person person = this.indexedData.get(search);

		if (person != null) {
			if (Configuration.getInstance().getSearchType() == SearchType.Category) {
				computePopularity = false;
				person.getRelationships().clear();
				person.categoriesToRelationships(indexedData);
				calculator.rank(person);
			}

			if (calculator.getClass().equals(Popularity.class)) {
				computePopularity = false;

				if (Configuration.getInstance().getSearchType() == SearchType.Relationship) {
					List<Person> persons = new ArrayList<>(this.indexedData.values());
					RelationshipOccurrenceNumber roc = new RelationshipOccurrenceNumber();
					roc.computeScore(persons, true);
					calculator.computeScore(persons, false);

				} else if (Configuration.getInstance().getSearchType() == SearchType.Category) {
					RelationshipLinksNumber rln = new RelationshipLinksNumber();
					rln.computeScore(person, true);
				}
			}

			calculator.computeScore(person, computePopularity);
			Collections.sort(person.getRelationships());
			return person.getRelationships();
		}
		return null;
	}

	@Override
	public List<Relationship> searchBy(Person search, Judge calculator) throws ServletException {
		return this.searchBy(search.getName(), calculator);
	}

}

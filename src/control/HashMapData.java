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

	public HashMapData(Carla carla) throws SQLException {
		this(carla, new ConnectionFactory().getConnection());
	}

	public HashMapData(Carla carla, Connection connection) throws SQLException {
		this.carla = carla;
		this.connection = connection;
		this.indexedData = new HashMap<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		List<Person> persons = pdao.getAllPersons();
		CategoryDAO cdao = new CategoryDAO(connection);
		List<Category> categories = cdao.getAllCategories();
		PersonCategoryDAO pcdao = new PersonCategoryDAO(connection);
		List<PersonCategory> personCategories = pcdao.getAllPersonsCategories();

		// constrói o grafo
		for (Category category : categories) {
			// boolean = ;
			for (PersonCategory personCategory : personCategories) {
				if (personCategory.getIdCategory() == category.getId()) {
					for (Person person : persons) {
						if (personCategory.getIdPerson() == person.getId()) {
							person.addCategory(category);
							category.addPerson(person);
						}
					}
				}
			}
		}

		for (Person person : persons) {
			this.indexedData.put(person.getName(), person);
			person.calculatePopularity();
		}

		this.connection.close();
	}

	// Antigo construtor, com a implementação de relacionamentos sem categoria
	/*
	 * public HashMapData(Carla carla, Connection connection) throws
	 * SQLException { this.carla = carla; this.connection = connection;
	 * this.indexedData = new HashMap<>(); PersonDAO pdao = new
	 * PersonDAO(this.connection); List<Person> persons = pdao.getAllPersons();
	 * 
	 * for (Person person : persons) { this.indexedData.put(person.getName(),
	 * person); }
	 * 
	 * /*RelationshipOccurrenceNumber roc = new RelationshipOccurrenceNumber();
	 * roc.computeScore(persons, true);
	 * 
	 * Popularity pop = new Popularity(); pop.computeScore(persons, false);* /
	 * 
	 * this.connection.close(); }
	 */

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
	public List<Relationship> searchBy(String search, Judge calculator)
			throws ServletException {

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
					List<Person> persons = new ArrayList<>(
							this.indexedData.values());
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
	public List<Relationship> searchBy(Person search, Judge calculator)
			throws ServletException {
		return this.searchBy(search.getName(), calculator);
	}

}

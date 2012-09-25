package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import jdbc.ConnectionFactory;
import dao.PersonDAO;

import model.Person;
import model.Relationship;
import model.rank.Judge;
import model.rank.Popularity;
import model.rank.RelationshipOccurrenceNumber;

public class HashMapData implements Search {

	private HashMap<String, Person> indexedData = null;
	private Carla carla = null;
	private Connection connection = null;

	public HashMapData(Carla carla) throws SQLException {
		this.carla = carla;
		this.connection = new ConnectionFactory().getConnection();
		this.indexedData = new HashMap<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		List<Person> persons = pdao.getAllPersons();
		
		for (Person person : persons) {
			this.indexedData.put(person.getName(), person);
		}

		RelationshipOccurrenceNumber roc = new RelationshipOccurrenceNumber();
		roc.computeScore(persons, true);
		
		Popularity pop = new Popularity();
		pop.computeScore(persons, false);
		
		
		this.connection.close();
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
	public List<Relationship> searchBy(String search,
			Judge calculator) throws ServletException {
		Person person = this.indexedData.get(search);
		if (person != null) {
			calculator.computeScore(person, true);
			Collections.sort(person.getRelationships());
			return person.getRelationships();
		}
		return null;
	}
	
	@Override
	public List<Relationship> searchBy(Person search,
			Judge calculator) throws ServletException {
		return this.searchBy(search.getName(), calculator);
	}

}

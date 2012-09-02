package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;

import jdbc.ConnectionFactory;
import dao.PersonDAO;

import model.Person;
import model.Relationship;
import model.comparator.RelationshipComparator;

public class HashMapData implements Search {

	private HashMap<String, Person> indexedData = null;
	private Carla carla = null;
	private Connection connection = null;

	public HashMapData(Carla carla) throws SQLException {
		this.carla = carla;
		this.connection = new ConnectionFactory().getConnection();
		this.indexedData = new HashMap<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		ArrayList<Person> persons = pdao.getAllPersons();

		// melhorar o desempenho!! O(n³)
		for (Person person : persons) {
			for (Relationship relationship : person.getRelationships()) {
				int id = relationship.getPerson2().getId();
				for (Person person2 : persons) {
					if (id == person2.getId()) {
						relationship.setPerson2(person2);
					}
				}
			}
			this.indexedData.put(person.getName(), person);
		}

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
	public ArrayList<Relationship> searchBy(String search,
			RelationshipComparator comparator) throws ServletException {
		Person person = this.indexedData.get(search);
		if (person != null) {
			if (person.getOrderBy() != comparator.getClass()) {
				person.sortBy(comparator);
			}
			return person.getRelationships();
		}
		return null;
	}
	
	@Override
	public ArrayList<Relationship> searchBy(Person search,
			RelationshipComparator comparator) throws ServletException {
		Person person = this.indexedData.get(search);
		if (person != null) {
			if (person.getOrderBy() != comparator.getClass()) {
				person.sortBy(comparator);
			}
			return person.getRelationships();
		}
		return null;
	}

}

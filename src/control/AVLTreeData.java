package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import jdbc.ConnectionFactory;
import dao.PersonDAO;

import model.Person;
import model.Relationship;
import model.rank.Judge2;
import model.rank.Judge;

public class AVLTreeData implements Search {

	private AVLTree<Person> indexedData = null;
	private Carla carla = null;
	private Connection connection = null;

	public AVLTreeData(Carla carla) throws SQLException {
		this.carla = carla;
		this.connection = new ConnectionFactory().getConnection();
		this.indexedData = new AVLTree<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		this.indexedData.insertAll(pdao.getAllPersons());
		this.connection.close();
	}

	@Override
	public String didYouMean(String search, double minScore) {
		Iterator<Person> itr = indexedData.iterator();
		String didYouMean = null;
		double score, similar = Double.MIN_NORMAL;
		while (itr.hasNext()) {
			Person person = (Person) itr.next();
			score = this.carla.getScore(person.getName(), search, "1");
			if (similar < score && score >= minScore) {
				similar = score;
				didYouMean = person.getName();
			}
		}
		return didYouMean;
	}

	@Override
	public List<Relationship> searchBy(String search,
			Judge calculator) throws ServletException {
		Person person1 = new Person();
		person1.setName(search);
		Person person = this.indexedData.find(person1);
		if (person != null) {
			if (person.getCalculatedBy() != calculator.getClass()) {
				new Judge2().computeScore(person, calculator);
			}
			return person.getRelationships();
		}
		return null;
	}

	@Override
	public List<Relationship> searchBy(Person search,
			Judge calculator) throws ServletException {
		Person person = this.indexedData.find(search);
		if (person != null) {
			if (person.getCalculatedBy() != calculator.getClass()) {
				new Judge2().computeScore(person, calculator);
			}
			return person.getRelationships();
		}
		return null;
	}

}

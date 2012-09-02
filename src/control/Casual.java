package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jdbc.ConnectionFactory;

import dao.PersonDAO;

import model.Person;
import model.Relationship;

@Deprecated
public class Casual {

	private HashMap<String, List<Relationship>> indexedData = null;
	private AVLTree<Person> avlTree = null;
	private Connection connection = null;

	@Deprecated
	public Casual() throws SQLException {
		this.connection = new ConnectionFactory().getConnection();
		this.indexedData = new HashMap<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		List<Person> persons = pdao.getAllPersons();
		for (Person person : persons) {
			this.indexedData.put(person.getName(), person.getRelationships());
		}
		this.connection.close();
	}

	@Deprecated
	public Casual(String avlTree) throws SQLException {
		this.connection = new ConnectionFactory().getConnection();
		this.avlTree = new AVLTree<>();
		PersonDAO pdao = new PersonDAO(this.connection);
		this.avlTree.insertAll(pdao.getAllPersons());
		this.connection.close();
	}

	@Deprecated
	public List<Relationship> search(String personName) {
		return this.indexedData.get(personName);
	}
	
	@Deprecated
	public List<Relationship> search(Person person) {
		return this.indexedData.get(person.getName());
	}
	
	@Deprecated
	public int size(String personName) {
		if (this.indexedData != null) {
			return this.search(personName).size();
		} else {
			return this.find(personName).size();
		}
	}
	
	@Deprecated
	public int size(Person person) {
		if (this.indexedData != null) {
			return this.search(person).size();
		} else {
			return this.find(person).size();
		}
	}

	@Deprecated
	public List<Relationship> find(String personName) {
		Person person = new Person();
		person.setName(personName);
		return this.find(person);
	}
	
	@Deprecated
	public List<Relationship> find(Person person) {
		return this.avlTree.find(person).getRelationships();
	}

}

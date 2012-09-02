package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;

import model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.PersonDAO;

public class PersonDAOTest {
	
	private Connection connection;

	@Before
	public void setUp() throws Exception {
		connection = new ConnectionFactory().getTestConnection();
	}

	@After
	public void tearDown() throws Exception {
		connection.close();
	}

	@Test
	public void testPersonDAO() {
		PersonDAO personDAO = new PersonDAO(connection);
		
		assertNotNull(personDAO);
		assertEquals("person", personDAO.getTable());
	}

	@Test
	public void testInsertPerson() {
		PersonDAO personDAO = new PersonDAO(connection);
		
		List<Person> persons = personDAO.getPerson("João Silva");
		int before = persons.size();
		
		Person person = new Person();
		person.setName("João Silva");
		person.setRelationships(null);
		
		personDAO.insert(person);
		
		persons = personDAO.getPerson("João Silva");
		int after = persons.size();
		
		int actual = after - before;
		assertEquals(1, actual);
	}

	@Test
	public void testInsertListOfPerson() {
		PersonDAO personDAO = new PersonDAO(connection);
		List<Person> persons = new ArrayList<>();
		
		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		persons.add(thiago);

		Person sergio = new Person();
		sergio.setName("Sérgio Mergen");
		persons.add(sergio);

		Person jp = new Person();
		jp.setName("João Pablo da Silva");
		persons.add(jp);

		Person bruno = new Person();
		bruno.setName("Bruno Vicelli");
		persons.add(bruno);
		
		personDAO.insert(persons);
		
		List<Person> allPersons = personDAO.getPerson("");
		boolean contains = allPersons.containsAll(persons);
		
		assertTrue(contains);
	}

	@Test
	public void testUpdate() {
		PersonDAO personDAO = new PersonDAO(connection);
		
		Person person = new Person();
		person.setName("Maria Silva");
		person.setRelationships(null);
		
		personDAO.insert(person);
		List<Person> persons = personDAO.getPerson("Maria Silva");
		
		if (persons.size() != 1) {
			fail("Mais de uma pessoa selecionada");
		}

		person = persons.get(0);
		person.setName("Pedro Silva");
		personDAO.update(person);
		
		Person person2 = personDAO.getPerson(person.getId());
		
		assertEquals(person.getName(), person2.getName());
	}

	@Test
	public void testDelete() {
		PersonDAO personDAO = new PersonDAO(connection);
		Person person = new Person();
		person.setName("Pedro Paulo");
		personDAO.insert(person);
		
		List<Person> persons = personDAO.getPerson("Pedro Paulo");
		
		if (persons.size() != 1) {
			fail("Mais de uma pessoa selecionada");
		}
		
		person = persons.get(0);
		
		personDAO.delete(person);
		
		persons = personDAO.getPerson("Pedro Paulo");
		
		assertEquals(0, persons.size());
	}

	@Test
	public void testGetPersonInt() {
		PersonDAO personDAO = new PersonDAO(connection);
		Person person = new Person();
		person.setName("Georgi Facello");
		personDAO.insert(person);
		
		List<Person> persons = personDAO.getPerson("Georgi Facello");
		if (persons.size() != 1) {
			fail("Mais de uma pessoa selecionada");
		}
		
		Person person2 = personDAO.getPerson(persons.get(0).getId());
		
		assertEquals(person.getName(), person2.getName());
	}

	@Test
	public void testGetPersonString() {
		PersonDAO personDAO = new PersonDAO(connection);
		Person person = new Person();
		person.setName("Parto Bamford");
		personDAO.insert(person);
		
		List<Person> persons = personDAO.getPerson("Parto Bamford");
		if (persons.size() != 1) {
			fail("Mais de uma pessoa selecionada");
		}
		
		assertEquals(person.getName(), persons.get(0).getName());
	}

	@Test
	public void testGetAllPersons() {
		fail("Not yet implemented");
	}

}

package test.model;

import static org.junit.Assert.*;

import model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerson() {
		Person person = new Person();
		
		assertNotNull(person);
	}

	@Test
	public void testPersonIntStringArrayListOfRelationship() {
		Person person = new Person(1, "Georgi Facello", null);
		
		assertNotNull(person);
		assertEquals(1, person.getId());
		assertEquals("Georgi Facello", person.getName());
		assertEquals(null, person.getRelationships());
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetName() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRelationships() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRelationships() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrderBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testCompareTo() {
		Person ulises = new Person(1, "Ulises Takanami", null);
		Person martien = new Person(2, "Martien Improta", null);
		
		int expected = ulises.compareTo(ulises);
		assertEquals(0, expected);
		
		expected = ulises.compareTo(martien);
		assertEquals(1, expected);
		
		expected = martien.compareTo(martien);
		assertEquals(0, expected);
		
		expected = martien.compareTo(ulises);
		assertEquals(-1, expected);
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByNumberOfRelationships() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByMaxDistance() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByMinDistance() {
		fail("Not yet implemented");
	}

	@Test
	public void testSortByAverageDistance() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}

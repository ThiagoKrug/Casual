package test.model.rank;

import static org.junit.Assert.*;

import java.util.List;

import model.Person;
import model.Relationship;
import model.rank.AverageDistance;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.AllTests;

public class AverageDistanceTest {
	
	private List<Person> persons;
	private double delta = 0.0000000000001d;

	@Before
	public void setUp() throws Exception {
		this.persons = AllTests.persons;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testComputeScore() {
		AverageDistance ad = new AverageDistance();
		Person ThiagoCassioKrug = null;
		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		thiago.setId(4);
		int i = persons.indexOf(thiago);
		if (i >= 0) {
			ThiagoCassioKrug = persons.get(i);
			ad.computeScore(ThiagoCassioKrug);
		} else {
			fail("Não encontrou a pessoa da pesquisa!");
		}
		
		List<Relationship> relationships = ThiagoCassioKrug.getRelationships();
		// cálculo do Bruno Vicelli -> 1 - (((30 + 45 + 25) / 3) / 108313) = 0.99969224992998685907201043888237
		assertEquals(relationships.get(0).getScore(), 0.99969224992998685907201043888237d, delta);
	}

}

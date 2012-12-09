package test;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import model.Person;
import model.Relationship;
import model.rank.AverageDistance;
import model.rank.MaxDistance;
import model.rank.MinDistance;
import model.rank.RelationshipOccurrenceNumber;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RankESort {
	
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
	public void testAverageDistance() {
		AverageDistance ad = new AverageDistance();
		Person ThiagoCassioKrug = null;
		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		thiago.setId(4);
		int i = persons.indexOf(thiago);
		if (i >= 0) {
			ThiagoCassioKrug = persons.get(i);
			ad.computeScore(ThiagoCassioKrug, true);
		} else {
			fail("Não encontrou a pessoa da pesquisa!");
		}
		Collections.sort(ThiagoCassioKrug.getRelationships());
		List<Relationship> relationships = ThiagoCassioKrug.getRelationships();
		int j = 0;
		assertEquals(relationships.get(j).getPerson2().getName(), "Marcelo Maia Lopes");
		assertEquals(relationships.get(j++).getScore(), 0.99972302493698817316480939499414d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Bruno Vicelli");
		assertEquals(relationships.get(j++).getScore(), 0.99969224992998685907201043888237d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Helison Reus Teixeira");
		assertEquals(relationships.get(j++).getScore(), 0.99966455242368567638849137838179d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Mateus Henrique Dal Forno");
		assertEquals(relationships.get(j++).getScore(), 0.99941835236767516364609972948769d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Renan Marcel Uchôa");
		assertEquals(relationships.get(j++).getScore(), 0.99847355965273482099717177685658d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafhael Rodrigues Cunha");
		assertEquals(relationships.get(j++).getScore(), 0.99832583961912851335173678752012d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafael Tavares Amorim");
		assertEquals(relationships.get(j++).getScore(), 0.99739643440768882774920831294489d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Thiago Aquino de Lima");
		assertEquals(relationships.get(j++).getScore(), 0.98303066113947540923065559997415d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Juliano Rodovalho Macedo");
		assertEquals(relationships.get(j++).getScore(), 0.97067295707809773526723477329591d, delta);
		
	}
	
	@Test
	public void testMinDistance() {
		MinDistance md = new MinDistance();
		Person ThiagoCassioKrug = null;
		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		thiago.setId(4);
		int i = persons.indexOf(thiago);
		if (i >= 0) {
			ThiagoCassioKrug = persons.get(i);
			md.computeScore(ThiagoCassioKrug, true);
		} else {
			fail("Não encontrou a pessoa da pesquisa!");
		}
		Collections.sort(ThiagoCassioKrug.getRelationships());
		List<Relationship> relationships = ThiagoCassioKrug.getRelationships();
		
		int j = 0;
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Marcelo Maia Lopes");
		assertEquals(relationships.get(j++).getScore(), 0.99998153499579921154432062633294d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Bruno Vicelli");
		assertEquals(relationships.get(j++).getScore(), 0.99998153499579921154432062633294d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafael Tavares Amorim");
		assertEquals(relationships.get(j++).getScore(), 0.99991690748109645194944281849824d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Helison Reus Teixeira");
		assertEquals(relationships.get(j++).getScore(), 0.99990767497899605772160313166471d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Thiago Aquino de Lima");
		assertEquals(relationships.get(j++).getScore(), 0.99989844247689566349376344483118d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Mateus Henrique Dal Forno");
		assertEquals(relationships.get(j++).getScore(), 0.9998061174558917212153665764959d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafhael Rodrigues Cunha");
		assertEquals(relationships.get(j++).getScore(), 0.99959376990758265397505377932473d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Renan Marcel Uchôa");
		assertEquals(relationships.get(j++).getScore(), 0.9993260273466712213677028611524d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Juliano Rodovalho Macedo");
		assertEquals(relationships.get(j++).getScore(), 0.99711945934467700091401770793903d, delta);
	}
	
	@Test
	public void testMaxDistance() {
		MaxDistance md = new MaxDistance();
		Person ThiagoCassioKrug = null;
		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		thiago.setId(4);
		int i = persons.indexOf(thiago);
		if (i >= 0) {
			ThiagoCassioKrug = persons.get(i);
			md.computeScore(ThiagoCassioKrug, true);
		} else {
			fail("Não encontrou a pessoa da pesquisa!");
		}
		Collections.sort(ThiagoCassioKrug.getRelationships());
		List<Relationship> relationships = ThiagoCassioKrug.getRelationships();
		
		int j = 0;
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Marcelo Maia Lopes");
		assertEquals(relationships.get(j++).getScore(), 0.98147960078660917895358821194132d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Bruno Vicelli");
		assertEquals(relationships.get(j++).getScore(), 0.98142420577400681358655009094015d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Helison Reus Teixeira");
		assertEquals(relationships.get(j++).getScore(), 0.97165621855178972053216142106672d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Renan Marcel Uchôa");
		assertEquals(relationships.get(j++).getScore(), 0.87781706720338278876958444508046d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Juliano Rodovalho Macedo");
		assertEquals(relationships.get(j++).getScore(), 0.80018095704116772686565786193716d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Mateus Henrique Dal Forno");
		assertEquals(relationships.get(j++).getScore(), 0.78367324328566284748829780358775d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafael Tavares Amorim");
		assertEquals(relationships.get(j++).getScore(), 0.62412637448875019619066963337734d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Thiago Aquino de Lima");
		assertEquals(relationships.get(j++).getScore(), 0.24201157755763389436171096728925d, delta);
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafhael Rodrigues Cunha");
		assertEquals(relationships.get(j++).getScore(), 0.0d, delta);
	}
	
	@Test
	public void testRelationshipOccurrenceNumber() {
		RelationshipOccurrenceNumber ron = new RelationshipOccurrenceNumber();
		Person ThiagoCassioKrug = null;
		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		thiago.setId(4);
		int i = persons.indexOf(thiago);
		if (i >= 0) {
			ThiagoCassioKrug = persons.get(i);
			ron.computeScore(ThiagoCassioKrug, true);
		} else {
			fail("Não encontrou a pessoa da pesquisa!");
		}
		Collections.sort(ThiagoCassioKrug.getRelationships());
		List<Relationship> relationships = ThiagoCassioKrug.getRelationships();
		
		int j = 0;
		
		assertEquals(relationships.get(j).getPerson2().getName(), "Rafhael Rodrigues Cunha");
		assertEquals(relationships.get(j++).getScore(), 1.0d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Helison Reus Teixeira");
		assertEquals(relationships.get(j++).getScore(), 0.9333333333333333d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Rafael Tavares Amorim");
		assertEquals(relationships.get(j++).getScore(), 0.9333333333333333d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Bruno Vicelli");
		assertEquals(relationships.get(j++).getScore(), 0.8d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Mateus Henrique Dal Forno");
		assertEquals(relationships.get(j++).getScore(), 0.8d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Renan Marcel Uchôa");
		assertEquals(relationships.get(j++).getScore(), 0.7333333333333333d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Marcelo Maia Lopes");
		assertEquals(relationships.get(j++).getScore(), 0.6666666666666666d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Thiago Aquino de Lima");
		assertEquals(relationships.get(j++).getScore(), 0.6666666666666666d, delta);

		assertEquals(relationships.get(j).getPerson2().getName(), "Juliano Rodovalho Macedo");
		assertEquals(relationships.get(j++).getScore(), 0.3333333333333333d, delta);
	}

}

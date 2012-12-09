package test.dao;

import static org.junit.Assert.*;

import graphviz.GraphViz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import jdbc.ConnectionFactory;

import model.Person;
import model.Relationship;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.PersonDAO;

public class PersonDAOTest {

	private List<Person> persons;

	@Before
	public void setUp() throws Exception {
		PersonDAO pdao = new PersonDAO(new ConnectionFactory().getTestConnection());
		persons = pdao.getAllPersons();
	}

	/*@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertListOfQextendsModel() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testPersonDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPersonInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPersonString() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetAllPersons() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("concentrate=true;");

		Person p = persons.get(0);
		String name = p.getName().replaceAll(" ", "");
		name = name.replaceAll("\\.", "");
		name = name.replaceAll(",", "");
		name = name.replaceAll("-", "");
		name = name.replaceAll("'", "");
		for (int i = 0; i < 10; i++) {
			System.out.println(p);
			
			double j = Math.random() * p.getRelationships().size();
			p = p.getRelationships().get((int) j).getPerson2();
			String name2 = p.getName().replaceAll(" ", "");
			name2 = name2.replaceAll("\\.", "");
			name2 = name2.replaceAll(",", "");
			name2 = name2.replaceAll("-", "");
			name2 = name2.replaceAll("'", "");
			gv.addln(name + " -> " + name2 + ";");
			name = name2;
		}

		System.out.println(gv.getDotSource());
		gv.addln(gv.end_graph());
		gv.increaseDpi();
		
		//String type = "png";
		//File out = new File("D:/workspace_j2ee/Casual/out." + type); // Windows
		//out.delete();
		//gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
		File out = new File("D:/workspace_j2ee/Casual/10.dot");
		out.delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(out));
			bw.write(gv.getDotSource());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testBreadthFirstSearch() {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());
		gv.addln("concentrate=true;");

		int i = 0;
		for (Person p : persons) {
			String name = p.getName().replaceAll(" ", "");
			name = name.replaceAll("\\.", "");
			name = name.replaceAll(",", "");
			name = name.replaceAll("-", "");
			name = name.replaceAll("'", "");
			int j = 0;
			for (Relationship relationship : p.getRelationships()) {
				String name2 = relationship.getPerson2().getName();
				name2 = name2.replaceAll(" ", "");
				name2 = name2.replaceAll("\\.", "");
				name2 = name2.replaceAll(",", "");
				name2 = name2.replaceAll("-", "");
				name2 = name2.replaceAll("'", "");
				gv.addln(name + " -> " + name2 + ";");
				if (j == 40) {
					break;
				}
				j++;
			}
			if (i == 40) {
				break;
			}
			i++;
		}
		System.out.println(gv.getDotSource());
		gv.addln(gv.end_graph());
		gv.increaseDpi();
		//String type = "png";
		//File out = new File("D:/workspace_j2ee/Casual/BreadthFirstSearch." + type); // Windows
		//out.delete();
		//gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
		
		File out = new File("D:/workspace_j2ee/Casual/bfs.dot");
		out.delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(out));
			bw.write(gv.getDotSource());
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*@Test
	public void testSetRelationship() {
		fail("Not yet implemented");
	}

	@Test
	public void testAbstractDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTable() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTable() {
		fail("Not yet implemented");
	}*/

}

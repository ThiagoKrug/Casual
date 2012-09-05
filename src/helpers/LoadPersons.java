package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ScriptRunner;

import jdbc.ConnectionFactory;

import dao.PersonDAO;
import dao.RelationshipDAO;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class LoadPersons {

	// private List<Person> names;
	private Connection connection;

	public LoadPersons(Connection connection) {
		// names = new ArrayList<>();
		this.connection = connection;
	}

	public static void main(String[] args) {
		try {
			Connection connection = new ConnectionFactory().getConnection();
			LoadPersons load = new LoadPersons(connection);
			// load.recreateDatabase("casual");
			load.loadPersons("names");
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doRelationships(List<Person> persons, String url) {
		for (int i = 0; i < persons.size(); i++) {
			ArrayList<Relationship> relationships = new ArrayList<>();
			boolean insert = false;
			Person person1 = persons.get(i);
			int certo = i + 1;
			for (int j = certo; j < persons.size(); j++) {
				Person person2 = persons.get(j);
				String name1 = person1.getName();
				String name2 = person2.getName();

				if (name1.compareTo(name2) != 0) {

					RelationshipLink rlink = new RelationshipLink();
					rlink.setLink(url);
					int occurrenceNumber = person1.getPageOccurrencesNumber()
							* person2.getPageOccurrencesNumber();
					rlink.setOccurrenceNumber(occurrenceNumber);
					Double random = Math.random() * 100000;
					int num = random.intValue();
					rlink.setAverageDistance(num);

					Relationship relationship = new Relationship();
					relationship.addRelationshipLink(rlink);
					relationship.setPerson1(person1);
					relationship.setPerson2(person2);

					relationships.add(relationship);

					System.out.println("Relacionamento entre "
							+ person1.getName() + " e " + person2.getName());
					insert = true;
				} else {
					System.out.println("Nomes iguais: " + person1.getName()
							+ " e " + person2.getName());
					insert = false;
				}
			}
			if (insert) {
				person1.setRelationships(relationships);
			}
		}
	}

	public void loadPersons(String dir) {
		File[] files = getFilesName(dir);
		for (File file : files) {
			System.out.println("File name: " + file.getName());

			String url = "http://pt.wikipedia.org/wiki/" + file.getName();
			List<Person> persons = readNames(file);
			insert(persons);
			getIds(persons);
			// PersonDAO pdao = new PersonDAO(connection);
			// ArrayList<Person> persons2 = pdao.getAllPersons();
			doRelationships(persons, url);
			insertRelationships(persons);
		}

	}

	private void insertRelationships(List<Person> persons) {
		RelationshipDAO rdao = new RelationshipDAO(connection);
		for (Person person : persons) {
			List<Relationship> relationships = person.getRelationships();
			for (Relationship relationship : relationships) {
				System.out.println("Inserindo relacionamento entre id:"
						+ relationship.getPerson1().getId() + ", nome:"
						+ relationship.getPerson1().getName() + " e id:"
						+ relationship.getPerson2().getId() + ", nome:"
						+ relationship.getPerson2().getName());
				rdao.insert(relationship);
			}
		}
	}

	public void getIds(List<Person> persons) {
		PersonDAO pdao = new PersonDAO(connection);
		for (Person person : persons) {
			List<Person> p = pdao.getPerson(person.getName());
			person.setId(p.get(0).getId());
		}
	}

	public void insert(List<Person> persons) {
		PersonDAO pdao = new PersonDAO(connection);
		for (Person person : persons) {
			List<Person> p = pdao.getPerson(person.getName());
			if (p != null) {
				if (p.size() == 0) {
					pdao.insert(person);
					System.out.println("Inseriu a pessoa " + person.getName());
				}
			}
		}
	}

	public List<Person> readNames(File file) {
		List<Person> persons = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				Person exists = null;
				if (persons.size() > 0) {
					for (Person person : persons) {
						if (person.getName().equals(line)) {
							exists = person;
						}
					}
					if (exists != null) {
						exists.addPageOccurrencesNumber();
						System.out
								.println("Aumentado o número de ocorrências da pessoa "
										+ exists.getName());
					} else {
						Person p = new Person();
						p.setName(line);
						p.setPageOccurrencesNumber(1);
						persons.add(p);
						System.out.println("Pessoa " + line
								+ " lida do arquivo " + file.getName());
					}
				} else {
					Person p = new Person();
					p.setName(line);
					p.setPageOccurrencesNumber(1);
					persons.add(p);
					System.out.println("Pessoa " + line + " lida do arquivo "
							+ file.getName());
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return persons;
	}

	public File[] getFilesName(String dir) {
		File directory = new File(dir);
		if (directory.isDirectory()) {
			System.out.println("Pasta: " + directory.getAbsolutePath());
			return directory.listFiles();
		}
		return null;
	}

	public void recreateDatabase(String database) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost", "root", "");

			String sql = "DROP SCHEMA IF EXISTS `" + database + "`";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();

			sql = "CREATE SCHEMA `" + database
					+ "` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();

			connection.close();
			connection = new ConnectionFactory().getConnection();

			ScriptRunner sr = new ScriptRunner(connection, false, true);
			sr.runScript(new FileReader("casual.sql"));

			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

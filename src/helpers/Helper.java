package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionFactory;

import dao.PersonDAO;
import dao.RelationshipDAO;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public final class Helper {

	private static ArrayList<Person> createPersons() {
		ArrayList<Person> persons = new ArrayList<>();

		Person thiago = new Person();
		thiago.setName("Thiago Cassio Krug");
		persons.add(thiago);

		Person sergio = new Person();
		sergio.setName("S�rgio Mergen");
		persons.add(sergio);

		Person jp = new Person();
		jp.setName("Jo�o Pablo da Silva");
		persons.add(jp);

		Person bruno = new Person();
		bruno.setName("Bruno Vicelli");
		persons.add(bruno);

		Person mateus = new Person();
		mateus.setName("Mateus Dal Forno");
		persons.add(mateus);

		Person lucas = new Person();
		lucas.setName("Lucas Pereira Capanelli");
		persons.add(lucas);

		Person zend = new Person();
		zend.setName("Rafael Tavares Amorim");
		persons.add(zend);

		Person rafhael = new Person();
		rafhael.setName("Rafhael Cunha");
		persons.add(rafhael);

		Person lima = new Person();
		lima.setName("Thiago Lima");
		persons.add(lima);

		Person aline = new Person();
		aline.setName("Aline Vieira de Mello");
		persons.add(aline);

		Person wolmir = new Person();
		wolmir.setName("Wolmir Nemitz");
		persons.add(wolmir);

		Person juliano = new Person();
		juliano.setName("Juliano Rodovalho Macedo");
		persons.add(juliano);

		Person helison = new Person();
		helison.setName("Helison Reus Teixeira");
		persons.add(helison);

		return persons;
	}

	private static void insertPersons(Connection connection) {
		new PersonDAO(connection).insert(Helper.createPersons());
	}

	private static void createRelationships(Connection connection) {
		int n, num;
		Double random;
		RelationshipDAO rdao = new RelationshipDAO(connection);
		List<Person> persons = new PersonDAO(connection).getAllPersons();
		ArrayList<String> sites = Helper.readSites();

		for (Person person1 : persons) {
			for (Person person2 : persons) {
				Relationship r = new Relationship();

				r.setPerson1(person1);
				r.setPerson2(person2);

				random = Math.random() * 15;
				n = random.intValue();
				for (int i = 0; i < n; i++) {
					RelationshipLink link = new RelationshipLink();
					
					random = Math.random() * sites.size();
					num = random.intValue();
					link.setLink(sites.get(num));

					random = Math.random() * 100000;
					num = random.intValue();
					link.setDistance(num);
					
					random = Math.random() * 10;
					num = random.intValue();
					link.setOccurrenceNumber(num);
					
					r.addRelationshipLink(link);
				}

				rdao.insert(r);
			}
		}
	}

	private static ArrayList<String> readSites() {
		String linha = "";
		ArrayList<String> sites = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"sites.txt")));
			while ((linha = br.readLine()) != null) {
				sites.add(linha);
			}
			return sites;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Connection connection = new ConnectionFactory().getConnection();
		Helper.insertPersons(connection);
		Helper.createRelationships(connection);
		/*try {
			Casual casual = new Casual();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}

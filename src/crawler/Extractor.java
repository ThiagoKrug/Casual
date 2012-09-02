package crawler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import jdbc.ConnectionFactory;

import dao.PersonDAO;
import dao.RelationshipDAO;

import model.Person;
import model.Relationship;
import model.RelationshipLink;

public class Extractor {

	private HashMap<String, Collection<Person>> hashPerson;
	private Collection<Person> persons;

	public Extractor() {
		hashPerson = new HashMap<>();
		persons = Controller.getInstance().getPersons();
	}
	
	public void insert(List<Person> persons) {
		PersonDAO pdao = new PersonDAO(new ConnectionFactory().getConnection());
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

	public void extract(String page, String url) {
		ArrayList<Person> persons = this.extractPersons(page);
		System.out.println("Pessoas extraídas: " + persons.size() + " da url: "
				+ url);
		System.out.println(persons);

		for (int i = 0; i < persons.size(); i++) {
			Person p1 = persons.get(i);
			this.extractRelationships(page, url, p1, persons);
		}
		insert(persons);
		getIds(persons);
		insertRelationships(persons);
	}
	
	public void getIds(List<Person> persons) {
		PersonDAO pdao = new PersonDAO(new ConnectionFactory().getConnection());
		for (Person person : persons) {
			List<Person> p = pdao.getPerson(person.getName());
			person.setId(p.get(0).getId());
		}
	}
	
	private void insertRelationships(List<Person> persons) {
		RelationshipDAO rdao = new RelationshipDAO(new ConnectionFactory().getConnection());
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

	/**
	 * Extrai uma pessoa de uma página.
	 * 
	 * @param page
	 * @param name
	 * @return
	 */
	public Person extractPerson(String page, String name) {
		Pattern p = Pattern.compile(".*" + name + ".*");
		boolean isFound = p.matcher(page).find();
		if (isFound) {
			Person person = new Person();
			person.setName(name);
			System.out.println("Pessoa " + name + " extraída!");
			return person;
		}
		return null;
	}

	/**
	 * Extrai as pessoas das páginas através de uma lista pré-processada de
	 * nomes
	 * 
	 * @param page
	 * @return
	 */
	public ArrayList<Person> extractPersons(String page) {
		ArrayList<Person> ps = new ArrayList<>();
		for (Iterator<Person> itPerson = persons.iterator(); itPerson.hasNext();) {
			Person person = (Person) itPerson.next();

			Person p = this.extractPerson(page, person.getName());

			if (p != null) {
				ps.add(p);
			}
		}
		return ps;
	}

	/**
	 * Extrai todos os relacionamentos entre a pessoa 1 e a pessoa 2 nessa
	 * página
	 * 
	 * @param page
	 * @param url
	 * @param person1
	 * @param person2
	 * @return
	 */
	public RelationshipLink extractRelationshipLink(String page, String url,
			Person person1, Person person2) {

		int minDistance = Integer.MAX_VALUE;
		int maxDistance = Integer.MIN_VALUE;
		int sumAverageDistance = 0;
		int i = 0;
		
		RelationshipLink rlink = new RelationshipLink();

		boolean verifica1 = true;
		int index1 = -1;
		String name1 = person1.getName();

		while (verifica1) {

			boolean verifica2 = true;
			int index2 = -1;
			String name2 = person2.getName();

			index1 = page.indexOf(name1, index1 + 1);

			if (index1 == -1) {
				verifica1 = false;
				break;
			}

			while (verifica2) {

				index2 = page.indexOf(name2, index2 + 1);

				if (index2 == -1) {
					verifica2 = false;
					break;
				}

				int[] newIndexes = this.correctPosition(index1, index2, name1,
						name2);

				int distance = this.calculateDistance(newIndexes[0],
						newIndexes[1]);

				minDistance = this.calculateMinorDistance(distance, minDistance);
				maxDistance = this.calculateMajorDistance(distance, maxDistance);
				sumAverageDistance += distance;
				i++;

				rlink.addOccurrenceNumber();
			}
		}
		rlink.setLink(url);
		rlink.setMinDistance(minDistance);
		rlink.setMaxDistance(maxDistance);
		rlink.setAverageDistance(sumAverageDistance / i);

		System.out.println("Relacionamento entre " + person1.getName() + " e "
				+ person2.getName());

		return rlink;
	}

	/**
	 * Extrai os relacionamentos de todas as pessoas com a pessoa 1 nessa página
	 * 
	 * @param page
	 * @param url
	 * @param person1
	 * @param person2
	 */
	public void extractRelationships(String page, String url, Person person1,
			ArrayList<Person> persons) {

		ArrayList<Relationship> relationships = new ArrayList<>();

		for (Person person2 : persons) {
			if (!person1.getName().equalsIgnoreCase(person2.getName())) {
				Relationship r = new Relationship();
				r.setPerson1(person1);
				r.setPerson2(person2);

				RelationshipLink rlink = this.extractRelationshipLink(page,
						url, person1, person2);
				r.addRelationshipLink(rlink);

				relationships.add(r);
			}
		}

		person1.setRelationships(relationships);
		System.out.println(person1);
	}

	/**
	 * Corrige os indexes das pessoas em relação à outra. Se a pessoa 2 estiver
	 * antes a pessoa 1, a pessoa 2 precisa colocar o seu index após o final de
	 * seu nome, para não aumentar a distância entre elas. Retorna os novos
	 * valores em um vetor, onde sempre a posição 0 é o index da pessoa 1, e a
	 * posição 2 o index da pessoa 2.
	 * 
	 * @param index1
	 * @param index2
	 * @param name1
	 * @param name2
	 * @return
	 */
	private int[] correctPosition(int index1, int index2, String name1,
			String name2) {
		if (index1 > index2) {
			index2 = index2 + name2.length();
		} else if (index1 < index2) {
			index1 = index1 + name1.length();
		} else {
			System.out
					.println("Chora pq o algoritmo naum funfo :s. correctPosition method");
		}
		int[] a = { index1, index2 };
		return a;
	}

	/**
	 * Calcula a distância entre duas pessoas a partir de seus indexes.
	 * 
	 * @param index1
	 * @param index2
	 * @return
	 */
	private int calculateDistance(int index1, int index2) {
		int distance = 0;
		if (index1 > index2) {
			distance = index1 - index2;
		} else if (index1 < index2) {
			distance = index2 - index1;
		} else {
			System.out
					.println("Chora pq o algoritmo naum funfo :s. calculateDistance method");
		}
		return distance;
	}

	/**
	 * Calcula a menor distância entre as duas passadas por parâmetro
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	private int calculateMinorDistance(int num1, int num2) {
		if (num1 >= num2) {
			return num2;
		} else {
			return num1;
		}
	}
	
	/**
	 * Calcula a maior distância entre as duas passadas por parâmetro
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	private int calculateMajorDistance(int num1, int num2) {
		if (num1 >= num2) {
			return num1;
		} else {
			return num2;
		}
	}

}

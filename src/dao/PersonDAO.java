package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Model;
import model.Person;
import model.Relationship;

public class PersonDAO extends AbstractDAO {

	public PersonDAO(Connection connection) {
		super("person", connection);
	}

	@Override
	public void insert(Model model) {
		Person person = (Person) model;
		String sql = "insert into person (name) values (?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, person.getName());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(List<? extends Model> models) {
		for (Model model : models) {
			this.insert(model);
		}
	}

	@Override
	public void update(Model model) {
		Person person = (Person) model;
		String sql = "update person set name=? where id_person=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, person.getName());
			stmt.setInt(2, person.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Model model) {
		Person person = (Person) model;
		String sql = "delete from person where id_person=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, person.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Person getPerson(int id) {
		PreparedStatement stmt;
		try {
			stmt = this.connection
					.prepareStatement("select * from person where id_person = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();
			Person person = new Person();
			person.setId(rs.getInt("id_person"));
			person.setName(rs.getString("name"));

			rs.close();
			stmt.close();
			return person;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Person> getPerson(String name) {
		PreparedStatement stmt;
		List<Person> persons = new ArrayList<>();
		try {
			stmt = this.connection
					.prepareStatement("select * from person where name like ?");
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id_person"));
				person.setName(rs.getString("name"));

				persons.add(person);
			}

			rs.close();
			stmt.close();
			return persons;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Person> getAllPersons() {
		try {
			ArrayList<Person> persons = new ArrayList<>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from person");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("id_person"));
				person.setName(rs.getString("name"));
				// person.setRelationships(rdao.getRelationship(person));
				persons.add(person);
			}
			rs.close();
			stmt.close();

			return setRelationship(persons);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Person> setRelationship(List<Person> persons) {
		RelationshipDAO rdao = new RelationshipDAO(this.connection);
		for (Iterator<Person> iterator = persons.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			List<Relationship> relationships = rdao.getRelationship(person);
			for (Relationship relationship : relationships) {
				for (Iterator<Person> iterator2 = persons.iterator(); iterator2
						.hasNext();) {
					Person person2 = (Person) iterator2.next();
					if (relationship.getPerson2().getId() == person2.getId()) {
						relationship.setPerson2(person2);
						break;
					}
				}
			}
			person.setRelationships(relationships);
		}
		return persons;
	}

}

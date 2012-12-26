package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Person;
import model.PersonCategory;

public class PersonCategoryDAO {
	
	private Connection connection;
	@SuppressWarnings("unused")
	private String table;

	public PersonCategoryDAO(Connection connection) {
		this.table = "person_category";
		this.connection = connection;
	}

	public void insert(Person person, Category category) {
		this.insert(person.getId(), category.getId());
	}
	
	public void insert(int idPerson, int idCategory) {
		String sql = "insert into person_category (id_person, id_category) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setInt(1, idPerson);
			stmt.setInt(2, idCategory);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insert(Person person) {
		for (Category category : person.getCategories()) {
			this.insert(person, category);
		}
	}
	
	public void insert(Category category) {
		for (Person person : category.getPersons()) {
			this.insert(person, category);
		}
	}

	public void update(Person person, Category oldCategory, Category newCategory) {
		this.updateCategory(person.getId(), oldCategory.getId(), newCategory.getId());
	}
	
	public void updateCategory(int idPerson, int idOldCategory, int idNewCategory) {
		String sql = "update person_category set id_person=?, id_category=? where id_person=? and id_category=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idPerson);
			stmt.setInt(2, idNewCategory);
			stmt.setInt(3, idPerson);
			stmt.setInt(4, idOldCategory);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(Category category, Person oldPerson, Person newPerson) {
		this.updatePerson(category.getId(), oldPerson.getId(), newPerson.getId());
	}
	
	public void updatePerson(int idCategory, int idOldPerson, int idNewPerson) {
		String sql = "update person_category set id_person=?, id_category=? where id_person=? and id_category=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idNewPerson);
			stmt.setInt(2, idCategory);
			stmt.setInt(3, idOldPerson);
			stmt.setInt(4, idCategory);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Person person, Category category) {
		this.delete(person.getId(), category.getId());
	}
	
	public void delete(int idPerson, int idCategory) {
		String sql = "delete from person_category where id_person=? and id_category=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idPerson);
			stmt.setInt(2, idCategory);
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<PersonCategory> getAllPersonsCategories() {
		try {
			ArrayList<PersonCategory> personCategories = new ArrayList<>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from person_category order by 2");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PersonCategory personCategory = new PersonCategory();
				personCategory.setIdPerson(rs.getInt("id_person"));
				personCategory.setIdCategory(rs.getInt("id_category"));
				personCategories.add(personCategory);
			}
			rs.close();
			stmt.close();

			return personCategories;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

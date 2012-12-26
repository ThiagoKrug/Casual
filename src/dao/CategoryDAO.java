package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Model;
import model.Category;

public class CategoryDAO extends AbstractDAO {

	public CategoryDAO(Connection connection) {
		super("category", connection);
	}

	@Override
	public void insert(Model model) {
		Category category = (Category) model;
		String sql = "insert into category (name, link) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, category.getName());
			stmt.setString(2, category.getLink());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(List<? extends Model> models) {
		for (Model model : models) {
			Category category = (Category) model;
			this.insert(category);
		}
	}

	@Override
	public void update(Model model) {
		Category category = (Category) model;
		String sql = "update category set name=?, link=? where id_category=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, category.getName());
			stmt.setString(2, category.getLink());
			stmt.setInt(3, category.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Model model) {
		Category category = (Category) model;
		String sql = "delete from category where id_category=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, category.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Category getCategory(int id) {
		PreparedStatement stmt;
		try {
			stmt = this.connection
					.prepareStatement("select * from category where id_category = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();
			Category category = new Category();
			category.setId(rs.getInt("id_category"));
			category.setName(rs.getString("name"));
			category.setLink(rs.getString("link"));

			rs.close();
			stmt.close();
			return category;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertPersons(Category category) {
		new PersonCategoryDAO(connection).insert(category);
	}

	public List<Category> getCategory(String name) {
		PreparedStatement stmt;
		List<Category> categories = new ArrayList<>();
		try {
			stmt = this.connection.prepareStatement("select * from category where name like ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id_category"));
				category.setName(rs.getString("name"));
				category.setLink(rs.getString("link"));

				categories.add(category);
			}

			rs.close();
			stmt.close();
			return categories;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Category> getAllCategories() {
		try {
			ArrayList<Category> categories = new ArrayList<>();
			PreparedStatement stmt = this.connection
					.prepareStatement("select * from category");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setId(rs.getInt("id_category"));
				category.setName(rs.getString("name"));
				category.setLink(rs.getString("link"));
				categories.add(category);
			}
			rs.close();
			stmt.close();

			return categories;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

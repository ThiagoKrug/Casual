package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Model;
import model.Page;

public class PageDAO extends AbstractDAO {

	public PageDAO(Connection connection) {
		super("page", connection);
	}

	@Override
	public void insert(Model model) {
		Page page = (Page) model;
		String sql = "insert into page (url, size) values (?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, page.getUrl());
			stmt.setInt(2, page.getSize());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(List<? extends Model> models) {
		for (Model model : models) {
			Page page = (Page) model;
			this.insert(page);
		}
	}

	@Override
	public void update(Model model) {
		Page page = (Page) model;
		String sql = "update page set url=?, size=? where id_page=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, page.getUrl());
			stmt.setInt(2, page.getSize());
			stmt.setInt(3, page.getId());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Model model) {
		Page page = (Page) model;
		String sql = "delete from page where id_page=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, page.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Page getPage(int id) {
		PreparedStatement stmt;
		try {
			stmt = this.connection
					.prepareStatement("select * from page where id_page = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();
			Page page = new Page();
			page.setId(rs.getInt("id_page"));
			page.setUrl(rs.getString("url"));
			page.setSize(rs.getInt("size"));

			rs.close();
			stmt.close();
			return page;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

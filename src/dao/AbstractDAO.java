package dao;

import java.sql.Connection;
import java.util.List;

import model.Model;

public abstract class AbstractDAO {

	protected Connection connection;
	protected String table;
	
	protected AbstractDAO(String tableName, Connection connection) {
		this.table = tableName;
		this.connection = connection;
	}
	
	public String getTable() {
		return table;
	}
	
	public void setTable(String table) {
		this.table = table;
	}
	
	public abstract void insert(Model model);
	
	public abstract void insert(List<? extends Model> model);
	
	public abstract void update(Model model);
	
	public abstract void delete(Model model);
	
}

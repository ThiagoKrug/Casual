package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.dao.PersonDAOTest;
import test.dao.RelationshipDAOTest;
import test.dao.ScriptRunner;
import test.jdbc.ConnectionFactoryTest;
import test.model.PersonTest;
import test.model.RelationshipLinkTest;
import test.model.RelationshipTest;

@RunWith(Suite.class)
@SuiteClasses({ PersonDAOTest.class, RelationshipDAOTest.class,
		ConnectionFactoryTest.class, PersonTest.class, RelationshipTest.class,
		RelationshipLinkTest.class })
public class AllTests {

	@BeforeClass
	public static void setUp() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost", "root", "");

			String sql = "DROP SCHEMA IF EXISTS `casual_test`";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			
			sql = "CREATE SCHEMA `casual_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
			stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			
			connection.close();
			connection = new ConnectionFactory().getTestConnection();
			
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

	@AfterClass
	public static void tearDown() {

	}

}

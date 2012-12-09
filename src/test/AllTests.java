package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionFactory;

import model.Person;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.control.HashMapDataTest;
import test.model.PageTest;
import test.model.PersonTest;
import test.model.RelationshipLinkTest;
import test.model.RelationshipTest;
import test.model.rank.AverageDistanceTest;
import test.model.rank.popularity.PopularityTest;
import test.model.rank.popularity.PopularityTest1;
import test.model.rank.popularity.SamePopularityTest;
import test.setup.SetUp;
import util.ScriptRunner;

@RunWith(Suite.class)
@SuiteClasses({ PersonTest.class, PageTest.class, RelationshipTest.class,
		RelationshipLinkTest.class, AverageDistanceTest.class, RankESort.class,
		HashMapDataTest.class, PopularityTest.class, SamePopularityTest.class,
		PopularityTest1.class })
public class AllTests {

	public static List<Person> persons;

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
			sr.runScript(new FileReader("data/casual.sql"));

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
		SetUp.createPersons();
		persons = SetUp
				.loadPersons(new ConnectionFactory().getTestConnection());
	}

}

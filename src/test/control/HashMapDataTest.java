package test.control;

import static org.junit.Assert.*;

import java.sql.SQLException;

import jdbc.ConnectionFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import control.Carla;
import control.HashMapData;

public class HashMapDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashMapData() {
		try {
			HashMapData hmd = new HashMapData(new Carla(2, 0, 1), new ConnectionFactory().getTestConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDidYouMean() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchByStringJudge() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchByPersonJudge() {
		fail("Not yet implemented");
	}

}

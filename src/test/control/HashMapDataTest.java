package test.control;

import static org.junit.Assert.*;

import java.sql.SQLException;

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
	public void testHashMapDataCarla() {
		fail("Not yet implemented");
	}

	@Test
	public void testDidYouMean() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchByStringJudge() {
		try {
			HashMapData hmd = new HashMapData(new Carla(2, 0, 1));
			hmd.searchBy("Thiago Cassio Krug", calculator);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchByPersonJudge() {
		fail("Not yet implemented");
	}

}

package me.h2.cafe;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context.xml"})
public class CoffeeServiceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CoffeeRepository repo;
	
	@Autowired
	CoffeeService service;
	
	private static ArrayList<Coffee> menu = new ArrayList<Coffee>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Coffee("americano", 1000));
			add(new Coffee("cafe latte", 1500));
			add(new Coffee("cafe mocha", 1500));
		}
	};
	
	private static H2Database db;
	
	@BeforeClass
	public static void setup() throws SQLException {
		try {
			db = new H2Database();
			for(Coffee c : menu) db.save(c);
		} catch(SQLException e) {
			throw e;
		} finally {
			db.shutdown();
		}
		System.out.println("load data...");
	}
	
	@AfterClass
	public static void shutdown() {
		db.shutdown();
	}

	@Test
	public void testGetMenu() {
		logger.info("testGetMenu");
		List<Coffee> coffees = service.getMenu();
		assertEquals(3, coffees.size());
	}
	
	@Test
	public void testGetCheapMenu() {
		logger.info("testGetCheapMenu");
		List<Coffee> coffees = service.getCheapMenu(1200);
		assertEquals(1, coffees.size());
	}
}

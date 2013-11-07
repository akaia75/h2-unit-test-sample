package me.h2.cafe;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
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
			add(new Coffee("caffe latte", 1500));
			add(new Coffee("cafe mocha", 1500));
		}
	};
	
	@Before
	public void setup() {
		for(Coffee c : menu) repo.save(c);
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

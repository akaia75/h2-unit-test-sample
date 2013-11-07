package me.h2.cafe;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class H2Database {

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	private Server server;
	private AbstractApplicationContext ac;
	
	public H2Database() {
		
	}
	
	public void start() throws SQLException {
		logger.info("starting database...");
		
		File file = new File(data);
		if(file.exists()) file.delete();
		
		server = Server.createTcpServer("-tcp");
        server.start();
	}
	
	private static ArrayList<Coffee> menu = new ArrayList<Coffee>() {
		private static final long serialVersionUID = 1L;
		{
			add(new Coffee("americano", 1000));
			add(new Coffee("cafe latte", 1500));
			add(new Coffee("cafe mocha", 1500));
		}
	};
	
	public void init() {
		ac = new ClassPathXmlApplicationContext("/context.xml", Server.class);
		CoffeeRepository repo = ac.getBean(CoffeeRepository.class);
		for(Coffee c : menu) repo.save(c);
		ac.close();
	}
	
	private static final String data = System.getProperty("user.home") + "/sample.h2.db";
	private void clean() {
		System.out.println(data);
		File file = new File(data);
		if(file.exists()) file.delete();
	}
	
	public void shutdown() {
		logger.info("shuting down database...");
		clean();
		server.shutdown();
		
	}

	private CoffeeRepository repo;
	void save(Coffee coffee) {
		repo.save(coffee);
	}
}
package me.h2.cafe;

import java.io.File;
import java.sql.SQLException;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class H2Database {

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	private Server server;
	private AbstractApplicationContext ac;
	//FIXME
	private String data = "/users/travis/sample.h2.db";
	public H2Database() throws SQLException {
		
		logger.info("starting database...");
		
		File file = new File(data);
		if(file.exists()) file.delete();
		
		server = Server.createTcpServer("-tcp");
        server.start();
        
		ac = new ClassPathXmlApplicationContext("/context.xml", Server.class);
		this.repo = ac.getBean(CoffeeRepository.class);
	}
	
	public void shutdown() {
		logger.info("shuting down database...");
		server.shutdown();
		ac.close();
	}

	private CoffeeRepository repo;
	void save(Coffee coffee) {
		repo.save(coffee);
	}
}

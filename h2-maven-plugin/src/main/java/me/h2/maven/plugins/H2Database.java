package me.h2.maven.plugins;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.maven.plugin.AbstractMojo;
import org.h2.tools.Server;

abstract class H2Database extends AbstractMojo {

	static ExecutorService executor = Executors.newSingleThreadExecutor();
	
	protected void start() throws SQLException {
		clean();
		executor.execute(new Runner());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
	    }
	}
	
	private static Server server;
	class Runner implements Runnable {
		@Override
	    public void run() {
			try {
    			server = Server.createTcpServer("-tcp");
    	        server.start();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final String data = System.getProperty("user.home") + "/sample.h2.db";
	private void clean() {
		File file = new File(data);
		if(file.exists()) file.delete();
	}
	
	protected void shutdown() {
		clean();
		executor.shutdown();
	    executor.shutdownNow();
	}

	protected void init(String file) throws Exception {
		
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/sample"); 
		Statement st = conn.createStatement();
		
		URL url = getClass().getClassLoader().getResource(file);
		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String sql = null;
		while((sql = br.readLine()) != null) st.executeUpdate(sql);
	}
}

package me.h2.maven.plugins;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public class DBWrapper extends H2Database {
	
	public void start(String init) {
		System.out.println("starting database...");
		try {
			start();
			init(init);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop() {
		System.out.println("stopping database...");
		shutdown();
	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		
	}
}

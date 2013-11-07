package me.h2.maven.plugins;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * 
 * @phase test
 *
 * @goal stop
 * 
 */
public class DBStopper extends H2Database {

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("stopping database...");
		shutdown();
	}
}

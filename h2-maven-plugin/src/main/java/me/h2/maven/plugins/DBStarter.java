package me.h2.maven.plugins;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
*
* @phase test-compile
* 
* @goal start
* 
*/
public class DBStarter extends H2Database {
	
	/**
	 * @parameter
	 */
	private String init;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		System.out.println("starting database...");
		try {
			start();
			init(init);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package br.com.core;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public class GitCore {
	
	public void GitConnect() throws Exception {
		
		try {
			
			//Basic authentication
			GitHubClient client = new GitHubClient();
			client.setCredentials("user", "passw0rd");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

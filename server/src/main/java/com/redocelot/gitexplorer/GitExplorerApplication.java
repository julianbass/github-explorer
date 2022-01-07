package com.redocelot.gitexplorer;

import java.io.IOException;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GitExplorerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(GitExplorerApplication.class, args);

		// Request request = new Request.Builder()
		// 		.url("https://api.github.com/repos/octocat/hello-world/commits")
		// 		.build();

		// OkHttpClient client = new OkHttpClient();

		// Call call = client.newCall(request);
		// Response response = call.execute();

		// Object file = JSONValue.parse(response.body().string());
		// JSONArray jsonArray = (JSONArray) file;
		// JSONObject obj1 = (JSONObject) jsonArray.get(0);
		// System.out.print(obj1.get("commit"));

		// ISourceRepo repo = new SourceRepoInMemory();
		// GetSources interactor = new GetSources(repo);
		// System.out.println(interactor.execute().get(0).getRepoName());
	}

}

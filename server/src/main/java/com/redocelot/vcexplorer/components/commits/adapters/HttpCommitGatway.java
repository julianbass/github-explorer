package com.redocelot.vcexplorer.components.commits.adapters;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.commits.entities.Author;
import com.redocelot.vcexplorer.components.commits.entities.Commit;
import com.redocelot.vcexplorer.components.commits.entities.File;
import com.redocelot.vcexplorer.frameworks.http.requesthandlers.HttpRequestHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class HttpCommitGatway implements CommitDSGateway {

    private HttpRequestHandler requestHandler;

    public HttpCommitGatway(HttpRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public ArrayList<Commit> getCommits(String owner, String repo) {
        ArrayList<Commit> commits = new ArrayList<>();
        try {
            Object response = this.requestHandler.get("https://api.github.com/repos/" + owner + "/" + repo + "/commits");
            JSONArray jsonArray = (JSONArray) response;

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject responseObj = (JSONObject) jsonArray.get(i);

                String sha = (String) responseObj.get("sha");
                JSONObject commitObj = (JSONObject) responseObj.get("commit");
                JSONObject authorObj = (JSONObject) commitObj.get("author");
                String name = (String) authorObj.get("name");
                String email = (String) authorObj.get("email");
                String date = (String) authorObj.get("date");
                String message = (String) commitObj.get("message");
                Commit commit = new Commit(sha, new Author(name, email), date, message);

                commits.add(commit);
            }

        } catch (Exception e) {
            // TODO: handle exception

        }

        return commits;
    }

    @Override
    public Commit getCommit(String owner, String repo, String sha) {
        
        try {
            Object response = this.requestHandler
                    .get("https://api.github.com/repos/" + owner + "/" + repo + "/commits/" + sha);

            JSONObject responseObj = (JSONObject) response;

            
            JSONObject commitObj = (JSONObject) responseObj.get("commit");
            JSONObject authorObj = (JSONObject) commitObj.get("author");
            String name = (String) authorObj.get("name");
            String email = (String) authorObj.get("email");
            String date = (String) authorObj.get("date");
            String message = (String) commitObj.get("message");

            ArrayList<File> files = new ArrayList<>();
            JSONArray fileArray = (JSONArray) responseObj.get("files");
            
            for (int i = 0; i < fileArray.size(); i++) {
                JSONObject fileObj = (JSONObject) fileArray.get(i);
                String filename = (String) fileObj.get("filename");
                Long changes = (Long) fileObj.get("changes");
                Long additions = (Long) fileObj.get("additions");
                Long deletions = (Long) fileObj.get("deletions");
                String status = (String) fileObj.get("status");

                File file = new File(filename, additions, deletions, changes, status);
                files.add(file);
               
            }
           
            Commit commit = new Commit(sha, new Author(name, email), date, message, files);
            
            return commit;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.print(e);

        }
        return null;

       
    }

}

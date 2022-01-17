package com.redocelot.gitexplorer.components.commits.repos;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.redocelot.gitexplorer.components.commits.domain.Author;
import com.redocelot.gitexplorer.components.commits.domain.Commit;
import com.redocelot.gitexplorer.components.commits.domain.File;
import com.redocelot.gitexplorer.core.http.RequestHandler;

public class HttpCommitRepo implements CommitRepo {

    @Override
    public ArrayList<Commit> getCommits(String owner, String repo) {
        ArrayList<Commit> commits = new ArrayList<>();
        try {
            Object response = RequestHandler.get("https://api.github.com/repos/" + owner + "/" + repo + "/commits");
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
            Object response = RequestHandler
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
                System.out.print(filename);
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

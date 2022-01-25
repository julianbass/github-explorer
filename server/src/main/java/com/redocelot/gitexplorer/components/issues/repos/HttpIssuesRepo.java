package com.redocelot.gitexplorer.components.issues.repos;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.issues.domain.Issues;
import com.redocelot.gitexplorer.core.http.RequestHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HttpIssuesRepo implements IssuesRepo {

    @Override
    public ArrayList<Issues> getIssues(String owner, String name) {
        ArrayList<Issues> issues = new ArrayList<>();
        try {
            Object response = RequestHandler.get("https://api.github.com/repos/" + owner + "/" + name + "/issues");
            JSONArray jsonArray = (JSONArray) response;

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject responseObj = (JSONObject) jsonArray.get(i);

                String title = (String) responseObj.get("title");
                String body = (String) responseObj.get("body");
                String state = (String) responseObj.get("state");
                JSONObject userObj = (JSONObject) responseObj.get("user");
                String user = (String) userObj.get("login");
                Issues issue = new Issues(title, user, body, state);

                issues.add(issue);
            }
            
        } catch (Exception e) {
            //TODO: handle exception
        }
        return issues;
    }

    @Override
    public Issues getIssue(String owner, String name, String sha) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

package com.redocelot.vcexplorer.components.issues.adapters;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.issues.entities.Issues;
import com.redocelot.vcexplorer.frameworks.http.requesthandlers.HttpRequestHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HttpIssuesDSGateway implements IssuesDSGateway {

    private HttpRequestHandler requestHandler;

    public HttpIssuesDSGateway(HttpRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    @Override
    public ArrayList<Issues> getIssues(String owner, String name) {
        ArrayList<Issues> issues = new ArrayList<>();
        try {
            Object response = this.requestHandler.get("https://api.github.com/repos/" + owner + "/" + name + "/issues");
            JSONArray jsonArray = (JSONArray) response;

            for (int i = 0; i < jsonArray.size(); i++) {

                JSONObject responseObj = (JSONObject) jsonArray.get(i);

                String title = (String) responseObj.get("title");
                String body = (String) responseObj.get("body");
                String state = (String) responseObj.get("state");
                JSONObject userObj = (JSONObject) responseObj.get("user");
                String user = (String) userObj.get("login");
                String createdAt = (String) responseObj.get("created_at");
                Issues issue = new Issues(title, user, body, state, createdAt);

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

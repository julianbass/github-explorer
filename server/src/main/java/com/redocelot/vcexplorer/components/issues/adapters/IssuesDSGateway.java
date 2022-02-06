package com.redocelot.vcexplorer.components.issues.adapters;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.issues.entities.Issues;

public interface IssuesDSGateway {
    ArrayList<Issues> getIssues(String owner, String name);
    Issues getIssue(String owner, String name, String sha); 
}

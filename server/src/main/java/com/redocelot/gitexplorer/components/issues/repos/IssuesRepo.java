package com.redocelot.gitexplorer.components.issues.repos;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.issues.domain.Issues;

public interface IssuesRepo {
    ArrayList<Issues> getIssues(String owner, String name);
    Issues getIssue(String owner, String name, String sha); 
}

package com.redocelot.gitexplorer.components.issues.usecases;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.issues.domain.Issues;
import com.redocelot.gitexplorer.components.issues.repos.IssuesRepo;

public class GetIssuesInteractor {

    private IssuesRepo repo;

    public GetIssuesInteractor(IssuesRepo repo) {
        this.repo = repo;
    }

   public ArrayList<Issues> execute(String owner, String name) {
        return this.repo.getIssues(owner, name);

    }
    
}

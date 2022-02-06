package com.redocelot.vcexplorer.components.issues.usecases;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.issues.adapters.IssuesDSGateway;
import com.redocelot.vcexplorer.components.issues.entities.Issues;

public class GetIssuesInteractor {

    private IssuesDSGateway repo;

    public GetIssuesInteractor(IssuesDSGateway repo) {
        this.repo = repo;
    }

   public ArrayList<Issues> execute(String owner, String name) {
        return this.repo.getIssues(owner, name);

    }
    
}

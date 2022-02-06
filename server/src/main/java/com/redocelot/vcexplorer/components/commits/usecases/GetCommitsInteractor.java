package com.redocelot.vcexplorer.components.commits.usecases;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.commits.entities.Commit;
import com.redocelot.vcexplorer.components.commits.adapters.CommitDSGateway;

public class GetCommitsInteractor {

    private CommitDSGateway repo;

    public GetCommitsInteractor(CommitDSGateway repo) {
        this.repo = repo;
    }


    public ArrayList<Commit> execute(String owner, String repo) {        
        return this.repo.getCommits(owner, repo);
    }
    
}

package com.redocelot.vcexplorer.components.commits.usecases;

import com.redocelot.vcexplorer.components.commits.adapters.CommitDSGateway;
import com.redocelot.vcexplorer.components.commits.entities.Commit;

public class GetCommitByShaInteractor {

    private CommitDSGateway repo;

    public GetCommitByShaInteractor(CommitDSGateway repo) {
        this.repo = repo;
    }


    public Commit execute(String owner, String repoName, String sha) {
        Commit commit = this.repo.getCommit(owner, repoName, sha);
        return commit;
    }
    
}

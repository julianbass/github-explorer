package com.redocelot.gitexplorer.components.commits.usecases;

import com.redocelot.gitexplorer.components.commits.domain.Commit;
import com.redocelot.gitexplorer.components.commits.repos.CommitRepo;

public class GetCommitByShaInteractor {

    private CommitRepo repo;

    public GetCommitByShaInteractor(CommitRepo repo) {
        this.repo = repo;
    }


    public Commit execute(String owner, String repoName, String sha) {
        Commit commit = this.repo.getCommit(owner, repoName, sha);
        return commit;
    }
    
}

package com.redocelot.gitexplorer.components.commits.usecases;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.commits.domain.Commit;
import com.redocelot.gitexplorer.components.commits.repos.CommitRepo;

public class GetCommitsInteractor {

    private CommitRepo repo;

    public GetCommitsInteractor(CommitRepo repo) {
        this.repo = repo;
    }


    public ArrayList<Commit> execute(String owner, String repo) {        
        return this.repo.getCommits(owner, repo);
    }
    
}

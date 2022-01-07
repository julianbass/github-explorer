package com.redocelot.gitexplorer.infra.http;


import java.util.ArrayList;

import com.redocelot.gitexplorer.components.commits.domain.Commit;
import com.redocelot.gitexplorer.components.commits.repos.CommitRepo;
import com.redocelot.gitexplorer.components.commits.repos.HttpCommitRepo;
import com.redocelot.gitexplorer.components.commits.usecases.GetCommitByShaInteractor;
import com.redocelot.gitexplorer.components.commits.usecases.GetCommitsInteractor;
import com.redocelot.gitexplorer.components.sources.domain.Source;
import com.redocelot.gitexplorer.components.sources.repos.ISourceRepo;
import com.redocelot.gitexplorer.components.sources.repos.SourceRepoInMemory;
import com.redocelot.gitexplorer.components.sources.usecases.GetSources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@RestController
public class GitExplorerController {
    

    @RequestMapping(value = "/api/repos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Source> getRepos() {
        ISourceRepo repo = new SourceRepoInMemory();
		GetSources interactor = new GetSources(repo);
		System.out.println(interactor.execute().get(0).getRepoName());
        return interactor.execute();
    }

    @RequestMapping(value = "/api/repos/{owner}/{repo}/commits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Commit> getCommits(@PathVariable(value="owner") String owner, @PathVariable(value="repo") String repo) {
        CommitRepo commitRepo = new HttpCommitRepo();
        GetCommitsInteractor interactor = new GetCommitsInteractor(commitRepo);
        return interactor.execute(owner, repo);
        
    }

    @RequestMapping(value = "/api/repos/{owner}/{repo}/commits/{sha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Commit getCommit(@PathVariable(value="owner") String owner, @PathVariable(value="repo") String repo, @PathVariable(value="sha") String sha) {
        CommitRepo commitRepo = new HttpCommitRepo();
        GetCommitByShaInteractor interactor = new GetCommitByShaInteractor(commitRepo);
        Commit commit = interactor.execute(owner, repo, sha);
       
        return commit;
        
    }

}

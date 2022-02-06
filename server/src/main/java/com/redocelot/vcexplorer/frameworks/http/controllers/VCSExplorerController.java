package com.redocelot.vcexplorer.frameworks.http.controllers;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.commits.adapters.CommitDSGateway;
import com.redocelot.vcexplorer.components.commits.adapters.HttpCommitGatway;
import com.redocelot.vcexplorer.components.commits.entities.Commit;
import com.redocelot.vcexplorer.components.commits.usecases.GetCommitByShaInteractor;
import com.redocelot.vcexplorer.components.commits.usecases.GetCommitsInteractor;
import com.redocelot.vcexplorer.components.issues.adapters.HttpIssuesDSGateway;
import com.redocelot.vcexplorer.components.issues.adapters.IssuesDSGateway;
import com.redocelot.vcexplorer.components.issues.entities.Issues;
import com.redocelot.vcexplorer.components.issues.usecases.GetIssuesInteractor;
import com.redocelot.vcexplorer.components.metrics.adapters.HttpMetricsDSGateway;
import com.redocelot.vcexplorer.components.metrics.adapters.MetricsDSGateway;
import com.redocelot.vcexplorer.components.metrics.entities.RepoMetrics;
import com.redocelot.vcexplorer.components.metrics.usecases.GetRepoMetricsInteractor;
import com.redocelot.vcexplorer.components.sourcerepositories.adapters.SourceRepositoryDSGateway;
import com.redocelot.vcexplorer.components.sourcerepositories.adapters.SourceRepositoryInMemoryDSGateway;
import com.redocelot.vcexplorer.components.sourcerepositories.entities.SourceRepository;
import com.redocelot.vcexplorer.components.sourcerepositories.usecases.GetSourceRepositories;
import com.redocelot.vcexplorer.frameworks.http.requesthandlers.HttpRequestHandler;
import com.redocelot.vcexplorer.frameworks.http.requesthandlers.OkHttp3RequestHandler;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

@RestController
public class VCSExplorerController {

    private ArrayList<SourceRepository> store;
    private HttpRequestHandler requestHandler;

    public VCSExplorerController() {
        this.store = new ArrayList<SourceRepository>();
        this.store.add(new SourceRepository("octocat", "Hello-World"));
        this.store.add(new SourceRepository("ReactiveX", "RxJava"));
        this.requestHandler = new OkHttp3RequestHandler();
    }

    @RequestMapping(value = "/api/repos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<SourceRepository> getRepos() {
        SourceRepositoryDSGateway repo = new SourceRepositoryInMemoryDSGateway(this.store);
        GetSourceRepositories interactor = new GetSourceRepositories(repo);
        return interactor.execute();
    }

    @RequestMapping(value = "/api/repos/{owner}/{repo}/commits", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Commit> getCommits(@PathVariable(value = "owner") String owner,
            @PathVariable(value = "repo") String repo) {
        CommitDSGateway commitRepo = new HttpCommitGatway(this.requestHandler);
        GetCommitsInteractor interactor = new GetCommitsInteractor(commitRepo);
        return interactor.execute(owner, repo);

    }

    @RequestMapping(value = "/api/repos/{owner}/{repo}/commits/{sha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Commit getCommit(@PathVariable(value = "owner") String owner, @PathVariable(value = "repo") String repo,
            @PathVariable(value = "sha") String sha) {
        CommitDSGateway commitRepo = new HttpCommitGatway(this.requestHandler);
        GetCommitByShaInteractor interactor = new GetCommitByShaInteractor(commitRepo);
        Commit commit = interactor.execute(owner, repo, sha);

        return commit;

    }

    @RequestMapping(value = "/api/repos/{owner}/{repo}/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public RepoMetrics getMetrics(@PathVariable(value = "owner") String owner,
            @PathVariable(value = "repo") String repo) {
        MetricsDSGateway metricsRepo = new HttpMetricsDSGateway(this.requestHandler);
        GetRepoMetricsInteractor interactor = new GetRepoMetricsInteractor(metricsRepo);
        RepoMetrics repoMetrics = interactor.execute(owner, repo);

     
        return repoMetrics;

    }

    @RequestMapping(value = "/api/repos/{owner}/{repo}/issues", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Issues> getIssues(@PathVariable(value = "owner") String owner,
            @PathVariable(value = "repo") String repo) {
        IssuesDSGateway commitRepo = new HttpIssuesDSGateway(this.requestHandler);
        GetIssuesInteractor interactor = new GetIssuesInteractor(commitRepo);
        return interactor.execute(owner, repo);

    }

    @PostMapping(path = "/api/repos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody SourceRepository source) {
        
        SourceRepositoryDSGateway repo = new SourceRepositoryInMemoryDSGateway(this.store);
        repo.addSource(source);
        
        return;
    }

}

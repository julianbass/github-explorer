package com.redocelot.gitexplorer.components.metrics.usecases;

import com.redocelot.gitexplorer.components.metrics.domain.RepoMetrics;
import com.redocelot.gitexplorer.components.metrics.repo.MetricsRepo;

public class GetRepoMetricsInteractor {
    private MetricsRepo repo;

    public GetRepoMetricsInteractor(MetricsRepo repo) {
        this.repo = repo;
    }

    public RepoMetrics execute(String owner, String repoName) {
        return this.repo.getMetrics(owner, repoName);
    }

}

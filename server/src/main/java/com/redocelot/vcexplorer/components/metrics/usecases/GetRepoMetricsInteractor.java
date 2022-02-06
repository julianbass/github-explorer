package com.redocelot.vcexplorer.components.metrics.usecases;

import com.redocelot.vcexplorer.components.metrics.adapters.MetricsDSGateway;
import com.redocelot.vcexplorer.components.metrics.entities.RepoMetrics;

public class GetRepoMetricsInteractor {
    private MetricsDSGateway repo;

    public GetRepoMetricsInteractor(MetricsDSGateway repo) {
        this.repo = repo;
    }

    public RepoMetrics execute(String owner, String repoName) {
        return this.repo.getMetrics(owner, repoName);
    }

}

package com.redocelot.gitexplorer.components.metrics.repo;

import com.redocelot.gitexplorer.components.metrics.domain.RepoMetrics;

public interface MetricsRepo {
    RepoMetrics getMetrics(String owner, String repoName); 
}

package com.redocelot.vcexplorer.components.metrics.adapters;

import com.redocelot.vcexplorer.components.metrics.entities.RepoMetrics;

public interface MetricsDSGateway {
    RepoMetrics getMetrics(String owner, String repoName); 
}

package com.redocelot.gitexplorer.components.metrics.domain;

public class RepoMetrics {
    private Long healthPercentage;
    
    public RepoMetrics(Long healthPercentage) {
        this.healthPercentage = healthPercentage;
    }

    public Long getHealthPercentage() {
        return healthPercentage;
    }
    
}

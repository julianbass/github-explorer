package com.redocelot.vcexplorer.components.metrics.entities;

public class RepoMetrics {
    private Long healthPercentage;
    
    public RepoMetrics(Long healthPercentage) {
        this.healthPercentage = healthPercentage;
    }

    public Long getHealthPercentage() {
        return healthPercentage;
    }
    
}

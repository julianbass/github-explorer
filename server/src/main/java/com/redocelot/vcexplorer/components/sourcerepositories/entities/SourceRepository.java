package com.redocelot.vcexplorer.components.sourcerepositories.entities;

import com.redocelot.vcexplorer.core.Entity;

public class SourceRepository extends Entity {
    
    private String owner;
    private String repoName;

    public SourceRepository(String owner, String repoName) {
        super();
        this.owner = owner;
        this.repoName = repoName;
    }

    public String getOwner() {
        return owner;
    }

    public String getRepoName() {
        return repoName;
    }

}

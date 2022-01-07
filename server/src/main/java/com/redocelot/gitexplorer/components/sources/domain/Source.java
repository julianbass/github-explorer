package com.redocelot.gitexplorer.components.sources.domain;

import com.redocelot.gitexplorer.core.Entity;

public class Source extends Entity {
    
    private String owner;
    private String repoName;

    public Source(String owner, String repoName) {
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

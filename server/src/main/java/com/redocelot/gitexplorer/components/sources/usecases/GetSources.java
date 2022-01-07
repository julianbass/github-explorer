package com.redocelot.gitexplorer.components.sources.usecases;

import com.redocelot.gitexplorer.components.sources.repos.ISourceRepo;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.sources.domain.Source;

public class GetSources {
    
    private ISourceRepo repo;

    public GetSources(ISourceRepo repo) {
        this.repo = repo;
    }

    public ArrayList<Source> execute() {
        return this.repo.getSources();
    }
}

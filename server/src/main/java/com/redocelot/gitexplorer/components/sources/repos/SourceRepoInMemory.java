package com.redocelot.gitexplorer.components.sources.repos;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.sources.domain.Source;

public class SourceRepoInMemory implements ISourceRepo {

    private ArrayList<Source> store;

    public SourceRepoInMemory() {
        this.store = new ArrayList<Source>();
    }

    @Override
    public ArrayList<Source> getSources() {        
        return this.store;
    }

    @Override
    public void addSource(Source source) {
        this.store.add(source);
    }

    
    
}

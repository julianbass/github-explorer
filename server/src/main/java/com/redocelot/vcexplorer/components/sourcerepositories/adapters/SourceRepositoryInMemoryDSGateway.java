package com.redocelot.vcexplorer.components.sourcerepositories.adapters;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.sourcerepositories.entities.SourceRepository;

public class SourceRepositoryInMemoryDSGateway implements SourceRepositoryDSGateway {

    private ArrayList<SourceRepository> store;

    public SourceRepositoryInMemoryDSGateway(ArrayList<SourceRepository> store) {
        this.store = store; 
        
    }

    @Override
    public ArrayList<SourceRepository> getSources() {        
        return this.store;
    }

    @Override
    public void addSource(SourceRepository source) {
        this.store.add(source);
    }

    
    
}

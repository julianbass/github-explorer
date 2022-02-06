package com.redocelot.vcexplorer.components.sourcerepositories.adapters;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.sourcerepositories.entities.SourceRepository;

public interface SourceRepositoryDSGateway {
    public ArrayList<SourceRepository> getSources();
    public void addSource(SourceRepository source);
}

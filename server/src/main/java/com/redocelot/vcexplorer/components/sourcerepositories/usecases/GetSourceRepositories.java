package com.redocelot.vcexplorer.components.sourcerepositories.usecases;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.sourcerepositories.adapters.SourceRepositoryDSGateway;
import com.redocelot.vcexplorer.components.sourcerepositories.entities.SourceRepository;

public class GetSourceRepositories {
    
    private SourceRepositoryDSGateway repo;

    public GetSourceRepositories(SourceRepositoryDSGateway repo) {
        this.repo = repo;
    }

    public ArrayList<SourceRepository> execute() {
        return this.repo.getSources();
    }
}

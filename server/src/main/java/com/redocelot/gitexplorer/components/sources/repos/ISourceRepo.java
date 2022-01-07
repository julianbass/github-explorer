package com.redocelot.gitexplorer.components.sources.repos;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.sources.domain.Source;

public interface ISourceRepo {
    public ArrayList<Source> getSources();
    public void addSource(Source source);
}

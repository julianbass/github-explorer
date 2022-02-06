package com.redocelot.vcexplorer.components.commits.adapters;

import java.util.ArrayList;

import com.redocelot.vcexplorer.components.commits.entities.Commit;

public interface CommitDSGateway {
   ArrayList<Commit> getCommits(String owner, String repo);
   Commit getCommit(String owner, String repo, String sha);
}

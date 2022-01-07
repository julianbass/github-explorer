package com.redocelot.gitexplorer.components.commits.repos;

import java.util.ArrayList;

import com.redocelot.gitexplorer.components.commits.domain.Commit;

public interface CommitRepo {
   ArrayList<Commit> getCommits(String owner, String repo);
   Commit getCommit(String owner, String repo, String sha);
}

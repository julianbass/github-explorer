package com.redocelot.vcexplorer.components.commits.entities;

public class File {

    private String filename;
    private Long additions;
    private Long deletions;
    private Long changes;
    private String status; 

    public File(String filename, Long additions, Long deletions, Long changes,  String status) {
        this.filename = filename;
        this.additions = additions;
        this.deletions = deletions;
        this.changes = changes;
        this.status = status;
    }

    public String getFilename() {
        return filename;
    }

    public Long getAdditions() {
        return additions;
    }

    public Long getChanges() {
        return changes;
    }

    public Long getDeletions() {
        return deletions;
    }

    public String getStatus() {
        return status;
    }
    
}

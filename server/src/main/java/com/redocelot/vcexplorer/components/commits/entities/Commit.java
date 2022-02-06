package com.redocelot.vcexplorer.components.commits.entities;

import java.util.ArrayList;

import com.redocelot.vcexplorer.core.Entity;

public class Commit extends Entity {

    private String sha;
    private Author author;
    private String createdOn;
    private String message;
    private ArrayList<File> files;

    public Commit(String sha, Author author, String createdOn, String message, ArrayList<File> files) {
        this(sha, author, createdOn, message);
        this.files  = files;
    }
    
    public Commit(String sha, Author author, String createdOn, String message) {
        super();
        this.sha = sha;
        this.author = author;
        this.createdOn = createdOn;
        this.message = message;
    }

  

    public Author getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getSha() {
        return sha;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

}

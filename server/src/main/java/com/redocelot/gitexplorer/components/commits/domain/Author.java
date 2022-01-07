package com.redocelot.gitexplorer.components.commits.domain;

import com.redocelot.gitexplorer.core.Entity;

public class Author extends Entity {
    
    private String name;
    private String email;   


    public Author(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}

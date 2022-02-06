package com.redocelot.vcexplorer.components.commits.entities;

import com.redocelot.vcexplorer.core.Entity;

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

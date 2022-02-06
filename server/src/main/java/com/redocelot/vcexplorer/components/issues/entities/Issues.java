package com.redocelot.vcexplorer.components.issues.entities;

import com.redocelot.vcexplorer.core.Entity;

public class Issues extends Entity {
    
    private String title;
    private String user;
    private String body;
    private String state;
    private String createdAt;
    
    public Issues(String title, String user, String body, String state, String createdAt) {
        super();
        this.title = title;
        this.user = user;
        this.body = body;
        this.state = state;
        this.createdAt = createdAt;
    }


    public String getTitle() {
        return this.title;
    }

    public String getUser() {
        return this.user;
    }

    public String getBody() {
        return this.body;
    }

    public String getState() {
        return this.state;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

}

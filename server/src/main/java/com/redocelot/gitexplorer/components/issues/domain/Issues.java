package com.redocelot.gitexplorer.components.issues.domain;

import com.redocelot.gitexplorer.core.Entity;

public class Issues extends Entity {
    
    private String title;
    private String user;
    private String body;
    private String state;
    
    public Issues(String title, String user, String body, String state) {
        super();
        this.title = title;
        this.user = user;
        this.body = body;
        this.state = state;
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
}

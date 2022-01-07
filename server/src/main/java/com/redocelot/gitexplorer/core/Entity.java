package com.redocelot.gitexplorer.core;

import java.util.UUID;

public class Entity {
    
    private UUID id;
    
    
    public Entity() {
        this.id = UUID.randomUUID();;
    }

    public Entity(String id) {
        this.id = UUID.fromString(id);
    }

    public UUID getId() {
        return this.id;
    }
}

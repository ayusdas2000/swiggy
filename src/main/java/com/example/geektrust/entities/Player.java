package com.example.geektrust.entities;

public class Player extends BaseEntity {
    private String name;

    public Player(String id, String name) {
        this(name);
        this.id = id;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

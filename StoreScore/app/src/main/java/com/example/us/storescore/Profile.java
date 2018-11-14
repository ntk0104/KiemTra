package com.example.us.storescore;

public class Profile {
    private int id;
    private String username;
    private int score;

    public Profile(int id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}

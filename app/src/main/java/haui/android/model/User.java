package haui.android.model;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String username;
    private int score;

    public User(Integer id, String username, Integer score) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return id + "-" + username + "-" + score;
    }
}

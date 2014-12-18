package com.lavalloisir.beans.business;

public class Rating {
	
    private int score;
    private User user;
    private Leisure leisure;
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Leisure getLeisure() {
        return leisure;
    }

    public void setLeisure(Leisure leisure) {
        this.leisure = leisure;
    }
    
    public Rating() {
    }
}

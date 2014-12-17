package com.lavalloisir.beans.business;

public class Rating {
	
    private int rating;
    private User user;
    private Leisure leisure;
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
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

package com.lavalloisir.beans.business;

import java.sql.Timestamp;
import java.util.Date;

public class Rating {
	
	private int id;
    private int rating;
    private String summaryComment;
    private String comment;
    private Timestamp dateRating;
    private User user;
    private Leisure leisure;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSummaryComment() {
        return summaryComment;
    }

    public void setSummaryComment(String summaryComment) {
        this.summaryComment = summaryComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateRating() {
        return dateRating;
    }

    public void setDateRating(Timestamp dateRating) {
        this.dateRating = dateRating;
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

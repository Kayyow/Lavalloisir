package com.lavalloisir.beans.business;

import java.util.Date;

public class Rating {
	
    private int rating;
    private String summaryComment;
    private String comment;
    private Date dateRating;
    private User user;
    private Leisure idLeisure;

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

    public void setDateRating(Date dateRating) {
        this.dateRating = dateRating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Leisure getIdLeisure() {
        return idLeisure;
    }

    public void setIdLeisure(Leisure idLeisure) {
        this.idLeisure = idLeisure;
    }
    
    public Rating() {
    }

	

}

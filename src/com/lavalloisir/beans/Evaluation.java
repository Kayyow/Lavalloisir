package com.lavalloisir.beans;

public class Evaluation {
	
    private int note;
    private String opinion;
    private User user;
    private Leisure leisure;
       
    public Evaluation(int note, String opinion, User user, Leisure leisure) {
		super();
		this.note = note;
		this.opinion = opinion;
		this.user = user;
		this.leisure = leisure;
	}

	public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
    public String getOpinion() {
    	return opinion;
    }
    
    public void setOpinion(String opinion) {
    	this.opinion = opinion;
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
}

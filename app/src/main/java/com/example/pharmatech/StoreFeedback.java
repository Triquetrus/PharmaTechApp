package com.example.pharmatech;

public class StoreFeedback {

    String feedback;
    String suggestion;
    String RATING;

    public StoreFeedback() {
    }

    public StoreFeedback(String feedback, String suggestion, String RATING) {
        this.feedback = feedback;
        this.suggestion = suggestion;
        this.RATING = RATING;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getRATING() {
        return RATING;
    }

    public void setRATING(String RATING) {
        this.RATING = RATING;
    }
}

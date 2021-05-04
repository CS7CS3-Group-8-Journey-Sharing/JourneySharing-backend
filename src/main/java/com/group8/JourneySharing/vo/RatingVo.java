package com.group8.JourneySharing.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RatingVo {

    @NotEmpty(message = "userEmail is a required parameter")
    private String userEmail;

    @NotNull(message = "rating is required parameter")
    private Integer rating;

    public RatingVo(String userEmail,Integer rating) {
        this.userEmail = userEmail;
        this.rating = rating;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingVo{" +
                "userEmail='" + userEmail + '\'' +
                ", rating=" + rating +
                '}';
    }
}

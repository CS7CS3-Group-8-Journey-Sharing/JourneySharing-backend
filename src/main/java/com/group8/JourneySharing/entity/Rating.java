package com.group8.JourneySharing.entity;

public class Rating {

    private double totalRating;
    private int numOfRating;

    public Rating()
    {
        this.totalRating = 0.0;
        this.numOfRating = 0;
    }

    public Rating(double totalRating, int numOfRating) {
        this.totalRating = totalRating;
        this.numOfRating = numOfRating;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    public int getNumOfRating() {
        return numOfRating;
    }

    public void setNumOfRating(int numOfRating) {
        this.numOfRating = numOfRating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "totalRating=" + totalRating +
                ", numOfRating=" + numOfRating +
                '}';
    }

    public void addRating (double rating)
    {
        this.totalRating+=rating;
        this.numOfRating++;
    }
}

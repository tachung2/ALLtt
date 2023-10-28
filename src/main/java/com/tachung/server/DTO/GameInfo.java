package com.tachung.server.DTO;

public class GameInfo {
    private String name;
    private String releaseDate;
    private Double aggregatedRating;

    // Getters and Setters
    public GameInfo() {}

    public GameInfo(String name, String releaseDate, Double aggregatedRating) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.aggregatedRating = aggregatedRating;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getAggregatedRating() {
        return aggregatedRating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAggregatedRating(Double aggregatedRating) {
        this.aggregatedRating = aggregatedRating;
    }

}

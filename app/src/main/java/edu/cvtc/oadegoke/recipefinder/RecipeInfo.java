package edu.cvtc.oadegoke.recipefinder;

import java.util.ArrayList;

public class RecipeInfo {

    // Variables to hold the Recipe's information
    private String image;
    private String title;
    private String nationality;
    private String instructions;
    private String sourceLink;
    private String youtubeLink;

    // Constructor class
    public RecipeInfo(String image, String title, String nationality,
                      String instructions, String sourceLink,
                      String youtubeLink) {
        this.image = image;
        this.title = title;
        this.nationality = nationality;
        this.instructions = instructions;
        this.sourceLink = sourceLink;
        this.youtubeLink = youtubeLink;
    }

    // Getters and Setters
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }



}

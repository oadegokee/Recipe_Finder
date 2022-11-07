package edu.cvtc.oadegoke.recipefinder;

public class MyRecipeInfo {

    // Variables to hold the Recipe's information
    private int mId;
    private String myRecipeTitle;
    private String myRecipeNationality;
    private String myRecipeInstructions;

    // Constructor class
    public MyRecipeInfo(int id, String title, String nationality, String instructions) {
        this.mId = id;
        this.myRecipeTitle = title;
        this.myRecipeNationality = nationality;
        this.myRecipeInstructions = instructions;
    }

    // Getters and Setters
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getMyRecipeTitle() {
        return myRecipeTitle;
    }

    public void setMyRecipeTitle(String myRecipeTitle) {
        this.myRecipeTitle = myRecipeTitle;
    }

    public String getMyRecipeNationality() {
        return myRecipeNationality;
    }

    public void setMyRecipeNationality(String myRecipeNationality) {
        this.myRecipeNationality = myRecipeNationality;
    }

    public String getMyRecipeInstructions() {
        return myRecipeInstructions;
    }

    public void setMyRecipeInstructions(String myRecipeInstructions) {
        this.myRecipeInstructions = myRecipeInstructions;
    }


}

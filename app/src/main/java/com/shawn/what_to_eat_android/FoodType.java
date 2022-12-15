package com.shawn.what_to_eat_android;

// Class for a type of food
public class FoodType {
    // Name of the food type
    private String name;
    // Resource ID of the image for the food type
    private int imageResId;
    private String collectionName;

    // Constructor for the FoodType class
    public FoodType(String name, int imageResId, String collectionName) {
        this.name = name;
        this.imageResId = imageResId;
        this.collectionName = collectionName;
    }

    // Getter for the name of the food type
    public String getName() {
        return name;
    }

    // Getter for the resource ID of the image for the food type
    public int getImageResId() {
        return imageResId;
    }

    public String getCollectionName() {
        return collectionName;
    }
}

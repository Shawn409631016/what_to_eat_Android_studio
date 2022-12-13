package com.shawn.what_to_eat_android;

public class Food {
    private String name;
    private String calories;
    private String carbohydrates;
    private String protein;
    private String img;
    private String uri;

    public Food() {
        // Default constructor required for calls to DataSnapshot.getValue(Food.class)
    }

    public Food(String name, String calories, String carbohydrates, String protein, String img, String uri) {
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.img = img;
        this.uri = uri;
    }


    public String getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public String getProtein() {
        return protein;
    }

    public String getImg() {
        return img;
    }

    public String getUri() {
        return uri;
    }

}


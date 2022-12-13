package com.shawn.what_to_eat_android;

public class Food {
    private String mName;
    private int mCalories;
    private int mCarbohydrates;
    private int mProtein;

    public Food() {
        // Default constructor required for calls to DataSnapshot.getValue(Food.class)
    }

    public Food(String name, int calories, int carbohydrates, int protein) {
        mName = name;
        mCalories = calories;
        mCarbohydrates = carbohydrates;
        mProtein = protein;
    }

    public String getName() {
        return mName;
    }

    public int getCalories() {
        return mCalories;
    }

    public int getCarbohydrates() {
        return mCarbohydrates;
    }

    public int getProtein() {
        return mProtein;
    }
}


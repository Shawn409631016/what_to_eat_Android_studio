package com.shawn.what_to_eat_android;

public class Food {
    private String name;
    private String calories;
    private String carbohydrates;
    private String protein;
    private String img;
    private String uri;
    private String 碳水化合物;
    private String 蛋白質;

    public Food() {
        // Default constructor required for calls to DataSnapshot.getValue(Food.class)
    }

    public Food(String name, String calories, String carbohydrates, String protein, String img, String uri, String 碳水化合物, String 蛋白質) {
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.img = img;
        this.uri = uri;
        this.碳水化合物 = 碳水化合物;
        this.蛋白質 = 蛋白質;
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

    public String get碳水化合物() { return 碳水化合物; }

    public String get蛋白質() { return 蛋白質; }
}


package model;

import java.time.LocalDate;

public class Payment {
    private String category;
    private double amount;
    private LocalDate date;


    private static double dailyUse = 0;
    private static double foodDrink = 0;
    private static double enter = 0;
    private static double trans = 0;
    private static double studies = 0;
    private static double housing = 0;
    private static double beautyCloth = 0;
    private static double other = 0;

 
    public Payment(String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.date = LocalDate.now();
        addToCategory();
    }

    // EFFECTS: return the category of this payment
    public String getCategory() {
        return this.category;
    }

    // EFFECTS: return the amount of this payment
    public double getAmount() {
        return this.amount;
    }

    // EFFECTS: return the date that this payment been created.
    public LocalDate getDate() {
        return this.date;
    }

    // MODIFIES: this 
    // EFFECTS: adds amount to correct category total
    private void addToCategory() {
        switch (category.toLowerCase()) {
            case "daily use": dailyUse += amount;
                break;
            case "food":
            case "drink":
                foodDrink += amount;
                break;
            case "entertainment": enter += amount;
                break;
            case "transportation": trans += amount;
                break;
            case "studies": studies += amount;
                break;
            case "housing": housing += amount;
                break;
            case "beauty":
            case "cloth":
                beautyCloth += amount;
                break;
            default: other += amount;
                break;
        }
    }

    // EFFECTS: remove the earning amount from specified category 
    public void removeHandle() {
        removeCate(category, amount);
    }

    // MODIFIES: this
    // EFFECTS: remove amount to correct category total
    private void removeCate(String category, double amount) {
        switch (category.toLowerCase()) {
            case "daily use": dailyUse -= amount;
                break;
            case "food":
            case "drink":
                foodDrink -= amount;
                break;
            case "entertainment": enter -= amount;
                break;
            case "transportation": trans -= amount;
                break;
            case "studies": studies -= amount;
                break;
            case "housing": housing -= amount;
                break;
            case "beauty":
            case "cloth":
                beautyCloth -= amount;
                break;
            default: other -= amount;
                break;
        }
    }

    public static double getDailyTotal() {
        return dailyUse;
    }

    public static double getFoodDrinkTotal() {
        return foodDrink;
    }

    public static double getEntertainmentTotal() {
        return enter;
    }

    public static double getTransportationTotal() {
        return trans;
    }

    public static double getStudiesTotal() {
        return studies;
    }

    public static double getHousingTotal() {
        return housing;
    }

    public static double getBeautyClothTotal() {
        return beautyCloth;
    }

    public static double getOtherTotal() {
        return other;
    }

    // MODIFIES: this class
    // EFFECTS: reset all the cate total back to 0
    public static void resetCategoryTotal() {
        dailyUse = 0;
        foodDrink = 0;
        enter = 0;
        trans = 0;
        studies = 0;
        housing = 0;
        beautyCloth = 0;
        other = 0;

    }


}



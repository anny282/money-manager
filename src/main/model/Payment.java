package model;

import java.time.LocalDate;

public class Payment {
private String category;
    private double amount;
    private LocalDate date;


    private static double dailyUse = 0;
    private static double food = 0;
    private static double enter = 0;
    private static double trans = 0;
    private static double studies = 0;
    private static double housing = 0;
    private static double beauty = 0;
    private static double other = 0;

 
    public Payment(String category, double amount){
        this.category = category;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    // EFFECTS: return the category of this payment
    public String getCategory(){
        return this.category;
    }

    // EFFECTS: return the amount of this payment
    public double getAmount(){
        return this.amount;
    }

    // EFFECTS: return the date that this payment been created.
    public LocalDate getDate(){
        return this.date;
    }

    // MODIFIES: this class
    // EFFECTS: adds amount to correct category total
    private void addToCategory() {

    }

    public static double getdailyTotal(){
        return 0.0;
    }

    public static double getFoodTotal(){
        return 0.0;
    }

    public static double getEntertainmentTotal(){
        return 0.0;
    }

    public static double getTransportationTotal(){
        return 0.0;
    }

    public static double getStudiesTotal(){
        return 0.0;
    }

    public static double getHousingTotal(){
        return 0.0;
    }

    public static double getBeautyClothTotal(){
        return 0.0;
    }

    public static double getOtherTotal(){
        return 0.0;
    }

    // MODIFIES: this class
    // EFFECTS: reset all the cate total back to 0
    public static void resetCategoryTotal() {

    }

}



package model;

import java.time.LocalDate;

public class Earning {
    private String category;
    private double amount;
    private LocalDate date;
    
    private static double job = 0;
    private static double lotto = 0;
    private static double financial = 0;
    private static double other = 0;

 
    public Earning(){

    }

    // EFFECTS: return the category of this earning
    public String getCategory(){
        return "";
    }

    // EFFECTS: return the amount of this earning
    public double getAmount(){
        return 0.0;
    }

    // EFFECTS: return the date that this payment been created.
    public LocalDate getDate(){
        return LocalDate.now();
    }

    // MODIFIES: this class
    // EFFECTS: adds amount to correct category total
    private void addToCategory() {

    }

    // MODIFIES: this class
    // EFFECTS: reset all the cate total back to 0
    public static void resetCategoryTotal() {

    }

}

package model;

import java.time.LocalDate;

public class Earning {
    private String category;
    private double amount;
    private LocalDate date;

 
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

}

package model;

import java.time.LocalDate;

import org.json.JSONObject;

import persistence.Writable;

public class Earning implements Writable{
    private String category;
    private double amount;
    private LocalDate date;
    
    private static double job = 0;
    private static double lotto = 0;
    private static double financial = 0;
    private static double other = 0;

 
    public Earning(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        addToCategory();
    }

    // EFFECTS: return the category of this earning
    public String getCategory() {
        return this.category;
    }

    // EFFECTS: return the amount of this earning
    public double getAmount() {
        return this.amount;
    }

    // EFFECTS: return the date that this earning been created.
    public LocalDate getDate() {
        return this.date;
    }

    // MODIFIES: this class
    // EFFECTS: adds amount to correct category total
    private void addToCategory() {
        switch (category.toLowerCase()) {
            case "job":
                job += amount;
                break;
            case "lotto":
                lotto += amount;
                break;
            case "financial":
                financial += amount;
                break;
            default:
                other += amount;
                break;
        }
    }

    // EFFECTS: remove the earning amount from specified category 
    public void removeHandle() {
        removeCate(category, amount);
    }

    private void removeCate(String category, double amount) {
        switch (category.toLowerCase()) {
            case "job":
                job -= amount;
                break;
            case "lotto":
                lotto -= amount;
                break;
            case "financial":
                financial -= amount;
                break;
            default:
                other -= amount;
                break;
        }
    }

    public static double getJobTotal() {
        return job;
    }

    public static double getLottoTotal() {
        return lotto;
    }

    public static double getFinancialTotal() {
        return financial;
    }

    public static double getOtherTotal() {
        return other;
    }

    // MODIFIES: this class
    // EFFECTS: reset all the cate total back to 0
    public static void resetCategoryTotal() {
        job = 0;
        lotto = 0;
        financial = 0;
        other = 0;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("amount", amount);
        json.put("date", date.toString());
        return json;
    }


}

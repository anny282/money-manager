package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

public class User implements Writable{
    private String name;
    private double budget;
    private double bankAmt;
    private boolean broke;


    private List<Payment> payments;
    private List<Earning> earnings;


    public User(String name, double bankAmt) {
        this.name = name;
        this.bankAmt = bankAmt;
        this.budget = 0.0;
        this.broke = false;
        this.payments = new ArrayList<>();
        this.earnings = new ArrayList<>();
    }

// MODIFIES: this
// EFFECTS: Change name to the input new name
    public void setNewName(String name) {
        this.name = name;
    }

// MODIFIES: this
// EFFECTS: set the budget value to the current input one
    public void setBudget(double budget) {
        this.budget = budget;
    }

// MODIFIES: this
// EFFECTS: set the money amount in the bank amount
    public void setBankAmt(double bankAmt) {
        this.bankAmt = bankAmt;
    }
    

    public String getName() {
        return this.name;
    }

    public double getBudget() {
        return this.budget;
    }

    public double getBankAmt() {
        return this.bankAmt;
    }

    public boolean getBroke() {
        return this.broke;
    }

    public List<Payment> getPayments() {
        return this.payments;

    }

    public List<Earning> getEarnings() {
        return this.earnings;
    }

// REQUIRES: amt > 0, category of payment must be one in the payments option
// MODIFIES: this 
// EFFECTS: add this payment to the payment list, return the budgetLeft
// Warning if out of buget
    public void addPayment(String cate, double amt, LocalDate date) {
        Payment p = new Payment(cate, amt, date);
        this.payments.add(p);
    }
    
// REQUIRES: amt > 0, category of payment must be one in the Earnings option
// MODIFIES: this 
// EFFECTS: add this earning to the earnings list
    public void addEarning(String cate, double amt, LocalDate date) {
        Earning e = new Earning(cate, amt, date);
        this.earnings.add(e);
    }

// REQUIRES: pos >= 0, <list.size()
// MODIFIES: this 
// EFFECTS: remove this payment to the payment list by select the index position
// of payment in the Payments List
    public void removePayment(int pos) {
        payments.get(pos).removeHandle();
        payments.remove(pos);
    }

// REQUIRES: pos >= 0, < List.size()   
// MODIFIES: this 
// EFFECTS: remove this earning to the earning list by select the index position
//of earning in the Earnings List
    public void removeEarning(int pos) {
        earnings.get(pos).removeHandle();
        earnings.remove(pos);
    }

// EFFECTS: returns the total amount of all payments made by the user
    public double getTotalPayments() {
        double sum = 0.0;
        for (Payment p : payments) {
            sum = sum + p.getAmount();
        }
        return sum;
    
    }

// EFFECTS: returns the total amount of all earnings received by the user
    public double getTotalEarnings() {
        double sum = 0.0;
        for (Earning e : earnings) {
            sum = sum + e.getAmount();
        }
        return sum;
    }

// MODIFIES: this
// EFFECTS: reset payment list and earning list, budget keep same as last
// reset the outOfBudget, warning bankamt<0, add result(amt of money) for record in bankAmt
    public void endCurrentRecord() {
        double result = 0.0;
        result = getTotalEarnings() - getTotalPayments();
        this.bankAmt += result;
        if (this.bankAmt < 0) {
            this.broke = true;
        }
        getBroke();
        payments.clear();
        earnings.clear();
        Earning.resetCategoryTotal();
        Payment.resetCategoryTotal();

    }

// REQUIRES: budget > 0
// EFFECTS: return budget amount left, budget=0,always0
    public double getBudgetLeft() {
        if (budget == 0) {
            return 0;
        } else {
            double budgetLeft = 0.0;
            budgetLeft = budget - getTotalPayments();
            return budgetLeft;
        }
    
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("bankAmt", bankAmt);
        json.put("budget", budget);
        json.put("earnings", earningsToJson());
        json.put("payments", paymentsToJson());
        return json;
    }

    // EFFECTS: returns earnings in this workroom as a JSON array
    private JSONArray earningsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Earning e : earnings) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns payments in this workroom as a JSON array
    private JSONArray paymentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Payment p : payments) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }


}

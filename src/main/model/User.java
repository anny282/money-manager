package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class User {
private String name;
private double budget;
private double bankAmt;
private boolean outOfBudget;
private boolean broke;


private List<Payment> payments;
private List<Earning> earnings;


public User(String name, double bankAmt){
    this.name = name;
    this.bankAmt = bankAmt;
    this.budget = 0.0;
    this.outOfBudget = false;
    this.broke = false;
    this.payments = new ArrayList<>();
    this.earnings = new ArrayList<>();
}

// MODIFIES: this
// EFFECTS: Change name to the input new name
public void setNewName(String name){
    this.name = name;
}

// MODIFIES: this
// EFFECTS: set the budget value to the current input one
public void setBudget(double budget){
    this.budget = budget;
}

// MODIFIES: this
// EFFECTS: set the money amount in the bank amount
public void setBankAmt(double bankAmt){
    this.bankAmt = bankAmt;
}

public String getName(){
    return this.name;
}

public double getBudget(){
    return this.budget;
}

public double getBankAmt(){
    return this.bankAmt;
}

public boolean getOutOfBudget(){
    return this.outOfBudget;
}

public boolean getBroke(){
    return this.broke;
}

public List<Payment> getPayments(){
    return this.payments;

}

public List<Earning> getEarnings(){
    return this.earnings;
}

// REQUIRES: amt > 0, category of payment must be one in the payments option
// MODIFIES: this 
// EFFECTS: add this payment to the payment list, return the budgetLeft
// Warning if out of buget
public void addPayment(String cate, double amt){
    Payment p = new Payment(cate, amt);
    this.payments.add(p);
    getBudgetLeft();
}
// REQUIRES: amt > 0, category of payment must be one in the Earnings option
// MODIFIES: this 
// EFFECTS: add this earning to the earnings list
public void addEarning(String cate, double amt){
    Earning e = new Earning(cate, amt);
    this.earnings.add(e);
    getTotalEarnings();
}

// REQUIRES: pos >= 0
// MODIFIES: this 
// EFFECTS: remove this payment to the payment list by select the index position
// of payment in the Payments List
public void removePayment(int pos){
    this.payments.remove(pos);
    getBudgetLeft();
}

// REQUIRES: pos >= 0
// MODIFIES: this 
// EFFECTS: remove this earning to the earning list by select the index position
//of earning in the Earnings List
public void removeEarning(int pos){
    this.earnings.remove(pos);
    getTotalEarnings();
}

// EFFECTS: returns the total amount of all payments made by the user
public double getTotalPayments(){
    double sum = 0.0;
    for (Payment p : payments){
        sum = sum + p.getAmount();
    }
    return sum;
    
}

// EFFECTS: returns the total amount of all earnings received by the user
public double getTotalEarnings(){
    double sum = 0.0;
    for (Earning e : earnings){
        sum = sum + e.getAmount();
    }
    return sum;
}

// MODIFIES: this
// EFFECTS: reset payment list and earning list, budget keep same as last
// reset the outOfBudget, warning bankamt<0, add result(amt of money) for record in bankAmt
public void endCurrentRecord(){
    double result = 0.0;
    result = getTotalEarnings() - getTotalPayments();
    this.bankAmt += result;
    if (this.bankAmt < 0){
        this.broke = true;
    }
    getBroke();
    payments.clear();
    earnings.clear();
    this.outOfBudget = false;

}

// REQUIRES: budget > 0
// EFFECTS: return budget amount left, budget=0,always0
public double getBudgetLeft(){
    if(budget == 0){
        return 0;
    }
    double budgetLeft = 0.0;
    budgetLeft = budget - getTotalPayments();
    if (budgetLeft < 0){
        outOfBudget = true;
    }
    getOutOfBudget();
    return budgetLeft;
}

}

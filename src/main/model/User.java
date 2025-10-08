package model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class User {
private String name;
private double budget;
private double budgetLeft;
private double bankAmt;


private List<Payment> payments;
private List<Earning> earnings;
private Report report;

public User(String name, double bankAmt){
    this.name = name;
    this.bankAmt = bankAmt;
    this.budget = 0.0;
    this.budgetLeft = 0.0;
    this.payments = new ArrayList();
    this.earnings = new ArrayList();
}

// MODIFIES: this
// EFFECTS: Change name to the input new name
public void setNewName(String name){

}

// MODIFIES: this
// EFFECTS: set the budget value to the current input one
public void setBudget(double budget){
    
}

// MODIFIES: this
// EFFECTS: set the money amount in the bank amount
public void setBankAmt(double bankAmt){
    
}

public String getName(){
    return "";
}

public LocalDate getCurrentDate(){
    return LocalDate.now();
}

public double getBudget(){
    return 0.0;
}

public double getBankAmt(){
    return 0.0;
}


public List<Payment> getPayments(){
    return null;

}

public List<Earning> getEarnings(){
    return null;
}

// REQUIRES: amt > 0, category of payment must be one in the payments option
// MODIFIES: this 
// EFFECTS: add this payment to the payment list, return the budgetLeft percentage
// Warning if out of buget
public void addPayment(String cate, double amt){

}
// REQUIRES: amt > 0, category of payment must be one in the Earnings option
// MODIFIES: this 
// EFFECTS: add this earning to the earnings list
public void addEarning(String cate, double amt){

}

// REQUIRES: amt > 0, category of payment must be one in the payments option
// Must be the date before current
// MODIFIES: this 
// EFFECTS: remove this payment to the payment list by confirm the date, cate and amt
// return the budgetLeft percentage
public void removePayment(String cate, double amt, LocalDate date){

}

// REQUIRES: amt > 0, category of payment must be one in the earnings option
// Must be the date before current
// MODIFIES: this 
// EFFECTS: remove this earning to the earning list by confirm the date, cate and amt
public void removeEarning(String cate, double amt, LocalDate date){

}

public double getTotalPayments(){
    return 0.0;
}

public double getTotalEarnings(){
    return 0.0;
}

// MODIFIES: this
// EFFECTS: reset payment list and earning list, budget keep same as last, warning bankamt<0
public void endCurrentMonth(){

}

// REQUIRES: budget > 0
// EFFECTS: return budget amount left, budget=0,always0
public double getBudgetLeft(){
    return 0.0;
}



}

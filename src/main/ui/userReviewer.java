package ui;

import model.Earning;
import model.Payment;
import model.User;

import java.util.Scanner;


// EFFECTS: A spending and earning result application that allows the user see
// and help build the manage skill of money
public class userReviewer {
    private User user;
    private Scanner answer;

    private boolean isProgramRunning;

    public userReviewer(){
        init();
        System.out.println("Welcome to the MoneyManage user Reviewer APP!");
        System.out.println("Enter your name: ");
        String name = answer.nextLine();
        System.out.println("Enter your set for your initial bank account amount: ");
        double bankAmt = answer.nextDouble();
        this.answer.nextLine();
        user = new User(name, bankAmt);
        while (this.isProgramRunning) {
            handleMenu();
        }

    }

    // MODIFIES: this
    // EFFECTS: initialies the application with the start value
    public void init(){
        this.answer = new Scanner(System.in);
        this.isProgramRunning = true;
    }

    // EFFECTS: displays and processes input for the main Menu
    public void handleMenu(){
        displayMainMenu();
        String input = this.answer.nextLine();
        processMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMainMenu(){
        System.out.println("Please select an option: \n");
        System.out.println("a: Set your account");
        System.out.println("b: Earning page");
        System.out.println("c: Spending page");
        System.out.println("d: End this report");
        System.out.println("e: Quit");
        printDivider();
    }

    // EFFECTS: processes the user's imput in the main menu
    public void processMenuCommands(String input){
        printDivider();
        switch(input.toLowerCase()){
            case "a":
                handleSetAccount();
                break;
            case "b":
                earningPage();
                break;
            case "c":
                spendingPage();
                break;
            case "d":
                endReport();
                break;
            case "e":
                quitApplication();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // EFFECTS: displays and processes input for the set account Menu
    public void handleSetAccount(){
        setAccountMenu();
        int input = 0;
        while(!(input == 5)){
            input = this.answer.nextInt();
            processSetAccount(input);
        }
    }

    // EFFECTS: displays a list of commands that can be used in the setAccount menu
    private void setAccountMenu(){
            System.out.println("Accont Setting: \n");
            System.out.println("1. Set name");
            System.out.println("2. Set bank amount");
            System.out.println("3. Set budget");
            System.out.println("4. View current account info");
            System.out.println("5. Back to main menu");
            printDivider();
        
    }

    // EFFECTS: processes the user's imput in the main menu
    public void processSetAccount(int input){
        printDivider();
        switch(input){
            case 1:
                setName();
                break;
            case 2:
                setBankAmt();
                break;
            case 3:
                setBudget();
                break;
            case 4:
                viewAccountInfo();
                break;
            case 5:
                System.out.println("Returning to the menu...");
                this.answer.nextLine();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: user
    // EFFECTS: update user's name
    private void setName(){
        System.out.println("Enter new name: ");
        this.answer.nextLine();
        String name = answer.nextLine();
        user.setNewName(name);
        System.out.println("Name updated to: " + name);
        printDivider();
        setAccountMenu();
    }

    // MODIFIES: user
    // EFFECTS: update user's bankAmt
    private void setBankAmt(){
        System.out.println("Enter new bank amount: ");
        double amt = answer.nextDouble();
        user.setBankAmt(amt);
        System.out.println("Bank amount updated to: $" + amt);
        printDivider();
        setAccountMenu();
    }

    // MODIFIES: user
    // EFFECTS: updates the user's budget for current record
    private void setBudget(){
        System.out.println("Enter new budget: ");
        double budget = answer.nextDouble();
        user.setBudget(budget);
        System.out.println("Budget updated to: $" + budget);
        printDivider();
        setAccountMenu();
    }

    // EFFECTS: displays the user's current account information
    private void viewAccountInfo() {
        System.out.println("Current Account Information:");
        System.out.println("Name: " + user.getName());
        System.out.println("Bank Amount: $" + user.getBankAmt());
        System.out.println("Budget: $" + user.getBudget());
        printDivider();
        setAccountMenu();
    }

    // EFFECTS: displays and processes input for the earning Menu
    public void earningPage(){
        earningMenu();
        int input = 0;
        while(!(input == 4)){
            input = this.answer.nextInt();
            processEarning(input);
        }
    }

    // EFFECTS: displays a list of commands that can be used in the earning menu
    private void earningMenu(){
        System.out.println("Earning option: \n");
        System.out.println("1. Add Earning");
        System.out.println("2. Remove Earning");
        System.out.println("3. See the Earning Summary");
        System.out.println("4. Back to main menu");
        printDivider();
    }
    
    // EFFECTS: processes the user's imput in the earning menu
    public void processEarning(int input){
        printDivider();
        switch(input){
            case 1:
                addEarning();
                break;
            case 2:
                removeEarning();
                break;
            case 3:
                seeEReport();
                break;
            case 4:
                System.out.println("Returning to the menu...");
                this.answer.nextLine();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: user
    // EFFECTS: let user add a new earning with category and amount
    private void addEarning(){
        double amount = 0.0;
        String cate = "";
        System.out.println("Enter earning amount: ");
        amount = this.answer.nextDouble();
        this.answer.nextLine();
        System.out.println("Choose from Job, Lotto, Financial");
        System.out.println("(any type not listed will be counted as Other).");
        System.out.println("Enter earning category:");
        cate = this.answer.nextLine();
        user.addEarning(cate, amount);
        System.out.println("Earning added successfully!");
        System.out.println("Total Earnings: $" + user.getTotalEarnings());
        printDivider();
        earningMenu();
    }

    // MODIFIES: user
    // EFFECTS: removes an earning by index
    private void removeEarning(){
        for (int i =0; i < user.getEarnings().size(); i++){
            Earning e = user.getEarnings().get(i);
            System.out.println((i + 1) + ". " 
                + "Category: " + e.getCategory()
                + "| Amount: $" + e.getAmount()
                + "| Date: " + e.getDate());
        }

        System.out.println("Enter number of earning you want to remove: ");
        int index = this.answer.nextInt();
        if(index < 1 || index > user.getEarnings().size()){
            System.out.println("Invalid index. Please try again");
        } else{
            user.removeEarning(index -1);
            System.out.println("Earning removed successfully!");
        }
        printDivider();
        earningMenu();
    }

    // EFFECTS: show the currect earning report
    private void seeEReport(){
        System.out.println("Here is your earning summary!!");
        System.out.println("Total Earnings: $" + user.getTotalEarnings());
        System.out.println("Earning by Category: ");
        System.out.println("Job: $" + Earning.getJobTotal());
        System.out.println("Lotto: $" + Earning.getLottoTotal());
        System.out.println("Financial: $" + Earning.getFinancialTotal());
        System.out.println("Other: $" + Earning.getOtherTotal());
        printDivider();
        earningMenu();
    }


        // EFFECTS: displays and processes input for the payment Menu
    public void spendingPage(){
        paymentMenu();
        int input = 0;
        while(!(input == 4)){
            input = this.answer.nextInt();
            processPayment(input);
        }
    }

    // EFFECTS: displays a list of commands that can be used in the payment menu
    private void paymentMenu(){
        System.out.println("Payment option: \n");
        System.out.println("1. Add Payment");
        System.out.println("2. Remove Payment");
        System.out.println("3. See the Payment Summary");
        System.out.println("4. Back to main menu");
        printDivider();
    }
    
    // EFFECTS: processes the user's imput in the payment menu
    public void processPayment(int input){
        printDivider();
        switch(input){
            case 1:
                addPayment();
                break;
            case 2:
                removePayment();
                break;
            case 3:
                seePReport();
                break;
            case 4:
                System.out.println("Returning to the menu...");
                this.answer.nextLine();
                break;
            default:
                System.out.println("Invalid option inputted. Please try again.");
        }
        printDivider();
    }

    // MODIFIES: user
    // EFFECTS: let user add a new earning with category and amount
    private void addPayment(){
        System.out.println("Enter payment amount");
        double amount = this.answer.nextDouble();
        this.answer.nextLine();
        System.out.print("Choose from daily use, food, drink, entertainment ");
        System.out.println("transportation, studies, housing, beauty, cloth");
        System.out.println("(any type not listed will be counted as \"Other\").");
        System.out.println("Enter payment category");
        String category = this.answer.nextLine();
        user.addPayment(category, amount);
        System.out.println("Payment added successfully!");
        System.out.println("Total Payments: $" + user.getTotalPayments());
        printDivider();
        paymentMenu();
    }

    // MODIFIES: user
    // EFFECTS: removes an earning by index
    private void removePayment(){
        for (int i =0; i < user.getPayments().size(); i++){
            Payment p = user.getPayments().get(i);
            System.out.println((i + 1) + ". " 
                + "Category: " + p.getCategory()
                + "| Amount: $" + p.getAmount()
                + "| Date: " + p.getDate());
        }

        System.out.println("Enter number of payment you want to remove: ");
        int index = this.answer.nextInt();
        if(index < 1 || index > user.getPayments().size()){
            System.out.println("Invalid index. Please try again");
        } else{
            user.removePayment(index -1);
            System.out.println("Payment removed successfully!");
        }
        printDivider();
        paymentMenu();
    }

    // EFFECTS: show the currect payment report
    private void seePReport(){
        System.out.println("Here is your payments summary!!");
        System.out.println("Total Payments: $" + user.getTotalPayments());
        System.out.println("Payment by Category: ");
        System.out.println("Daily Use: $" + Payment.getDailyTotal());
        System.out.println("Food & Drink: $" + Payment.getFoodDrinkTotal());
        System.out.println("Entertainment: $" + Payment.getEntertainmentTotal());
        System.out.println("Transportation: $" + Payment.getTransportationTotal());
        System.out.println("Studies: $" + Payment.getStudiesTotal());
        System.out.println("Housing: $" + Payment.getHousingTotal());
        System.out.println("Beauty & Clothes: $" + Payment.getBeautyClothTotal());
        System.out.println("Other: $" + Payment.getOtherTotal());
        printDivider();
        paymentMenu();
    }
    

    // MODIFIES: user
    // EFFECTS: ends the current month, updates savings, clears payments and earnings, prints a summary
    private void endReport() {
        printDivider();
        System.out.println("Ending current month...");

        double totalEarnings = user.getTotalEarnings();
        double totalPayments = user.getTotalPayments();
        double netIncome = totalEarnings - totalPayments;

        // Print summary
        System.out.println("Month Summary:");
        System.out.println("Total Earnings: $" + totalEarnings);
        System.out.println("Total Payments: $" + totalPayments);
        System.out.println("Net income added to savings: $" + netIncome);
        user.endCurrentRecord();
        System.out.println("Current bank amount: $" + user.getBankAmt());
        if (user.getBankAmt() < 0) {
           System.out.println("Warning: Your savings are below zero. You are broke!");
        }
         System.out.println("All monthly earnings and payments have been cleared.");
        printDivider();
        handleMenu();
}

    // EFFECTS: prints out a line of dashes to act as a divider
    private void printDivider() {
        System.out.println("------------------------------------");
    }


    // MODIFIES: this
    // EFFECTS: prints a closing message and marks the program as not running
    public void quitApplication() {
        System.out.println("Thanks for using the Flashcard Reviewer app!");
        System.out.println("Have a good day!");
        this.isProgramRunning = false;
    }



}

package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.User;
import persistence.JsonWriter;
import ui.tabs.MainMenuTab;
import ui.tabs.ProfileTab;
import ui.tabs.earnings.EarningTab;
import ui.tabs.spendings.SpendingTab;
import persistence.JsonReader; 


public class MoneyManageGUI extends JFrame {
    public static final int MAINMENU_TAB_INDEX = 0;
    public static final int PROFILE_TAB_INDEX = 1;
    public static final int EARNING_TAB_INDEX = 2;
    public static final int SPENDING_TAB_INDEX = 3;

    public static final int WIDTH = 700;
    public static final int HEIGHT = 600;
    private JTabbedPane sidebar;
    private User user;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/user.json";


    // MODIFIES: this
    // EFFECTS: creates MoneyMAnageGUI, loads MoneyManage appliances, display sidebar and tabs
    MoneyManageGUI() {
        super("MoneyManage Console");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        user = createUser();
        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: prompts the user for a username and initial bank amount using dialog boxes;
    //          keeps asking until a valid number is entered;
    //          exits the program if user cancels input;
    //          returns a new User with the given name and bank amount.
    private User createUser() {
        String username = JOptionPane.showInputDialog(this, "Enter your name: ");
        if (username == null) {
            System.exit(0);
        }
        double bankAmt = 0;

        boolean validInput = false;
        while (!validInput) {
            try {
                String input = JOptionPane.showInputDialog(this, "Enter your bank amount: ");
                if (input == null) {
                    System.exit(0);
                }
                bankAmt = Double.parseDouble(input);
                validInput = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for bank amount.");
            }
        }

        return new User(username, bankAmt);
    }

    // REQUIRES: sidebar has been initialized; controller is valid
    // MODIFIES: this
    // EFFECTS: creates all application tabs (main menu, profile, earning, spending);
    //          adds them to the sidebar JTabbedPane with correct titles and indices.
    private void loadTabs() {
        JPanel mainMenuTab = new MainMenuTab(this);
        JPanel profileTab = new ProfileTab(this);
        JPanel earningTab = new EarningTab(this);
        JPanel spendingTab = new SpendingTab(this);

        sidebar.add(mainMenuTab, MAINMENU_TAB_INDEX);
        sidebar.setTitleAt(MAINMENU_TAB_INDEX, "Main Menu");
        sidebar.add(profileTab, PROFILE_TAB_INDEX);
        sidebar.setTitleAt(PROFILE_TAB_INDEX, "Profile");
        sidebar.add(earningTab, EARNING_TAB_INDEX);
        sidebar.setTitleAt(EARNING_TAB_INDEX, "Earning");
        sidebar.add(spendingTab, SPENDING_TAB_INDEX);
        sidebar.setTitleAt(SPENDING_TAB_INDEX, "Spending");

    }

    // EFFECTS: returns the current User object for this application.
    public User getUser() {
        return user;
    }

    // EFFECTS: returns the JTabbedPane sidebar used for navigation in the GUI.
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    // MODIFIES: user
    // EFFECTS: saves user to JSON file
    public void saveUserToFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to save file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: user
    // EFFECTS: loads user from JSON file
    public void loadUserFromFile() {
        try {
            user = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Data loaded successfully!");
            refreshAllTabs();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: MainMenuTab
    // EFFECTS: updates the main menu tab to reflect the current user's name,
    //          bank amount, and budget left.
    public void refreshMainMenu() {
        MainMenuTab main = (MainMenuTab) sidebar.getComponentAt(MAINMENU_TAB_INDEX);
        main.updateBalanceLabel();
        main.updateWelcomeLabel();
        main.updateBudgetLeftLabel();
    }

    // MODIFIES: MainMenuTab, ProfileTab, EarningTab, SpendingTab
    // EFFECTS: refreshes all tabs to reflect the current user's updated data;
    //          updates main menu labels, profile info, earning summary, and
    //          spending summary.
    public void refreshAllTabs() {
        // Main Menu
        MainMenuTab main = (MainMenuTab) sidebar.getComponentAt(MAINMENU_TAB_INDEX);
        main.updateBalanceLabel();
        main.updateWelcomeLabel();
        main.updateBudgetLeftLabel();

        // Profile
        ProfileTab profile = (ProfileTab) sidebar.getComponentAt(PROFILE_TAB_INDEX);
        profile.refresh();

        // Earnings
        EarningTab earning = (EarningTab) sidebar.getComponentAt(EARNING_TAB_INDEX);
        earning.refreshSummary();

        // Spending
        SpendingTab spending = (SpendingTab) sidebar.getComponentAt(SPENDING_TAB_INDEX);
        spending.refreshSummary();
    }
}



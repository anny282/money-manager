package ui.tabs;

import javax.swing.*;

import model.User;

import java.awt.*;

import ui.MoneyManageGUI;

public class ProfileTab extends Tab {
    private JLabel nameLabel;
    private JLabel bankLabel;
    private JLabel budgetLabel;

    private JButton editNameButton;
    private JButton editBankButton;
    private JButton editBudgetButton;

    public ProfileTab(MoneyManageGUI controller) {
        super(controller);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addLabels(gbc);
        addButtons(gbc);
    }

    // MODIFIES: this
    // EFFECTS: adds name, bank amount, and budget labels to the panel
    //          using the provided GridBagLayout constraints.
    private void addLabels(GridBagConstraints gbc) {
        User user = getController().getUser();

        gbc.gridx = 0;
        gbc.gridy = 0;

        nameLabel = new JLabel("Name: " + user.getName());
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(nameLabel, gbc);

        gbc.gridy++;
        bankLabel = new JLabel("Bank Amout: $ " + user.getBankAmt());
        bankLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(bankLabel, gbc);

        gbc.gridy++;
        budgetLabel = new JLabel("Budget: $ " + user.getBudget());
        budgetLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
        add(budgetLabel, gbc);
    }

    // MODIFIES: this
    // EFFECTS: creates and adds edit buttons for name, bank amount,
    //          and budget to the panel using the provided layout constraints.
    private void addButtons(GridBagConstraints gbc) {

        gbc.gridx = 1;
        gbc.gridy = 0;

        createNameButton();
        add(editNameButton, gbc);

        gbc.gridy++;
        createBankButton();
        add(editBankButton, gbc);

        gbc.gridy++;
        createBudgetButton();
        add(editBudgetButton, gbc);
    }

    // MODIFIES: this
    // EFFECTS: creates the budget edit button and assigns it an action
    //          that opens a dialog for setting a new budget.
    private void createBudgetButton() {
        editBudgetButton = new JButton("Edit");
        editBudgetButton.addActionListener(e -> editBudget());
    }

    // MODIFIES: user, this
    // EFFECTS: repeatedly prompts user for a valid budget amount;
    //          updates the user's budget and all related labels;
    //          updates the main menu; does nothing if user cancels input.
    private void editBudget() {
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Set a new budget: $ ");
            if (input == null) {
                return;
            }

            try {
                double budget = Double.parseDouble(input);
                getController().getUser().setBudget(budget);
                ;
                updateLabels();
                getController().refreshMainMenu();
                return;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid budget!");
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: creates the bank edit button and assigns it an action
    //          that opens a dialog for entering a new bank amount.
    private void createBankButton() {
        editBankButton = new JButton("Edit");
        editBankButton.addActionListener(e -> editBankAmount());
    }


    // MODIFIES: user, this
    // EFFECTS: repeatedly prompts user for a valid bank amount;
    //          updates the user's bank amount and all related labels;
    //          updates the main menu; does nothing if user cancels input.
    private void editBankAmount() {
        while (true) {
            String input = JOptionPane.showInputDialog(this, "Enter new bank amount: $ ");
            if (input == null) {
                return;
            }

            try {
                double amt = Double.parseDouble(input);
                getController().getUser().setBankAmt(amt);
                updateLabels();
                getController().refreshMainMenu();
                return;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }

        }
    }


    // MODIFIES: this
    // EFFECTS: creates the edit-name button and assigns it an action
    //          that opens a dialog for entering a new username.
    private void createNameButton() {
        editNameButton = new JButton("Edit");
        editNameButton.addActionListener(e -> editName());
    }

    // MODIFIES: user, this
    // EFFECTS: prompts the user for a new non-empty name;
    //          updates name label and main menu;
    //          does nothing if user cancels or enters an empty name.
    private void editName() {
        String newName = JOptionPane.showInputDialog(this, "Enter new name:");
        if (newName == null || newName.trim().isEmpty()) {
            return;
        }

        getController().getUser().setNewName(newName);
        updateLabels();
        getController().refreshMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: refreshes all profile tab labels to show the most recent
    //          name, bank amount, and budget values from the user.
    private void updateLabels() {
        User user = getController().getUser();
        nameLabel.setText("Name: " + user.getName());
        bankLabel.setText("Bank Amount: $" + String.format("%.2f", user.getBankAmt()));
        budgetLabel.setText("Budget: $" + String.format("%.2f", user.getBudget()));
    }

    // MODIFIES: this
    // EFFECTS: updates all displayed labels to reflect the user's
    //          current information; used after loading saved data.
    public void refresh() {
        nameLabel.setText("Name: " + getController().getUser().getName());
        bankLabel.setText("Bank Amount: $" + getController().getUser().getBankAmt());
        budgetLabel.setText("Budget: $" + getController().getUser().getBudget());
    }

}

package ui.tabs;

import javax.swing.*;
import java.awt.*;

import ui.MoneyManageGUI;

public class MainMenuTab extends Tab {
    private JLabel welcomeLabel;
    private JLabel balanceLabel;
    private JLabel budgetLeftLabel;

    private JButton saveButton;
    private JButton loadButton;
    private JButton endButton;

    // EFFECTS: constructs the main menu tab with labels and buttons
    public MainMenuTab(MoneyManageGUI controller) {
        super(controller);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 4;

        addLabels(gbc);

        addButtons(gbc);
    }

    // EFFECTS: initializes and adds welcome and balance labels
    private void addLabels(GridBagConstraints gbc) {
        gbc.gridx = 1;
        gbc.gridy = 0;

        JLabel logo = createLogoLabel();
        add(logo,gbc);
        gbc.gridy++;

        welcomeLabel = new JLabel("Welcome, " + getController().getUser().getName() + "!");
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, gbc);

        gbc.gridy++;
        balanceLabel = new JLabel("Current Balance: $ " + getController().getUser().getBankAmt());
        balanceLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(balanceLabel, gbc);

        gbc.gridy++;
        budgetLeftLabel = new JLabel("Budget Left: $ " + getController().getUser().getBudgetLeft());
        budgetLeftLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(budgetLeftLabel, gbc);
    }

    // EFFECTS: initializes and adds all buttons in a 2x2 grid
    private void addButtons(GridBagConstraints gbc) {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setPreferredSize(new Dimension(400, 50));

        createSaveButton();
        createLoadButton();
        createEndButton();

        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(endButton);

        gbc.gridx = 1;
        gbc.gridy += 2;
        add(buttonPanel, gbc);
    }

    // EFFECTS: creates Save button
    private void createSaveButton() {
        saveButton = new JButton("Save Record");
        saveButton.addActionListener(e -> saveUser());
    }

    // EFFECTS: creates Load button
    private void createLoadButton() {
        loadButton = new JButton("Load Record");
        loadButton.addActionListener(e -> loadUser());
    }

    // EFFECTS: creates End button
    private void createEndButton() {
        endButton = new JButton("End Record");
        endButton.addActionListener(e -> endReport());
    }

    private JLabel createLogoLabel() {
        ImageIcon icon = new ImageIcon("./data/logo.png");
        Image scaled = icon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaled);

        JLabel logoLabel = new JLabel(icon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return logoLabel;
    }

    // EFFECTS: saves user through controller and updates balance label
    private void saveUser() {
        getController().saveUserToFile();
        updateBalanceLabel();
    }

    // EFFECTS: loads user through controller and updates balance label
    private void loadUser() {
        getController().loadUserFromFile();
        updateBalanceLabel();
    }

    // MODIFIES: user
    // EFFECTS: ends the current report, show summary and update balance
    private void endReport() {
        double totalEarnings = getController().getUser().getTotalEarnings();
        double totalPayments = getController().getUser().getTotalPayments();
        double netIncome = totalEarnings - totalPayments;
        getController().getUser().endCurrentRecord();

        String message = String.format(
                "Summary: \nTotal Earnings: $%.2f\nTotal Payments: $%.2f\nNet Income: $%.2f\nNew Bank Amount: $%.2f",
                totalEarnings, totalPayments, netIncome, getController().getUser().getBankAmt());
        JOptionPane.showMessageDialog(this, message, "Monthly Report", JOptionPane.INFORMATION_MESSAGE);
        updateBalanceLabel();
        updateBudgetLeftLabel();
    }

    // MODIFIES: balanceLabel
    // EFFECTS: update balance display
    public void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: $ " 
                + String.format("%.2f", getController().getUser().getBankAmt()));
    }

    // MODIFIES: welcomeLabel
    // EFFECTS: update balance display
    public void updateWelcomeLabel() {
        welcomeLabel.setText("Welcome, " + getController().getUser().getName() + "!");
    }

    // MODIFIES: bugetLeftLabel
    // EFFECTS: update balance display
    public void updateBudgetLeftLabel() {
        budgetLeftLabel.setText("Budget Left: $ " 
                + String.format("%.2f", getController().getUser().getBudgetLeft()));
    }

}

package ui.tabs.spendings;

import model.Payment;
import ui.MoneyManageGUI;
import ui.tabs.Tab;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class SpendingTab extends Tab {

    private JLabel totalLabel;
    private JLabel dailyLabel;
    private JLabel foodDrinkLabel;
    private JLabel enterLabel;
    private JLabel transportLabel;
    private JLabel studiesLabel;
    private JLabel housingLabel;
    private JLabel beautyClothLabel;
    private JLabel otherLabel;

    private JButton addButton;
    private JButton deleteButton;

    private SpendingAddPanel addPanel;
    private SpendingDeletePanel deletePanel;

    public SpendingTab(MoneyManageGUI controller) {
        super(controller);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        buildSummary();
        buildButtons();
        buildAddPanel();
        buildDeletePanel();

        refreshSummary();
        hidePanel();

    }

    // MODIFIES: this
    // EFFECTS: creates and adds all spending summary labels to this tab.
    private void buildSummary() {
        JPanel summary = new JPanel(new GridLayout(9, 1));
        totalLabel = new JLabel();
        dailyLabel = new JLabel();
        foodDrinkLabel = new JLabel();
        enterLabel = new JLabel();
        transportLabel = new JLabel();
        studiesLabel = new JLabel();
        housingLabel = new JLabel();
        beautyClothLabel = new JLabel();
        otherLabel = new JLabel();

        summary.add(totalLabel);
        summary.add(dailyLabel);
        summary.add(foodDrinkLabel);
        summary.add(enterLabel);
        summary.add(transportLabel);
        summary.add(studiesLabel);
        summary.add(housingLabel);
        summary.add(beautyClothLabel);
        summary.add(otherLabel);

        add(summary);

    }

    // MODIFIES: this
    // EFFECTS: creates Add/Delete buttons and adds them to this tab.
    private void buildButtons() {
        JPanel buttonPanel = new JPanel();

        createAddButton();
        createDeleteButton();

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel);

    }

    // MODIFIES: this
    // EFFECTS: creates the delete button and assigns its action to open the delete panel.
    private void createDeleteButton() {
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> clickDelete());
    }

    // MODIFIES: this
    // EFFECTS: toggles delete panel visibility, refreshes its list,
    //          and hides the add panel.
    private void clickDelete() {
        deletePanel.setVisible(!deletePanel.isVisible());
        deletePanel.refreshList();
        addPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates the add button and assigns its action to open the add panel.
    private void createAddButton() {
        addButton = new JButton("Add");
        addButton.addActionListener(e -> clickAdd());
    }

    // MODIFIES: this
    // EFFECTS: toggles add panel visibility and hides the delete panel.
    private void clickAdd() {
        addPanel.setVisible(!addPanel.isVisible());
        deletePanel.refreshList();
        deletePanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates the add-spending panel and adds it to this tab.
    private void buildAddPanel() {
        addPanel = new SpendingAddPanel(this);
        add(addPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the delete-spending panel and adds it to this tab.
    private void buildDeletePanel() {
        deletePanel = new SpendingDeletePanel(this);
        add(deletePanel);
    }

    // MODIFIES: this
    // EFFECTS: updates all category and total spending labels.
    public void refreshSummary() {
        var user = getController().getUser();

        totalLabel.setText("Total Spendings: $" + user.getTotalPayments());
        dailyLabel.setText("Daily Use: $" + Payment.getDailyTotal());
        foodDrinkLabel.setText("Food & Drink: $" + Payment.getFoodDrinkTotal());
        enterLabel.setText("Entertainment: $" + Payment.getEntertainmentTotal());
        transportLabel.setText("Transportation: $" + Payment.getTransportationTotal());
        studiesLabel.setText("Studies: $" + Payment.getStudiesTotal());
        housingLabel.setText("Housing: $" + Payment.getHousingTotal());
        beautyClothLabel.setText("Beauty & Clothes: $" + Payment.getBeautyClothTotal());
        otherLabel.setText("Other: $" + Payment.getOtherTotal());
    }

    // MODIFIES: user, this
    // EFFECTS: adds a new spending record, updates summaries and main menu;
    //          shows budget warning if exceeded; hides panels.
    public void addSpending(String category, double amount) {
        getController().getUser().addPayment(category, amount, LocalDate.now());
        refreshSummary();
        deletePanel.refreshList();
        getController().refreshMainMenu();

        if (getController().getUser().getBudgetLeft() < 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Warning: You have exceeded your budget!",
                    "Budget Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        hidePanel();
    }

    // MODIFIES: user, this
    // EFFECTS: deletes the spending at the given index,
    //          updates summaries and main menu, and hides panels.
    public void deleteSpending(int index) {
        getController().getUser().removePayment(index);
        refreshSummary();
        deletePanel.refreshList();
        getController().refreshMainMenu();
        hidePanel();
    }

    // MODIFIES: this
    // EFFECTS: hides both the add and delete panels.
    public void hidePanel() {
        addPanel.setVisible(false);
        deletePanel.setVisible(false);
    }

}

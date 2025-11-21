package ui.tabs.earnings;

import ui.MoneyManageGUI;
import ui.tabs.Tab;
import model.Earning;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class EarningTab extends Tab {

    private JLabel totalLabel;
    private JLabel jobLabel;
    private JLabel lottoLabel;
    private JLabel financialLabel;
    private JLabel otherLabel;

    private JButton addButton;
    private JButton deleteButton;

    private EarningAddPanel addPanel;
    private EarningDeletePanel deletePanel;

    public EarningTab(MoneyManageGUI controller) {
        super(controller);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        buildSummary();
        buildButtons();
        buildAddPanel();
        buildDeletePanel();

        refreshSummary();
    }

    // MODIFIES: this
    // EFFECTS: updates all earning summary labels to reflect current totals.
    public void refreshSummary() {
        var user = getController().getUser();

        totalLabel.setText("Total Earning: $" + user.getTotalEarnings());
        jobLabel.setText("Job: $" + Earning.getJobTotal());
        lottoLabel.setText("Lotto: $" + Earning.getLottoTotal());
        financialLabel.setText("Financial: $" + Earning.getFinancialTotal());
        otherLabel.setText("Other: $" + Earning.getOtherTotal());
    }

    // MODIFIES: this
    // EFFECTS: creates the delete panel and adds it to the tab.
    private void buildDeletePanel() {
        deletePanel = new EarningDeletePanel(this);
        add(deletePanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the add panel and adds it to the tab.
    private void buildAddPanel() {
        addPanel = new EarningAddPanel(this);
        add(addPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the add/delete buttons and adds them to the panel.
    private void buildButtons() {
        JPanel buttonPanel = new JPanel();

        createAddButton();
        createDeleteButton();

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates delete button and assigns action to open the delete panel.
    private void createDeleteButton() {
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> clickDelete());
    }

    // MODIFIES: this
    // EFFECTS: toggles delete panel visibility and refreshes its list;
    //          hides the add panel.
    private void clickDelete() {
        deletePanel.setVisible(!deletePanel.isVisible());
        deletePanel.refreshList();
        addPanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates add button and assigns action to open the add panel.
    private void createAddButton() {
        addButton = new JButton("Add");
        addButton.addActionListener(e -> clickAdd());
    }

    // MODIFIES: this
    // EFFECTS: toggles add panel visibility and hides delete panel.
    private void clickAdd() {
        addPanel.setVisible(!addPanel.isVisible());
        deletePanel.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates earning summary labels and adds them to the tab.
    private void buildSummary() {
        JPanel summary = new JPanel(new GridLayout(5, 1));

        totalLabel = new JLabel();
        jobLabel = new JLabel();
        lottoLabel = new JLabel();
        financialLabel = new JLabel();
        otherLabel = new JLabel();

        summary.add(totalLabel);
        summary.add(jobLabel);
        summary.add(lottoLabel);
        summary.add(financialLabel);
        summary.add(otherLabel);

        add(summary);
    }

    // MODIFIES: user, this
    // EFFECTS: adds an earning to the user, refreshes summaries and main menu.
    public void addEarning(String category, double amount) {
        getController().getUser().addEarning(category, amount, LocalDate.now());
        refreshSummary();
        deletePanel.refreshList();
        getController().refreshMainMenu();
    }

    // MODIFIES: user, this
    // EFFECTS: removes the earning at the given index and refreshes summaries and main menu.
    public void deleteEarning(int index) {
        getController().getUser().removeEarning(index);
        refreshSummary();
        deletePanel.refreshList();
        getController().refreshMainMenu();
    }

    // MODIFIES: this
    // EFFECTS: hides both the add and delete panels.
    public void hidePanel() {
        addPanel.setVisible(false);
        deletePanel.setVisible(false);
    }
}

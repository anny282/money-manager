package ui.tabs.earnings;

import javax.swing.*;
import java.awt.*;

public class EarningAddPanel extends JPanel {

    private JComboBox<String> categoryBox;
    private JTextField amountField;
    private JButton confirmButton;

    private EarningTab parent;

    public EarningAddPanel(EarningTab parent) {
        this.parent = parent;

        setLayout(new GridLayout(3,2,5,5));
        setBorder(BorderFactory.createTitledBorder("Add Earning"));
        setVisible(false);

        categoryBox = new JComboBox<>(new String[] {
            "Job", "Lotto", "Financial", "Other"
        });

        amountField = new JTextField();
        confirmButton = new JButton("Confirm");

        confirmButton.addActionListener(e -> addRecord());

        add(new JLabel("Category: "));
        add(categoryBox);
        add(new JLabel("Amount: $"));
        add(amountField);
        add(confirmButton);
    }

    

    // MODIFIES: parent
    // EFFECTS: adds a new earning with the selected category and amount,
    //          hides the panel; shows error message if amount is invalid.
    private void addRecord() {
        try {
            String category = categoryBox.getSelectedItem().toString();
            double amount = Double.parseDouble(amountField.getText());
            amountField.setText("");

            parent.addEarning(category, amount);
            parent.hidePanel();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter the valid number!");
        }
    }   
    
}


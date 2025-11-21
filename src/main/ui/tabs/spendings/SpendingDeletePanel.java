package ui.tabs.spendings;

import javax.swing.*;
import java.awt.*;

public class SpendingDeletePanel extends JPanel {

    private SpendingTab parent;
    private JList<String> list;

    public SpendingDeletePanel(SpendingTab parent) {
        this.parent = parent;

        setLayout(new GridLayout(3, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Delete Spending"));
        setVisible(false);

        list = new JList<>();

        JButton deleteButton = new JButton("Delete Selected");

        deleteButton.addActionListener(e -> {
            int index = list.getAnchorSelectionIndex();
            if (index >= 0) {
                parent.deleteSpending(index);
                parent.hidePanel();
            }
        });

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
        refreshList();
    }

    // MODIFIES: this
    // EFFECTS: updates the displayed list of spendings using the user's current payments.
    void refreshList() {
        var user = parent.getController().getUser();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (int i = 0; i < user.getPayments().size(); i++) {
            var s = user.getPayments().get(i);
            model.addElement((i + 1) + "." 
                    + s.getCategory() + " $" + s.getAmount() + " [" + s.getDate() + "]");
        }
        list.setModel(model);
    }
}

package ui.tabs.earnings;

import javax.swing.*;
import java.awt.*;

public class EarningDeletePanel extends JPanel {

    private EarningTab parent;
    private JList<String> list;

    public EarningDeletePanel(EarningTab parent) {
        this.parent = parent;
        setLayout(new GridLayout(3, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Delete Earning"));
        setVisible(false);

        list = new JList<>();

        JButton deleteButton = new JButton("Delete Selected");

        deleteButton.addActionListener(e -> {
            int index = list.getSelectedIndex();
            if (index >= 0) {
                parent.deleteEarning(index);
                parent.hidePanel();
            }
        });

        add(new JScrollPane(list), BorderLayout.CENTER);
        add(deleteButton, BorderLayout.SOUTH);
        refreshList();
    }

    // MODIFIES: this
    // EFFECTS: updates the list to show all current earnings in the user's account.
    void refreshList() {
        var user = parent.getController().getUser();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (int i = 0; i < user.getEarnings().size(); i++) {
            var e = user.getEarnings().get(i);
            model.addElement((i + 1) + "."
                    + e.getCategory() + " $" + e.getAmount() + " [" + e.getDate() + "]");
        }
        list.setModel(model);
    }
}

package ui.tabs;

import javax.swing.*;
import java.awt.*;

import ui.MoneyManageGUI;

public abstract class Tab extends JPanel {

    private final MoneyManageGUI controller;

    //REQUIRES: MoneyManageGUI controller that holds this tab
    public Tab(MoneyManageGUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the MoneyManageGUI controller for this tab
    public MoneyManageGUI getController() {
        return controller;
    }
    
}

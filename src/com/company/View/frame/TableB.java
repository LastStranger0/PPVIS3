package com.company.View.frame;

import javax.swing.*;
import java.awt.*;

public class TableB {
    JPanel panel;
    public TableB(JScrollPane scrollPane){
        panel = new JPanel();
        scrollPane.setMinimumSize(new Dimension(100, 180));
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return panel;
    }
}

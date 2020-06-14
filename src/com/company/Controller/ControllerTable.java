package com.company.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ControllerTable {
    public JTable create(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("x");
        tableModel.addColumn("y");
        JTable table = new JTable();
        table.setModel(tableModel);
        return table;
    }

    public JTable add(JTable table, String fcol, String scol){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] {fcol,scol});
        return table;
    }
}

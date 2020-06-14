package com.company.View.frame;

import com.company.Controller.ControllerTable;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class ViewFrame {
    ControllerTable table;
    private JPanel panel;
    private JTextField minX;
    private JTextField maxX;
    private JTextField a;

    private JButton make1;
    private JButton make2;
    private JButton stop;

    private JLabel min;
    private JLabel max;
    private JLabel A;

    private JPanel tableBPanel;
    private JPanel functionAPanel;

    private JTable funcBTable;
    private JScrollPane scrollPane;

    public ViewFrame(){
        table = new ControllerTable();
        minX = new JTextField(5);
        maxX = new JTextField(5);
        a = new JTextField(5);
        make1 = new JButton("Draw A" );
        make2 = new JButton("Draw B");
        stop = new JButton("Stop");
        min = new JLabel("min X = ");
        max = new JLabel("max X = ");
        A = new JLabel("a = ");
        panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(min)
                        .addComponent(minX)
                        .addComponent(max)
                        .addComponent(maxX)
                        .addComponent(A)
                        .addComponent(a)
                        .addComponent(stop)
                        .addComponent(make1)
                        .addComponent(make2))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(min)
                .addComponent(minX)
                .addComponent(max)
                .addComponent(maxX)
                .addComponent(A)
                .addComponent(a)
                .addComponent(stop)
                .addComponent(make1)
                .addComponent(make2)
        );
    }

    public JTextField getA() {
        return a;
    }

    public JTextField getMaxX() {
        return maxX;
    }

    public JTextField getMinX() {
        return minX;
    }

    public JPanel getPanel(){
        return panel;
    }

    public JButton getMake1() {
        return make1;
    }

    public JButton getMake2() {
        return make2;
    }

    public JButton getStop() {
        return stop;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getFunctionAPanel() {
        return functionAPanel;
    }

    public JTable getFuncBTable() {
        return funcBTable;
    }

    public JPanel getTableBPanel() {
        return tableBPanel;
    }

    public ControllerTable getTable() {
        return table;
    }

    public void setFuncBTable(JTable funcBTable) {
        this.funcBTable = funcBTable;
    }

    public void setA(JTextField a) {
        this.a = a;
    }

    public void setA(JLabel a) {
        A = a;
    }

    public void setFunctionAPanel(JPanel functionAPanel) {
        this.functionAPanel = functionAPanel;
    }

    public void setMake1(JButton make1) {
        this.make1 = make1;
    }

    public void setMake2(JButton make2) {
        this.make2 = make2;
    }

    public void setMinX(JTextField minX) {
        this.minX = minX;
    }

    public void setMax(JLabel max) {
        this.max = max;
    }

    public void setMaxX(JTextField maxX) {
        this.maxX = maxX;
    }

    public void setMin(JLabel min) {
        this.min = min;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setTable(ControllerTable table) {
        this.table = table;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void setStop(JButton stop) {
        this.stop = stop;
    }

    public void setTableBPanel(JPanel tableBPanel) {
        this.tableBPanel = tableBPanel;
    }
}

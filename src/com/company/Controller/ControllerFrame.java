package com.company.Controller;

import javax.swing.*;
import static java.lang.Math.*;
import java.awt.*;
import java.awt.geom.Point2D;

import com.company.View.frame.TableB;
import com.company.View.frame.ViewFrame;

public class ControllerFrame {
    private Thread threadA = new Thread();
    private Thread threadB = new Thread();
    private ViewFrame l;
    private TableB B;
    private boolean stop = false;

    public JPanel createInf(){
        l = new ViewFrame();
        return l.getPanel();
    }

    public JPanel createTableB(JScrollPane scrollPane){
        B = new TableB(scrollPane);
        return B.getPanel();
    }

    public int Afunc(int x){
        return 3*x+4;
    }

    public double factorial(double number) {
        double result = 1;

        for (double factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    public double Bfunc(double x, double a){
            double y = 0;
        double value = 1;
        double k = 0;

        while (abs(value) >= 0.01){
            value = (pow(-1, k+1))*(pow(2,2*k+1))*pow(a*x, 2*k)/factorial(2*k);
            y+=value;
            k++;
        }
        return abs(y);
    }

    public void action(JFrame mainFrame, JSplitPane splitPane,ControllerGraph controller){
        l.getMake1().addActionListener(e->{

            stop = false;
            if(threadA.isAlive()){
                threadB.stop();
            }
            controller.clear(1);
            threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    int x = 1;
                    while (x<=9 && !stop){
                        int y = Afunc(x);
                        Point point = new Point(x, y);
                        controller.add(point, 1);
                        x+=1;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException en) {
                            JOptionPane.showMessageDialog(mainFrame, "Ошибка!\nЧто-то пошло не так!");
                        }
                    }
                    Thread.interrupted();
                }
            });
            threadA.start();
            splitPane.setLeftComponent(l.getFunctionAPanel());
        });

        l.getMake2().addActionListener(e -> {
            if(splitPane.getLeftComponent() != null){
                l.getTableBPanel().remove(l.getScrollPane());
            }
            l.setFuncBTable(l.getTable().create());
            l.setScrollPane(new JScrollPane(l.getFuncBTable()));
            l.setTableBPanel(createTableB(l.getScrollPane()));
            splitPane.setLeftComponent(l.getTableBPanel());
            stop = false;
            if(threadB.isAlive()){
                threadB.stop();
            }
            controller.clear(2);
            threadB = new Thread(new Runnable() {
                @Override
                public void run() {
                    double x = Double.parseDouble(l.getMinX().getText());
                    while (x <= Double.parseDouble(l.getMaxX().getText()) && !stop){
                        double y = Bfunc(x, Double.parseDouble(l.getA().getText()));
                        Point2D point2D = new Point2D.Double(x, y);
                        controller.add(point2D, 2);
                        l.setFuncBTable(l.getTable().add(l.getFuncBTable(), String.format("%.4f", (x)), String.format("%.4f", (y))));
                        x += 0.1;
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException en) {
                            JOptionPane.showMessageDialog(mainFrame, "Ошибка!\nЧто-то пошло не так!");
                        }
                    }
                    Thread.interrupted();
                }
            });
            threadB.start();
        });
        l.getStop().addActionListener(e -> {
            stop = !stop;
        });
    }
}

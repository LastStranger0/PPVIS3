package com.company.View;

import com.company.Controller.ControllerGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class ViewOption extends JPanel {
    private ViewGraph viewGraph;
    private JSlider slider;

    public ViewOption(ControllerGraph controller) {
        super();
        this.setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);

        slider = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);

        viewGraph = new ViewGraph(controller);

        toolBar.setFloatable(false);
        toolBar.setLayout(new FlowLayout());

        JScrollPane scrollPanel = new JScrollPane(viewGraph);

        scrollPanel.setAutoscrolls(true);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            private Point point;

            @Override
            public void mousePressed(MouseEvent e) {
                point = e.getPoint();
            }


            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (point != null) {
                    JViewport viewPort = (JViewport) SwingUtilities.getAncestorOfClass(JViewport.class, viewGraph);
                    if (viewPort != null) {
                        int deltaX = point.x - e.getX();
                        int deltaY = point.y - e.getY();

                        Rectangle view = viewPort.getViewRect();
                        view.x += deltaX * 0.3;
                        view.y += deltaY * 0.2;
                        viewGraph.scrollRectToVisible(view);
                    }
                }
            }
        };
        viewGraph.addMouseListener(mouseAdapter);
        viewGraph.addMouseMotionListener(mouseAdapter);
        scrollPanel.setPreferredSize(new Dimension(800, 600));
        scrollPanel.setVisible(true);

        JLabel ALabel = new JLabel("-");
        ALabel.setFont(new Font("Arial", Font.BOLD, 20));
        ALabel.setForeground(new Color(255, 200, 0));
        JLabel BLabel = new JLabel("+");
        BLabel.setFont(new Font("Arial", Font.BOLD, 20));
        BLabel.setForeground(new Color(0, 0, 255));

        toolBar.add(ALabel);
        toolBar.add(slider);
        toolBar.add(BLabel);
        this.add(scrollPanel, BorderLayout.WEST);
        this.add(toolBar, BorderLayout.SOUTH);
        this.setVisible(true);
        scrollPanel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    if (e.getWheelRotation() < 0) {
                        viewGraph.setZoom(viewGraph.getZoom() + 0.1);

                    } else {
                        viewGraph.setZoom(viewGraph.getZoom() - 0.1);
                    }
                    slider.setValue((int) (viewGraph.getZoom() * 100));
                    viewGraph.revalidate();
                }
            }
        });
    }

    public ViewGraph getViewGraph() {
        return viewGraph;
    }
}

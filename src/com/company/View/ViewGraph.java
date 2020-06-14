package com.company.View;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.company.Controller.ControllerGraph;

public class ViewGraph extends JPanel{
    private List<Point2D> GraphA;
    private List<Point2D> GraphB;

    private Color colorpoint = new Color(0x000000);
    private Stroke Stroke = new BasicStroke(3);
    private double zoom;

    public ViewGraph(ControllerGraph controller) {
        this.GraphA = controller.getList(1);
        this.GraphA.sort(new XComparator());
        this.GraphB = controller.getList(2);
        this.GraphB.sort(new XComparator());
        this.zoom = 0.5;
        this.setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        int prefW = 1100;
        int prefH = 900;
        return new Dimension((int) (prefW * zoom), (int) (prefH * zoom));
    }

    static class XComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p1, Point2D p2) {
            return p1.getX() >= p2.getX() ? 1 : 0;
        }
    }

    static class YComparator implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p1, Point2D p2) {
            return p1.getY() >= p2.getY() ? 1 : 0;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        {
            double hatch = 0,
                    maxAX = findStep((int) Collections.max(GraphA, new XComparator()).getX()),
                    maxAY = findStep((int) Collections.max(GraphA, new YComparator()).getY()),
                    maxBX = findStep((int) Collections.max(GraphB, new XComparator()).getX()),
                    maxBY = findStep((int) Collections.max(GraphB, new YComparator()).getY()),
                    maxX = Math.max(maxBX, maxAX),
                    maxY = Math.max(maxBY, maxAY);
            int border = 55;
            int pointWidth = 10;
            int border_gap = (int) (border * zoom),
                    hatchLength = (int) (pointWidth * zoom);
            double xScale = (((double) getWidth() - 2 * border_gap) / maxX),
                    yScale = (((double) getHeight() - 2 * border_gap) / maxY);
            int fontSize;
            int fontSize1 = 10;
            if (zoom < 3) {
                fontSize = (int) (fontSize1 * zoom);
            } else {
                fontSize = fontSize1 * 3;
            }
            List<Point> zoomedPointsA = new ArrayList<>();
            List<Point> zoomedPointsB = new ArrayList<>();

            add(border_gap, xScale, yScale, zoomedPointsA, GraphA);

            add(border_gap, xScale, yScale, zoomedPointsB, GraphB);

            g2.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
            g2.drawLine(border_gap, getHeight() - border_gap, border_gap, border_gap);
            g2.drawLine(border_gap, getHeight() - border_gap, getWidth() - border_gap, getHeight() - border_gap);


            hatch = maxY / 10;

            int Hatch = 10;
            for (int index = 0; index < Hatch; index++) {
                int x0 = border_gap - hatchLength / 2,
                        x1 = x0 + hatchLength,
                        y0 = getHeight() - (((index + 1) * (getHeight() - border_gap * 2)) / Hatch + border_gap);
                g2.drawLine(x0, y0, x1, y0);
                g2.drawString(String.format("%.4f", hatch * (index + 1)), 0, y0 + fontSize / 2);
            }

            hatch = maxX / 10;

            for (int index = 0; index < Hatch; index++) {
                int x0 = (index + 1) * (getWidth() - border_gap * 2) / Hatch + border_gap,
                        y0 = getHeight() - border_gap + hatchLength / 2,
                        y1 = y0 - hatchLength;
                g2.drawLine(x0, y0, x0, y1);
                g2.drawString(String.format("%.4f", hatch * (index + 1)), x0 - fontSize / 2, y0 + border_gap / 2);
            }
            g2.drawString("0", border_gap, getHeight() - border_gap / 2);
            Stroke oldStroke = g2.getStroke();
            setColor(g2, zoomedPointsA, oldStroke, new Color(0, 0, 0));
            for (Point point : zoomedPointsA) {
                int x = (point.x - pointWidth / 2),
                        y = (point.y - pointWidth / 2);
                g2.fillOval(x, y, pointWidth, pointWidth);
            }
            setColor(g2, zoomedPointsB, oldStroke, new Color(0, 222, 0));
            for (Point point : zoomedPointsB) {
                int x = point.x - pointWidth / 2,
                        y = point.y - pointWidth / 2;
                g2.fillOval(x, y, pointWidth, pointWidth);
            }
        }
    }

    private void setColor(Graphics2D g2, List<Point> zoomedA, Stroke oldStroke, Color ColorA) {
        g2.setColor(ColorA);
        g2.setStroke(Stroke);
        for (int i = 0; i < zoomedA.size() - 1; i++) {
            Point point = zoomedA.get(i);
            int x1 = point.x,
                    y1 = point.y;
            point = zoomedA.get(i + 1);
            int x2 = point.x,
                    y2 = point.y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(colorpoint);
    }

    private void add(int border_gap, double xScale, double yScale, List<Point> zoomedB, List<Point2D> pointsB) {
        for (Point2D point : pointsB) {
            Point newPoint = new Point();
            newPoint.x = (int) (border_gap + point.getX() * xScale);
            newPoint.y = (int) (getHeight() - point.getY() * yScale - border_gap);
            zoomedB.add(newPoint);
        }
    }

    public void setZoom(double zoom) {
        if (zoom < 0.5) {
            this.zoom = 0.5;
        } else {
            this.zoom = Math.min(zoom, 1.5);
        }
    }

    public double getZoom() {
        return zoom;
    }

    public int findStep(int number) {
        int step = 10;
        while (step < number) {
            step = step + 10;
        }
        return step;
    }

}

package com.company.Model;

import java.util.ArrayList;
import java.util.List;
import java.awt.geom.*;

public class graphA {
    private List<Point2D> points;

    public graphA(){
        points = new ArrayList<>();
        points.add(new Point2D.Double(0,0));
    }

    public void add(Point2D point){
        points.add(point);
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public void clear(){
        points.clear();
    }
}

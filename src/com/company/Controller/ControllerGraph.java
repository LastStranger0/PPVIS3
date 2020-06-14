package com.company.Controller;

import javax.swing.*;
import java.awt.geom.*;
import java.util.List;

import com.company.Model.*;
import com.company.View.ViewOption;

public class ControllerGraph {
    private graphA graphA;
    private graphB graphB;
    private ViewOption viewOption;

    public ControllerGraph(JSplitPane splitPane){
        graphA = new graphA();
        graphB = new graphB();
        viewOption = new ViewOption(this);
        splitPane.setRightComponent(viewOption);
    }

    public void clear(int listnumber){
        if(listnumber == 1){
            graphA.clear();
        }
        else if (listnumber == 2){
            graphB.clear();
        }
    }

    public void add(Point2D point, int listnumber){
        if(listnumber == 1){
            graphA.add(point);
        }
        else if (listnumber == 2){
            graphB.add(point);
        }
        getViewOption().getViewGraph().repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Point2D> getList(int listnumber){
        if(listnumber == 1){
            return graphA.getPoints();
        }
        else {
            return  graphB.getPoints();
        }
    }

    public ViewOption getViewOption(){
        return viewOption;
    }

}

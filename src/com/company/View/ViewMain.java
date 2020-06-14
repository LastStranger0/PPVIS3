package com.company.View;

import javax.swing.*;
import java.awt.*;

import com.company.Controller.*;

public class ViewMain {
    ControllerFrame controllerFrame = new ControllerFrame();
    private JFrame mainFrame = new JFrame("Graphic");
    private JPanel background = new JPanel();
    private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    private ControllerGraph controller = new ControllerGraph(splitPane);

    public ViewMain(){
        background.setLayout(new BorderLayout());

        JPanel leftpanel = controllerFrame.createInf();
        leftpanel.setBorder(BorderFactory.createEtchedBorder());

        JSplitPane splitPaneHor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        background.add(splitPaneHor,BorderLayout.CENTER);
        splitPaneHor.setLeftComponent(leftpanel);
        splitPaneHor.setRightComponent(splitPane);
        mainFrame.add(background);
        mainFrame.setSize(new Dimension(1280,720));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        controllerFrame.action(mainFrame,splitPane,controller);
        mainFrame.setVisible(true);
    }
}

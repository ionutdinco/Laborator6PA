package com.optional;

import javax.naming.ldap.Control;
import javax.swing.*;

import static java.awt.BorderLayout.CENTER;

public class MainFrame extends JFrame {
        ConfigPanel configPanel;
        ControlPanel controlPanel;
        DrawingPanel canvas;

        public MainFrame() {
            super("My Drawing Application");
            init();
        }

        private void init() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            //create the components
            canvas = new DrawingPanel(this);
            ConfigPanel config = new ConfigPanel(canvas.frame);
            canvas.add(config);
            canvas.setConfig(config);
            ControlPanel control = new ControlPanel(this);
            canvas.add(control);
            ShapesBar bar = new ShapesBar(this,canvas.image);
            canvas.add(bar);
            canvas.setLeftbar(bar);


            //arrange the components in the container (frame)
            //JFrame uses a BorderLayout by default
            add(canvas, CENTER); //this is BorderLayout.CENTER

            //invoke the layout manager
            pack();
        }


}

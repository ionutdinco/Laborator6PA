package com.optional;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;

public class ConfigPanel  extends JPanel  {
    final MainFrame frame;
    JPanel sidesLabel; // we’re drawing regular polygons
    JSpinner sidesField,size; // number of sides
    JComboBox colorCombo; // the color of the shape
    JColorComboBox box ;

    private int sidesShape,sizeShape;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create the label and the spinner
        sidesLabel = new JPanel();
        sidesField = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        size = new JSpinner(new SpinnerNumberModel(30, 5, 100, 1));
        sidesLabel.add(sidesField); sidesLabel.add(size);

        sidesField.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spiner = (JSpinner) e.getSource();
                sidesShape= (int) spiner.getValue();
                System.out.println(sidesShape);
            }
        });

        size.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spiner = (JSpinner) e.getSource();
                sizeShape = (int) spiner.getValue();
            }
        });
        sidesField.getValue();
        Vector model = new Vector();

        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        float a = rand.nextFloat();
        Color randomColor = new Color(r, g, b,a);
         box = new JColorComboBox();
        ColorRenderer render = new ColorRenderer();
        render.setColors(box.getColors());

        add(sidesLabel); //JPanel uses FlowLayout b default
        add(sidesField);
        add(size);
        add(box);


    }
   public int getSides()
   {
       return this.sidesShape;
   }
    public int getSizeShape()
    {
        return this.sizeShape;
    }

    public String getBox() {
        return box.getColor();
    }
}

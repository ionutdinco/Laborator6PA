package com.optional;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ShapesBar extends JPanel  {

    final MainFrame frame;
    final static int W = 1650, H = 1080;
    JPanel panel;
    BufferedImage image;
    Graphics2D graphics;
    List<Polygon> forms = new ArrayList<>();
    int preX, preY;

    public ShapesBar(MainFrame frame,BufferedImage image) {
        this.frame = frame;
        graphics = image.createGraphics();
        init();
    }

    public void init() {
        panel = new JPanel();
        panel.setBounds(1000, H / 4, W / 6, H / 3);
        panel.setBackground(Color.green);
        add(panel);
        Polygon pol1=drawShapePol(1300, 500);
        graphics.setColor(Color.pink);
        graphics.fill(pol1);
        forms.add(pol1);


    }

    public Polygon drawShapePol(int x, int y) {
        int[] X = {x,x-30,x+30};
        int[] Y = {y-30,y+30,y+30};
        Polygon pol = new Polygon(X,Y,3);

        return pol;
    }

    public void setImage(BufferedImage image) {
        this.image = image;

    }



}


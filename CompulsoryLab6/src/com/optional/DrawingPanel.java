package com.optional;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;


public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1650, H = 1080;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image
    ConfigPanel config;
    ShapesBar leftbar;
    Map<Integer, List<Integer>> positionXY = new HashMap<>();
    int contorElemente = 0;
    Boolean delete = false;
    Boolean duplicate = false;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        init();
    }

    private void createOffscreenImage() {

        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.cyan); //fill the image with white
        graphics.fillRect(W / 5, H / 6, W / 5 + W / 3, H / 6 + H / 3);

    }

    public void updateOffscreenImage(File file) {

        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        try {
            ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        graphics = image.createGraphics();

    }


    private void init() {
        setPreferredSize(new Dimension(W, H));
        setBackground(Color.white);

        //setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if ((e.getX() >= W / 5 + config.getSizeShape()) && (e.getX() <= (2 * W / 5 + W / 3) - config.getSizeShape()) && (e.getY() >= H / 6 + config.getSizeShape()) && (e.getY() <= (2 * H / 6 + H / 3) - config.getSizeShape())) {

                    for (var iterator : positionXY.entrySet()) {
                        if (e.getX() >= iterator.getValue().get(0) - iterator.getValue().get(2) && e.getX() <= iterator.getValue().get(0) + iterator.getValue().get(2) && e.getY() >= iterator.getValue().get(1) - iterator.getValue().get(2) && e.getY() <= iterator.getValue().get(1) + iterator.getValue().get(2)) {
                            drawDeleteShape(iterator.getValue().get(0), iterator.getValue().get(1), iterator.getValue().get(3), iterator.getValue().get(2));
                            repaint();
                            List list = new ArrayList();
                            list.add(iterator.getValue().get(0));
                            list.add(iterator.getValue().get(1));
                            list.add(iterator.getValue().get(2));
                            list.add(iterator.getValue().get(3));
                            positionXY.remove(iterator.getKey(), list);
                            delete = true;
                        }

                    }
                    if (duplicate) {
                        graphics.setColor(Color.red);
                        Polygon pol = leftbar.drawShapePol(e.getX(), e.getY());
                        graphics.setColor(Color.pink);
                        graphics.fill(pol);
                        duplicate = false;
                        System.out.println("desenez");
                        repaint();
                    } else {
                        if (!delete) {

                            drawShape(e.getX(), e.getY());
                            repaint();
                            System.out.println("oriceeeee");

                        }
                    }
                    delete = false;
                }
                for (var iterator : leftbar.forms) {
                    if (iterator.contains(e.getX(), e.getY())) {
                        duplicate = true;
                    }
                }
            }

        });
    }

    private void drawShape(int x, int y) {
        int radius = this.config.getSizeShape();
        int sides = this.config.getSides(); //get the value from UI (in ConfigPanel)
        List<Integer> aux = new ArrayList();
        aux.add(x);
        aux.add(y);
        aux.add(radius);
        aux.add(sides);
        positionXY.put(contorElemente++, aux);
        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(config.box.getColor());
            color = (Color) field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }


    private void drawDeleteShape(int x, int y, int sidess, int radiuss) {
        int radius = radiuss;
        int sides = sidess;

        graphics.setColor(Color.cyan);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }


    public void setConfig(ConfigPanel config) {
        this.config = config;
    }

    public void setLeftbar(ShapesBar leftbar) {
        this.leftbar = leftbar;
    }

    @Override
    public void update(Graphics g) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

}

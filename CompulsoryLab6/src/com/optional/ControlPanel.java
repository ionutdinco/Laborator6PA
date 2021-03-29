package com.optional;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    final static int W = 1650, H = 1080;
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resetBtn = new JButton("Reset");
    Graphics2D graphics;
    File file;
    BufferedImage image;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        saveBtn.setBounds(1000, 800, 40, 40);
        add(saveBtn);
        loadBtn.setBounds(1050, 800, 40, 40);
        add(loadBtn);
        resetBtn.setBounds(1100, 800, 40, 40);
        add(resetBtn);

        saveBtn.addActionListener(this::save);
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel l = new JLabel();
                JFileChooser save = new JFileChooser();
                save.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = save.showOpenDialog(getParent());
                if (result == JFileChooser.APPROVE_OPTION) {
                    int W = 1650;
                    int H = 1080;
                    file = save.getSelectedFile();
                    image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D graphics = image.createGraphics();

                    System.out.println("ammmmmm luat");


                }
                // if the user cancelled the operation
                else
                    l.setText("the user cancelled the operation");
            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOffscreenImage();
            }
        });

    }

    private void save(ActionEvent e) {

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home/me/Documents"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(frame.canvas.image, "PNG", new File("D:/test.png"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }

    @Override
    public void update(Graphics g) {
    } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    private void createOffscreenImage() {

        BufferedImage image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.cyan); //fill the image with white
        graphics.fillRect(W / 5, H / 6, W / 5 + W / 3, H / 6 + H / 3);
        repaint();

    }
}


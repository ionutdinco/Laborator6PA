package com.optional;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;

public class JColorComboBox extends JComboBox {

        static Hashtable<String, Color> colors;
        String color;

        public JColorComboBox() {
            super();
            DefaultComboBoxModel model = new DefaultComboBoxModel();
            Enumeration colorNames = addColors().keys();
            while(((Enumeration<?>) colorNames).hasMoreElements()){
                String temp = colorNames.nextElement().toString();
                model.addElement(temp);
                System.out.println("colors"+temp);
            }
            super.setModel(model);
            setRenderer(new ColorRenderer());
            this.setOpaque(true);
            this.setSelectedIndex(0);
            super.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    color = (String) model.getSelectedItem();

                }
            });
        }

        @Override
        public void setSelectedItem(Object anObject) {
            super.setSelectedItem(anObject);

            setBackground((Color)colors.get(anObject));
            //setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
            if(anObject.toString().equals("BLACK") || anObject.toString().equals("DARK_GRAY")){
                setForeground(Color.white);
            }
        }
        public Color getSelectedColor(){

            return this.getBackground();
        }

        private Hashtable addColors(){

            colors = new <String, Color>Hashtable();

            colors.put("WHITE", Color.WHITE);
            colors.put("BLUE", Color.BLUE);
            colors.put("GREEN", Color.GREEN);
            colors.put("YELLOW", Color.YELLOW);
            colors.put("ORANGE", Color.ORANGE);
            colors.put("CYAN", Color.CYAN);
            colors.put("DARK_GRAY", Color.DARK_GRAY);
            colors.put("GRAY", Color.GRAY);
            colors.put("RED", Color.RED);
            colors.put("PINK",Color.PINK);
            colors.put("MAGENTA", Color.MAGENTA);
            colors.put("BLACK", Color.BLACK);

            return colors;
        }

    public  Hashtable<String, Color> getColors() {
        return colors;
    }

    public String getColor() {
        return color;
    }
}

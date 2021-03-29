package com.optional;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

class ColorRenderer extends JLabel implements javax.swing.ListCellRenderer {
    static Hashtable<String, Color> colors;

    public ColorRenderer() {
        this.setOpaque(true);
    }

    public void setColors(Hashtable<String, Color> colors) {
        ColorRenderer.colors = colors;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object key, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        Color color = colors.get(key);;
        String name = key.toString();

        list.setSelectionBackground(null);
        list.setSelectionForeground(null);

        if(isSelected){
            setBorder(BorderFactory.createEtchedBorder());
        } else {
            setBorder(null);
        }
        super.setBackground(color);
        super.setText(name);
        super.setForeground(Color.black);
        if(name.equals("BLACK") || name.equals("DARK_GRAY")){
            super.setForeground(Color.white);
        }

        return this;
    }

}


package com.optional;

import java.awt.*;

public class RegularStar extends Polygon {
    public RegularStar() {
        int midX = 500;
        int midY = 340;
        int radius[] = {118, 40, 90, 40};
        int nPoints = 16;

        for (double current = 0.0; current < nPoints; current++) {
            int i = (int) current;
            double max=100;
            double x = Math.cos(current * ((2 * Math.PI) / max)) * radius[i % 4];
            double y = Math.sin(current * ((2 * Math.PI) / max)) * radius[i % 4];

            this.addPoint((int) x + midX,(int) y + midY);
        }
    }
}

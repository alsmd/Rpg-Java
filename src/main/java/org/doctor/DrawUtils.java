package org.doctor;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawUtils {


    public static int getMessageWidth(String message, Font font, Graphics2D g2){
        g2.setFont(font);
        Rectangle2D bounds = g2.getFontMetrics().getStringBounds(message, g2);
        return (int) bounds.getWidth();
    }

    public static int getMessageHeight(String message, Font font, Graphics2D g2){
        g2.setFont(font);
        if (message.length() == 0) return 0;
        TextLayout tl = new TextLayout(message, font, g2.getFontRenderContext());

        return (int) tl.getBounds().getHeight();
    }

    public static boolean isAsciiPrintable(char ch) {
        return ch >= 32 && ch < 127;
    }

    public static boolean isAsciiPrintable(String str) {
        for (int i = 0; i < str.length(); i++){
            if (!isAsciiPrintable(str.charAt(i)))
                return false;
        }
        return true;
    }

    public static boolean isAsciiPrintable(StringBuffer str) {
        for (int i = 0; i < str.length(); i++){
            if (!isAsciiPrintable(str.charAt(i)))
                return false;
        }
        return true;
    }

}

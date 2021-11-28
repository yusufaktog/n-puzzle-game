package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;


public class ButtonLoader {

    public static void loadPreferences(JButton button, Color fColor, Color bColor, Font font) {
        button.setForeground(fColor);
        button.setBackground(bColor);
        button.setFont(font);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public static void loadButton(JPanel parent, JButton button, String text, boolean visible, int x, int y, int width, int height) {
        button.setText(text);
        button.setBounds(x, y, width, height);
        button.setVisible(visible);
        parent.add(button);

    }

} 
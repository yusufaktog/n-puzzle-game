package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;

public class MyGameFrame extends JFrame {
    GamePanel panel;

    public MyGameFrame() {
        panel = new GamePanel();
        loadPreferences();

    }

    final void loadPreferences() {

        this.add(panel);
        this.setTitle("N-Puzzle game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyGameFrame().setVisible(true));
    }
}
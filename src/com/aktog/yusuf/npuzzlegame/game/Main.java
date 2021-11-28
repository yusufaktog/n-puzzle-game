package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyGameFrame().setVisible(true));
    }
}
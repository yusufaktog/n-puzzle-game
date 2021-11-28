package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

public class MyGameFrame extends JFrame {

    GamePanel gamePanel;
    JButton resetButton;
    JButton exitButton;

    public MyGameFrame() {
        newGame();
        loadPreferences();
    }

    final void loadPreferences() {

        this.setTitle("N-Puzzle game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void newGame() {
        gamePanel = new GamePanel();
        this.add(gamePanel);

        resetButton = new JButton();
        exitButton = new JButton();

        ButtonLoader.loadPreferences(resetButton,
                Color.blue,
                Color.red,
                new Font("Magneto Kalın", Font.PLAIN, 25));

        ButtonLoader.loadButton(gamePanel,
                resetButton,
                "RESTART",
                true,
                GamePanel.PANEL_WIDTH - 330,
                GamePanel.PANEL_HEIGHT - 50,
                190,
                50);

        resetButton.addActionListener(e -> restart());

        ButtonLoader.loadPreferences(exitButton,
                Color.blue,
                Color.red,
                new Font("Magneto Kalın", Font.PLAIN, 25));

        ButtonLoader.loadButton(gamePanel,
                exitButton,
                "EXIT",
                true,
                GamePanel.PANEL_WIDTH - 130,
                GamePanel.PANEL_HEIGHT - 50,
                120,
                50);

        exitButton.addActionListener(e -> System.exit(0));

    }

    public void restart() {
        this.remove(gamePanel);
        newGame();
        SwingUtilities.updateComponentTreeUI(this);
    }


}
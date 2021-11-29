package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

public class MyGameFrame extends JFrame {

    public static int SIZE;

    GamePanel gamePanel;
    JButton resetButton;
    JButton exitButton;
    JButton mainMenuButton;

    private final int difficulty;

    public MyGameFrame(int difficulty) {
        this.difficulty = difficulty;
        adjustSize(difficulty);
        newGame(difficulty);
        loadPreferences();
    }
    public static void adjustSize(int difficulty){
        SIZE = difficulty;
    }
    final void loadPreferences() {

        this.setTitle("N-Puzzle game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void newGame(int difficulty) {

        gamePanel = new GamePanel(difficulty);
        this.add(gamePanel);

        resetButton = new JButton();
        exitButton = new JButton();
        mainMenuButton = new JButton();


        loadInGameButton(resetButton, "RESTART", GamePanel.PANEL_WIDTH - 330, GamePanel.PANEL_HEIGHT - 50, 190, false);
        loadInGameButton(exitButton, "EXIT", GamePanel.PANEL_WIDTH - 130, GamePanel.PANEL_HEIGHT - 50, 120, false);
        loadInGameButton(mainMenuButton, "MAIN MENU", 0, GamePanel.PANEL_HEIGHT - 50, 200, true);

        resetButton.addActionListener(e -> restart());
        exitButton.addActionListener(e -> System.exit(0));
        mainMenuButton.addActionListener(e -> {
            this.dispose();
            new OptionsScreen().setVisible(true);
        });

    }

    public void restart() {
        this.remove(gamePanel);
        newGame(difficulty);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void loadInGameButton(JButton button, String text, int x, int y, int width, boolean isMain) {
        Font inGameButtonFont = isMain ? new Font("MV Boli", Font.PLAIN, 25) : new Font("Magneto Kalın", Font.PLAIN, 25);
        ButtonLoader.loadPreferences(button, Color.blue, Color.red, inGameButtonFont);
        ButtonLoader.loadButton(gamePanel, button, text, true, x, y, width, 50);

    }


}
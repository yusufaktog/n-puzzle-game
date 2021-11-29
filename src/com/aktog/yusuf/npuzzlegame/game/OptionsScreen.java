package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class OptionsScreen extends JFrame {
    JPanel panel;
    private final ButtonGroup difficultyButtons;
    JButton startButton;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JButton insaneButton;
    private JButton demonButton;

    public OptionsScreen() {
        difficultyButtons = new ButtonGroup();
        panel = new JPanel();
        this.add(panel);

        loadPanel();
        loadPreferences();

        instantiateAllButtons();
        loadAllButtons();

        ButtonLoader.loadButton(panel, startButton, "START", true, 250, 500, 310, 50);
        ButtonLoader.loadPreferences(startButton, Color.black, Color.CYAN, new Font("Ink Free", Font.BOLD, 25));

        startButton.addActionListener(e -> {
            try {
                initiateTheGame(getSelectedButton().getText());
            } catch (NullPointerException x) {
                JOptionPane.showMessageDialog(null,
                        "PLease choose one of the difficulties...",
                        "No difficulty selected!",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    final void loadPanel() {

        panel.setPreferredSize(new Dimension(800, 800));
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);
        panel.setLayout(null);
    }

    public void loadButton(JButton button, String text, int x, int y, int width, int height) {
        difficultyButtons.add(button);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        ButtonLoader.loadPreferences(button, Color.black, Color.yellow, new Font("Ink Free", Font.ITALIC, 25));
        ButtonLoader.loadButton(panel, button, text, true, x, y, width, height);

        button.addActionListener(e -> {
            button.setBorderPainted(true);
            button.setBackground(Color.red);
            repaintSelectedOldButtons(button);
        });

    }

    public int getSelectedButtonCount() {
        int count = 0;
        for (Iterator<AbstractButton> it = difficultyButtons.getElements().asIterator(); it.hasNext(); ) {

            AbstractButton button = it.next();
            if (button.getBackground() == Color.red)
                count++;
        }
        return count;
    }

    final void loadPreferences() {

        this.setTitle("Options Screen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public AbstractButton getSelectedButton() {
        for (Iterator<AbstractButton> it = difficultyButtons.getElements().asIterator(); it.hasNext(); ) {

            AbstractButton button = it.next();
            if (button.getBackground() == Color.red)
                return button;
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OptionsScreen().setVisible(true));
    }

    private void initiateTheGame(String choice) {
        switch (choice) {
            case "EASY":
                this.dispose();
                newGame(2);
                break;
            case "NORMAL":
                this.dispose();
                newGame(3);
                break;
            case "HARD":
                this.dispose();
                newGame(4);
                break;
            case "INSANE":
                this.dispose();
                newGame(5);
                break;
            case "DEMON":
                this.dispose();
                newGame(6);
                break;
        }
    }

    public void newGame(int difficulty) {
        SwingUtilities.invokeLater(() -> new MyGameFrame(difficulty).setVisible(true));
    }

    private void clearOldSelections(AbstractButton exceptSelected) {
        for (Iterator<AbstractButton> it = difficultyButtons.getElements().asIterator(); it.hasNext(); ) {

            AbstractButton button = it.next();
            if (!button.equals(exceptSelected))
                button.setBackground(Color.yellow);

        }
    }

    private void repaintSelectedOldButtons(AbstractButton button) {
        if (getSelectedButtonCount() > 1)
            clearOldSelections(button);
    }

    private void loadAllButtons() {
        loadButton(easyButton, "EASY", 250, 200, 150, 50);
        loadButton(normalButton, "NORMAL", 410, 200, 150, 50);
        loadButton(hardButton, "HARD", 250, 300, 150, 50);
        loadButton(insaneButton, "INSANE", 410, 300, 150, 50);
        loadButton(demonButton, "DEMON", 330, 400, 150, 50);
    }

    private void instantiateAllButtons() {
        easyButton = new JButton();
        normalButton = new JButton();
        hardButton = new JButton();
        insaneButton = new JButton();
        demonButton = new JButton();
        startButton = new JButton();

    }

}
package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    static int PANEL_WIDTH = 600;
    static int PANEL_HEIGHT = 600;
    static final int BOARD_SIZE = 3;

    private final GameBoard gameBoard;


    public GamePanel() {
        gameBoard = new GameBoard(BOARD_SIZE);
        loadPreferences();
    }

    final void loadPreferences() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH += Cell.WIDTH, PANEL_HEIGHT += Cell.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
        System.out.println("panel: " + this.getSize());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        drawGrid(g);
        paintCellValues(g);
        g.setColor(Color.red);

    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i <= gameBoard.getN(); i++) {
            g2.drawLine(Cell.WIDTH / 2 + i * Cell.WIDTH, Cell.HEIGHT / 2, Cell.WIDTH / 2 + i * Cell.WIDTH, PANEL_HEIGHT - Cell.HEIGHT / 2);
        }

        for (int i = 0; i <= gameBoard.getN(); i++) {
            g2.drawLine(Cell.WIDTH / 2, Cell.HEIGHT / 2 + Cell.HEIGHT * i, PANEL_WIDTH - Cell.WIDTH / 2, Cell.WIDTH / 2 + Cell.WIDTH * i);
        }

    }


    private void paintCellValues(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = Cell.WIDTH / 4;
        gameBoard.printBoard();
        g2.setFont(new Font("Ink Free", Font.BOLD, fontSize));
        for (Cell[] cells : gameBoard.getBoard()) {
            for (Cell cell : cells) {

                g2.setColor(Color.red);

                if (cell.isEmptyCell()) {
                    g2.setColor(new Color(255, 0, 0, 40));
                }
                g2.drawString(cell.getValue(), cell.getX() + Cell.WIDTH / 2 - fontSize / 3, cell.getY() + Cell.HEIGHT / 2);

            }
        }

    }
}
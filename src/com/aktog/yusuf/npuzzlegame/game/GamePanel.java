package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    static int PANEL_WIDTH = 600;
    static int PANEL_HEIGHT = 600;
    private final GameBoard gameBoard;

    static {
        PANEL_WIDTH += Cell.WIDTH;
        PANEL_HEIGHT += Cell.HEIGHT;
    }

    public GamePanel(int difficulty) {
        this.requestFocus();
        gameBoard = new GameBoard(difficulty);
        loadPreferences();
        addActionListeners();

    }

    final void loadPreferences() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        drawGrid(g);
        loadCells();
    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i <= gameBoard.getN(); i++) {
            g2.drawLine(Cell.WIDTH / 2 + i * Cell.WIDTH, Cell.HEIGHT / 2, Cell.WIDTH / 2 + i * Cell.WIDTH, PANEL_HEIGHT - Cell.HEIGHT / 2);
        }

        for (int i = 0; i <= gameBoard.getN(); i++) {
            g2.drawLine(Cell.WIDTH / 2, Cell.HEIGHT / 2 + Cell.HEIGHT * i, PANEL_WIDTH - Cell.WIDTH / 2, Cell.WIDTH / 2 + Cell.WIDTH * i);
        }
    }

    private void addActionListeners() {
        for (Cell[] cells : gameBoard.getBoard()) {
            for (Cell cell : cells) {
                cell.addActionListener(e -> {
                    if (moveCell(cell))
                        repaint();
                    if (checkWinCondition()){
                        //AnimationHandler.victoryAnimation(gameBoard.getBoard(),this);
                        JOptionPane.showMessageDialog(null, "WIN", "!Congrats", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        }
    }

    private void loadCells() {

        for (int i = 0; i < gameBoard.getN(); i++) {
            for (int j = 0; j < gameBoard.getN(); j++) {
                Cell cell = gameBoard.getBoard()[i][j];
                ButtonLoader.loadButton(this,
                        cell,
                        cell.getValue(),
                        !cell.isEmptyCell(),
                        cell.getX(),
                        cell.getY(),
                        Cell.WIDTH * 9 / 10,
                        Cell.HEIGHT * 9 / 10);
            }
        }
    }

    public void debug(Cell cell) {

        int x1 = (Cell.HEIGHT / 2 + cell.getY()) / Cell.HEIGHT - 1;
        int y1 = (cell.getX() + Cell.WIDTH / 2) / Cell.WIDTH - 1;

        System.out.println("[" + x1 + "][" + y1 + "]" + " with value " + gameBoard.getBoard()[x1][y1].getValue() + " ==? " +
                "cell at position " + "(" + cell.getX() + "," + cell.getY() + ")" + " with value " + cell.getValue());

    }

    public boolean moveCell(Cell cell) {

        if (!hasNeighbourEmptyCell(cell)) {
            System.out.println(cell.getValue() + " has not an neighbour empty cell");
            return false;
        }

        Cell emptyCell = determineEmptyCell();

        AnimationHandler.animateCell(cell, emptyCell, this);

        swapWith(cell);

        return true;
    }

    public Cell determineEmptyCell() {
        for (Cell[] cells : gameBoard.getBoard()) {
            for (Cell cell : cells) {
                if (cell.isEmptyCell())
                    return cell;
            }
        }
        return null;
    }

    public void swapWith(Cell clickedCell) {
        Cell emptyCell = determineEmptyCell();

        int x1 = (Cell.HEIGHT / 2 + clickedCell.getY()) / Cell.HEIGHT - 1;
        int y1 = (clickedCell.getX() + Cell.WIDTH / 2) / Cell.HEIGHT - 1;


        int x2 = (Cell.HEIGHT / 2 + emptyCell.getY()) / Cell.HEIGHT - 1;
        int y2 = (emptyCell.getX() + Cell.WIDTH / 2) / Cell.HEIGHT - 1;

        Cell temp = gameBoard.getBoard()[x1][y1];
        gameBoard.getBoard()[x1][y1] = gameBoard.getBoard()[x2][y2];
        gameBoard.getBoard()[x2][y2] = temp;

    }

    public boolean hasNeighbourEmptyCell(Cell cell) {

        int x1 = (Cell.HEIGHT / 2 + cell.getY()) / Cell.HEIGHT - 1;
        int y1 = (cell.getX() + Cell.WIDTH / 2) / Cell.WIDTH - 1;


        Cell[][] board = gameBoard.getBoard();

        try {
            if (board[x1][y1 + 1].isEmptyCell()) // cell below is empty cell
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            if (board[x1][y1 - 1].isEmptyCell()) // cell above is empty cell
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            if (board[x1 - 1][y1].isEmptyCell()) // left cell is empty cell
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            if (board[x1 + 1][y1].isEmptyCell()) // right below is empty cell
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        return false;
    }


    public boolean checkWinCondition() {
        StringBuilder currentPattern = new StringBuilder();
        for (Cell[] cells : gameBoard.getBoard()) {
            for (Cell cell : cells) {
                currentPattern.append(cell.getValue());
            }
        }
        return currentPattern.toString().equals(generateWinPattern());
    }

    private String generateWinPattern() {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < gameBoard.getN() * gameBoard.getN(); i++) {
            pattern.append(i + 1);
        }
        return pattern.toString();
    }

}
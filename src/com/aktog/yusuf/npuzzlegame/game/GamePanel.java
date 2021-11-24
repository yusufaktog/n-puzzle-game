package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

import static java.awt.Cursor.HAND_CURSOR;

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
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        drawGrid(g);
        paintCellValues(g);


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


    private void paintCellValues(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = Cell.WIDTH / 4;
        gameBoard.printBoard();
        Font buttonFont = new Font("Ink Free", Font.BOLD, fontSize);
        g2.setFont(buttonFont);

        for (int i = 0; i < gameBoard.getN(); i++) {
            for (int j = 0; j < gameBoard.getN(); j++) {
                Cell cell = gameBoard.getBoard()[i][j];

                this.add(cell);
                cell.setBounds(cell.getX(), cell.getY(), Cell.WIDTH * 9 / 10, Cell.HEIGHT * 9 / 10);
                cell.setBorderPainted(false);
                if (!cell.isEmptyCell()) {
                    cell.setForeground(cell.getfColor());
                    cell.setBackground(cell.getbColor());
                    cell.setVisible(true);
                    cell.setText(cell.getValue());
                    cell.setFont(buttonFont);
                    cell.setFocusPainted(false);
                    cell.setCursor(new Cursor(HAND_CURSOR));
                    cell.addActionListener(e -> {
                        if (e.getSource() == cell) {
                            System.out.println(cell.getX() + "," + cell.getY());
                            if (checkWinCondition())
                                JOptionPane.showMessageDialog(null, "WIN", "!Congrats", JOptionPane.INFORMATION_MESSAGE);
                            moveCell(cell);
                            repaint();
                        }

                    });
                }
            }
        }
    }

    public void debug(Cell clickedCell) {
        Cell emptyCell = determineEmptyCell();

        int x1 = (Cell.HEIGHT / 2 + clickedCell.getY()) / 200 - 1;
        int y1 = (clickedCell.getX() + Cell.WIDTH / 2) / 200 - 1;

        System.out.println(x1 + "," + y1);

        int x2 = (Cell.HEIGHT / 2 + emptyCell.getY()) / 200 - 1;
        int y2 = (emptyCell.getX() + Cell.WIDTH / 2) / 200 - 1;

        System.out.println(x2 + "," + y2);
    }

    public boolean moveCell(Cell cell) {
        if (!hasNeighbourEmptyCell(cell))
            return false;

        Cell emptyCell = determineEmptyCell();


        int prevPosX = cell.getX();
        int prevPosY = cell.getY();

        cell.setX(emptyCell.getX());
        cell.setY(emptyCell.getY());


        emptyCell.setX(prevPosX);
        emptyCell.setY(prevPosY);


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

        int x1 = (Cell.HEIGHT / 2 + clickedCell.getY()) / 200 - 1;
        int y1 = (clickedCell.getX() + Cell.WIDTH / 2) / 200 - 1;

        System.out.println(gameBoard.getBoard()[x1][y1].getValue() + "===?" + clickedCell.getValue());

        int x2 = (Cell.HEIGHT / 2 + emptyCell.getY()) / 200 - 1;
        int y2 = (emptyCell.getX() + Cell.WIDTH / 2) / 200 - 1;
        System.out.println(gameBoard.getBoard()[x2][y2].getValue() + "===?" + emptyCell.getValue());

        Cell temp = gameBoard.getBoard()[x1][y1];
        gameBoard.getBoard()[x1][y1] = gameBoard.getBoard()[x2][y2];
        gameBoard.getBoard()[x2][y2] = temp;

        System.out.println(gameBoard.getBoard()[x1][y1].getValue() + "is swapped by " + gameBoard.getBoard()[x2][y2].getValue());

    }

    public boolean hasNeighbourEmptyCell(Cell cell) {

        int x1 = (Cell.HEIGHT / 2 + cell.getY()) / 200 - 1;
        int y1 = (cell.getX() + Cell.WIDTH / 2) / 200 - 1;


        Cell[][] board = gameBoard.getBoard();
        System.out.println(board[x1][y1].getValue());
        try {
            // cell below is empty cell
            if (board[x1][y1 + 1].isEmptyCell())
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            // cell above is empty cell
            if (board[x1][y1 - 1].isEmptyCell())
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            // left cell is empty cell
            if (board[x1 - 1][y1].isEmptyCell())
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        try {
            // right below is empty cell
            if (board[x1 + 1][y1].isEmptyCell())
                return true;


        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
        return false;
    }

    private String generateWinPattern() {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < gameBoard.getN() * gameBoard.getN(); i++) {

            pattern.append(i + 1);

        }
        return pattern.toString();
    }

    public boolean checkWinCondition() {
        StringBuilder currentPattern = new StringBuilder();

        for (Cell[] cells : gameBoard.getBoard()) {
            for (Cell cell : cells) {
                currentPattern.append(cell.getValue());
            }
        }
        System.out.println(currentPattern + "<===>" + generateWinPattern());
        return currentPattern.toString().equals(generateWinPattern());
    }

}
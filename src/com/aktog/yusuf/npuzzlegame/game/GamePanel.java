package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicIconFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

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

        for (Cell[] cells : gameBoard.getBoard()) {
            for (Cell cell : cells) {
                this.add(cell);
                cell.setBackground(Color.blue);


                cell.setBorder(BorderFactory.createRaisedBevelBorder());

                cell.setBounds(cell.getX(), cell.getY(), Cell.WIDTH * 9 / 10, Cell.HEIGHT * 9 / 10);
                cell.setVisible(true);
                cell.setText(cell.getValue());
                cell.setFont(buttonFont);
                cell.setFocusPainted(false);

                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("action fired");
                    }
                });


/*                if (cell.isEmptyCell())
                    g2.setColor(new Color(255, 0, 0, 40));
                else
                    g2.setColor(Color.red);*/
/*                g2.setColor(new Color(200,200,150,200));
                g2.fillRoundRect(cell.getX()  ,cell.getY() ,Cell.WIDTH*9/10,Cell.HEIGHT*9/10,30,30);


                g2.drawString(cell.getValue(), cell.getX() + Cell.WIDTH / 2 - fontSize / 2, cell.getY() + Cell.HEIGHT / 2);*/


            }
        }

    }
}
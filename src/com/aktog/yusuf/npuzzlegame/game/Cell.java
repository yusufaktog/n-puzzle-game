package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    public static int WIDTH = GamePanel.PANEL_WIDTH / MyGameFrame.BOARD_SIZE;
    public static int HEIGHT = GamePanel.PANEL_HEIGHT / MyGameFrame.BOARD_SIZE;

    private int x, y;
    private final boolean emptyCell;
    private final String value;


    public Cell(int x, int y, String value, Boolean emptyCell) {
        super();
        this.x = x;
        this.y = y;
        this.value = value;
        this.emptyCell = emptyCell;
        initCell();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public boolean isEmptyCell() {
        return emptyCell;
    }

    private void initCell() {
        ButtonLoader.loadPreferences(this,
                Color.red,
                Color.blue,
                new Font("Ink Free", Font.BOLD, Cell.WIDTH / 4));
    }


}
package com.aktog.yusuf.npuzzlegame.game;

public class Cell {
    public static final int WIDTH = GamePanel.PANEL_WIDTH  / GamePanel.BOARD_SIZE  ;
    public static final int HEIGHT = GamePanel.PANEL_HEIGHT / GamePanel.BOARD_SIZE;

    private int x,y;
    private boolean emptyCell;
    private String value;

    public Cell(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
    public Cell(int x, int y, String value,Boolean isEmptyCell) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.emptyCell = isEmptyCell;
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

    public void setValue(String value) {
        this.value = value;
    }
    public boolean isEmptyCell() {
        return emptyCell;
    }

    public void setEmpty(boolean emptyCell) {
        this.emptyCell = emptyCell;
    }
}
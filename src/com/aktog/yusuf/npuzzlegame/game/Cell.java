package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

public class Cell extends JButton {

    public static final int WIDTH = GamePanel.PANEL_WIDTH / GamePanel.BOARD_SIZE;
    public static final int HEIGHT = GamePanel.PANEL_HEIGHT / GamePanel.BOARD_SIZE;

    private int x, y;
    private boolean emptyCell;
    private String value;
    private Color fColor;
    private Color bColor;
    private int alpha;

    public Cell(int x, int y, String value, Boolean isEmptyCell) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.emptyCell = isEmptyCell;
        alpha = 255;
        fColor = new Color(255, 0, 0, alpha);
        bColor = new Color(0, 0, 255, alpha);
    }

    public void setEmptyCell(boolean emptyCell) {
        this.emptyCell = emptyCell;
    }

    public void blurCell(){
        if(emptyCell){
            this.setEnabled(false);
            this.setBackground(Color.black);
            this.setForeground(Color.black);
        }
    }
    public Color getfColor() {
        return fColor;
    }

    public void setfColor(Color fColor) {
        this.fColor = fColor;
    }

    public Color getbColor() {
        return bColor;
    }

    public void setbColor(Color bColor) {
        this.bColor = bColor;
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

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
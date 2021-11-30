package com.aktog.yusuf.npuzzlegame.game;

import javax.swing.*;
import java.awt.*;

public class AnimationHandler {
    public static final int animationSpeed = 1;
    public static int fps = Cell.WIDTH;

    public static void animateCell(Cell cell, Cell emptyCell, JPanel panel) {
        String direction = detectMovementDirection(cell, emptyCell);

        int prevPosX = cell.getX();
        int prevPosY = cell.getY();

        switch (direction) {
            case "LEFT":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cell.setX(cell.getX() - 1);
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
            case "RIGHT":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cell.setX(cell.getX() + Cell.WIDTH / fps);
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
            case "UP":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        cell.setY(cell.getY() - Cell.HEIGHT / fps);
                        panel.repaint();
                    }
                }).start();
                break;
            case "DOWN":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cell.setY(cell.getY() + Cell.HEIGHT / fps);
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
        }
    }

    private static void moveEmptyCell(Cell emptyCell, int prevPosX, int prevPosY) {
        emptyCell.setX(prevPosX);
        emptyCell.setY(prevPosY);
    }

    public static String detectMovementDirection(Cell cell, Cell emptyCell) {

        if (cell.getX() > emptyCell.getX() && cell.getY() == emptyCell.getY())
            return "LEFT";

        if (cell.getX() < emptyCell.getX() && cell.getY() == emptyCell.getY())
            return "RIGHT";

        if (cell.getY() > emptyCell.getY() && cell.getX() == emptyCell.getX())
            return "UP";

        if (cell.getY() < emptyCell.getY() && cell.getX() == emptyCell.getX())
            return "DOWN";

        return "UNKNOWN";
    }

}
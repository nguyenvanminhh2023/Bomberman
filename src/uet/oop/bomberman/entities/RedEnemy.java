/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

/**
 *
 * @author Tram Tram
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RedEnemy extends MoveEntity {
    public RedEnemy(int x, int y, int orient, int speed) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        this.speed = speed;
        this.runBomb = Bomber.cant_run;
        this.img = new ImageIcon(getClass().getResource("/sprites1/red_enemy_down.png")).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null) - 23;
    }

    public void showRedEnemy(Graphics2D graphic2d) {
        graphic2d.drawImage(img, x, y - 23, null);
    }

    @Override
    public void changeOrient(int orient) {
        super.changeOrient(orient);
        switch (orient) {
            case LEFT:
                img = new ImageIcon(getClass().getResource("/sprites1/red_enemy_left.png")).getImage();
                break;
            case RIGHT:
                img = new ImageIcon(getClass().getResource("/sprites1/red_enemy_right.png")).getImage();
                break;
            case UP:
                img = new ImageIcon(getClass().getResource("/sprites1/red_enemy_up.png")).getImage();
                break;
            case DOWN:
                img = new ImageIcon(getClass().getResource("/sprites1/red_enemy_down.png")).getImage();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean move(int count, ArrayList<Wall> arrWall, ArrayList<Brick> arrBrick, ArrayList<Bomb> arrBomb) {
        return super.move(count, arrWall, arrBrick, arrBomb);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package uet.oop.bomberman.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Tram Tram
 */
public class FlameBomb {
    private int x, y, size, time;
    private Image left, right, up, down;

    public FlameBomb(int x, int y, int size, ArrayList<Wall> arrWall, ArrayList<Brick> arrBrick) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.time = 250;
        left = new ImageIcon(getClass().getResource("/sprites1/bombflame_left_" + Integer.toString(size) + ".png")).getImage();
        right = new ImageIcon(getClass().getResource("/sprites1/bombflame_right_" + Integer.toString(size) + ".png")).getImage();
        up = new ImageIcon(getClass().getResource("/sprites1/bombflame_up_" + Integer.toString(size) + ".png")).getImage();
        down = new ImageIcon(getClass().getResource("/sprites1/bombflame_down_" + Integer.toString(size) +".png")).getImage();
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < arrBrick.size(); j++) {
                if (impactBrick(x - i * 45, y, (i + 1) * 45, 45, arrBrick.get(j))) {
                    int t = (x - arrBrick.get(j).getX()) / 45;
                    left = new ImageIcon(getClass().getResource("/sprites1/bombflame_left_" + Integer.toString(Math.abs(t)) + ".png")).getImage();
                }
                if (impactBrick(x, y - i * 45, 45, (i + 1) * 45, arrBrick.get(j))) {
                    int t = (y - arrBrick.get(j).getY()) / 45;
                    up = new ImageIcon(getClass().getResource("/sprites1/bombflame_up_" + Integer.toString(Math.abs(t)) + ".png")).getImage();
                }
            }
            for (int j = arrBrick.size() - 1; j >= 0; j--) {
                if (impactBrick(x, y, (i + 1) * 45, 45, arrBrick.get(j))) {
                    int t = (x - arrBrick.get(j).getX()) / 45;
                    right = new ImageIcon(getClass().getResource("/sprites1/bombflame_right_" + Integer.toString(Math.abs(t)) + ".png")).getImage();
                }
                if (impactBrick(x, y, 45, (i + 1) * 45, arrBrick.get(j))) {
                    int t = (y - arrBrick.get(j).getY()) / 45;
                    down = new ImageIcon(getClass().getResource("/sprites1/bombflame_down_" + Integer.toString(Math.abs(t)) + ".png")).getImage();
                }
            }
            for (int j = 0; j < arrWall.size(); j++) {
                if (impactWall(x - i * 45, y, (i + 1) * 45, 45, arrWall.get(j))) {
                    int t = (x - arrWall.get(j).getX()) / 45;
                    if (Math.abs(t) > 1) left = new ImageIcon(getClass().getResource("/sprites1/bombflame_left_" + Integer.toString(Math.abs(t) - 1) + ".png")).getImage();
                    else left = new ImageIcon(getClass().getResource("/sprites1/bombflame_left_1.png")).getImage();
                }                
                if (impactWall(x, y - i * 45, 45, (i + 1) * 45, arrWall.get(j))) {
                    int t = (y - arrWall.get(j).getY()) / 45;
                    if (Math.abs(t) > 1) up = new ImageIcon(getClass().getResource("/sprites1/bombflame_up_" + Integer.toString(Math.abs(t) - 1) + ".png")).getImage();
                    else up = new ImageIcon(getClass().getResource("/sprites1/bombflame_up_1.png")).getImage();
                }
            }
            for (int j = arrWall.size() - 1; j >= 0; j--) {
                if (impactWall(x, y, (i + 1) * 45, 45, arrWall.get(j))) {
                    int t = (x - arrWall.get(j).getX()) / 45;
                    if (Math.abs(t) > 1) right = new ImageIcon(getClass().getResource("/sprites1/bombflame_right_" + Integer.toString(Math.abs(t) - 1) + ".png")).getImage();
                    else right = new ImageIcon(getClass().getResource("/sprites1/bombflame_right_1.png")).getImage();
                }
                if (impactWall(x, y, 45, (i + 1) * 45, arrWall.get(j))) {
                    int t = (y - arrWall.get(j).getY()) / 45;
                    if (Math.abs(t) > 1) down = new ImageIcon(getClass().getResource("/sprites1/bombflame_down_" + Integer.toString(Math.abs(t) - 1) + ".png")).getImage();
                    else down = new ImageIcon(getClass().getResource("/sprites1/bombflame_down_1.png")).getImage();
                }
            }
        }
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void showFlameBomb(Graphics2D graphics2d) {
        graphics2d.drawImage(left, x + 45 - left.getWidth(null), y, null);
        graphics2d.drawImage(right, x, y, null);
        graphics2d.drawImage(up, x, y + 45 - up.getHeight(null), null);
        graphics2d.drawImage(down, x, y, null);
    }

    public boolean impactWall(int x, int y, int width, int height, Wall wall) {
        Rectangle flameRect = new Rectangle(x, y, width, height);
        Rectangle wallRect = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
        return flameRect.intersects(wallRect);
    }

    public boolean impactBrick(int x, int y, int width, int height, Brick brick) {
        Rectangle flameRect = new Rectangle(x, y, width, height);
        Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        return flameRect.intersects(brickRect);
    }

    public boolean flameImpactMoveEntity(MoveEntity moveEntity) {
        Rectangle leftRect = new Rectangle(x + 45 - left.getWidth(null) + 5, y + 5, left.getWidth(null) - 5, left.getHeight(null) - 10);
        Rectangle rightRect = new Rectangle(x, y + 5, right.getWidth(null) - 5, right.getHeight(null) - 10);
        Rectangle upRect = new Rectangle(x + 5, y + 45 - up.getHeight(null) + 5, up.getWidth(null) - 5, up.getHeight(null) - 10);
        Rectangle downRect = new Rectangle(x + 5, y, down.getWidth(null) - 10, down.getHeight(null) - 5);
        Rectangle moveEntityRect = new Rectangle(moveEntity.getX(), moveEntity.getY(), moveEntity.getWidth(), moveEntity.getHeight());
        if (leftRect.intersects(moveEntityRect) || rightRect.intersects(moveEntityRect) || upRect.intersects(moveEntityRect) || downRect.intersects(moveEntityRect)) {
            return true;
        }
        return false;
    }

    public boolean flameImpactWall(Wall wall) {
        return false;
    }

    public boolean flameImpactBrick(Brick brick) {
        Rectangle leftRect = new Rectangle(x + 45 - left.getWidth(null), y, left.getWidth(null), left.getHeight(null));
        Rectangle rightRect = new Rectangle(x, y, right.getWidth(null), right.getHeight(null));
        Rectangle upRect = new Rectangle(x, y + 45 - up.getHeight(null), up.getWidth(null), up.getHeight(null));
        Rectangle downRect = new Rectangle(x, y, down.getWidth(null), down.getHeight(null));
        Rectangle brickRect = new Rectangle(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
        if(leftRect.intersects(brickRect) || rightRect.intersects(brickRect) || upRect.intersects(brickRect) || downRect.intersects(brickRect)){
            return true;
        }
        return false;
    }

    public boolean flameImpactBomb(Bomb bomb) {
        Rectangle leftRect = new Rectangle(x + 45 - left.getWidth(null), y, left.getWidth(null), left.getHeight(null));
        Rectangle rightRect = new Rectangle(x, y, right.getWidth(null), right.getHeight(null));
        Rectangle upRect = new Rectangle(x, y + 45 - up.getHeight(null), up.getWidth(null), up.getHeight(null));
        Rectangle downRect = new Rectangle(x, y, down.getWidth(null), down.getHeight(null));
        Rectangle bombRect = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
        if(leftRect.intersects(bombRect) || rightRect.intersects(bombRect) || upRect.intersects(bombRect) || downRect.intersects(bombRect)) {
            return true;
        }
        return false;
    }

    public boolean flameImpactItem(Item item) {
        Rectangle leftRect = new Rectangle(x + 45 - left.getWidth(null), y, left.getWidth(null), left.getHeight(null));
        Rectangle rightRect = new Rectangle(x, y, right.getWidth(null), right.getHeight(null));
        Rectangle upRect = new Rectangle(x, y + 45 - up.getHeight(null), up.getWidth(null), up.getHeight(null));
        Rectangle downRect = new Rectangle(x, y, down.getWidth(null), down.getHeight(null));
        Rectangle itemRect = new Rectangle(item.getX(), item.getY(), item.getWidth(), item.getHeight());
        if (leftRect.intersects(itemRect) || rightRect.intersects(itemRect) || upRect.intersects(itemRect) || downRect.intersects(itemRect)) {
            if (item.getTime() > 0) {
                item.setTime(item.getTime() - 1);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public void runTimeBomb() {
        if (time > 0) {
            time--;
        }
    }

    public int getTime() {
        return time;
    }
}
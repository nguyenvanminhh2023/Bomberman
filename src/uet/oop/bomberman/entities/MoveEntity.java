/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Tram Tram
 */
public class MoveEntity {
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;
    public static final int LIVE = 1;
    public static final int DIE = 0;
    public static final int BOMBER = 1;
    public static final int BOMB = 2;
    public static final int ENEMY = 3;
    
    protected int x, y;
    protected int type, orient, speed, width, height, runBomb;
    public Image img;
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getOrient() {
        return orient;
    }
    
    public void changeOrient(int orient) {
        this.orient = orient;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getType() {
        return type;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if(speed < 2) {
            return;
        }
        this.speed = speed;
    }
    
    public int getRunBomb() {
        return runBomb;
    }
    
    public void setRunBomb(int runBomb) {
        this.runBomb = runBomb;
    }
    
    public void showEntity(Graphics2D graphics2d) {
        switch(type) {
            case BOMBER:
                graphics2d.drawImage(img, x, y - 20, null);
                break;
            case BOMB:
                graphics2d.drawImage(img, x, y, null);
                break;
            case ENEMY:
                graphics2d.drawImage(img, x, y - 23, null);
                break;
            default:
                break;
        }
    }
    
    public boolean move(int count, ArrayList<Wall> arrWall, ArrayList<Brick> arrBrick, ArrayList<Bomb> arrBomb) {
        if (count % speed != 0) {
            return true;
        }
        switch(orient) {
            case LEFT:
                if (x <= 0) {
                    return false;
                }
                x = x - 1;
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).bombImpactMoveEntity(this) == 1) {
                        x = x + 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrWall.size(); i++) {
                    int t = arrWall.get(i).wallImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x + 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrBrick.size(); i++) {
                    int t = arrBrick.get(i).brickImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x + 1;
                        return false;
                    }
                }
                break;
            case RIGHT:
                if (x > 1367 - width) {
                    return false;
                }
                x = x + 1;
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).bombImpactMoveEntity(this) == 1) {
                        x = x - 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrWall.size(); i++) {
                    int t = arrWall.get(i).wallImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x - 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrBrick.size(); i++) {
                    int t = arrBrick.get(i).brickImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                y = y + 1;
                            } else {
                                y = y - 1;
                            }
                        }
                        x = x - 1;
                        return false;
                    }
                }
                break;
            case UP:
                if (y <= 0) {
                    return false;
                }
                y = y - 1;
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).bombImpactMoveEntity(this) == 1) {
                        y = y + 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrWall.size(); i++) {
                    int t = arrWall.get(i).wallImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y + 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrBrick.size(); i++) {
                    int t = arrBrick.get(i).brickImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y + 1;
                        return false;
                    }
                }
                break;
            case DOWN:
                if (y >= 587 - height) {
                    return false;
                }
                y = y + 1;
                for (int i = 0; i < arrBomb.size(); i++) {
                    if (arrBomb.get(i).bombImpactMoveEntity(this) == 1) {
                        y = y - 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrWall.size(); i++) {
                    int t = arrWall.get(i).wallImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y - 1;
                        return false;
                    }
                }
                for (int i = 0; i < arrBrick.size(); i++) {
                    int t = arrBrick.get(i).brickImpactMoveEntity(this);
                    if (t != 0) {
                        if (t >= -20 && t < 20) {
                            if (t > 0) {
                                x = x + 1;
                            } else {
                                x = x - 1;
                            }
                        }
                        y = y - 1;
                        return false;
                    }
                }
                break;
            default:
                break;
        }
        return true;
    }
}

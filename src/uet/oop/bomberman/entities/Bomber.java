/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Tram Tram
 */
public class Bomber extends MoveEntity {
    public static int can_run = 0;
    public static int cant_run = 1;
    protected int status, sizeOfBomb, quantityOfBomb, score, life;
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getType() {
        return type;
    }
    
    public void setImg(Image img) {
        this.img = img;
    }
    
    public int getQuantityOfBomb() {
        return quantityOfBomb;
    }
    
    public void setQuantityOfBomb(int quantityOfBomb) {
        this.quantityOfBomb = quantityOfBomb;
    }
    
    public int getSizeOfBomb() {
        return sizeOfBomb;
    }
    
    public void setSizeOfBomb(int sizeOfBomb) {
        this.sizeOfBomb = sizeOfBomb;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


    
    public Bomber(int x, int y, int type, int orient, int speed, int sizeOfBomb, int quantityOfBomb) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.orient = orient;
        this.speed = speed;
        this.sizeOfBomb = sizeOfBomb;
        this.quantityOfBomb = quantityOfBomb;
        this.status = LIVE;
        this.life = 3;
        this.score = 0;
        this.img = new ImageIcon(getClass().getResource("/sprites1/bomber_down.png")).getImage();
        width = img.getWidth(null);
        height = img.getHeight(null) - 20;
    }
    
    public void setNew(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = LIVE;
        this.img = new ImageIcon(getClass().getResource("/sprites1/bomber_down.png")).getImage();
    }
    
    @Override
    public void changeOrient(int orient) {
        if (status == DIE) {
            return;
        }
        super.changeOrient(orient);
        switch(orient) {
            case LEFT:
                img = new ImageIcon(getClass().getResource("/sprites1/bomber_left.png")).getImage();
                break;
            case RIGHT:
                img = new ImageIcon(getClass().getResource("/sprites1/bomber_right.png")).getImage();
                break;
            case UP:
                img = new ImageIcon(getClass().getResource("/sprites1/bomber_up.png")).getImage();
                break;
            case DOWN:
                img = new ImageIcon(getClass().getResource("/sprites1/bomber_down.png")).getImage();
                break;
            default:
                break;
        }
    }
    
    @Override
    public boolean move(int count, ArrayList<Wall> arrWall, ArrayList<Brick> arrBrick, ArrayList<Bomb> arrBomb) {
        if (status == DIE) {
            return false;
        }
        return super.move(count, arrWall, arrBrick, arrBomb);
    }
    
    public boolean bomberImpactMoveEntity(MoveEntity moveEntity) {
        if (status == DIE) {
            return false;
        }
        Rectangle bomberRect = new Rectangle(x, y, width, height);
        Rectangle moveEntityRect = new Rectangle(moveEntity.getX(), moveEntity.getY(), moveEntity.getWidth(), moveEntity.getHeight());
        return bomberRect.intersects(moveEntityRect);
    }
}

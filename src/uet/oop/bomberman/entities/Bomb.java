/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Tram Tram
 */
public class Bomb extends MoveEntity {
    protected int size, time_explode;
    
    public Bomb(int x, int y, int size, int time_explode) {
        x = (x / 45) * 45;
        y = (y / 45) * 45;
        this.x = x;
        this.y = y;
        this.size = size;
        this.time_explode = time_explode;
        this.orient = 0;
        this.type = MoveEntity.BOMB;
        img = new ImageIcon(getClass().getResource("/sprites1/bomb.png")).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    
    public void toExplode() {
        this.time_explode--;
    }
    
    public int getTimeExplode() {
        return time_explode;
    }
    
    public void setTimeExplode(int time_explode) {
        this.time_explode = time_explode;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean setRun(Bomber bomber) {
        Rectangle bomberRect = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight());
        Rectangle bombRect = new Rectangle(x, y, 45, 45);
        return bombRect.intersects(bomberRect);
    }
    
    public boolean impactOtherBomb(int xOther, int yOther) {
        Rectangle rect1 = new Rectangle(x, y, 45, 45);
        Rectangle rect2 = new Rectangle(xOther, yOther, 45, 45);
        return rect1.intersects(rect2);
    }
    
    public int bombImpactMoveEntity(MoveEntity moveEntity) {
        if (moveEntity.getRunBomb() == Bomber.can_run) {
            return 0;
        }
        Rectangle bombRect = new Rectangle(x, y, 45, 45);
        Rectangle moveEntityRect = new Rectangle(moveEntity.getX(), moveEntity.getY(), moveEntity.getWidth(), moveEntity.getHeight());
        if (bombRect.intersects(moveEntityRect)) {
            return 1;
        }
        return 0;
    }
}

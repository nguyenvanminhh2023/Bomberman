/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Tram Tram
 */
public class Item {
    protected int x, y, width, height, time;
    public Image img;
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getTime() {
        return time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public void showItem(Graphics2D graphics2d) {
        graphics2d.drawImage(img, x, y, null);
    }
    
    public boolean itemImpactBomber(Bomber bomber) {
        Rectangle itemRect = new Rectangle(x, y, width, height);
        Rectangle bomberRect = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight());
        return itemRect.intersects(bomberRect);
    }
}

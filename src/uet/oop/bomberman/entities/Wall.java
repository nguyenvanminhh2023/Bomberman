/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 *
 * @author Tram Tram
 */
public class Wall {
    private int x, y, width, height;
    private Image img;
    
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
    
    public Wall(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.img = new ImageIcon(getClass().getResource("/sprites1/wall.png")).getImage();
        this.width = 45;
        this.height = 45;
    }
    
    public void showWall(Graphics2D graphics2d) {
        graphics2d.drawImage(img, x, y, null);
    }
    
    public int wallImpactMoveEntity(MoveEntity moveEntity) {
        Rectangle wallRect = new Rectangle(x, y, width, height);
        Rectangle moveEntityRect = new Rectangle(moveEntity.getX(), moveEntity.getY(), moveEntity.getWidth(), moveEntity.getHeight());
        Rectangle rect = new Rectangle();
        if (wallRect.intersects(moveEntityRect)) {
            wallRect.intersect(wallRect, moveEntityRect, rect);
            if (rect.getHeight() == 1 && (moveEntity.getOrient() == moveEntity.UP || moveEntity.getOrient() == moveEntity.DOWN)) {
                if (moveEntity.getX() == rect.getX()) {
                    return (int)rect.getWidth();
                } else {
                    return (int)-rect.getWidth();
                }
            } else {
                if (moveEntity.getY() == rect.getY()) {
                    return (int)rect.getHeight();
                } else {
                    return (int)-rect.getHeight();
                }
            }
        }
        return 0;
    }
}

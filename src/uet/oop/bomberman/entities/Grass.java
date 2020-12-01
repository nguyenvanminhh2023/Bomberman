/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Tram Tram
 */
public class Grass {
    private int x, y, width, height, type;
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
    
    public Grass(int x, int y) {
        //super();
        this.x = x;
        this.y = y;
        this.img = new ImageIcon(getClass().getResource("/sprites1/grass.png")).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }
    
    public void showGrass(Graphics2D graphic2d) {
        graphic2d.drawImage(img, x, y, null);
    }
}

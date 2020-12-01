/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import javax.swing.ImageIcon;

/**
 *
 * @author Tram Tram
 */
public class FlameItem extends Item {
    public FlameItem(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.img = new ImageIcon(getClass().getResource("/sprites1/item_flame.png")).getImage();
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.time = 250;
    }
}

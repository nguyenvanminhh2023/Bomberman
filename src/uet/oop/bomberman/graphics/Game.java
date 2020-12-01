/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.graphics;

import javax.swing.JFrame;
import java.awt.CardLayout;

/**
 *
 * @author Tram Tram
 */
public class Game extends JFrame {
    public static final int WIDTHJF = 1412;
    public static final int HEIGHTJF = 632;
    private Screen screen;
    
    public Game() {
        setSize(WIDTHJF, HEIGHTJF);
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen = new Screen(this);
	add(screen);
    }
}

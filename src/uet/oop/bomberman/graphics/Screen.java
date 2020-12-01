/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.graphics;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Tram Tram
 */
public class Screen extends JPanel {
    private CardLayout mCardLayout;
    private Game game;
    private PlayGame playGame;
    
    public Screen(Game game) {
        this.game = game;
        mCardLayout = new CardLayout();
        setLayout(mCardLayout);
        playGame = new PlayGame(this);
        add(playGame);
        //Sound.getIstance().getAudio(Sound.PLAYGAME).loop();
    }
    
    public Game getGame() {
        return game;
    }
}

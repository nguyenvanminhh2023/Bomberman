/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import uet.oop.bomberman.entities.ManageEntity;
import uet.oop.bomberman.entities.Bomber;
import sound.Sound;

/**
 *
 * @author Tram Tram
 */
public class PlayGame extends JPanel implements Runnable, ActionListener {
    private Screen screen;
    private ManageEntity manageEntity = new ManageEntity();
    private BitSet traceKey = new BitSet();
    public static boolean running = true;
    private int count = 0;
    private int timeDie = 0;
    private int timeLose = 0;
    private int timeWin = 0;

    public PlayGame(Screen screen) {
        this.screen = screen;
        setBackground(Color.WHITE);
        setLayout(null);
        setFocusable(true);
        addKeyListener(keyAdapter);
        Thread mytheard = new Thread(this);
        mytheard.start();
    }

    //private void initCompts() {
        
    //}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setStroke(new java.awt.BasicStroke(2));
	graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        manageEntity.showAllGrass(graphics2d);
        manageEntity.showAllItem(graphics2d);
        manageEntity.showAllBomb(graphics2d);
        manageEntity.showAllBrick(graphics2d);
        manageEntity.showAllWall(graphics2d);
        manageEntity.showAllEnemy(graphics2d);
        manageEntity.showAllRedEnemy(graphics2d);
        manageEntity.showResult(graphics2d, manageEntity.getResult());

        //for (int i = 0; i < mManager.getArrBomb().size(); i++) mManager.getArrBomb().get(i).showEntity(graphics2d);
        manageEntity.getBomber().showEntity(graphics2d);
    }

    private KeyAdapter keyAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            traceKey.set(e.getKeyCode());
        }
        
        @Override
        public void keyReleased(KeyEvent e) {
            traceKey.clear(e.getKeyCode());
        }
    };
    
    
    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        while (running) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (traceKey.get(KeyEvent.VK_LEFT)) {
                manageEntity.getBomber().changeOrient(Bomber.LEFT);
                manageEntity.getBomber().move(count, manageEntity.getArrWall(), manageEntity.getArrBrick(), manageEntity.getArrBomb());
            }
            
            if (traceKey.get(KeyEvent.VK_RIGHT)) {
                manageEntity.getBomber().changeOrient(Bomber.RIGHT);
                manageEntity.getBomber().move(count, manageEntity.getArrWall(), manageEntity.getArrBrick(), manageEntity.getArrBomb());
            }
            
            if (traceKey.get(KeyEvent.VK_UP)) {
                manageEntity.getBomber().changeOrient(Bomber.UP);
                manageEntity.getBomber().move(count, manageEntity.getArrWall(), manageEntity.getArrBrick(), manageEntity.getArrBomb());
            }
            
            if (traceKey.get(KeyEvent.VK_DOWN)) {
                manageEntity.getBomber().changeOrient(Bomber.DOWN);
                manageEntity.getBomber().move(count, manageEntity.getArrWall(), manageEntity.getArrBrick(), manageEntity.getArrBomb());
            }
            
            if (traceKey.get(KeyEvent.VK_SPACE)) {
                manageEntity.initBomb();
                manageEntity.getBomber().setRunBomb(Bomber.can_run);
            }
            manageEntity.bomberEatItem();
            manageEntity.setRunBomber();
            manageEntity.explodedAllBomb();
            manageEntity.checkBomberDie();
            manageEntity.setResult();


            if(manageEntity.getBomber().getStatus() == Bomber.DIE) {
                timeDie++;
                if(timeDie == 3000){
                    manageEntity.setNewBomber();
                    timeDie = 0;
                }
            }
            if(manageEntity.getResult() == 1) {
                //timeLose++;
                //if(timeLose == 000) {
                    running = false;
                //}
            }

            if(manageEntity.getResult() == 2){
                timeWin++;
                if(timeWin == 800){
                    running = false;
                    //screen.setVisible(false);
                }
            }
            manageEntity.moveAllEnemy(count);
            manageEntity.moveAllRedEnemy(count);
            repaint();
            count++;
            if (count == 1000000) {
                count = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

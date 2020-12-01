/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.entities;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import sound.Sound;

/**
 *
 * @author Tram Tram
 */
public class ManageEntity {
    private Bomber bomber;
    private ArrayList<Brick> arrBrick;
    private ArrayList<Wall> arrWall;
    private ArrayList<Grass> arrGrass;
    private ArrayList<BombItem> arrBombItem;
    private ArrayList<FlameItem> arrFlameItem;
    private ArrayList<SpeedItem> arrSpeedItem;
    private ArrayList<Bomb> arrBomb;
    private ArrayList<FlameBomb> arrFlameBomb;
    private ArrayList<Enemy> arrEnemy;
    private ArrayList<RedEnemy> arrRedEnemy;
    private Random random = new Random();
    private int result = 0;
    
    public Bomber getBomber() {
        return bomber;
    }
    
    public ArrayList<Brick> getArrBrick() {
        return arrBrick;
    }
    
    public ArrayList<Wall> getArrWall() {
        return arrWall;
    }
    
    public ArrayList<Bomb> getArrBomb() {
        return arrBomb;
    }
    public int getResult() {
        return result;
    }

    public void setNewBomber(){
        bomber.setNew(45, 45);
    }
    
    public ManageEntity() {
        init();
    }

    private void init() {
        Sound.getIstance().getAudio(Sound.PLAYGAME).loop();
        bomber = new Bomber(45, 45, MoveEntity.BOMBER, MoveEntity.DOWN, 5, 1, 1);
        try {
            FileReader file;
            file = new FileReader("src/Level1.txt");
            BufferedReader input = new BufferedReader(file);
            String info = input.readLine();
            String str[] = info.split(" ");
            int level = Integer.parseInt(str[0]);
            int row = Integer.parseInt(str[1]);
            int col = Integer.parseInt(str[2]);
            char map[][] = new char[row][col];
            for (int i = 0; i < row; i++) {
                String column = input.readLine();
                for (int j = 0; j < col; j++) {
                    map[i][j] = column.charAt(j);
                }
            }
            arrWall = new ArrayList<Wall>();
            arrBrick = new ArrayList<Brick>();
            arrBombItem = new ArrayList<BombItem>();
            arrFlameItem = new ArrayList<FlameItem>();
            arrSpeedItem = new ArrayList<SpeedItem>();
            arrEnemy = new ArrayList<Enemy>();
            arrBomb = new ArrayList<Bomb>();
            arrFlameBomb = new ArrayList<FlameBomb>();
            arrRedEnemy = new ArrayList<RedEnemy>();
            arrGrass = new ArrayList<Grass>();
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    switch (map[i][j]) {
                        case '#':
                            Wall wall = new Wall(j * 45, i * 45);
                            arrWall.add(wall);
                            break;
                        case '*':
                            Brick brick = new Brick(j * 45, i * 45);
                            arrBrick.add(brick);
                            break;   
                        case '1':
                            Grass grass1 = new Grass(j * 45, i * 45);
                            arrGrass.add(grass1);
                            Enemy enemy = new Enemy(j * 45, i * 45, 3, 10);
                            arrEnemy.add(enemy);
                            break;
                        case '2':
                            Grass grass2 = new Grass(j * 45, i * 45);
                            arrGrass.add(grass2);
                            RedEnemy redEnemy = new RedEnemy(j * 45, i * 45, 3, 4);
                            arrRedEnemy.add(redEnemy);
                            break;
                        case 'b':
                            {
                                Brick brick1 = new Brick(j * 45, i * 45);
                                arrBrick.add(brick1);
                                BombItem bombItem = new BombItem(j * 45, i * 45);
                                arrBombItem.add(bombItem);
                                break;
                            }
                        case 'f':
                            {
                                Brick brick2 = new Brick(j * 45, i * 45);
                                arrBrick.add(brick2);
                                FlameItem flameItem = new FlameItem(j * 45, i * 45);
                                arrFlameItem.add(flameItem);
                                break;
                            }
                        case 's':
                            {
                                Brick brick3 = new Brick(j * 45, i * 45);
                                arrBrick.add(brick3);
                                SpeedItem speedItem = new SpeedItem(j * 45, i * 45);
                                arrSpeedItem.add(speedItem);
                                break;
                            }
                        default:
                            Grass grass = new Grass(j * 45, i * 45);
                            arrGrass.add(grass);
                            break;
                    }


                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void initBomb() {
        if (bomber.getStatus() == Bomber.DIE) {
            return;
        }
        int x = bomber.getX() + bomber.getWidth() / 2;
        int y = bomber.getY() + bomber.getHeight() / 2;
        for (int i = 0; i < arrBomb.size(); i++) {
            if (arrBomb.get(i).impactOtherBomb(x, y)) {
                return;
            }
        }
        if (arrBomb.size() >= bomber.getQuantityOfBomb()) {
            return;
        }
        Sound.getIstance().getAudio(Sound.BOMB).play();
        Bomb bomb = new Bomb(x, y, bomber.getSizeOfBomb(), 2000);
        arrBomb.add(bomb);
    }

    public void showResult(Graphics2D graphics2d, int result) {
        graphics2d.setFont(new Font("Arial", Font.BOLD, 70));
        graphics2d.setColor(Color.RED);
        if(result == 1) {
            graphics2d.drawString("YOU LOSE :(((", 500, 250);
        }
        if(result == 2) {
            graphics2d.drawString("YOU WIN !!!", 500, 250);
        }
    }

    
    public void showAllWall(Graphics2D graphics2d) {
        for (int i = 0; i < arrWall.size(); i++) {
            arrWall.get(i).showWall(graphics2d);
        }
    }
    
    public void showAllBrick(Graphics2D graphics2d) {
        for (int i = 0; i < arrBrick.size(); i++) {
            arrBrick.get(i).showBrick(graphics2d);
        }
    }
    
    public void showAllEnemy(Graphics2D graphics2d) {
        for (int i = 0; i < arrEnemy.size(); i++) {
            arrEnemy.get(i).showEnemy(graphics2d);
        }
    }
    
    public void showAllRedEnemy(Graphics2D graphics2d) {
        for (int i = 0; i < arrRedEnemy.size(); i++) {
            arrRedEnemy.get(i).showRedEnemy(graphics2d);
        }
    }
    
    public void showAllGrass(Graphics2D graphics2d) {
        for (int i = 0; i < arrGrass.size(); i++) {
            arrGrass.get(i).showGrass(graphics2d);
        }
    }
    
    public void showAllBomb(Graphics2D graphics2d) {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).showEntity(graphics2d);
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            arrFlameBomb.get(i).showFlameBomb(graphics2d);
        }
    }

    public void showAllItem(Graphics2D graphics2d) {
        for (int i = 0; i < arrBombItem.size(); i++) {
            arrBombItem.get(i).showItem(graphics2d);
        }
        if (arrFlameItem.size() > 0) {
        for (int i = 0; i < arrFlameItem.size(); i++) {
            arrFlameItem.get(i).showItem(graphics2d);
        }}
        for (int i = 0; i < arrSpeedItem.size(); i++) {
            arrSpeedItem.get(i).showItem(graphics2d);
        }
    }

    public void bomberEatItem() {
        for (int i = 0; i < arrBombItem.size(); i++) {
            if (arrBombItem.get(i).itemImpactBomber(bomber)) {
                Sound.instance.getAudio(Sound.ITEM).play();
                bomber.setQuantityOfBomb(bomber.getQuantityOfBomb() + 1);
                arrBombItem.remove(i);
            }
        }
        for (int i = 0; i < arrFlameItem.size(); i++) {
            if (arrFlameItem.get(i).itemImpactBomber(bomber)) {
                Sound.instance.getAudio(Sound.ITEM).play();
                bomber.setSizeOfBomb(bomber.getSizeOfBomb() + 1);
                arrFlameItem.remove(i);
            }
        }
        for (int i = 0; i < arrSpeedItem.size(); i++) {
            if (arrSpeedItem.get(i).itemImpactBomber(bomber)) {
                Sound.instance.getAudio(Sound.ITEM).play();
                bomber.setSpeed(bomber.getSpeed() - 1);
                arrSpeedItem.remove(i);
            }
        }
    }
    
    public void setResult() {
        if (bomber.getLife()== 0 ) {
            result = 1;
            Sound.getIstance().getAudio(Sound.PLAYGAME).stop();
            Sound.getIstance().getAudio(Sound.LOSE).play();
        }
        if (arrRedEnemy.size() == 0 && arrEnemy.size() == 0) {
            result = 2;
            Sound.getIstance().getAudio(Sound.PLAYGAME).stop();
            Sound.getIstance().getAudio(Sound.WIN).play();
        }
    }

    public void checkBomberDie() {
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            if (arrFlameBomb.get(i).flameImpactMoveEntity(bomber)  && bomber.getStatus() == Bomber.LIVE ) {
                Image icon = new ImageIcon(getClass().getResource("/sprites1/bomber_dead.png")).getImage();
                bomber.setImg(icon);
                if (bomber.getStatus() == Bomber.DIE) {
                    return;
                }
                bomber.setLife(bomber.getLife()- 1);
                bomber.setStatus(Bomber.DIE);
                Sound.instance.getAudio(Sound.DIE).play();
            }
        }
        for (int i = 0; i < arrEnemy.size(); i++) {
            if (bomber.bomberImpactMoveEntity(arrEnemy.get(i))) {
                Image icon = new ImageIcon(getClass().getResource("/sprites1/ghost.png")).getImage();
                bomber.setImg(icon);
                if (bomber.getStatus() == Bomber.DIE) {
                    return;
                }
                bomber.setLife(bomber.getLife()- 1);
                bomber.setStatus(Bomber.DIE);
                Sound.instance.getAudio(Sound.DIE).play();
            }
        }
        for (int i = 0; i < arrRedEnemy.size(); i++) {
            if (bomber.bomberImpactMoveEntity(arrRedEnemy.get(i))) {
                Image icon = new ImageIcon(getClass().getResource("/sprites1/ghost.png")).getImage();
                bomber.setImg(icon);
                if (bomber.getStatus() == Bomber.DIE) {
                    return;
                }
                bomber.setLife(bomber.getLife()- 1);
                bomber.setStatus(Bomber.DIE);
                Sound.instance.getAudio(Sound.DIE).play();
            }
        }
    }


    
    public void moveAllEnemy(int count) {
        for (int i = 0; i < arrEnemy.size(); i++) {
            if (arrEnemy.get(i).move(count, arrWall, arrBrick, arrBomb) == false) {
                int orient = random.nextInt(4) + 1;
                arrEnemy.get(i).changeOrient(orient);
            }
        }
    }
    
    /*public void moveAllRedEnemy(int count) {
        for (int i = 0; i < arrRedEnemy.size(); i++) {
            if((arrRedEnemy.get(i).x - bomber.x) * (arrRedEnemy.get(i).x - bomber.x) +
                    (arrRedEnemy.get(i).y - bomber.y) * (arrRedEnemy.get(i).y - bomber.y) > 50625) {
                if (arrRedEnemy.get(i).move(count, arrWall, arrBrick, arrBomb) == false) {
                    int orient = random.nextInt(4) + 1;
                    arrRedEnemy.get(i).changeOrient(orient);
                }
            } else {
                if(arrRedEnemy.get(i).getX() % 45 == 0 && arrRedEnemy.get(i).getY() % 45 == 0) {
                    int orient;
                    if (arrRedEnemy.get(i).getX() <= bomber.getX() && arrRedEnemy.get(i).getY() <= bomber.getY()) {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 1 || orient == 3);
                    } else if (arrRedEnemy.get(i).getX() >= bomber.getX() && arrRedEnemy.get(i).getY() >= bomber.getY()) {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 2 || orient == 4);
                    } else if (arrRedEnemy.get(i).getX() >= bomber.getX() && arrRedEnemy.get(i).getY() <= bomber.getY()) {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 2 || orient == 3);
                    } else {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 1 || orient == 4);
                    }
                    arrRedEnemy.get(i).changeOrient(orient);
                }

                boolean a = arrRedEnemy.get(i).move(count, arrWall, arrBrick, arrBomb);
            }
        }
    }*/
    
    public void moveAllRedEnemy(int count) {
        for (int i = 0; i < arrRedEnemy.size(); i++) {
            if((arrRedEnemy.get(i).x - bomber.x) * (arrRedEnemy.get(i).x - bomber.x) +
                    (arrRedEnemy.get(i).y - bomber.y) * (arrRedEnemy.get(i).y - bomber.y) > 50625 * 2) {
                if (arrRedEnemy.get(i).move(count, arrWall, arrBrick, arrBomb) == false) {
                    int orient = random.nextInt(4) + 1;
                    arrRedEnemy.get(i).changeOrient(orient);
                }
            } else {
                if(arrRedEnemy.get(i).getX() % 45 == 0 && arrRedEnemy.get(i).getY() % 45 == 0) {
                    int orient;
                    if (arrRedEnemy.get(i).getX() <= bomber.getX() && arrRedEnemy.get(i).getY() <= bomber.getY()) {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 1 || orient == 3);
                    } else if (arrRedEnemy.get(i).getX() >= bomber.getX() && arrRedEnemy.get(i).getY() >= bomber.getY()) {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 2 || orient == 4);
                    } else if (arrRedEnemy.get(i).getX() >= bomber.getX() && arrRedEnemy.get(i).getY() <= bomber.getY()) {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 2 || orient == 3);
                    } else {
                        do {
                            orient = random.nextInt(4) + 1;
                        } while (orient == 1 || orient == 4);
                    }
                    arrRedEnemy.get(i).changeOrient(orient);
                }

                //boolean a = arrRedEnemy.get(i).move(count, arrWall, arrBrick, arrBomb);
                if (arrRedEnemy.get(i).move(count, arrWall, arrBrick, arrBomb) == false) {
                    int orient = random.nextInt(4) + 1;
                    arrRedEnemy.get(i).changeOrient(orient);
                    boolean c = arrRedEnemy.get(i).move(count, arrWall, arrBrick, arrBomb);

                }
            }
        }
    }
    
    public void setRunBomber() {
        if (arrBomb.size() > 0) {
            if (arrBomb.get(arrBomb.size() - 1).setRun(bomber) == false) {
                bomber.setRunBomb(Bomber.cant_run);
            }
        }
    }
    
    public void explodedAllBomb() {
        for (int i = 0; i < arrBomb.size(); i++) {
            arrBomb.get(i).toExplode();
            if (arrBomb.get(i).getTimeExplode() == 250) {
                FlameBomb flameBomb = new FlameBomb(arrBomb.get(i).getX(), arrBomb.get(i).getY(),
                        arrBomb.get(i).getSize(), arrWall, arrBrick);
                Sound.getIstance().getAudio(Sound.BOM_EXPLODED).play();
                arrFlameBomb.add(flameBomb);
                arrBomb.remove(i);
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrBomb.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactBomb(arrBomb.get(j))) {
                    FlameBomb flameBomb = new FlameBomb(arrBomb.get(j).getX(), arrBomb.get(j).getY(),
                            arrBomb.get(j).getSize(), arrWall, arrBrick);
                    arrFlameBomb.add(flameBomb);
                    arrBomb.remove(j);
                }
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrBrick.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactBrick(arrBrick.get(j))) {
                    Grass grass = new Grass(arrBrick.get(j).getX(), arrBrick.get(j).getY());
                    arrBrick.remove(j);
                    arrGrass.add(grass);
                }
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrEnemy.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactMoveEntity(arrEnemy.get(j))) {
                    arrEnemy.remove(j);
                    Sound.instance.getAudio(Sound.DIE).play();
                }
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrRedEnemy.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactMoveEntity(arrRedEnemy.get(j))) {
                    arrRedEnemy.remove(j);
                    Sound.instance.getAudio(Sound.DIE).play();
                }
            }
        }      
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrBombItem.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactItem(arrBombItem.get(j))) {
                    arrBombItem.remove(j);
                }
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrFlameItem.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactItem(arrFlameItem.get(j))) {
                    arrFlameItem.remove(j);
                }
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            for (int j = 0; j < arrSpeedItem.size(); j++) {
                if (arrFlameBomb.get(i).flameImpactItem(arrSpeedItem.get(j))) {
                    arrSpeedItem.remove(j);
                }
            }
        }
        for (int i = 0; i < arrFlameBomb.size(); i++) {
            arrFlameBomb.get(i).runTimeBomb();
            if (arrFlameBomb.get(i).getTime() == 0) {
                arrFlameBomb.remove(i);
            }
        }
    }
}

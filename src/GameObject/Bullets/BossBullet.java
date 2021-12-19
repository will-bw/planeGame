package GameObject.Bullets;

import GameObject.GameObject;
import GameUtils.Datas;
import GameUtils.GameImagines;
import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;

public class BossBullet extends Bullets {
    private int direction;
    public static boolean isRun=true;//线程初始一直跑
    public BossBullet(int x, int y, int direction) {
        super(x, y, 4, new JLabel(new ImageIcon(GameImagines.boss_bullet_image)), new Rectangle(x, y, 15, 15), 16, 16);
        this.direction = direction;
    }

    public BossBullet() {
        super();
    }

    @Override
    public void run() {
        while (isRun) {
            if (StartGame.state == 1) {
                if (Datas.boss.isOnPane) {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Datas.bossBullets.add(new BossBullet(Datas.boss.x + 86, Datas.boss.y + Datas.boss.height - 16, 0));
                    Datas.bossBullets.add(new BossBullet(Datas.boss.x + 104, Datas.boss.y + Datas.boss.height + 2, 1));
                    Datas.bossBullets.add(new BossBullet(Datas.boss.x + 122, Datas.boss.y + Datas.boss.height - 16, 2));
                    try {
                        Thread.sleep(7500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    while (!Datas.boss.isOnPane) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }else {
                while(StartGame.state!=1&&isRun&&StartGame.state!=0){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void move() {
        if (direction == 1) {
            y += speed;
        } else if (direction == 0) {
            y += speed;
            x -= (int) ((double) (speed) * Math.tan(Math.PI / 12.0) + 0.5);
        } else if (direction == 2) {
            y += speed;
            x += speed * Math.tan(Math.PI / 12.0);
        }
    }
}

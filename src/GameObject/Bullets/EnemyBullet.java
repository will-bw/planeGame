package GameObject.Bullets;

import GameObject.GameObject;
import GameObject.Planes.EnemyPlane;
import GameUtils.Datas;
import GameUtils.GameImagines;
import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;

public class EnemyBullet extends Bullets {
    public static boolean isRun=true;//线程初始一直跑
    public EnemyBullet(int x, int y) {
        super(x, y,6, new JLabel(new ImageIcon(GameImagines.enemyBullet)), new Rectangle(x,y,8,20),9,21);
    }

    public EnemyBullet() {}

    @Override
    public void move() {
        y+=speed;
    }

    @Override
    public void run() {
        while(isRun){
            if(StartGame.state==1){
                for (EnemyPlane enemyPlane : Datas.enemyPlanes) {
                    if(enemyPlane!=null){
                        if(enemyPlane.name==0&&enemyPlane.isAlive){
                            Datas.enemyBullets.add(new EnemyBullet(enemyPlane.x+enemyPlane.width/2-4, enemyPlane.y+ enemyPlane.height));
                        }
                    }
                }
                if(Datas.boss.isOnPane){
                    Datas.enemyBullets.add(new EnemyBullet(Datas.boss.x+21,Datas.boss.y+220));
                    Datas.enemyBullets.add(new EnemyBullet(Datas.boss.x+226,Datas.boss.y+220));
                    Datas.enemyBullets.add(new EnemyBullet(Datas.boss.x+124,Datas.boss.y+220));
                }
                try {
//                    两秒发射一次子弹
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
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
}

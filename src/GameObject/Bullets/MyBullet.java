package GameObject.Bullets;

import GameObject.GameObject;
import GameUtils.Datas;
import GameUtils.GameImagines;
import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;

public class MyBullet extends Bullets {
    public int direction;//0是上，1是左，2是右
    public static boolean isRun=true;//线程初始一直跑
    public MyBullet(int x, int y, JLabel image, int direction) {
        super(x, y,5, image, new Rectangle(x,y,6,20), 8, 22);
        this.direction = direction;
    }

    public MyBullet() {
    }

    @Override
    public void move() {
        if (direction == 0) {
            y -= speed;
        } else if (direction == 1) {
            y -= speed;
            x -= (int) ((double) (speed) * Math.tan(Math.PI / 12.0) + 0.5);
        } else if (direction == 2) {
            y -= speed;
            x += speed * Math.tan(Math.PI / 12.0);
        }
    }

    @Override
    public void run() {
        while (isRun) {
            if (StartGame.state == 1) {
                Datas.myBullets.add(new MyBullet(Datas.myPlane.x+(Datas.myPlane.width-6>>1), Datas.myPlane.y, new JLabel(new ImageIcon(GameImagines.bullet_up_image)), 0));
                Datas.myBullets.add(new MyBullet(Datas.myPlane.x+(Datas.myPlane.width-6>>1)-3, Datas.myPlane.y, new JLabel(new ImageIcon(GameImagines.bullet_left_image)), 1));
                Datas.myBullets.add(new MyBullet(Datas.myPlane.x+(Datas.myPlane.width-6>>1)+3, Datas.myPlane.y, new JLabel(new ImageIcon(GameImagines.bullet_right_image)), 2));
                try {
                    Thread.sleep(Datas.myPlane.isOnPane?1200:2000);//每隔2000ms添加新的子弹,如果boss出场了就加快频率
                    //Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                while(StartGame.state!=1&&isRun&&StartGame.state!=0){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
//    }
    }
}

package GameObject.Planes;

import GameObject.Bullets.Bullets;
import GameObject.GameObject;
import GameUtils.GameImagines;

import javax.swing.*;
import java.awt.*;

public class Planes extends GameObject {
    public int blood;
    public JLabel bloodImage;
    public boolean isBloodOnPane=false;
    public Planes() {}

    public Planes(int x, int y,int speed,int blood, JLabel image, Rectangle body, int width, int height) {
        super(x, y, image, body, width, height);
        this.speed=speed;
        this.blood=blood;
    }
    public boolean isCollidedWithBullet (Bullets bullet){
        //碰撞检测
        return body.intersects(bullet.body);
    }
}

package GameObject.Planes;

import GameObject.GameObject;
import GameUtils.Datas;
import GameUtils.GameImagines;
import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;

public class MyPlane extends Planes {
    public boolean up = false, down = false, left = false, right = false;

    public MyPlane(int x, int y, JLabel image) {

        super(x, y, 0, 15, image, new Rectangle(x, y + 12, 70, 70), 70, 91);
        this.bloodImage = new JLabel(new ImageIcon(GameImagines.myBlood));//初始化满血
    }

    @Override
    public void move() {
//        重写move方法
        if (up && Datas.myPlane.y > 0) {
            Datas.myPlane.y -= 5;
        }
        if (down && Datas.myPlane.y < 800) {
            Datas.myPlane.y += 5;
        }
        if (left && Datas.myPlane.x > 0) {
            Datas.myPlane.x -= 5;
        }
        if (right && Datas.myPlane.x < 640) {
            Datas.myPlane.x += 5;
        }
    }

    @Override
    public void setBody() {
        body.x = x;
        body.y = y + 6;
    }

    public void reSet(){
        blood=15;
        isAlive=true;
        isOnPane=false;
        x=315;
        y=700;
    }
}

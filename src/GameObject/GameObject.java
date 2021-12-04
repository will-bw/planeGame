package GameObject;

import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;

public class GameObject implements Runnable{
    public int x,y;//坐标
    public JLabel image;//图片
    public Rectangle body;//代表身体的矩形
    public int width,height;//大小
    public int speed;//速度
    public boolean isOnPane=false;//初始不在游戏面板上
    public boolean isAlive=true;//初始都是活着的

    public GameObject(){}
    public GameObject(int x, int y, JLabel image, Rectangle body, int width, int height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.body = body;
        this.width = width;
        this.height = height;
    }
    public boolean isOutOfBorder(){
        return x+width<0||x> StartGame.FrameWidth||y>StartGame.FrameHeight||y+height<0;
    }

    @Override
    public void run() {}

    public void setBody(){
        body.x=x;
        body.y=y;
    }
    public void move(){}
}

package GameObject.Planes;

import GameObject.GameObject;
import GameUtils.GameImagines;
import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Boss extends Planes {
    private int speed_x, speed_y;
    private int direction_x, direcyion_y;
    private int count=50;
    public boolean isOnPane = false;
    SecureRandom random = null;
    public Boss(int x, int y) {
        super(x, y, 0, 60, new JLabel(new ImageIcon(GameImagines.boss_image)), new Rectangle(x, y + 20, 256, 207), 256, 227);
        this.bloodImage=new JLabel(new ImageIcon(GameImagines.bossBlood));
    }

    @Override
    public void run() {}

    @Override
    public void setBody() {
        body.x=x;
        body.y=y+20;
    }

    public void reSet(){
        blood=60;
        x=StartGame.FrameWidth-256>>1;
        y=-200;
        isOnPane=false;
        isAlive=true;
        count=50;
    }

    public void move(){
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(count>=50){
            speed_x=random.nextInt(3);
            speed_y=random.nextInt(3);
            direction_x=random.nextInt(2);
            direcyion_y= random.nextInt(2);
            count=0;
        }
        if(direction_x==0){
            //向左走
            if(x-speed_x>=0){
                x-=speed_x;
            }else {
                x+=speed_x;
                direction_x=1;
            }
        }else {
            //向右走
            if(x+speed_x+this.width<=700){
                x+=speed_x;
            }else {
                x-=speed_x;
                direction_x=0;
            }
        }
        if(direcyion_y==0){
            //向上走
            if(y-speed_y>=0){
                y-=speed_y;
            }else {
                y+=speed_y;
                direcyion_y=1;
            }
        }else {
            //向下走
            if(y+speed_y+height<=650){
                y+=speed_y;
            }else{
                y-=speed_y;
                direcyion_y=0;
            }
        }
        count++;
    }
}
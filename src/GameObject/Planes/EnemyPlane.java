package GameObject.Planes;

import GameUtils.Datas;
import GameUtils.GameImagines;
import PlaneGame.PageUPdate;
import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EnemyPlane extends Planes {
    public  int name;
    public int direction=2;
    public static boolean isRun=true;//线程初始一直跑
    SecureRandom random = null;
    private int intervals,rand_x,rand_name,rand_speed;
    public EnemyPlane(int name,int x, int y,int speed,JLabel image) {
        super(x, y,speed,2 , image,new Rectangle(x+2,y+2,60,58),65,65);
        this.name=name;
        this.bloodImage=new JLabel(new ImageIcon(GameImagines.enemy_blood));//初始化满血
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public EnemyPlane() {}

    @Override
    public void move() {
//        重写move方法
        y += speed;
        if(PageUPdate.updataCount >=30){//每刷新指定次数界面改变一次方向
            direction =random.nextInt(3);
        }
        if(direction==0){
            x+= 2;
        }else if(direction==1){
            x-= 2;
        }
        if(PageUPdate.updataCount>=30){
            //这个count是用来让敌机随机转向的
            PageUPdate.updataCount=0;
        }
    }

    @Override
    public void setBody() {
        body.x=x + 2;
        body.y=y + 2;
    }

    @Override
    public void run() {
        while(isRun){
            if(StartGame.state==1){
                try {
                    //获得敌机1的随机投放间隔和随机坐标
                    SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                    //如果boss在面板上那么就减小敌机投放的速率
                    intervals=Datas.boss.isOnPane?(random.nextInt(6)+1)*500+200:(random.nextInt(6)+1)*500;
                    rand_x=random.nextInt(27)*25+10;
                    rand_name=random.nextInt(2);
                    rand_speed=random.nextInt(3)+2;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                //随机投放敌机种类
                if(rand_name==0){
                    Datas.enemyPlanes.add(new EnemyPlane(rand_name,rand_x,-65,rand_speed,new JLabel(new ImageIcon(GameImagines.enemy1_image))));
                }else {
                    Datas.enemyPlanes.add(new EnemyPlane(rand_name,rand_x,-65,rand_speed,new JLabel(new ImageIcon(GameImagines.enemy2_image))));
                }
                try {
                    //间隔投放敌机
                    Thread.sleep(intervals);
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
    }
}

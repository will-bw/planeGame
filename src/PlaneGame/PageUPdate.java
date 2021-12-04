package PlaneGame;

import GameObject.Bullets.BossBullet;
import GameObject.Bullets.EnemyBullet;
import GameObject.Planes.EnemyPlane;
import GameObject.Bullets.MyBullet;
import GameUtils.Datas;
import GameUtils.GameImagines;

import javax.swing.*;
import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PageUPdate implements Runnable {
    private GamePanel page;
    public static boolean isRun = true;
    public static int updataCount=70;
    public PageUPdate(GamePanel page) {
        this.page = page;
    }


    @Override
    public void run() {
        pageUpdata();
    }

    private void pageUpdata() {
//        添加得分标签
        Font font=new Font("微软雅黑",Font.BOLD , 24);
        GameImagines.scoreLabel.setFont(font);
        GameImagines.scoreLabel.setForeground(Color.red);
        page.add(GameImagines.scoreLabel,0);
        GameImagines.scoreLabel.setBounds(0,0,150,30);
//        添加我的血条
        page.add(Datas.myPlane.bloodImage);
        Datas.myPlane.bloodImage.setBounds(0,860,700,8);



//        循环更新界面




        while (isRun) {
            if (StartGame.state == 1) {
                try {
                    Thread.sleep(25);//25ms刷新一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updataCount++;
                backgroundUpdate();
                myPlaneUpdate();
                myBulletsUpdate();
                enemyPlaneUpdate();
                enemyBulletsUpdate();
                bossUpdate();
                bossBulletUpdate();
                GameImagines.scoreLabel.setText("得分："+StartGame.score);
                page.repaint();
            } else if (StartGame.state==2)  {
                while(StartGame.state==2){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }else {
                if(StartGame.state==3){
                    //失败
                    page.add(GameImagines.gameOver,0);
                    GameImagines.gameOver.setBounds(StartGame.FrameWidth - 256 >> 1, (StartGame.FrameHeight - 69 >> 1), 256, 69);
                    //GameImagines.gameOver.setOpaque(true);
                    font=new Font("华文行楷", Font.BOLD,30 );
                    GameImagines.scoreLabel.setFont(font);
                    GameImagines.scoreLabel.setBounds((StartGame.FrameWidth - 150 >> 1),(StartGame.FrameHeight - 293 >> 1)+200,150,30);
                    //GameImagines.scoreLabel.setOpaque(true);
                    page.repaint();
                    isRun=false;
                }else if(StartGame.state==4){
                    //通关
                    page.add(GameImagines.victory,0);
                    GameImagines.victory.setBounds(StartGame.FrameWidth - 256 >> 1, (StartGame.FrameHeight - 256 >> 1), 256, 256);
                    //GameImagines.victory.setOpaque(true);
                    font=new Font("华文行楷", Font.BOLD,30 );
                    GameImagines.scoreLabel.setFont(font);
                    GameImagines.scoreLabel.setBounds((StartGame.FrameWidth - 150 >> 1),(StartGame.FrameHeight - 293 >> 1)+200,150,30);
                    //GameImagines.scoreLabel.setOpaque(true);
                    page.repaint();
                    isRun=false;
                }
                /*BossBullet.isRun=false;
                EnemyPlane.isRun=false;
                EnemyBullet.isRun=false;
                MyBullet.isRun=false;*/
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                page.reStart();
            }
        }
    }





    //                更新背景位置
    private void backgroundUpdate() {
        if (StartGame.bgi_y >= 0) {
            StartGame.bgi_y = -900;
        } else {
            StartGame.bgi_y += 1;
        }
    }

    //                    更新我的飞机的位置
    private void myPlaneUpdate() {
        if (!Datas.myPlane.isOnPane) {
            page.add(Datas.myPlane.image);
            Datas.myPlane.isOnPane = true;
        }
        Datas.myPlane.image.setBounds(Datas.myPlane.x, Datas.myPlane.y, Datas.myPlane.width, Datas.myPlane.height);//加上我方飞机
        Datas.myPlane.setBody();
        Datas.myPlane.bloodImage.setBounds(0,860,46*(Math.max(Datas.myPlane.blood, 0)),8);
        Datas.myPlane.move();
    }

    //                遍历我方子弹更新
    private void myBulletsUpdate() {
        synchronized (Datas.myBullets) {
            for (MyBullet myBullet : Datas.myBullets) {
                if (myBullet != null) {
                    if (myBullet.isOutOfBorder() && myBullet.isOnPane) {
                        //如果子弹在游戏面板上面并且越界了，删除子弹，注意删除的顺序
                        page.remove(myBullet.image);
                        Datas.myBullets.remove(myBullet);
                    } else {
                        if (!myBullet.isOnPane) {
                            //如果该子弹不在面板则加上去，否则只需要设置他的位置，刚开始每次都添加导致大量异常
                            page.add(myBullet.image);
                            myBullet.isOnPane = true;
                        }
                        myBullet.image.setBounds(myBullet.x, myBullet.y, myBullet.width, myBullet.height);
                        myBullet.setBody();
                        //子弹的下一次位置
                        myBullet.move();
                    }
                }
            }
        }
    }

    //                遍历敌机更新
    private synchronized void enemyPlaneUpdate() {
        for (EnemyPlane enemyPlane : Datas.enemyPlanes) {
            if (enemyPlane != null) {
                if (enemyPlane.isOutOfBorder() && enemyPlane.isOnPane) {
                    page.remove(enemyPlane.image);
                    Datas.enemyPlanes.remove(enemyPlane);
                } else {
                    if (!enemyPlane.isOnPane) {
                        page.add(enemyPlane.image);
                        enemyPlane.isOnPane = true;
                    }
                    if (enemyPlane.isAlive) {
                        enemyPlane.image.setBounds(enemyPlane.x, enemyPlane.y, enemyPlane.width, enemyPlane.height);
                    }
                    if (!enemyPlane.isAlive && enemyPlane.isBloodOnPane) {
                        page.remove(enemyPlane.bloodImage);
                    }
                    if (enemyPlane.blood > 0 && enemyPlane.isAlive) {
                        if (!enemyPlane.isBloodOnPane) {
                            page.add(enemyPlane.bloodImage);
                            enemyPlane.isBloodOnPane = true;
                        }
                        enemyPlane.bloodImage.setIcon(new ImageIcon(GameImagines.enemy_blood));
                        enemyPlane.bloodImage.setBounds(enemyPlane.x + 20, enemyPlane.y - 4, 25*enemyPlane.blood/2, 3);
                    }
                    enemyPlane.setBody();
                    enemyPlane.move();
                }
            }
        }
    }

    //    遍历敌方子弹更新
    private void enemyBulletsUpdate() {
        synchronized (Datas.enemyBullets) {
            for (EnemyBullet enemyBullet : Datas.enemyBullets) {
                if (enemyBullet != null) {
                    if (enemyBullet.isOutOfBorder() && enemyBullet.isOnPane) {
                        page.remove(enemyBullet.image);
                        Datas.enemyBullets.remove(enemyBullet);
                    } else {
                        if (!enemyBullet.isOnPane) {
                            page.add(enemyBullet.image);
                            enemyBullet.isOnPane = true;
                        }
                        enemyBullet.image.setBounds(enemyBullet.x, enemyBullet.y, enemyBullet.width, enemyBullet.height);
                        enemyBullet.setBody();
                        enemyBullet.move();
                    }
                }
            }
        }
    }
//    boss更新
    private void bossUpdate(){
        if(!Datas.boss.isOnPane){
            if(StartGame.score>=20){
                //达到指定分数派出boss
                page.add(Datas.boss.image);
                Datas.boss.image.setBounds(Datas.boss.x,Datas.boss.y,Datas.boss.width,Datas.boss.height);
                page.add(Datas.boss.bloodImage);
                Datas.boss.bloodImage.setBounds(Datas.boss.x+8, Math.max(Datas.boss.y-10, 0),240,9);
                Datas.boss.isOnPane=true;
            }
        }else{
            Datas.boss.image.setBounds(Datas.boss.x,Datas.boss.y,Datas.boss.width,Datas.boss.height);
            Datas.boss.bloodImage.setBounds(Datas.boss.x+8, Math.max(Datas.boss.y-10, 0),4*Datas.boss.blood,10);
            Datas.boss.move();
            Datas.boss.setBody();
        }
    }

//    遍历boss特殊子弹更新
    private void bossBulletUpdate(){
        for (BossBullet bossBullet : Datas.bossBullets) {
            if(bossBullet!=null){
                if(bossBullet.isAlive){
                    //System.out.println("遍历子弹了");
                    if(!bossBullet.isOnPane){
                        page.add(bossBullet.image);
                        bossBullet.isOnPane=true;
                       //System.out.println("第一次添加子弹");
                        bossBullet.image.setBounds(bossBullet.x,bossBullet.y,bossBullet.width,bossBullet.height);
                        bossBullet.setBody();
                    }else {
                        bossBullet.image.setBounds(bossBullet.x,bossBullet.y,bossBullet.width,bossBullet.height);
                        bossBullet.setBody();
                        bossBullet.move();
                       //System.out.println("子弹移动");
                    }
                }
                if((!bossBullet.isAlive||bossBullet.isOutOfBorder())&& bossBullet.isOnPane){
                    page.remove(bossBullet.image);
                   //System.out.println("移除子弹");
                    Datas.bossBullets.remove(bossBullet);
                }
            }
        }
    }
}


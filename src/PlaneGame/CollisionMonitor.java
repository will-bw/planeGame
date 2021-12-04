package PlaneGame;

import GameObject.Bullets.BossBullet;
import GameObject.Bullets.EnemyBullet;
import GameObject.Bullets.MyBullet;
import GameObject.Planes.EnemyPlane;
import GameUtils.Datas;


public class CollisionMonitor implements Runnable{
    public static boolean isRun=true;
    private GamePanel gamePanel;

    public CollisionMonitor(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void run() {
        while(isRun){
            if(StartGame.state==1){
                collisionMonitor();
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


    private synchronized void collisionMonitor(){
        for (EnemyPlane enemyPlane : Datas.enemyPlanes) {
            if(enemyPlane!=null){
                //如果还活着就进行碰撞检测
                if(enemyPlane.isAlive){
                    if(enemyPlane.body.intersects(Datas.myPlane.body)){
                        //敌机和我方飞机都碰撞，都死亡
                        enemyPlane.isAlive=false;
                        Datas.myPlane.isAlive=false;
                        gamePanel.remove(Datas.myPlane.image);
                        new Thread(new Explode(enemyPlane.x, enemyPlane.y, gamePanel)).start();
                        new Thread(new Explode(Datas.myPlane.x,Datas.myPlane.y, gamePanel)).start();
                        System.out.println("！！！！！！！敌机和我同归于尽啦！！！！！！！");
                        enemyPlane.y+=900;
                        StartGame.state=3;
                        continue;
                    }
                    for (MyBullet myBullet : Datas.myBullets) {
                        if(myBullet!=null){
                            if(enemyPlane.isCollidedWithBullet(myBullet)){
                                //如果敌机和我方子弹相碰撞，子弹移除，敌机生命减一
                                gamePanel.remove(myBullet.image);
                                myBullet.y+=900;
                                myBullet.setBody();
                                enemyPlane.blood--;
                                //System.out.println("击中敌机，敌机血量： "+enemyPlane.blood);
                                if(enemyPlane.blood<=0){
                                    enemyPlane.isAlive=false;
                                    new Thread(new Explode(enemyPlane.x, enemyPlane.y, gamePanel)).start();
                                    gamePanel.remove(enemyPlane.bloodImage);
                                    enemyPlane.y+=900;
                                    //enemyPlane.isOnPane=false;
                                    //System.out.println("敌机死亡，我方子弹移除");
                                    StartGame.score++;//得分加一
                                }
                            }
                        }
                    }
                }
            }
        }
//                            遍历敌方子弹判断是否和我方飞机碰撞
        for (EnemyBullet enemyBullet : Datas.enemyBullets) {
            if(enemyBullet!=null){
                if(Datas.myPlane.isCollidedWithBullet(enemyBullet)){
                    //如果碰撞则移除子弹，我生命减一
                    new Thread(new Explode(enemyBullet.x-20, enemyBullet.y-30,gamePanel)).start();
                    enemyBullet.y+=900;
                    enemyBullet.setBody();
                    Datas.myPlane.blood--;
                    if(Datas.myPlane.blood<=0){
                        Datas.myPlane.isAlive=false;
                        new Thread(new Explode(enemyBullet.x, enemyBullet.y,gamePanel)).start();
                        System.out.println("！！！！！！！！我被打死啦！！！！！！！！");
                        StartGame.state=3;
                        gamePanel.remove(Datas.myPlane.image);
                        enemyBullet.y+=900;
                        enemyBullet.setBody();
                    }
                }
            }
        }
//        遍历我方子弹与boss的碰撞
        if(Datas.boss.isOnPane){
            for (MyBullet myBullet : Datas.myBullets) {
                if(Datas.boss.isCollidedWithBullet(myBullet)){
                    new Thread(new Explode(myBullet.x-20,myBullet.y,gamePanel)).start();
                    myBullet.y-=900;
                    myBullet.setBody();
                    Datas.boss.blood--;
                    if(Datas.boss.blood<=0){
                        System.out.println("boss死了，通关");
                        //应该添加爆炸特效
                        StartGame.state=4;
                    }
                }
            }
        }

//        遍历boss特殊子弹和我方的碰撞
        if(Datas.boss.isOnPane){
            for (BossBullet bossBullet : Datas.bossBullets) {
                if(bossBullet!=null){
                    if(Datas.myPlane.isCollidedWithBullet(bossBullet)){
                        new Thread(new Explode(bossBullet.x-20, bossBullet.y-30,gamePanel)).start();
                        bossBullet.y+=900;
                        bossBullet.setBody();
                        //特殊子弹击中我方飞机，血量减两滴
                        Datas.myPlane.blood-=2;
                        if(Datas.myPlane.blood<=0){
                            Datas.myPlane.isAlive=false;
                            new Thread(new Explode(bossBullet.x, bossBullet.y,gamePanel)).start();
                            System.out.println("！！！！！！！！我被打死啦！！！！！！！！");
                            StartGame.state=3;
                            gamePanel.remove(Datas.myPlane.image);
                            bossBullet.y+=900;
                            bossBullet.setBody();
                        }
                    }
                }
            }
        }
//        判断我方飞机和boss的碰撞
        if(Datas.boss.isOnPane){
            if(Datas.myPlane.body.intersects(Datas.boss.body)){
                //发生碰撞
                gamePanel.remove(Datas.myPlane.image);
                new Thread(new Explode(Datas.myPlane.x,Datas.myPlane.y, gamePanel));
                StartGame.state=3;
            }
        }
    }
}

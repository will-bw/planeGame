<h2 align = "center" >飞机大战</h2>
***
###我大二上期的java大作业，实现飞机大战
  基本功能已经达到，但是在最后想添加一个重新开始的时候出现每次重新开始后都会莫名其妙可能飞机暂时不受控制
  并且游戏速度非常快，每次重新开始都会加快速度最后帧率都跟不上刷新，而且貌似飞机删除失败，满屏飞机
~~~java
public void reStart(){
        removeAll();
        Datas.boss.reSet();
        Datas.myPlane.reSet();
        Datas.myBullets.clear();
        Datas.bossBullets.clear();
        Datas.enemyBullets.clear();
        Datas.enemyPlanes.clear();
        StartGame.state = 1;//游戏开始！
        myThread.requestFocus();
        MyBullet.isRun=true;
        PageUPdate.isRun=true;
        EnemyPlane.isRun=true;
        EnemyBullet.isRun=true;
        CollisionMonitor.isRun=true;
        BossBullet.isRun=true;
        PageUPdate.updataCount=70;
        StartGame.score=0;
        new Thread(new MyBullet()).start();//我方子弹投放开始
        new Thread(new PageUPdate(myThread)).start();//页面更新开始
        new Thread(new EnemyPlane()).start();//敌机投放开始
        new Thread(new EnemyBullet()).start();//敌机子弹投放开始
        new Thread(new CollisionMonitor(myThread)).start();//碰撞监测开始
        new Thread(new BossBullet()).start();
    }


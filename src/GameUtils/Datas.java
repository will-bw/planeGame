package GameUtils;

import GameObject.Bullets.BossBullet;
import GameObject.Bullets.Bullets;
import GameObject.Bullets.EnemyBullet;
import GameObject.Bullets.MyBullet;
import GameObject.Planes.Boss;
import GameObject.Planes.EnemyPlane;
import GameObject.Planes.MyPlane;
import PlaneGame.StartGame;

import java.util.concurrent.CopyOnWriteArrayList;

public class Datas {
    public static MyPlane myPlane=new MyPlane(315,700,GameImagines.my_plane);//我的飞机
    public static CopyOnWriteArrayList<EnemyPlane> enemyPlanes=new CopyOnWriteArrayList<>();//敌机1
    public static CopyOnWriteArrayList<MyBullet> myBullets=new CopyOnWriteArrayList<>();//我方子弹
    public static CopyOnWriteArrayList<EnemyBullet> enemyBullets=new CopyOnWriteArrayList<>();//敌方子弹
    public static Boss boss=new Boss(StartGame.FrameWidth-256>>1,-220);//BOSS
    public static CopyOnWriteArrayList<BossBullet> bossBullets=new CopyOnWriteArrayList<>();//BOSS的特殊子弹
}
package GameUtils;


import PlaneGame.StartGame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Stack;

public class GameImagines {

    public static Image BackgroundImage=Toolkit.getDefaultToolkit().getImage("image//mapback1.jpg");//背景图片
    public static Image myPlane_image=Toolkit.getDefaultToolkit().getImage("image//my_plane.png");
    public static Image boss_image=Toolkit.getDefaultToolkit().getImage("image//BOSS.png");
    public static Image enemy1_image=Toolkit.getDefaultToolkit().getImage("image//enemy1.png");
    public static Image enemy2_image=Toolkit.getDefaultToolkit().getImage("image//enemy2.png");
    public static Image explode_image=Toolkit.getDefaultToolkit().getImage("image//explode1.png");
    public static Image boss_bullet_image=Toolkit.getDefaultToolkit().getImage("image//bossBullet.png");
    public static Image bullet_up_image=Toolkit.getDefaultToolkit().getImage("image//bullet_up.png");
    public static Image bullet_left_image=Toolkit.getDefaultToolkit().getImage("image//bullet_left.png");
    public static Image bullet_right_image=Toolkit.getDefaultToolkit().getImage("image//bullet_right.png");
    public static Image start_page=Toolkit.getDefaultToolkit().getImage("image//start_page2.png");
    public static Image enemyBullet=Toolkit.getDefaultToolkit().getImage("image//enemy_bullet.png");
    public static Image helpBack=Toolkit.getDefaultToolkit().getImage("image//helpBack.png");

    public static JButton start_icon=new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//start_icon2.png")));//开始按钮
    public static JButton help=new JButton(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//help.png")));//开始按钮
    public static JLabel my_plane=new JLabel(new ImageIcon(myPlane_image));
    public static JLabel scoreLabel=new JLabel("得分："+StartGame.score);
    public static Image enemy_blood= Toolkit.getDefaultToolkit().getImage("image//blood_1.png");
    public static Image myBlood= Toolkit.getDefaultToolkit().getImage("image//myblood.png");
    public static Image bossBlood= Toolkit.getDefaultToolkit().getImage("image//bossBlood.png");
    public static JLabel explode=new JLabel(new ImageIcon(explode_image));
    public static JLabel gameOver=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//gameover3.png")));
    public static JLabel victory=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//victory.png")));
    public static JLabel pause=new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image//pause.png")));
}

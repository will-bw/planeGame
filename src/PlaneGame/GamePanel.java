package PlaneGame;

import GameObject.Bullets.BossBullet;
import GameObject.Bullets.EnemyBullet;
import GameObject.Bullets.MyBullet;
import GameObject.Planes.EnemyPlane;
import GameObject.Planes.MyPlane;
import GameUtils.Datas;
import GameUtils.GameImagines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    JFrame game_frame;
    GamePanel myThread;

    //构造
    public GamePanel(JFrame frame) throws HeadlessException {
        game_frame = frame;
        myThread = this;
    }

    public void init() {

        //开始画面

        Container container = game_frame.getContentPane();
        setBounds(0, 0, StartGame.FrameWidth, StartGame.FrameHeight);
        setVisible(true);
        setFocusable(true);//获取焦点
        setLayout(null);//设置面板绝对布局
        add(GameImagines.start_icon);//添加开始按钮
        add(GameImagines.help);//添加帮助按钮
        GameImagines.help.setBounds(StartGame.FrameWidth - 180 >> 1, (StartGame.FrameHeight - 52 >> 1)+50, 180, 52);
        GameImagines.start_icon.addActionListener(new ActionListener() {
//            添加按钮监听，点击按钮结束开始画面，开始游戏
            @Override
            public void actionPerformed(ActionEvent e) {
                StartGame.state = 1;//游戏开始！
                myThread.requestFocus();
                myThread.remove(GameImagines.start_icon);
                myThread.remove(GameImagines.help);
                new Thread(new MyBullet()).start();//我方子弹投放开始
                new Thread(new PageUPdate(myThread)).start();//页面更新开始
                new Thread(new EnemyPlane()).start();//敌机投放开始
                new Thread(new EnemyBullet()).start();//敌机子弹投放开始
                new Thread(new CollisionMonitor(myThread)).start();//碰撞监测开始
                new Thread(new BossBullet()).start();
            }
        });

        GameImagines.help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Help(game_frame).init();
                myThread.requestFocus();
            }
        });
        GameImagines.help.setOpaque(false);
        GameImagines.start_icon.setOpaque(false);
        container.add(this);
        GameImagines.start_icon.setBounds(StartGame.FrameWidth - 180 >> 1, (StartGame.FrameHeight - 52 >> 1)-50, 180, 52);

        //开始页面结束

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keycode = e.getKeyCode();
                if (keycode == KeyEvent.VK_UP && StartGame.state == 1) {
                    Datas.myPlane.up=true;
                }  if (keycode == KeyEvent.VK_DOWN && StartGame.state == 1) {
                    Datas.myPlane.down=true;
                }  if (keycode == KeyEvent.VK_LEFT && StartGame.state == 1) {
                    Datas.myPlane.left=true;
                }  if (keycode == KeyEvent.VK_RIGHT && StartGame.state == 1) {
                    Datas.myPlane.right=true;
                }  if(keycode==KeyEvent.VK_SPACE&&StartGame.state==1){
                    StartGame.state=2;
                    myThread.add(GameImagines.pause);
                    GameImagines.pause.setBounds(StartGame.FrameWidth - 307 >> 1, (StartGame.FrameHeight -75 >> 1)-50, 307, 75);
                    myThread.repaint();
                }else if(keycode==KeyEvent.VK_SPACE&&StartGame.state==2){
                    StartGame.state=1;
                    myThread.remove(GameImagines.pause);
                    myThread.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keycode = e.getKeyCode();
                if (keycode == KeyEvent.VK_UP && StartGame.state == 1) {
                    Datas.myPlane.up=false;
                }  if (keycode == KeyEvent.VK_DOWN && StartGame.state == 1) {
                    Datas.myPlane.down=false;
                }  if (keycode == KeyEvent.VK_LEFT && StartGame.state == 1) {
                    Datas.myPlane.left=false;
                }  if (keycode == KeyEvent.VK_RIGHT && StartGame.state == 1) {
                    Datas.myPlane.right=false;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (StartGame.state == 0)//开始界面
        {
            g.drawImage(GameImagines.start_page, 0, 0, StartGame.FrameWidth, StartGame.FrameHeight, this);
        } else {//游戏开始
            g.drawImage(GameImagines.BackgroundImage, 0, StartGame.bgi_y, 700, 1800, this);//先画背景

        }
    }

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
}

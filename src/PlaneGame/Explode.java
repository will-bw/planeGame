package PlaneGame;

import GameObject.GameObject;
import GameObject.Planes.EnemyPlane;
import GameObject.Planes.Planes;
import GameUtils.Datas;
import GameUtils.GameImagines;

import javax.swing.*;

public class Explode implements Runnable {
    private GameObject object;
    private GamePanel gamePanel;
    private int x, y;//爆炸坐标
    private int width, height;

    public Explode(int x, int y, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.x = x;
        this.y = y;
        width = 5;
        height = 5;
    }

    @Override
    public void run() {
        synchronized (gamePanel) {
            while(StartGame.state==2){
                //暂停等待
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gamePanel.add(GameImagines.explode);
            GameImagines.explode.setBounds(x + (60 - width) >> 1, y + (60 - height) >> 1, 5, 5);
            for (int i = 2; i < 10; i++) {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                width = i * 5;
                height = i * 5;
                GameImagines.explode.setBounds(x + (60 - width) / 2, y + (60 - height) / 2, width, height);
            }

            for (int i = 2; i < 10; i++) {
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                width -= 5;
                height -= 5;
                GameImagines.explode.setBounds(x + (60 - width) / 2, y + (60 - height) / 2, width, height);
            }
            gamePanel.remove(GameImagines.explode);
        }
    }
}

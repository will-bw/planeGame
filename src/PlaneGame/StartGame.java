package PlaneGame;

import GameUtils.Datas;

import javax.swing.*;
import java.awt.*;

public class StartGame {

    public final static int FrameHeight = 900;//窗口尺寸
    public final static int FrameWidth=700;
    public static int state;//当前游戏状态,0是还没开始,1是正在进行,2是暂停,3是失败,4是通关
    public static int bgi_y;//背景的y坐标
    public static int score;//得分
    JFrame frame ;//窗口

    public StartGame() {}

    public void init() throws HeadlessException {

        frame = new JFrame("飞机大战");
        bgi_y=-900;
        score=0;
        state = 0;//初始状态
        frame.setVisible(true);//设置窗口可见
        frame.setSize(FrameWidth, FrameHeight);//设置窗口大小
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//可点击叉号关闭
        frame.setLocationRelativeTo(null);//窗口位置
        frame.setResizable(false);//窗口不可改变大小
        new GamePanel(frame).init();
    }

    public static void main(String[] args) {

        new StartGame().init();
    }
}

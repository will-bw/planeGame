package PlaneGame;

import GameUtils.GameImagines;

import javax.swing.*;
import java.awt.*;

public class Help extends JDialog {
    String help="<html>  欢迎来到飞机大战，游戏规则如下：<br><br>" +
            "  点击开始游戏按钮开始<br>"+"游戏过程中点击空格暂停游戏<br><br>"+
            "  通过方向键来控制飞机移动<br>"+
            "  我方飞机的生命值为15，<br>敌方普通飞机的生命值为2，<br>boss的生命值为60<br><br>"+
            "  我方子弹伤害为1，敌方普通子弹的伤害为1，<br>  boss的特殊子弹能直接摧毁我方飞机</html>";

    JLabel label = new JLabel(new ImageIcon(GameImagines.helpBack));
    JLabel labelText=new JLabel(help);

    public Help(JFrame owner) {

        super(owner, "帮助");
        setBounds(500,200,600,430);
        setLayout(null);
        setFocusable(false);
    }
    public void init(){
        labelText.setBounds(50,-10,600,400);
        labelText.setFont(new Font("微软雅黑",Font.BOLD,22));
        labelText.setForeground(Color.ORANGE);
        label.setBounds(-10,-10,600,400);
        getContentPane().add(labelText,0);
        getContentPane().add(label,-1);
//        气死人操作
        setVisible(true);
//        这里设置可见要最后设置，不然会出现无法显示的问题，在这卡了大半天

    }

}

package GameObject.Bullets;

import GameObject.GameObject;

import javax.swing.*;
import java.awt.*;

public class Bullets extends GameObject {
    public Bullets() {
    }

    public Bullets(int x, int y,int speed, JLabel image, Rectangle body, int width, int height) {
        super(x, y, image, body, width, height);
        this.speed=speed;
    }
}

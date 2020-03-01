package planewar;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Author:xiang
 * @Date:2020/2/29 19:17
 */
public class Plane extends GameObject {

    boolean left,right,up,down;//飞机的方向控制

    boolean live=true;//判断飞机是否存活
    @Override
    public void drawMyself(Graphics g) {
        if (live) {
            super.drawMyself(g);
            if (left) {
                x -= speed;
            }
            if (right) {
                x += speed;
            }
            if (up) {
                y -= speed;
            }
            if (down) {
                y += speed;
            }
        }
    }

    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
            case KeyEvent.VK_UP:
                up=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
        }
    }

    public void releasedDirection(KeyEvent e){

        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            case KeyEvent.VK_UP:
                up=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
        }

    }

    public Plane(Image img, double x, double y, int speed, int width, int height) {
        super(img, x, y, speed, width, height);
    }
}

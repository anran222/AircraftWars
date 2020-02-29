package planewar;

import java.awt.*;

/**
 * @Author:xiang
 * @Date:2020/2/29 18:45
 * 游戏物体的根类
 */
public class GameObject {
    Image img;//图片
    double x,y;//物体坐标
    int speed;//物体移动的速度
    int width;//窗口宽度
    int height;//窗口高度

    public GameObject() {
    }

    public GameObject(Image img, double x, double y, int speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width=img.getWidth(null);
        this.height=img.getHeight(null);
    }

    public GameObject(Image img, double x, double y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }
    public void drawMyself(Graphics g){
        g.drawImage(img,(int)x,(int)y,width,height,null);
    }
    //所有的物体都是矩形
    public Rectangle getTec(){
        return new Rectangle((int)x,(int)y,width,height);
    }
}

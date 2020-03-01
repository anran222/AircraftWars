package planewar;

import java.awt.*;

/**
 * @Author:xiang
 * @Date:2020/3/1 20:07
 * 爆炸类
 */
public class Explode {
    static double x;
    static double y;
    static Image[] images=new Image[1];

    int count;

    static {
        for (int i = 0; i <1 ; i++) {
            images[i]=GameUtil.getImage("images/boom.jpg");
        }
    }
    public void drawMyself(Graphics g){
        if (count<1) {
            g.drawImage(images[count], (int) x, (int) y, null);
            count++;
        }
    }
    public Explode(){
    }
    public Explode(double x,double y){
        this.x=x;
        this.y=y;
    }
}

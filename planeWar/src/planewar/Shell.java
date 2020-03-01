package planewar;

import java.awt.*;

/**
 * @Author:xiang
 * @Date:2020/3/1 15:58
 * 炮弹类
 */
public class Shell extends GameObject {
    double degree;//炮弹沿着指定角度飞行
    public Shell(){
        x=200;
        y=200;
        degree=Math.random()*Math.PI*2;
        width=10;
        height=10;
        speed=4;
    }

    @Override
    public void drawMyself(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.yellow);
        g.fillOval((int)x,(int)y,width,height);
        g.getColor();
        //根据自己算法指定路径
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        //碰到边界改变路径
        if (y>Constant.GAME_HEIGHT-this.height||y<40){
            degree=-degree;
        }
        if (x<0||x>Constant.GAME_WIDTH-this.width){
            degree=Math.PI-degree;
        }

    }
}

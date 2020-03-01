package planewar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author:xiang
 * @Date:2020/2/29 16:42
 * 游戏主窗口
 */
public class MygameFrame extends Frame {

    Image planeImg=GameUtil.getImage("images/飞机大战飞机.jpg");
    Image background=GameUtil.getImage("images/飞机大战背景.jpg");

    Plane p1=new Plane(planeImg,100,100,3,30,30);
    @Override
    public void paint(Graphics g){   //g是一只画笔完成绘制功能
        g.drawImage(background,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT,null);
        p1.drawMyself(g);
    }


    //初始化窗口
    public void launchFrame(){
        this.setTitle("飞机大战");
        setVisible(true);//是否可见
        setSize(500,500);//窗口大小
        setLocation(300,150);//窗口位置
        //增加关闭窗口的动作
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//正常退出程序
            }
        });
        new PaintThread().start();//启动重画窗口的线程
    }

    //定义一个重画窗口的线程
    class PaintThread extends Thread{
        @Override
        public void run() {
            while (true){
                repaint();//内部类可以直接使用外部类的成员
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Image offScreenImage=null;
    public void update(Graphics g){
        if (offScreenImage==null){
            offScreenImage=this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
            Graphics goff=offScreenImage.getGraphics();
            paint(goff);
            g.drawImage(offScreenImage,0,0,null);
        }
    }

    public static void main(String[] args) {
        MygameFrame gameFrame=new MygameFrame();
        gameFrame.launchFrame();
    }
}

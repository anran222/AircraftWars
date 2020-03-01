package planewar;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * @Author:xiang
 * @Date:2020/2/29 16:42
 * 游戏主窗口
 */
public class MygameFrame extends Frame {

    Image planeImg=GameUtil.getImage("images/plane.jpg");
    Image background=GameUtil.getImage("images/background.jpg");

    Plane p1=new Plane(planeImg,100,100,3,30,30);
    Shell[] shells=new Shell[50];
    Explode expolde;//爆炸
    Date start=new Date();//游戏开始时间
    Date end;//结束时间
    long period=0;//玩了多少秒
    @Override
    public void paint(Graphics g){   //g是一只画笔完成绘制功能
        //画背景
        g.drawImage(background,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT,null);

        //画飞机
        g.setColor(Color.red);
        if (p1.live) {
            period = (System.currentTimeMillis() - start.getTime()) / 1000;
            g.drawString("坚持：" + period, 30, 50);
        }else {
            if (end==null){
                end=new Date();
                period=(end.getTime()-start.getTime())/1000;
            }
            g.setFont(new Font("微软雅黑",Font.BOLD,30));
            g.drawString("最终时间："+period,200,200);
        }
        p1.drawMyself(g);
        for (int i = 0; i <shells.length ; i++) {
            shells[i].drawMyself(g);
            boolean peng=shells[i].getTec().intersects(p1.getTec());
            if (peng){
                p1.live=false;

                //处理爆炸效果
                if (expolde==null) {
                    expolde = new Explode(p1.x, p1.y);
                }
                    expolde.drawMyself(g);
            }
        }
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
        new Thread(new PaintThread()).start();//启动重画窗口的线程
        this.addKeyListener(new KeyMonitor());//启动键盘监听
        //初始化创建50个炮弹
        for (int i = 0; i <50 ; i++) {
            shells[i]=new Shell();
        }
    }

    //定义一个重画窗口的线程
    class PaintThread implements Runnable{
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

    //内部类，实现键盘的监听
    class KeyMonitor extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("按下："+e.getKeyCode());
            p1.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("抬起："+e.getKeyCode());
            p1.releasedDirection(e);
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

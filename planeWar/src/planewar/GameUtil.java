package planewar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * @Author:xiang
 * @Date:2020/2/29 17:26
 * 游戏工具类
 */
public class GameUtil {

    //构造器私有
    private GameUtil(){

    }

    public static Image getImage(String path){             //传入图片路径
        BufferedImage img=null;
        URL u=GameUtil.class.getClassLoader().getResource(path);
        try {
            img=ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}

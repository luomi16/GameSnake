package com.game;
import javax.swing.*;
import java.net.URL;

/*用来获取游戏中所有涉及的图片*/
public class Images {
    /*面向对象思想：将图片进行封装，封装为一个对象，在程序中通过操纵这个对象来操纵图片*/

    /*将图片的路径封装为一个对象：*/
//    class获取字节码
//    getResource获取图片路径
//    在菜单Build下选中第二个Build Module，重写构建一下，生成对应的字节码信息
//    在out文件夹下找图片路径
//    '/'指代相对路径，相对file:/C:/Users/ASUS/Desktop/贪吃蛇小游戏/out/production/TestSnake/而言
    public static URL bodyURL=Images.class.getResource("/images/body.png");

    /*将图片封装为程序中一个对象：*/
//    ImageIcon构造器，可以直接把url传进去
    public static ImageIcon bodyImg=new ImageIcon(bodyURL);

    /*同理，将剩下的图片都封装：*/
    public static URL downURL=Images.class.getResource("/images/down.png");
    public static ImageIcon downImg=new ImageIcon(downURL);

    public static URL foodURL=Images.class.getResource("/images/food.png");
    public static ImageIcon foodImg=new ImageIcon(foodURL);

    public static URL headerURL=Images.class.getResource("/images/header.png");
    public static ImageIcon headerImg=new ImageIcon(headerURL);

    public static URL leftURL=Images.class.getResource("/images/left.png");
    public static ImageIcon leftImg=new ImageIcon(leftURL);

    public static URL rightURL=Images.class.getResource("/images/right.png");
    public static ImageIcon rightImg=new ImageIcon(rightURL);

    public static URL upURL=Images.class.getResource("/images/up.png");
    public static ImageIcon upImg=new ImageIcon(upURL);
}



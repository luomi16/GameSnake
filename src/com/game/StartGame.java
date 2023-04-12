package com.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        /*1.创建一个窗体：*/
        JFrame jf=new JFrame();
        /*2.给窗体设置一个标题：*/
        jf.setTitle("贪吃蛇小游戏");
        /*3.设置窗体弹出的坐标，对应窗体的宽和高：*/
//        获取屏幕的宽、高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jf.setBounds((width-800)/2,(height-800)/2,800,800);
        /*4.设置窗体大小不可调：*/
        jf.setResizable(false);
        /*5.关闭窗口的同时，程序随之关闭：*/
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*6.创建面板:*/
        GamePanel gp=new GamePanel();
//        将面板放入窗体中：
        jf.add(gp);

        /*默认情况下窗体隐藏，需要进行显现(最好放在最后)：*/
        jf.setVisible(true);

    }
}


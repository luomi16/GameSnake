package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/*面板*/
//继承JPanel才具备面板的功能，才成为一个面板
public class GamePanel extends JPanel {
    /*定义两个数组：
     * 一个数组，专门存储蛇的x轴坐标
     * 一个数组，专门存储蛇的y轴坐标
     * */
    int[] snakeX = new int[200];
    int[] snakeY = new int[200];
    /*定义蛇的长度：*/
    int length;

    /*定义蛇的行走方向：*/
    String direction;

    /*游戏只有两个状态：开始、暂停*/
    boolean isStart = false;//默认游戏为暂停状态

    /*加入一个定时器：*/
    Timer timer;

    /*定义食物的xy轴坐标*/
    int foodX, foodY;

    /*定义一个积分：*/
    int score;

    /*加入一个变量，判断小蛇生死状态：*/
    boolean isDie = false;//默认小蛇为活着的状态

    /*自定义一个初始化方法，初始化小蛇的坐标：*/
    public void init() {
        /*初始化蛇的长度：*/
        length = 3;
        /*初始化蛇头坐标:*/
        snakeX[0] = 175;
        snakeY[0] = 275;
        /*初始化第一节身子坐标:*/
        snakeX[1] = 150;
        snakeY[1] = 275;
        /*初始化第二节身子坐标:*/
        snakeX[2] = 125;
        snakeY[2] = 275;

        /*初始化蛇头的方向为右：*/
        direction = "R";//上U 下D 左L 右R

        /*初始化食物的坐标：*/
        foodX = 300;
        foodY = 200;

        /*初始化积分：*/
        score = 0;

    }


    /*构造器，调用一下小蛇默认方法init()，进行面板初始化的时候就默认了蛇的位置:*/
    public GamePanel() {
        init();
//        将焦点定位在当前操作的面板上
        this.setFocusable(true);
//        加入监听：
//        addKeyListener表示加入键盘监听
        this.addKeyListener(new KeyAdapter() {//涉及到适配器模式
            //            监听键盘按下操作
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
//                System.out.println(keyCode);//空格对应32
                /*监听空格：*/
                if (keyCode == KeyEvent.VK_SPACE) {//将空格32封装起来
                    if (isDie) {
//                    全部恢复到初始化状态
                        init();
                        isDie = false;
                    } else {
                        isStart = !isStart;
                        repaint();//重绘
                    }

                }
                /*监听向上箭头：*/
                if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
                    direction = "U";
                }
                /*监听向下箭头：*/
                if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
                    direction = "D";
                }
                /*监听向左箭头：*/
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                    direction = "L";
                }
                /*监听向右箭头：*/
                if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                    direction = "R";
                }

            }
        });

        /*对定时器做初始化：*/
//        延迟毫秒数、一个ActionListener类对象
        timer = new Timer(100, new ActionListener() {//创建ActionListener对象，一个匿名内部类
            //            ActionListener是 事件监听
//            相当于每100ms监听你是否发生一个动作
//            具体动作放入actionPerformed中
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart && isDie == false) {//游戏开始状态、小蛇活着的状态下才动
//                     由最后一节身子往前一节身子上动
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }
//                     动头的位置
                    if ("R".equals(direction)) {
                        snakeX[0] += 25;
                    }
                    if ("L".equals(direction)) {
                        snakeX[0] -= 25;
                    }
                    if ("U".equals(direction)) {
                        snakeY[0] -= 25;
                    }
                    if ("D".equals(direction)) {
                        snakeY[0] += 25;
                    }

                    /*监测碰撞的动作，也就是吃食物的动作：*/
//                     如果蛇头坐标与食物坐标一样时，即吃到食物
                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
//                         蛇长度加一
                        length++;
//                         食物坐标改变,随机生成坐标，随机数必须是25的倍数，x轴【25,725】，y轴【75,700】
//                         方法一：
//                         math.random()-->[0.0,1.0)
//                         math.random()*29-->[0.0,29.0)
//                         (int)(math.random()*29)-->[0,28]
//                         (int)(math.random()*29)+1-->[1,29]
                        foodX = ((int) (Math.random() * 29) + 1) * 25;//[1,29]*25=[25,725]
//                         方法二：
//                         [75,700]-->[3,28]*25
//                         [3,28]-->[0,25]+3
//                         [0,25]
//                         new Random().nextInt(26)-->[0,26)-->[0,25]
                        foodY = ((new Random().nextInt(26)) + 3) * 25;//[3,28]*25=[75,700]

                        /*每吃到一次食物，积分加10*/
                        score += 10;

                    }

                    /*死亡判定：*/
                    for (int i = 1; i < length; i++) {
//                         如果蛇头的坐标和身子的任意一节坐标一样
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
//                             判定为死亡状态
                            isDie = true;
                        }
                    }

                    /* 防止蛇超出边界：(超出边界就死亡) */
                    if (snakeX[0] > 725) {
//                        snakeX[0] = 25;
                        isDie = true;
                    }
                    if (snakeX[0] < 25) {
//                        snakeX[0] = 725;
                        isDie = true;
                    }
                    if (snakeY[0] < 75) {
//                        snakeY[0] = 700;
                        isDie = true;
                    }
                    if (snakeY[0] > 700) {
//                        snakeY[0] = 75;
                        isDie = true;
                    }

                    repaint();//重绘,根据新的蛇头蛇身的位置画
                }
            }
        });
//        启动计时器
        timer.start();
    }

    /*此方法特殊，就相当于图形版的main方法，自动调用*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*1.填充背景颜色：*/
//        颜色由rgb（red green blue）组成
//        可通过点击左边行号旁的色块进行颜色修改
        this.setBackground(new Color(243, 239, 190, 255));

        /*2.画头部的图片header.png:*/
//        Images.headerImg：调用图片类中的header.png图片
//        paintIcon()方法将图片在面板中绘制出来
//        this:当前面板
//        g:使用的画笔
//        x、y:对应坐标轴
        Images.headerImg.paintIcon(this, g, 15, 10);

        /*3.画一个矩形：*/
//        先调节画笔颜色：
        g.setColor(new Color(205, 228, 246));
//        fillRect填充矩形
        g.fillRect(15, 70, 750, 670);


        /*4.画小蛇：*/
//        画蛇头：
        if ("R".equals(direction)) {
            Images.rightImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("L".equals(direction)) {
            Images.leftImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("U".equals(direction)) {
            Images.upImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }
        if ("D".equals(direction)) {
            Images.downImg.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

//        画第一节身子：
//        Images.bodyImg.paintIcon(this,g,snakeX[1],snakeY[1]);
//        画第二节身子：
//        Images.bodyImg.paintIcon(this,g,snakeX[2],snakeY[2]);

//        用循环来画蛇身子：
        for (int i = 1; i < length; i++) {
            Images.bodyImg.paintIcon(this, g, snakeX[i], snakeY[i]);

        }

        /*5.如果游戏是暂停的，界面中间有一个提示语*/
        if (isStart == false) {
            /*画一个文字:*/
//            画笔设置颜色
            g.setColor(new Color(222, 13, 245));
//            设置文字的字体、加粗、大小
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
//            画文字:
//            drawString()方法，画字符串
//            文字内容、xy轴坐标
            g.drawString("点击空格开始游戏", 250, 330);

        }

        /*6.画食物：*/
        Images.foodImg.paintIcon(this, g, foodX, foodY);

        /*7.画积分（画文字）：*/
        g.setColor(new Color(246, 245, 245));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("积分：" + score, 280, 40);

        /*8.画死亡状态：*/
        if (isDie) {
            g.setColor(new Color(200, 110, 150));
            g.setFont(new Font("微软雅黑", Font.BOLD, 35));
            g.drawString("您的积分是： " + score, 50, 330);
            g.drawString("小蛇死亡！游戏停止，按下空格重新开始游戏", 50, 400);

        }

    }
}

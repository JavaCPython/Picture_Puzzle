package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PictureFrame extends JFrame{

    PictureFrame(){
        this.setTitle("Picture_Frame");
        this.setDefaultCloseOperation(3);
        this.setSize(960, 530);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setAlwaysOnTop(true);

        printFrame();

        addButtonEvent();

        this.setVisible(true);
    }

    private JPanel Picture  = new JPanel();

    //将按钮提出来，便于后续使用
    private JButton up_Button = new JButton();
    private JButton down_Button = new JButton();
    private JButton left_Button = new JButton();
    private JButton right_Button = new JButton();
    private JButton qiuzhu_Button = new JButton();
    private JButton chongzhi_Button = new JButton();

    //原始图像数组
    private int[][] Picture_array = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //带空白快的标准答案
    private int[][] ans = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //空白快的坐标
    private int x0 = 0,y0 = 0;

    //写在构造函数里面，这样新建此类就可以直接运行
    public void printFrame(){
        //添加图片
        Picture.setLayout(null);
        Picture.setBounds(150, 114, 360, 360);

        //将图片数组随机打乱
        set_PictureArray();

        //根据打乱的数组将图片添加到JPanel中
        for (int i = 0; i < Picture_array.length; i++) {
            for (int j = 0; j < Picture_array[i].length; j++) {
                JLabel label1 = new JLabel(new ImageIcon("pics/images/" + Picture_array[i][j] + ".png"));
                label1.setBounds(j * 90, i * 90, 90, 90);
                Picture.add(label1);
            }
        }
        this.add(Picture);

        //添加大标题
        JLabel title = new JLabel(new ImageIcon("pics/images/title.png"));
        title.setBounds(354,27,232,57);
        this.add(title);

        //添加示例图
        JLabel picture_demo = new JLabel(new ImageIcon("pics/images/canzhaotu.png"));
        picture_demo.setBounds(574,114,122,121);
        this.add(picture_demo);

        //添加按钮
        //上按钮
        up_Button.setIcon(new ImageIcon("pics/images/shang.png"));
        up_Button.setBounds(732,265,57,57);
        this.add(up_Button);

        //下按钮
        down_Button.setIcon(new ImageIcon("pics/images/xia.png"));
        down_Button.setBounds(732,347,57,57);
        this.add(down_Button);

        //左按钮
        left_Button.setIcon(new ImageIcon("pics/images/zuo.png"));
        left_Button.setBounds(650,347,57,57);
        this.add(left_Button);

        //右按钮
        right_Button.setIcon(new ImageIcon("pics/images/you.png"));
        right_Button.setBounds(813,347,57,57);
        this.add(right_Button);

        //求助按钮
        qiuzhu_Button = new JButton(new ImageIcon("pics/images/qiuzhu.png"));
        qiuzhu_Button.setBounds(626,444,108,45);
        this.add(qiuzhu_Button);

        //重置按钮
        chongzhi_Button = new JButton(new ImageIcon("pics/images/chongzhi.png"));
        chongzhi_Button.setBounds(786,444,108,45);
        this.add(chongzhi_Button);

        //背景图片
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon("pics/images/background.png");
        label.setIcon(icon);
        label.setBounds(0,0,960,530);
        this.add(label);
    }

    public void set_PictureArray(){

        //将数组随机打乱
        for (int i = 0; i < Picture_array.length; i++) {
            for (int j = 0; j < Picture_array[i].length; j++) {

                Random random = new Random();
                int x_ran = random.nextInt(4);
                int y_ran = random.nextInt(4);

                int temp = Picture_array[i][j];
                Picture_array[i][j] = Picture_array[x_ran][y_ran];
                Picture_array[x_ran][y_ran] = temp;
            }
        }

        //找到空白块的位置
        int x = -1,y = -1;
        wc:for (int i = 0; i < Picture_array.length; i++) {
            for (int j = 0; j < Picture_array[i].length; j++) {
                if(Picture_array[i][j] == 0){
                    x = i;
                    y = j;
                    break wc;
                }
            }
        }
        x0 = x;
        y0 = y;
        }

    public void addButtonEvent(){
        //为每个按钮添加事件
        up_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(x0 == 3) {
                    return;
                }
                Picture_array[x0][y0] = Picture_array[x0+1][y0];
                Picture_array[x0+1][y0] = 0;
                x0 = x0 + 1;

                if(isCompare()){
                    success();
                }

                picture_rePaint();
            }
        });

        down_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(x0 == 0){
                    return;
                }
                Picture_array[x0][y0] = Picture_array[x0-1][y0];
                Picture_array[x0-1][y0] = 0;
                x0 = x0 - 1;

                if(isCompare()){
                    success();
                }

                picture_rePaint();
            }
        });

        left_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(y0 == 3){
                    return;
                }
                Picture_array[x0][y0] = Picture_array[x0][y0+1];
                Picture_array[x0][y0+1] = 0;
                y0 = y0 + 1;

                if(isCompare()){
                    success();
                }

                picture_rePaint();
            }
        });

        right_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(y0 == 0){
                    return;
                }
                Picture_array[x0][y0] = Picture_array[x0][y0-1];
                Picture_array[x0][y0-1] = 0;
                y0 = y0 - 1;

                if(isCompare()){
                    success();
                }

                picture_rePaint();
            }
        });

        qiuzhu_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                success();
                picture_rePaint();
            }
        });

        chongzhi_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //使按钮失效
                up_Button.setEnabled(true);
                down_Button.setEnabled(true);
                left_Button.setEnabled(true);
                right_Button.setEnabled(true);
                qiuzhu_Button.setEnabled(true);

                //使数组恢复到初值
                int[][] temp = {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                Picture_array = temp;

                Picture.removeAll();

                set_PictureArray();

                picture_rePaint();
            }
        });


    }

    public void picture_rePaint() {
        //重新绘制图像，根据修改后的数组
        Picture.removeAll();
        for (int i = 0; i < Picture_array.length; i++) {
            for (int j = 0; j < Picture_array[i].length; j++) {
                JLabel label1 = new JLabel(new ImageIcon("pics/images/" + Picture_array[i][j] + ".png"));
                label1.setBounds(j * 90, i * 90, 90, 90);
                Picture.add(label1);
            }
        }
        Picture.repaint();
    }

    public void success(){
        //游戏胜利，设置数组为胜利后的数组，并将所有按钮设置失效（除了重置）
        Picture_array = new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        up_Button.setEnabled(false);
        down_Button.setEnabled(false);
        left_Button.setEnabled(false);
        right_Button.setEnabled(false);
        qiuzhu_Button.setEnabled(false);
    }

    public boolean isCompare(){
        //对比是否已经按照标准答案排序
        for (int i = 0; i < Picture_array.length; i++) {
            for (int j = 0; j < Picture_array[i].length; j++) {
                if(Picture_array[i][j] != ans[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

}

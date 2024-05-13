package edu.hitsz.application;

import edu.hitsz.application.game.EasyGame;
import edu.hitsz.application.game.Game;
import edu.hitsz.application.game.HardGame;
import edu.hitsz.application.game.MediumGame;
import edu.hitsz.component.StartMenu;

import javax.swing.*;
import java.awt.*;

/**
 * 程序入口
 * @author hitsz
 */
public class Main {

    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static final java.awt.CardLayout CARD_LAYOUT = new java.awt.CardLayout(0,0);
    public static final JPanel CARD_PANEL = new JPanel(CARD_LAYOUT);
    public static final JFrame FRAME = new JFrame("Aircraft War");
    public static int currentScore;
    public static String currentUsrName;

    public static void main(String[] args) {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        FRAME.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        FRAME.setResizable(false);
        //设置窗口的大小和位置,居中放置
        FRAME.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StartMenu startMenu = new StartMenu();
        CARD_PANEL.add(startMenu.getMainPanel(),"StartMenu");
        FRAME.add(CARD_PANEL);
        CARD_LAYOUT.show(CARD_PANEL,"StartMenu");
        FRAME.setVisible(true);
    }
    public static void startGame(){
        int mode = StartMenu.getGameMode();
        Game game;
        if(mode == 0){
            game = new EasyGame();
        }
        else if(mode == 1){
            game = new MediumGame();
        }
        else{
            game = new HardGame();
        }
        Main.CARD_PANEL.add(game,"Game");
        Main.CARD_LAYOUT.show(Main.CARD_PANEL,"Game");
        Main.FRAME.setVisible(true);
        game.action();
    }
}

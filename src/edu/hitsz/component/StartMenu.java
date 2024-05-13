package edu.hitsz.component;

import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu {
    private JPanel settingsPanel;
    private JButton newGameButton;
    private JButton settingsButton;

    public static void setGameMode(int gameMode) {
        StartMenu.gameMode = gameMode;
    }
    public static int getGameMode() {
        return StartMenu.gameMode;
    }

    private static int gameMode = 0;

    public StartMenu() {
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingPage settingPage = new SettingPage();
                Main.CARD_PANEL.add(settingPage.getMainPanel(),"SettingPage");
                Main.CARD_LAYOUT.show(Main.CARD_PANEL,"SettingPage");

            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.startGame();
            }
        });
    }
    public JPanel getMainPanel() {
        return settingsPanel;
    }



}

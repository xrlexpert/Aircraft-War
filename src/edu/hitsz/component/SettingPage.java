package edu.hitsz.component;

import edu.hitsz.application.GameConfig;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingPage {
    private JPanel mainPanel;
    private JButton confirmButton;
    private JButton backButton;
    private JPanel buttomPanel;
    private JComboBox modeSelection;
    private JPanel topButton;
    private JComboBox soundSelection;

    private JLabel sound;
    private JLabel mode;
    private static final String easyGameBackGround = "src/images/bg.jpg";
    private static final String mediumGameBackGround = "src/images/bg2.jpg";
    private static final String hardGameBackGround = "src/images/bg3.jpg";

    public SettingPage() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gameMode = modeSelection.getSelectedIndex();
                StartMenu.setGameMode(gameMode);
                if(gameMode == 0){
                    ImageManager.setBACKGROUND_IMAGE(easyGameBackGround);
                    System.out.println("Game mode has been changed to SIMPLE mode.");
                }
                else if(gameMode == 1){
                    ImageManager.setBACKGROUND_IMAGE(mediumGameBackGround);
                    System.out.println("Game mode has been changed to MEDIUM mode.");

                }
                else if(gameMode == 2){
                    ImageManager.setBACKGROUND_IMAGE(hardGameBackGround);
                    System.out.println("Game mode has been changed to HARD mode.");

                }
                int musicFlag = soundSelection.getSelectedIndex();
                if(musicFlag == 0){
                    GameConfig.setMusicFlag(true);
                    System.out.println("Music and sound effects have been ENABLED.");
                }
                else{
                    GameConfig.setMusicFlag(false);
                    System.out.println("Music and sound effects have been DISABLED");
                }


            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.CARD_LAYOUT.show(Main.CARD_PANEL,"StartMenu");

            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}

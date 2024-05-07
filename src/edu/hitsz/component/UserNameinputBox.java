package edu.hitsz.component;

import edu.hitsz.scores.Score;
import edu.hitsz.application.Game;
import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserNameinputBox {
    private JButton confirmButton;
    private JPanel getScorePanel;


    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel getContentPanel;
    private JTextField inputBox;
    private JPanel usrNamePanel;
    private JLabel prompt;

    public UserNameinputBox() {
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.currentUsrName = inputBox.getText();
                Date date = new Date( );
                SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
                Score now = new Score(Main.currentScore,Main.currentUsrName,ft.format(date));
                Game.scoreDao.addScore(now);
                EndPage endPage = new EndPage();
                Main.CARD_PANEL.add(endPage.getMainPanel(),"EndPage");
                Main.CARD_LAYOUT.show(Main.CARD_PANEL,"EndPage");
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}

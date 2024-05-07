package edu.hitsz.component;

import edu.hitsz.scores.Score;
import edu.hitsz.application.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EndPage {
    private JPanel topPanel;
    private JPanel buttomPanel;
    private JPanel mainPanel;
    private JScrollPane tableScrollPanel;
    private JTable scoresTable;
    private JLabel head;
    private JButton deleteButton;
    private JButton exitButton;

    public EndPage(){
        String[] columnName = {"rank","userName","score","date"};
        List<Score> ScoreList = Game.scoreDao.getAllScore();
        System.out.println(ScoreList.size());
        String [][] tableData = new String [ScoreList.size()][4];
        int i = 0;
        for (Score score : ScoreList){
            String [] data = new String [4];
            data[0] = Integer.toString(i + 1);
            data[1] = score.getUsername();
            data[2] = Integer.toString(score.getScore());
            data[3] = score.getDate();
            tableData[i] = data;
            i++;
        }
        DefaultTableModel model = new DefaultTableModel(tableData, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        scoresTable.setModel(model);
        tableScrollPanel.setViewportView(scoresTable);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = scoresTable.getSelectedRow();
                System.out.println(row);
                int result = JOptionPane.showConfirmDialog(deleteButton,
                        "是否确定中删除？");
                if (JOptionPane.YES_OPTION == result && row != -1) {
                    Score score = ScoreList.get(row);
                    model.removeRow(row);
                    Game.scoreDao.deleteScore(score.getId());
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.scoreDao.Print();
                Game.scoreDao.writeObject();
                System.exit(0);
            }
        });

    }
    public JPanel getMainPanel() {
        return mainPanel;
    }


}

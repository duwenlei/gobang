package com.miooim.gobang;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author duwenlei
 * @version 1.0
 * @ClassName WindowUi
 * @Date 2023/10/8 10:16
 * @Description TODO
 */
public class WindowUi {
    private JFrame frame = new JFrame();

    public void init() {
        frame.setTitle("人机五子棋");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(518, 538);
        Chessboard chessboardPanel = new Chessboard();
        frame.setContentPane(chessboardPanel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        chessboardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int sellSize = chessboardPanel.getSellSize();
                int x = (e.getX() - 5) / sellSize;
                int y = (e.getY() - 5) / sellSize;
                Chess chess = new Chess(x, y, 1);
                chessboardPanel.addChess(chess);
                if (chessboardPanel.isWin(chess)) {
                    JOptionPane.showMessageDialog(frame, "您获胜了", "结束", JOptionPane.PLAIN_MESSAGE);
                    // 重开
                    chessboardPanel.reload();
                }

                // 机器下棋
                Chess machineChess = chessboardPanel.machineLuozi();
                if (machineChess == null) {
                    JOptionPane.showMessageDialog(frame, "平局", "结束", JOptionPane.PLAIN_MESSAGE);
                    // 重开
                    chessboardPanel.reload();
                }

                chessboardPanel.addChess(machineChess);
                if (chessboardPanel.isWin(machineChess)) {
                    JOptionPane.showMessageDialog(frame, "人机获胜了", "结束", JOptionPane.PLAIN_MESSAGE);
                    // 重开
                    chessboardPanel.reload();
                }

            }
        });

    }
}

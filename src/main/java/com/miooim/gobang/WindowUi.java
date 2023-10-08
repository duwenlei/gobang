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
        Chessboard contentPane = new Chessboard();
        frame.setContentPane(contentPane);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int sellSize = contentPane.getSellSize();
                int x = (e.getX() - 5) / sellSize;
                int y = (e.getY() - 5) / sellSize;
                contentPane.addChess(new Chess(x, y, 1));
            }
        });

    }
}

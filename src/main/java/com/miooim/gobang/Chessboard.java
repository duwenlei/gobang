package com.miooim.gobang;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘
 *
 * @author duwenlei
 * @version 1.0
 * @ClassName Chessboard
 * @Date 2023/10/8 10:24
 * @Description TODO
 */
public class Chessboard extends JPanel {

    private List<Chess> chessList = new ArrayList<Chess>();

    /**
     * 棋盘线数
     */
    private static final int CHESSBOARD_SIZE = 15;

    /**
     * 边距
     */
    private static final int PADDING = 20;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawLine(g);
        drawChess(g);
    }

    /**
     * 画棋盘
     *
     * @param g
     */
    private void drawLine(Graphics g) {
        // 每一行的距离
        int cellSize = getSellSize();
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            // 横线
            g.drawLine(PADDING, PADDING + cellSize * i, getWidth() - PADDING, PADDING + cellSize * i);

            // 竖线
            g.drawLine(PADDING + cellSize * i, PADDING, PADDING + cellSize * i, PADDING + cellSize * (CHESSBOARD_SIZE - 1));
        }
    }

    private void drawChess(Graphics g) {
        int sellSize = getSellSize();
        for (int i = 0; i < chessList.size(); i++) {
            Chess chess = chessList.get(i);
            switch (chess.getChessOwner()) {
                case -1:
                    g.setColor(Color.WHITE);
                    break;
                case 1:
                    g.setColor(Color.BLACK);
                    break;
                case 0:
                    break;
                default:
                    throw new IllegalArgumentException("chess owner illegal.");
            }
            int x = chess.getX();
            int y = chess.getY();
            g.fillOval(x * sellSize + PADDING -sellSize/2, y * sellSize + PADDING -sellSize/2, sellSize, sellSize);
        }
    }

    public void addChess(Chess chess) {
        chessList.add(chess);
        repaint();
    }

    public int getSellSize() {
        // 每一行的距离
        return (getWidth() - 2 * PADDING) / (CHESSBOARD_SIZE - 1);
    }
}

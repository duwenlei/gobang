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

    /**
     * 棋子记录
     */
    private List<Chess> chessList = new ArrayList<Chess>();

    /**
     * 棋子站位
     */
    private int[][] chessPos = new int[CHESSBOARD_SIZE][CHESSBOARD_SIZE];

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
            g.fillOval(x * sellSize + PADDING - sellSize / 2, y * sellSize + PADDING - sellSize / 2, sellSize, sellSize);
        }
    }

    public void addChess(Chess chess) {
        if (isLegal(chess.getX(), chess.getY())) {
            chessList.add(chess);
            chessPos[chess.getX()][chess.getY()] = chess.getChessOwner();
            repaint();
        }
    }

    public boolean isLegal(int x, int y) {
        if (x >= 0 && y >= 0 && x < CHESSBOARD_SIZE && y < CHESSBOARD_SIZE && chessPos[x][y] == 0) {
            return true;
        }
        return false;
    }

    public boolean isWin(Chess chess) {
        // 水平方向
        if (horizontal(chess)) {
            return true;
        }

        // 垂直方向
        if (vertical(chess)) {
            return true;
        }

        // 左上右下 斜向
        if (leftSlant(chess)) {
            return true;
        }

        // 右上左下 斜向.
        if (rightSlant(chess)) {
            return true;
        }

        return false;
    }

    /**
     * ----*
     * ---*-
     * --*--
     * -*---
     * *----
     *
     * @return
     */
    private boolean rightSlant(Chess chess) {
        int x = chess.getX();
        int y = chess.getY();
        int owner = chess.getChessOwner();

        int sum = 0;
        int xi = x + 1;
        int yi = y - 1;

        // 右上 xi++ yi--
        while (xi < CHESSBOARD_SIZE && yi >= 0) {
            if (chessPos[xi][yi] == owner) {
                sum++;
            } else {
                break;
            }
            xi++;
            yi--;
        }

        xi = x - 1;
        yi = y + 1;
        // 左下 xi-- yi++
        while (xi >= 0 && yi < CHESSBOARD_SIZE) {
            if (chessPos[xi][yi] == owner) {
                sum++;
            } else {
                break;
            }
            xi--;
            yi++;
        }

        if (sum >= 4) {
            return true;
        }
        return false;
    }

    /**
     * *----
     * -*---
     * --*--
     * ---*-
     * ----*
     *
     * @return
     */
    private boolean leftSlant(Chess chess) {
        int x = chess.getX();
        int y = chess.getY();
        int owner = chess.getChessOwner();

        int sum = 0;
        int xi = x - 1;
        int yi = y - 1;
        // 左上
        while (xi >= 0 && yi >= 0) {
            if (chessPos[xi][yi] == owner) {
                sum++;
            } else {
                break;
            }
            xi--;
            yi--;
        }

        xi = x + 1;
        yi = y + 1;
        while (xi < CHESSBOARD_SIZE && yi < CHESSBOARD_SIZE) {
            if (chessPos[xi][yi] == owner) {
                sum++;
            } else {
                break;
            }
            xi++;
            yi++;
        }

        if (sum >= 4) {
            return true;
        }
        return false;
    }

    /**
     * --*--
     * --*--
     * --*--
     * --*--
     * --*--
     *
     * @return
     */
    private boolean vertical(Chess chess) {
        int x = chess.getX();
        int y = chess.getY();
        int owner = chess.getChessOwner();

        int sum = 0;
        // 上
        for (int i = y - 1; i >= 0; i--) {
            if (chessPos[x][i] == owner) {
                sum++;
            } else {
                break;
            }
        }

        // 下
        for (int i = y + 1; i < CHESSBOARD_SIZE; i++) {
            if (chessPos[x][i] == owner) {
                sum++;
            } else {
                break;
            }
        }

        if (sum >= 4) {
            return true;
        }
        return false;
    }

    /**
     * -----
     * *****
     * -----
     *
     * @return
     */
    private boolean horizontal(Chess chess) {
        int x = chess.getX();
        int y = chess.getY();
        int owner = chess.getChessOwner();

        int sum = 0;
        // 左
        for (int i = x - 1; i >= 0; i--) {
            if (chessPos[i][y] == owner) {
                sum++;
            } else {
                break;
            }
        }

        // 右
        for (int i = x + 1; i < CHESSBOARD_SIZE; i++) {
            if (chessPos[i][y] == owner) {
                sum++;
            } else {
                break;
            }
        }
        if (sum >= 4) {
            return true;
        }

        return false;
    }

    private static int[][] score = new int[CHESSBOARD_SIZE][CHESSBOARD_SIZE];

    /**
     * 机器下棋
     *
     * @return
     */
    public Chess machineLuozi() {
        //每次都初始化下score评分数组
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            for (int j = 0; j < CHESSBOARD_SIZE; j++) {
                score[i][j] = 0;
            }
        }

        //每次机器找寻落子位置，评分都重新算一遍（虽然算了很多多余的，因为上次落子时候算的大多都没变）
        //先定义一些变量
        int humanChessmanNum = 0;//五元组中的黑棋数量
        int machineChessmanNum = 0;//五元组中的白棋数量
        int tupleScoreTmp = 0;//五元组得分临时变量

        int goalX = -1;//目标位置x坐标
        int goalY = -1;//目标位置y坐标
        int maxScore = -1;//最大分数

        //1.扫描横向的15个行
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                int k = j;
                while (k < j + 5) {

                    if (chessPos[i][k] == -1) machineChessmanNum++;
                    else if (chessPos[i][k] == 1) humanChessmanNum++;

                    k++;
                }
                tupleScoreTmp = ScoreRules.getScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++) {
                    score[i][k] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //2.扫描纵向15行
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                int k = j;
                while (k < j + 5) {
                    if (chessPos[k][i] == -1) machineChessmanNum++;
                    else if (chessPos[k][i] == 1) humanChessmanNum++;

                    k++;
                }
                tupleScoreTmp = ScoreRules.getScore(humanChessmanNum, machineChessmanNum);
                //为该五元组的每个位置添加分数
                for (k = j; k < j + 5; k++) {
                    score[k][i] += tupleScoreTmp;
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量
            }
        }

        //3.扫描右上角到左下角上侧部分
        for (int i = 14; i >= 4; i--) {
            for (int k = i, j = 0; j < 15 && k >= 0; j++, k--) {
                int m = k;
                int n = j;
                while (m > k - 5 && k - 5 >= -1) {
                    if (chessPos[m][n] == -1) machineChessmanNum++;
                    else if (chessPos[m][n] == 1) humanChessmanNum++;

                    m--;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k - 5) {
                    tupleScoreTmp = ScoreRules.getScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m > k - 5; m--, n++) {
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //4.扫描右上角到左下角下侧部分
        for (int i = 1; i < 15; i++) {
            for (int k = i, j = 14; j >= 0 && k < 15; j--, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chessPos[n][m] == -1) machineChessmanNum++;
                    else if (chessPos[n][m] == 1) humanChessmanNum++;

                    m++;
                    n--;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = ScoreRules.getScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n--) {
                        score[n][m] += tupleScoreTmp;
                    }
                }
                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //5.扫描左上角到右下角上侧部分
        for (int i = 0; i < 11; i++) {
            for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chessPos[m][n] == -1) machineChessmanNum++;
                    else if (chessPos[m][n] == 1) humanChessmanNum++;

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = ScoreRules.getScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        score[m][n] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //6.扫描左上角到右下角下侧部分
        for (int i = 1; i < 11; i++) {
            for (int k = i, j = 0; j < 15 && k < 15; j++, k++) {
                int m = k;
                int n = j;
                while (m < k + 5 && k + 5 <= 15) {
                    if (chessPos[n][m] == -1) machineChessmanNum++;
                    else if (chessPos[n][m] == 1) humanChessmanNum++;

                    m++;
                    n++;
                }
                //注意斜向判断的时候，可能构不成五元组（靠近四个角落），遇到这种情况要忽略掉
                if (m == k + 5) {
                    tupleScoreTmp = ScoreRules.getScore(humanChessmanNum, machineChessmanNum);
                    //为该五元组的每个位置添加分数
                    for (m = k, n = j; m < k + 5; m++, n++) {
                        score[n][m] += tupleScoreTmp;
                    }
                }

                //置零
                humanChessmanNum = 0;//五元组中的黑棋数量
                machineChessmanNum = 0;//五元组中的白棋数量
                tupleScoreTmp = 0;//五元组得分临时变量

            }
        }

        //从空位置中找到得分最大的位置
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (chessPos[i][j] == Chess.K && score[i][j] > maxScore) {
                    goalX = i;
                    goalY = j;
                    maxScore = score[i][j];
                }
            }
        }

        if (goalX == -1 && goalY == -1) {
            return null;
        }

        return new Chess(goalX, goalY, Chess.M);
    }

    public int getSellSize() {
        // 每一行的距离
        return (getWidth() - 2 * PADDING) / (CHESSBOARD_SIZE - 1);
    }

    public void reload() {
        for (int i = 0; i < CHESSBOARD_SIZE; i++) {
            for (int j = 0; j < CHESSBOARD_SIZE; j++) {
                chessPos[i][j] = 0;
            }
        }
        chessList.clear();
        repaint();
    }
}

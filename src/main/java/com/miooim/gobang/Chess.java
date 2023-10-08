package com.miooim.gobang;

/**
 * 棋子
 *
 * @author duwenlei
 * @version 1.0
 * @ClassName Chess
 * @Date 2023/10/8 15:30
 * @Description TODO
 */
public class Chess {
    /**
     * x 轴
     */
    private int x;

    /**
     * y 轴
     */
    private int y;

    /**
     * 棋子归属
     * -1 ：机器
     * 0 ：空
     * 1 ：人
     */
    private int chessOwner;

    public Chess(int x, int y, int chessOwner) {
        this.x = x;
        this.y = y;
        this.chessOwner = chessOwner;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getChessOwner() {
        return chessOwner;
    }

    public void setChessOwner(int chessOwner) {
        this.chessOwner = chessOwner;
    }
}

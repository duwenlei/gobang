package com.miooim.gobang;

/**
 * 计分规则表
 *
 * @author duwenlei
 * @version 1.0
 * @ClassName ScoreRules
 * @Date 2023/10/9 12:58
 * @Description TODO
 */
public class ScoreRules {

    /**
     * @param m 机器子数
     * @param h 人类子数
     * @return
     */
    public static int getScore(int m, int h) {
        // 既没有机器也没有人类棋子
        if (m == 0 && h == 0) {
            return 7;
        }

        if (m == 1 && h == 0) {
            return 35;
        }

        if (m == 2 && h == 0) {
            return 800;
        }

        if (m == 3 && h == 0) {
            return 15000;
        }

        if (m == 4 && h == 0) {
            return 800000;
        }

        if (m == 0 && h == 1) {
            return 15;
        }

        if (m == 0 && h == 2) {
            return 400;
        }

        if (m == 0 && h == 3) {
            return 400;
        }

        if (m == 0 && h == 4) {
            return 100000;
        }

        // 既有机器也有人类棋子
        return 0;
    }
}

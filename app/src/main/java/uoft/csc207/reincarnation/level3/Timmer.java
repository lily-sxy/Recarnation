package uoft.csc207.reincarnation.level3;

import java.text.NumberFormat;

class Timmer {

    /**
     * the start time of the game.
     */
    private long startTime = System.currentTimeMillis();

    /**
     * the total played time of the game.
     */
    private long countTime;

    /**
     * the time that you pause the game.
     */
    private long pauseTime;

    /**
     * the time when you start the pausing view.
     */
    private long pauseStart;

    /**
     * update the count time.
     */
    int updateTime() {
        countTime = System.currentTimeMillis() - startTime - pauseTime;
        return (int) countTime;
    }

    /**
     * format the time for view.
     */
    String formatTime(int time) {
        NumberFormat format1 = NumberFormat.getInstance();
        format1.setMaximumIntegerDigits(2);
        format1.setMinimumIntegerDigits(2);
        NumberFormat format2 = NumberFormat.getInstance();
        format2.setMinimumIntegerDigits(3);
        format2.setMaximumIntegerDigits(3);
        int ms = (int) (time % 1000);
        int s = (int) (((time % 60000) - ms) / 1000);
        int min = (int) ((time - ms - s * 60) / 60000);
        return format1.format(min) + ":" + format1.format(s) + ":" + format2.format(ms);
    }

    /**
     * determent if the player lose the game.
     *
     * @return if the player lose the game.
     */
    boolean ifLose() {
        return countTime >= 60000;
    }

    /**
     * get the total played time.
     *
     * @return the total played time.
     */
    int getCountTime() {
        return (int) countTime;
    }

    /**
     * call it when start pausing.
     */
    void startPause() {
        pauseStart = System.currentTimeMillis();
    }

    /**
     * call it when resume.
     */
    void pauseEnd() {
        pauseTime += System.currentTimeMillis() - pauseStart;
        pauseStart = 0;
    }

    /**
     * call it when you want to get a new timer.
     */
    void renew() {
        countTime = 0;
        startTime = System.currentTimeMillis();
        pauseTime = 0;
    }
}

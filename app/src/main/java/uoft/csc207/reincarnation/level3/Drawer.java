package uoft.csc207.reincarnation.level3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import uoft.csc207.reincarnation.StartGame;
import uoft.csc207.reincarnation.language.Language;
import uoft.csc207.reincarnation.level3.item.Role;

/**
 * Represent a Drawer in FroggerManager
 * A drawer can be used to draw the graphs in graph factory on canvas
 */
class Drawer {
    /**
     * the Graph factory of the graph
     */
    private GraphFactory graphFactory;
    /**
     * the resume bottom in the pause page
     */
    private Paint resumeButton = new Paint();
    /**
     * the pause page
     */
    private Paint pauseView = new Paint();
    /**
     * the hint in the pause page
     */
    private Paint hint1 = new Paint();
    /**
     * the hint in the pause page
     */
    private Paint hint2 = new Paint();
    /**
     * the hint in the pause page
     */
    private Paint hint3 = new Paint();
    /**
     * the exist bottom in the pause page
     */
    private Paint exitButton = new Paint();
    /**
     * the level up page
     */
    private Paint levelView = new Paint();
    /**
     * the reminder of level up
     */
    private Paint levelUp = new Paint();
    /**
     * the yes bottom in the level up page
     */
    private Paint yesButton = new Paint();
    /**
     * the no bottom in the level up page
     */
    private Paint noButton = new Paint();
    /**
     * the languase that used in each page
     */
    private Language language = StartGame.getCurrentUser().getLanguage();


    /**
     * Initialize a drawer
     *
     * @param graphFactory: the graph factory that used in the drawer
     */
    Drawer(GraphFactory graphFactory) {
        this.graphFactory = graphFactory;
    }

    /**
     * Draw the background of the game on Canvas.
     *
     * @param canvas canvas used in the game.
     */
    void drawBackground(Canvas canvas) {
        Paint paintBackground = new Paint();
        paintBackground.setStyle(Paint.Style.FILL);
        Bitmap backgroundMap = graphFactory.createBitmap("Background");
        canvas.drawBitmap(backgroundMap, 0, 0, paintBackground);
    }

    /**
     * Draw the timer of the game through Canvas.
     *
     * @param canvas canvas used in the game.
     * @param time   the time of the timer that used to draw in the canvas.
     */
    void drawTimmer(Canvas canvas, String time) {
        Paint paintTime = new Paint();
        paintTime.setColor(Color.BLACK);
        paintTime.setTextSize(50);
        paintTime.setTypeface(Typeface.DEFAULT);
        canvas.drawText(time, 1500, 1000, paintTime);
    }

    /**
     * Draw the role that player played of the game on Canvas.
     *
     * @param canvas canvas used in the game.
     * @param role   the role that used to draw in the canvas.
     */
    void drawDeadTime(Canvas canvas, Role role) {

        Paint paintDead = new Paint();
        paintDead.setColor(Color.BLACK);
        paintDead.setTextSize(50);
        paintDead.setTypeface(Typeface.DEFAULT);
        String deadTime = language.getText("frogger.view.death") + role.getDeadTime();
        canvas.drawText(deadTime, 1700, 110, paintDead);
    }

    /**
     * Draw pause bottom of the game on Canvas.
     *
     * @param canvas canvas used in the game.
     */
    void drawPauseButton(Canvas canvas) {
        Paint button1 = new Paint();
        button1.setColor(Color.GRAY);
        button1.setStyle(Paint.Style.FILL);
        canvas.drawRect(1970, 110, 1980, 70, button1);
        Paint button2 = new Paint();
        button2.setColor(Color.GRAY);
        button2.setStyle(Paint.Style.FILL);
        canvas.drawRect(1990, 110, 2000, 70, button2);
    }

    /**
     * Draw pause page with resume, exit and hint after player pressed pause bottom.
     *
     * @param canvas canvas used in the game.
     */
    void drawPauseView(Canvas canvas) {
        pauseView.setColor(Color.BLACK);
        pauseView.setStyle(Paint.Style.FILL);
        pauseView.setAlpha(100);
        canvas.drawRect(0, 0, 10000, 10000, pauseView);

        // draw Hint;
        hint1.setTextSize(60);
        hint1.setColor(Color.CYAN);
        hint1.setTypeface(Typeface.DEFAULT);
        hint1.setAlpha(255);
        canvas.drawText(language.getText("frogger.pause.hint1"), 500, 800, hint1);

        hint2.setTextSize(60);
        hint2.setColor(Color.CYAN);
        hint2.setTypeface(Typeface.DEFAULT);
        hint2.setAlpha(255);
        canvas.drawText(language.getText("frogger.pause.hint2"), 500, 870, hint2);

        hint3.setTextSize(60);
        hint3.setColor(Color.CYAN);
        hint3.setTypeface(Typeface.DEFAULT);
        hint3.setAlpha(255);
        canvas.drawText(language.getText("frogger.pause.hint3"), 500, 940, hint3);

        // draw Resume button;
        resumeButton.setTextSize(100);
        resumeButton.setColor(Color.WHITE);
        resumeButton.setTypeface(Typeface.DEFAULT_BOLD);
        resumeButton.setAlpha(255);
        canvas.drawText(language.getText("frogger.pause.resume"), 800, 400, resumeButton);


        // draw Exit button;
        exitButton.setTextSize(100);
        exitButton.setColor(Color.WHITE);
        exitButton.setTypeface(Typeface.DEFAULT_BOLD);
        exitButton.setAlpha(255);
        canvas.drawText(language.getText("frogger.pause.exit"), 800, 600, exitButton);
    }

    /**
     * Resume the game by adjust the alpha of the page to show the game page.
     */
    void resumeView() {
        pauseView.setAlpha(0);
        hint1.setAlpha(0);
        hint2.setAlpha(0);
        hint3.setAlpha(0);
        resumeButton.setAlpha(0);
        exitButton.setAlpha(0);
    }

    /**
     * Draw level up page with after the player pass current level, where this level is not the
     * last level of the game. It draw with whether user want to level up and the yes or no
     * bottom.
     *
     * @param canvas canvas use in the game.
     */
    void drawLevelView(Canvas canvas) {
        levelView.setColor(Color.BLACK);
        levelView.setStyle(Paint.Style.FILL);
        levelView.setAlpha(255);
        canvas.drawRect(0, 0, 10000, 10000, levelView);

        //draw levelup
        levelUp.setTextSize(80);
        levelUp.setColor(Color.WHITE);
        levelUp.setTypeface(Typeface.DEFAULT);
        levelUp.setAlpha(255);
        canvas.drawText(language.getText("frogger.level.title"), 700, 300, levelUp);

        // press 'yes' to resume the game
        yesButton.setTextSize(100);
        yesButton.setColor(Color.WHITE);
        yesButton.setTypeface(Typeface.DEFAULT);
        yesButton.setAlpha(255);
        canvas.drawText(language.getText("frogger.level.yes"), 700, 650, yesButton);

        // press 'no' to end this game
        noButton.setTextSize(100);
        noButton.setColor(Color.WHITE);
        noButton.setTypeface(Typeface.DEFAULT);
        noButton.setAlpha(255);
        canvas.drawText(language.getText("frogger.level.no"), 700, 750, noButton);

    }

    /**
     * Close the level up page by adjusting the alpha.
     */
    void closeLevelView() {
        levelView.setAlpha(0);
        levelUp.setAlpha(0);
        yesButton.setAlpha(0);
        noButton.setAlpha(0);
    }

    /**
     * Draw the Win page after the game finish or after the play partially win if player choose to
     * finish this game after playing current level.
     *
     * @param canvas     canvas use in the game.
     * @param timeString the total time used in the game that will be used to shown in the win page.
     * @param deadTime   the total times of the player dead in the game.
     * @param iScore     the total score that play get in this game.
     */
    void drawWin(Canvas canvas, String timeString, String deadTime, double iScore) {
        Paint endingpage = new Paint();
        endingpage.setColor(Color.WHITE);
        endingpage.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, 10000, 10000, endingpage);

        // draw you Win
        Paint youWin = new Paint();
        youWin.setTextSize(80);
        youWin.setColor(Color.BLACK);
        youWin.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("frogger.win.title"), 700, 300, youWin);

        // draw using time
        Paint usingTime = new Paint();
        usingTime.setTextSize(80);
        usingTime.setColor(Color.BLACK);
        usingTime.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("frogger.win.time") + timeString, 700, 400, usingTime);

        // draw Death;
        Paint death = new Paint();
        death.setTextSize(80);
        death.setColor(Color.BLACK);
        death.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("frogger.win.death") + deadTime, 700, 500, death);

        // draw score
        Paint score = new Paint();
        score.setTextSize(80);
        score.setColor(Color.BLACK);
        score.setTypeface(Typeface.DEFAULT);
        String finalScore = String.valueOf(iScore);
        canvas.drawText(language.getText("frogger.win.score") + finalScore, 700, 600, score);

        // draw Continuous
        Paint continuous = new Paint();
        continuous.setTextSize(80);
        continuous.setColor(Color.BLACK);
        continuous.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("frogger.win.continuous"), 700, 800, continuous);
    }

    /**
     * Draw page shown after the player lose the game with bottom to choose whether to replay or
     * exists the game.
     *
     * @param canvas canvas use in the game.
     */
    void drawLoseView(Canvas canvas) {

        Paint loseView = new Paint();
        loseView.setColor(Color.WHITE);
        loseView.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, 10000, 10000, loseView);

        // draw you Win
        Paint youLose = new Paint();
        youLose.setTextSize(80);
        youLose.setColor(Color.BLACK);
        youLose.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("fail"), 700, 400, youLose);

        // draw restart
        Paint restart = new Paint();
        restart.setTextSize(80);
        restart.setColor(Color.BLACK);
        restart.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("frogger.lose.restart"), 700, 650, restart);

        // draw exit
        Paint exit = new Paint();
        exit.setTextSize(80);
        exit.setColor(Color.BLACK);
        exit.setTypeface(Typeface.DEFAULT);
        canvas.drawText(language.getText("frogger.lose.exit"), 700, 750, exit);
    }
}

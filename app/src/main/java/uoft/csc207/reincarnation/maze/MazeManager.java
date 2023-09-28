package uoft.csc207.reincarnation.maze;

import android.graphics.Canvas;

import java.util.Map;
import java.util.HashMap;

import uoft.csc207.reincarnation.maze.dimension.IMaze;
import uoft.csc207.reincarnation.maze.drawer.IDrawer;

class MazeManager {
    private float blockSize;
    private boolean give_up = false;
    private Map<String, Double> stats;

    private IMaze maze;
    private IDrawer drawer;

    MazeManager(float block_size, int sc_width, int sc_height) {
        this.stats = new HashMap<>();
        this.stats.put("maze_played", 0.0);
        this.stats.put("maze_won", 0.0);
        this.stats.put("distance_walked", 0.0);
        this.stats.put("coin_picked", 0.0);
        this.stats.put("last_seed", 0.0);

        blockSize = block_size;
        MazeFactory factory = new MazeFactory("2D", 64, blockSize, sc_width, sc_height);

        maze = factory.getMaze();
        drawer = factory.getDrawer();
    }

    void setDrawing(float block_size_in, int sc_width, int sc_height) {
        blockSize = block_size_in;
        drawer.setSize((int) block_size_in);
        drawer.updateScreen(sc_width, sc_height);
    }

    /**
     * Update the maze.
     * This method do nothing if maze have no moving entity.
     */
    public void update() {
    }

    public void update(float dx, float dy) {
        maze.movePlayer(dx, dy);
        if (maze.isEnd())
            this.collectStat();
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            drawer.draw(canvas);
        }
    }

    boolean isEnd() {
        return this.maze.isEnd() || this.give_up;
    }

    boolean isWin() {
        return !this.give_up;
    }

    double getCoin() {
        return this.maze.getCoin();
    }

    double getDistance() {
        return maze.getDistance();
    }

    long getSeed() {
        return this.maze.getSeed();
    }

    int getFail() {
        int played = (int) (double) this.stats.get("maze_played");
        int won = (int) (double) this.stats.get("maze_won");
        return played - won;
    }

    Map<String, Double> getStats() {
        return this.stats;
    }

    private void collectStat() {
        this.stats.put("maze_played", this.stats.get("maze_played") + 1);
        if (!this.give_up)
            this.stats.put("maze_won", this.stats.get("maze_won") + 1);
        this.stats.put("distance_walked", this.stats.get("distance_walked") + this.getDistance());
        this.stats.put("coin_picked", this.stats.get("coin_picked") + this.getCoin());
        this.stats.put("last_seed", (double) getSeed());
    }

    // This means give up on current maze and do a new one
    void retry(int sc_width, int sc_height) {
        this.give_up = true;
        this.collectStat();

        MazeFactory mf;
        switch ((int) (double) this.stats.get("maze_played")) {
            case 0:
            case 1:
                mf = new MazeFactory("2D", 64, blockSize, sc_width, sc_height);
                break;
            case 2:
            case 3:
                mf = new MazeFactory("2D", 32, blockSize, sc_width, sc_height);
                break;
            case 4:
                mf = new MazeFactory("2T", 32, blockSize, sc_width, sc_height);
                break;
            case 5:
                mf = new MazeFactory("2T", 16, blockSize, sc_width, sc_height);
                break;
            case 6:
            case 7:
                mf = new MazeFactory("2T", 8, blockSize, sc_width, sc_height);
                break;
            case 8:
                mf = new MazeFactory("2DS", 16, blockSize, sc_width, sc_height);
                break;
            default:
                mf = new MazeFactory("2D", 8, blockSize, sc_width, sc_height);
                break;
        }
        this.maze = mf.getMaze();
        this.drawer = mf.getDrawer();
        this.give_up = false;
    }

    // This means completely give up on mazes and exit
    void giveUp() {
        give_up = true;
        this.collectStat();
    }
}

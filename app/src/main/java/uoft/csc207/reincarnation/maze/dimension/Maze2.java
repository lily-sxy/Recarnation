package uoft.csc207.reincarnation.maze.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import uoft.csc207.reincarnation.maze.entity.Entity2;
import uoft.csc207.reincarnation.maze.entity.IEntity;
import uoft.csc207.reincarnation.maze.exception.PointOutOfMapException;
import uoft.csc207.reincarnation.maze.entity.Coin2;
import uoft.csc207.reincarnation.maze.entity.Player2;

/**
 * A 2D maze with x and y coordinates.
 * Maze2 implements interface IMaze.
 */
public class Maze2 implements IMaze<Point2> {
    private Point2 pEntry;
    private long seed;

    private Player2 player;

    private int height;
    private int width;

    /* true if the point is walk way.
     */
    private PointType[][] map;
    private List<Entity2> entities;

    private boolean shouldEnd;

    /**
     * Initialize a new 2D maze.
     *
     * @param h_in The height of the maze.
     * @param w_in The width of the maze.
     * @param pIn  The entry point of the maze.
     */
    public Maze2(int h_in, int w_in, Point2 pIn) {
        seed = new Random().nextInt();
        pEntry = pIn;
        height = h_in;
        width = w_in;
        shouldEnd = false;
        map = new PointType[height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                map[i][j] = PointType.WALL;
        entities = new ArrayList<>();

        MazeMaker mmk = new MazeMaker(this, new Random(seed));
        Map<String, Integer> options = new HashMap<>();
        options.put("NumCoin", 5);

        try {
            mmk.generate(options);
        } catch (PointOutOfMapException e) {
            e.printStackTrace();
        }

        player = new Player2(pEntry);
        entities.add(player);
    }

    /**
     * Get points adjacent to a given point.
     * Adjacent means an object is able to move to.
     *
     * @param p_in Given point to get adjacent points.
     * @return A list of of points that are adjacent to the given point, marked by isWalkWay.
     * @throws PointOutOfMapException Throws when the p_in is out of map.
     */
    public List<Point2> getAdjacent(Point2 p_in) throws PointOutOfMapException {
        if (p_in.getX() < 0 || p_in.getX() >= width)
            throw new PointOutOfMapException("x", p_in.getX());
        else if (p_in.getY() < 0 || p_in.getY() >= height)
            throw new PointOutOfMapException("y", p_in.getY());

        int x_in = p_in.getX();
        int y_in = p_in.getY();
        List<Point2> rtn = new ArrayList<>();
        if (0 <= y_in - 1) {
            rtn.add(new Point2(x_in, y_in - 1, map[y_in - 1][x_in] != PointType.WALL));
        }
        if (y_in + 1 < this.height) {
            rtn.add(new Point2(x_in, y_in + 1, map[y_in + 1][x_in] != PointType.WALL));
        }
        if (0 <= x_in - 1) {
            rtn.add(new Point2(x_in - 1, y_in, map[y_in][x_in - 1] != PointType.WALL));
        }
        if (x_in + 1 < this.width) {
            rtn.add(new Point2(x_in + 1, y_in, map[y_in][x_in + 1] != PointType.WALL));
        }
        return rtn;
    }

    /**
     * Check whether or not a point can be an exit.
     * By default all points on the boundry can be an exit.
     *
     * @param p_in A point to check.
     * @return true is p_in can be an exit.
     */
    public boolean canEnd(Point2 p_in) {
        return p_in.getX() == this.width - 1 || p_in.getY() == this.height - 1;
    }

    /**
     * Get all the points that are walk way in the maze.
     *
     * @return A list of points that are walk way.
     */
    public List<Point2> getWalkWays() {
        List<Point2> rtn = new ArrayList<>();
        for (int i = 0; i < this.height; i += 1) {
            for (int j = 0; j < this.width; j += 1) {
                if (this.map[i][j] == PointType.WALKWAY) {
                    Point2 pt = new Point2(j, i, true);
                    rtn.add(pt);
                }
            }
        }

        return rtn;
    }

    /**
     * Get the entry of the maze.
     *
     * @return A entry point.
     */
    public Point2 getEntry() {
        return this.pEntry;
    }

    /**
     * Get the exit of the maze.
     *
     * @return null.
     */
    public Point2 getExit() {
        return null;
    }

    /**
     * Set a point type to walk way or wall.
     *
     * @param point The point to set.
     * @param type  The type to set the given point to.
     */
    public void setPoint(Point2 point, PointType type) {
        this.map[point.getY()][point.getX()] = type;
    }

    /**
     * Get a point type of a certain point.
     *
     * @param p_in a 2d point that in this maze.
     * @return null if point out of map.
     * or the pointType at point.
     */
    public PointType getPoint(Point2 p_in) {
        int x_in = p_in.getX();
        int y_in = p_in.getY();

        if (x_in < 0 || x_in >= width)
            return null;
        else if (y_in < 0 || y_in >= height)
            return null;
        return this.map[y_in][x_in];
    }

    /**
     * Add an entity to the maze.
     *
     * @param entity_in The entity to add.
     */
    public void addEntity(IEntity<Point2> entity_in) {
        entities.add((Entity2) entity_in);
    }

    /**
     * Add a coin to the maze.
     *
     * @param point_in The location to add the coin.
     */
    public void addCoin(Point2 point_in) {
        entities.add(new Coin2(point_in.clone()));
    }

    /**
     * Set a player to the maze.
     *
     * @param player_in The player to set.
     */
    public void setPlayer(Player2 player_in) {
        player = player_in;
    }

    /**
     * Move the player by given x and y volicity.
     *
     * @param vx Volicity on x-axis.
     * @param vy Volicity on y-axis.
     */
    public void movePlayer(double vx, double vy) {
        double x = player.getX();
        double y = player.getY();

        if (vx < 0) {
            if (x - (int) x - player.getSize() < (-vx) * player.getSpeed() &&
                    ((int) x == 0 || map[(int) y][(int) x - 1] == PointType.WALL))
                vx = ((int) x - x + player.getSize()) / player.getSpeed();
        } else if (vx > 0) {
            if ((int) x + 1 - x - player.getSize() < vx * player.getSpeed() &&
                    ((int) x == width - 1 || map[(int) y][(int) x + 1] == PointType.WALL))
                vx = ((int) x + 1 - x - player.getSize()) / player.getSpeed();
        }
        if (vy < 0) {
            if (y - (int) y - player.getSize() < (-vy) * player.getSpeed() &&
                    ((int) y == 0 || map[(int) y - 1][(int) x] == PointType.WALL))
                vy = ((int) y - y + player.getSize()) / player.getSpeed();
        } else if (vy > 0) {
            if ((int) y + 1 - y - player.getSize() < vy * player.getSpeed() &&
                    ((int) y == width - 1 || map[(int) y + 1][(int) x] == PointType.WALL))
                vy = ((int) y + 1 - y - player.getSize()) / player.getSpeed();
        }

        player.move(vx, vy);

        List<Entity2> popList = new ArrayList<>();
        for (Entity2 entity : entities) {
            if (entity instanceof Coin2) {
                if (entity.getLocation().equals(player.getLocation())) {
                    player.pickCoin(((Coin2) entity).getValue());
                    popList.add(entity);
                }
            }
        }
        entities.removeAll(popList);

        if (this.map[player.getLocation().getY()][player.getLocation().getX()] == PointType.EXIT)
            this.shouldEnd = true;
    }

    /**
     * Return if the game is end.
     *
     * @return true if shouldEnd if ture.
     */
    public boolean isEnd() {
        return this.shouldEnd;
    }

    public Entity2 getPlayer() {
        return this.player;
    }

    public List<Entity2> getEntities() {
        return this.entities;
    }

    /**
     * Get the number of coins the player got.
     *
     * @return The number of coins the player got.
     */
    public double getCoin() {
        return this.player.getCoin();
    }

    /**
     * Get the distance the player walked through the game.
     *
     * @return The distance the player walked.
     */
    public double getDistance() {
        return this.player.getDistance();
    }

    /**
     * Get the seed of the maze.
     *
     * @return The seed of the maze.
     */
    public long getSeed() {
        return this.seed;
    }
}

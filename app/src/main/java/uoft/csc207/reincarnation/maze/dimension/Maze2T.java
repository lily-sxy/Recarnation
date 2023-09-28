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

// 2D triangle maze.
public class Maze2T implements IMaze<Point2> {
    private Point2 pEntry;
    private long seed;

    private Player2 player;

    private int size;

    /* true if the point is walk way.
     */
    private PointType[][] map;
    private List<Entity2> entities;

    private boolean shouldEnd;

    public Maze2T(int size_in, Point2 pIn) {
        seed = new Random().nextInt();
        pEntry = pIn;
        size = size_in;
        shouldEnd = false;
        map = new PointType[size][];
        for (int i = 0; i < size; i++) {
            map[i] = new PointType[2 * i + 1];
            for (int j = 0; j < 2 * i + 1; j++)
                map[i][j] = PointType.WALL;
        }
        entities = new ArrayList<>();

        MazeMaker mmk = new MazeMaker(this, new Random(seed));
        Map<String, Integer> options = new HashMap<>();
        options.put("NumCoin", 5);

        try {
            mmk.generate(options);
        } catch (PointOutOfMapException e) {
            e.printStackTrace();
        }

        player = new Player2(pEntry, 0.25);
        entities.add(player);
    }

    public List<Point2> getAdjacent(Point2 p_in) throws PointOutOfMapException {
        int x_in = p_in.getX();
        int y_in = p_in.getY();

        if (x_in < 0 || x_in > 2 * y_in)
            throw new PointOutOfMapException("x", x_in);
        else if (y_in < 0 || y_in >= size)
            throw new PointOutOfMapException("y", y_in);

        List<Point2> rtn = new ArrayList<>();
        if (x_in > 0) {
            rtn.add(new Point2(x_in - 1, y_in, this.map[y_in][x_in - 1] != PointType.WALL));
        }
        if (x_in < y_in * 2) {
            rtn.add(new Point2(x_in + 1, y_in, this.map[y_in][x_in + 1] != PointType.WALL));
        }
        if (x_in % 2 == 1) {
            if (y_in > 0) {
                rtn.add(new Point2(x_in - 1, y_in - 1, this.map[y_in - 1][x_in - 1] != PointType.WALL));
            }
        } else {
            if (y_in < size - 1) {
                rtn.add(new Point2(x_in + 1, y_in + 1, this.map[y_in + 1][x_in + 1] != PointType.WALL));
            }
        }
        return rtn;
    }

    public boolean canEnd(Point2 p_in) {
        return p_in.getY() == this.size - 1;
    }

    public List<Point2> getWalkWays() {
        List<Point2> rtn = new ArrayList<>();
        for (int i = 0; i < this.size; i += 1) {
            for (int j = 0; j < i * 2 + 1; j += 1) {
                if (this.map[i][j] == PointType.WALKWAY) {
                    Point2 pt = new Point2(j, i, true);
                    rtn.add(pt);
                }
            }
        }

        return rtn;
    }

    public Point2 getEntry() {
        return this.pEntry;
    }

    public Point2 getExit() {
        return null;
    }

    public void setPoint(Point2 point, PointType type) {
        this.map[point.getY()][point.getX()] = type;
    }

    public void addEntity(IEntity<Point2> entity_in) {
        entities.add((Entity2) entity_in);
    }

    public void addCoin(Point2 point_in) {
        entities.add(new Coin2(point_in.clone()));
    }

    public void setPlayer(Player2 player_in) {
        player = player_in;
    }

    public Entity2 getPlayer() {
        return this.player;
    }

    public List<Entity2> getEntities() {
        return this.entities;
    }

    public void movePlayer(double vx, double vy) {
        double x = player.getX();
        double y = player.getY();
        int centre_x = roundDown(x);
        int centre_y = roundDown(y);

        double v = Math.sqrt(vx * vx + vy * vy);
        if (v == 0)
            return;

        //check critical point for collision detection.
        if (checkPoint(x, y, centre_x, centre_y)) {
            int success_count = 0;
            int fail_count = 0;
            final int max_fail = 2;
            while (success_count < 4 && fail_count < max_fail) {
                success_count = 0;

                if (checkPoint(
                        x + vx * player.getSpeed(),
                        y + vy * player.getSpeed(),
                        centre_x, centre_y
                ))
                    success_count += 1;
                if (checkPoint(
                        x + vx / v * player.getSize() + vx * player.getSpeed(),
                        y + vy / v * player.getSize() + vy * player.getSpeed(),
                        centre_x, centre_y
                ))
                    success_count += 1;

                if (checkPoint(
                        x + vy / v * player.getSize() + vx * player.getSpeed(),
                        y - vx / v * player.getSize() + vy * player.getSpeed(),
                        centre_x, centre_y
                ))
                    success_count += 1;
                if (checkPoint(
                        x - vy / v * player.getSize() + vx * player.getSpeed(),
                        y + vx / v * player.getSize() + vy * player.getSpeed(),
                        centre_x, centre_y
                ))
                    success_count += 1;

                if (success_count < 4) {
                    fail_count += 1;
                    vx /= 2;
                    vy /= 2;
                    v /= 2;
                }
            }
            if (fail_count == max_fail) {
                return;
            }
        }

        // this part of code is used to preserve player's moving distance stats.
        player.move(vx, vy);
        ValidPoint vp = new ValidPoint(player.getX(), player.getY(), centre_x, centre_y);
        player.setX(vp.getX());
        player.setY(vp.getY());

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

        if (getPoint(player.getLocation()) == PointType.EXIT)
            this.shouldEnd = true;
    }

    private boolean checkPoint(double cx, double cy, int x, int y) {
        ValidPoint validPoint = new ValidPoint(cx, cy, x, y);
        cx = validPoint.getX();
        cy = validPoint.getY();

        PointType pt = getPoint(new Point2(roundDown(cx), roundDown(cy), true));
        return pt == PointType.EXIT || pt == PointType.WALKWAY;
    }

    /**
     * Get a point type of a certain point.
     *
     * @param p_in a 2d point that in this maze.
     * @return PointType.WALL if point out of map.
     * or the pointType at point.
     */
    public PointType getPoint(Point2 p_in) {
        int x_in = p_in.getX();
        int y_in = p_in.getY();

        if (x_in < 0 || x_in > 2 * y_in)
            return PointType.WALL;
        else if (y_in < 0 || y_in >= size)
            return PointType.WALL;
        return this.map[y_in][x_in];
    }

    public boolean isEnd() {
        return this.shouldEnd;
    }

    public double getCoin() {
        return this.player.getCoin();
    }

    public double getDistance() {
        return this.player.getDistance();
    }

    public long getSeed() {
        return this.seed;
    }

    // This inner class is used to calculate proper point.
    // Since the 2d number space used in this maze is not continuous.
    private class ValidPoint {
        // This value is sqrt(3), use for calculation.
        private final static double r3 = 1.7320508075688772935274463415059;

        double x;
        double y;

        ValidPoint(double x_in, double y_in, int x_centre, int y_centre) {
            double dy = y_in - y_centre;
            int actual_y = roundDown((dy) / r3 * 2);
            y = y_centre + actual_y + (dy - actual_y * r3 / 2);

            double dx = x_in - x_centre;
            int actual_x = roundDown(dx);
            // is_up denotes whether the triangle being test on is facing up.
            boolean is_up = (x_centre % 2 == 0) == (actual_y % 2 == 0);

            // at this point 0 <= dx < 1, 0 <= dy < r3/2;
            dx -= actual_x;
            dy = y - roundDown(y);
            x = actual_x * 2 + dx + actual_y + x_centre;
            if (is_up) {
                if (dx < 0.5 && dy < r3 / 2 - r3 * dx) {
                    x -= 0.5;
                } else if (dx > 0.5 && dy < r3 * dx - r3 / 2) {
                    x += 0.5;
                }
            } else {
                if (dx < 0.5 && dy > r3 * dx) {
                    x -= 0.5;
                } else if (dx > 0.5 && dy > r3 - r3 * dx) {
                    x += 0.5;
                }
            }
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }
    }

    /* Round down a double to a integer.
     * Note that java actually round up a negative double to int by default.
     */
    private static int roundDown(double d) {
        if (d < 0)
            return (int) d - 1;
        else
            return (int) d;
    }
}

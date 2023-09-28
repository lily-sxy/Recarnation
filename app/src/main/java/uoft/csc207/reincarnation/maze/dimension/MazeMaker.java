package uoft.csc207.reincarnation.maze.dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;

import uoft.csc207.reincarnation.maze.exception.PointOutOfMapException;

/**
 * A maze generator.
 */
class MazeMaker {
    private Random rand;
    private IMaze maze;

    /**
     * Initialize a MazeMaker with a given maze.
     *
     * @param maze_in The maze given.
     */

    MazeMaker(IMaze maze_in) {
        rand = new Random();
        maze = maze_in;
    }

    /**
     * Initialize a maze with given maze and random number.
     *
     * @param maze_in The maze given.
     * @param randIn  The random number given.
     */
    MazeMaker(IMaze maze_in, Random randIn) {
        rand = randIn;
        maze = maze_in;
    }

    /**
     * Generate maze by default.
     * One in one out, one correct path without anything else.
     */
    void generate() throws PointOutOfMapException {
        ArrayList<IPoint> points = new ArrayList<IPoint>();
        ArrayList<IPoint> exit_points = new ArrayList<IPoint>();

        points.add(maze.getEntry());
        maze.setPoint(maze.getEntry(), PointType.WALKWAY);

        while (!points.isEmpty()) {
            IPoint cursor = points.remove(rand.nextInt(points.size()));

            List<IPoint> temp_points = maze.getAdjacent(cursor);
            int count = 0;
            for (IPoint temp_p : temp_points) {
                if (temp_p.isWalkWay())
                    count++;
            }
            if (count < 2) {
                maze.setPoint(cursor, PointType.WALKWAY);
                if (maze.canEnd(cursor))
                    exit_points.add(cursor);
                for (IPoint temp_p : temp_points) {
                    if (!temp_p.isWalkWay())
                        points.add(temp_p);
                }
            }
        }
        if (exit_points.size() == 0)
            maze.setPoint(maze.getEntry(), PointType.EXIT);
        else
            maze.setPoint(exit_points.get(rand.nextInt(exit_points.size())), PointType.EXIT);
    }

    /**
     * Generate a maze with options.
     *
     * @param options The options to generate the maze.
     * @throws PointOutOfMapException Throws when the point if out of map.
     */
    void generate(Map<String, Integer> options) throws PointOutOfMapException {
        generate();

        List<IPoint> walkways = maze.getWalkWays();
        if (options.containsKey("NumCoin")) {
            for (int num_coin = options.get("NumCoin");
                 num_coin > 0 && !walkways.isEmpty();
                 num_coin--) {
                IPoint p_temp = walkways.remove(rand.nextInt(walkways.size()));
                maze.addCoin(p_temp);
            }
        }
    }
}

package uoft.csc207.reincarnation.maze;

import uoft.csc207.reincarnation.maze.dimension.IMaze;
import uoft.csc207.reincarnation.maze.dimension.Maze2Simple;
import uoft.csc207.reincarnation.maze.drawer.IDrawer;
import uoft.csc207.reincarnation.maze.dimension.Maze2;
import uoft.csc207.reincarnation.maze.dimension.Maze2T;
import uoft.csc207.reincarnation.maze.dimension.Point2;
import uoft.csc207.reincarnation.maze.drawer.SquareDrawer;
import uoft.csc207.reincarnation.maze.drawer.TriangleDrawer;

class MazeFactory {
    private IMaze maze;
    private IDrawer drawer;

    /**
     * Initialize a MazeFactory class.
     *
     * @param maze_type  Given type of the maze, "2D" or "2T".
     * @param size       Size of maze.
     * @param block_size Size of block.
     * @param sc_width   Screen width.
     * @param sc_height  Screen height.
     */
    MazeFactory(String maze_type, int size, float block_size, int sc_width, int sc_height) {
        switch (maze_type) {
            case "2D":
                maze = new Maze2(size, size, new Point2(0, 0, true));
                drawer = new SquareDrawer((Maze2) maze, size, size, block_size, sc_width, sc_height);
                break;
            case "2T":
                maze = new Maze2T(size, new Point2(0, 0, true));
                drawer = new TriangleDrawer((Maze2T) maze, size, block_size, sc_width, sc_height);
                break;
            case "2DS":
                maze = new Maze2Simple(size, size, new Point2(0, 0, true));
                drawer = new SquareDrawer((Maze2) maze, size, size, block_size, sc_width, sc_height);
        }
    }

    /**
     * Get the maze of the MazeFactory class.
     *
     * @return Maze.
     */
    IMaze getMaze() {
        return this.maze;
    }

    /**
     * Get the drawer of the MazeFactory.
     *
     * @return Drawer.
     */
    IDrawer getDrawer() {
        return this.drawer;
    }
}

package uoft.csc207.reincarnation.maze.exception;

public class PointOutOfMapException extends Exception {
    public PointOutOfMapException(String s, int i) {
        super("Point out of map:" + s + " = " + i);
    }
}

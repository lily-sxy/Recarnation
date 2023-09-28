package uoft.csc207.reincarnation.maze.userInput;

// A class to get move input value from user input.
public class MoveInput implements IUserInput {
    private float dx;
    private float dy;

    /**
     * Initialize a MoveInput class.
     *
     * @param x ç.
     * @param y Y coordinate of user input.
     */
    public MoveInput(float x, float y) {
        dx = x;
        dy = y;
    }

    /**
     * Get x coordinate.
     */
    @Override
    public float getX() {
        return dx;
    }

    /**
     * Get y coordinate.
     */
    @Override
    public float getY() {
        return dy;
    }

    /**
     * Get input type.
     * Move input type is marked by 1.
     *
     * @return 1
     */
    public int getType() {
        return 1;
    }
}

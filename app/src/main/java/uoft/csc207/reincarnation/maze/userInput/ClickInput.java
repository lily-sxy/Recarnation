package uoft.csc207.reincarnation.maze.userInput;

// A class to get click input from user input.
public class ClickInput implements IUserInput {
    private float x;
    private float y;

    /**
     * Initialize a ClickInput class.
     *
     * @param x_in X coordinate of user input.
     * @param y_in Y coordinate of user input.
     */
    public ClickInput(float x_in, float y_in) {
        x = x_in;
        y = y_in;
    }

    /**
     * Get x coordinate.
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Get y coordinate.
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * Get input type.
     * Click input type is marked by 0.
     *
     * @return 0
     */
    public int getType() {
        return 0;
    }
}

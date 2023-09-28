package uoft.csc207.reincarnation.maze.userInput;

public interface IUserInput {

    /**
     * Get x coordinate.
     */
    float getX();

    /**
     * Get y coordinate.
     */
    float getY();

    /**
     * Get input type.
     * Move input type is marked by 1.
     * Click input type is marked by 0.
     *
     * @return 1
     */
    int getType();

}

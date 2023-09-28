package uoft.csc207.reincarnation.maze.entity;


interface IPlayer {

    /**
     * Get speed of the player.
     *
     * @return speed of player.
     */
    double getSpeed();

    /**
     * Get size of the player.
     *
     * @return size of player.
     */
    double getSize();

    /**
     * Add number of coins.
     *
     * @param value Number of coins to add.
     */
    void pickCoin(double value);

    /**
     * Get the number of coins the player has collected.
     *
     * @return The value of coin.
     */
    double getCoin();


    /**
     * Move the player, without checking of boundary.
     * <p>
     * By default, the velocity vector should have length less than 1.
     *
     * @param vx velocity on x-axis
     * @param vy velocity on y-axis
     */
    void move(double vx, double vy);

    /**
     * Get the distance between the player entity and the entry point.
     *
     * @return Distance between the player entity an the entry.
     */
    double getDistance();
}

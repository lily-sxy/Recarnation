package uoft.csc207.reincarnation;

import java.util.Map;

/**
 * This is the interface for each level, it has very loose constrain.
 */
public interface ILevel {
    /**
     * Return the user statistics.
     * This is used to obtain user data from each level before level finish.
     *
     * @return the stats in map, notnull by default.
     */
    Map<String, Integer> getStats();
}

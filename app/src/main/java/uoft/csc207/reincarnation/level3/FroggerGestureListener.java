package uoft.csc207.reincarnation.level3;

import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Map;

import uoft.csc207.reincarnation.level3.item.Lane;
import uoft.csc207.reincarnation.level3.item.Role;

/**
 * Represents a gesture listenter in the game
 * A FroggerGesutreListener can receive the gesture to give the corresponding action.
 */


public class FroggerGestureListener extends GestureDetector.SimpleOnGestureListener {
    public static final int MIN_MOVE = 100;
    public FroggerView froggerView;
    private String statas = "Running";
    private int level = 0;

    /**
     * Use to correspond to the gesture to determine whether the role should go up,
     * go down,turn left, turn right and whether the role will reach to winning position to
     * identify winning.
     *
     * @param e1 the first motion event
     * @param e2 the first motion event
     * @param v  velocity x
     * @param v1 velocity y
     */
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        Role role = froggerView.getRole();
        Map lanes = froggerView.getLanes();
        String motion = "";
        if (statas.equals("Running")) {
            if (e1.getY() - e2.getY() > MIN_MOVE) {
                // up
                role.interact("Up");
                motion = "Up";
            } else if (e1.getY() - e2.getY() < -MIN_MOVE) {
                // down
                role.interact("Down");
                motion = "Down";
            } else if (e1.getX() - e2.getX() < -MIN_MOVE) {
                // right
                role.interact("Right");
                motion = "Right";
            } else if (e1.getX() - e2.getX() > MIN_MOVE) {
                // left
                role.interact("Left");
                motion = "Left";
            }
            helpRoleFollow(role, lanes, motion);
            if (role.getLocation()[1] == 0) {
                froggerView.win();
                froggerView.updateStates();
                statas = "Winning";
                level++;
            }
        }
        return true;
    }

    /**
     * Use to correspond to the gesture to determine whether the player click on the screen,
     *
     * @param e the motion event
     */
    @Override
    public boolean onDown(MotionEvent e) {
        super.onShowPress(e);
        // when button was pressed
        if (e.getX() <= 2000 && e.getX() >= 1970 && e.getY() >= 125 && e.getY() <= 175) {
            //click on the pause icon
            froggerView.pause();
            statas = "Pause";
        } else if (statas.equals("Pause") && e.getX() <= 1150 && e.getX() >= 800
                && e.getY() >= 380 && e.getY() <= 480) {
            //choose to resume the game when on the pause page
            froggerView.resume();
            statas = "Running";
        } else if (statas.equals("Pause") && e.getX() <= 1150 && e.getX() >= 800
                && e.getY() >= 570 && e.getY() <= 675) {
            // choose to exist the game on the pause page
            froggerView.terminate();
        /*} else if (statas.equals("Winning") && e.getX() <= 1130 && e.getX() >= 695
                && e.getY() >= 800 && e.getY() <= 875) {//add justify for different levels
            //when character get on the end place, it is winning,get into ending
            froggerView.ending(level);*/
        } else if (statas.equals("Winning")) {//check level
            System.out.println(level);
            if (level <= 2) {//if not in the final stage
                //level up
                if (e.getX() <= 860 && e.getX() >= 705
                        && e.getY() >= 625 && e.getY() <= 720) {
                    //click on yes and level up
                    froggerView.restart(level);
                    statas = "Running";
                } else if (e.getX() <= 845 && e.getX() >= 705
                        && e.getY() >= 750 && e.getY() <= 825) {
                    //click to exist the game and game over
                    level = 3;
                    froggerView.partialWin();
                }
                //exist
            } else if (e.getX() <= 1130 && e.getX() >= 695
                    && e.getY() >= 800 && e.getY() <= 875) {
                //if in the final stage->game view ending
                froggerView.ending();
            }

        } else if (froggerView.getIfLose() && e.getX() <= 950 && e.getX() >= 700
                && e.getY() >= 650 && e.getY() <= 725) {
            froggerView.restart(0);
            statas = "Running";
        } else if (froggerView.getIfLose() && e.getX() <= 845 && e.getX() >= 705
                && e.getY() >= 750 && e.getY() <= 825) {
            // stop game
            froggerView.terminate();
        }
        return true;
    }

    /**
     * Help to check whether the role followed the wood to identify if the role is still alive in
     * the game
     * clear all the broken wood if on a golden wood
     *
     * @param role   the role that player played.
     * @param lanes  a collection of lanes that shows in the graph
     * @param motion the motion of the role.
     */
    private void helpRoleFollow(Role role, Map lanes, String motion) {

        boolean ifClear = false;
        if (motion.equals("Left") && role.getFollowDistance() > -1) {
            role.changeFollowDistance(-1);
        } else if (motion.equals("Right") && role.getFollowDistance() < 3) {
            role.changeFollowDistance(1);
        } else if (motion.equals("Up") && role.getLocation()[1] <= 7
                && role.getLocation()[1] >= 2) {
            Lane onThisLane = (Lane) lanes.get(role.getLocation()[1]);
            ifClear = role.onWood(onThisLane);
        } else if (motion.equals("Down") && role.getLocation()[1] <= 7
                && role.getLocation()[1] >= 2) {
            Lane onThisLane = (Lane) lanes.get(role.getLocation()[1]);
            ifClear = role.onWood(onThisLane);
            if (role.getOnThisWood() == null) {
                role.dead();
            }
        } else if (!motion.equals("")) {
            role.unfollow();
        }
        if (ifClear) {
            System.out.println("Clean");
            froggerView.clearBroken();
        }
    }

}

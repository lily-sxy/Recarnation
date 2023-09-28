package uoft.csc207.reincarnation.language;

import java.util.HashMap;

public class English implements Language {
    private HashMap<String, String> translation;

    English() {
        translation = new HashMap<>();

        translation.put("main.start.start", "New Game");
        translation.put("main.start.cont", "Continuous");
        translation.put("main.start.exit", "Exit");

        translation.put("main.levels.back", "Back");
        translation.put("main.levels.level1", "Level 1");
        translation.put("main.levels.level2", "Level 2");
        translation.put("main.levels.level3", "Level 3");
        translation.put("main.levels.ending", "Ending");


        translation.put("escape.types.0", "Card");
        translation.put("escape.types.1", "Card");
        translation.put("escape.types.2", "Card");
        translation.put("escape.types.3", "Card");
        translation.put("escape.types.4", "Card");
        translation.put("escape.types.5", "Card");
        translation.put("escape.types.6", "Cheese");
        translation.put("escape.types.7", "Ice");
        translation.put("escape.types.8", "Key");

        translation.put("escape.get", "You got a ");

        translation.put("escape.nget.0", "There is a cabinet.");
        translation.put("escape.nget.1", "There is a carpet.");
        translation.put("escape.nget.2", "There is a mouse.");
        translation.put("escape.nget.3", "There is a shelf.");
        translation.put("escape.nget.4", "There is a stove.");

        translation.put("escape.open.0", "The cabinet is open.");
        translation.put("escape.open.1", "The carpet is open.");
        translation.put("escape.open.2", "Mouse start eating cheese.");
        translation.put("escape.open.3", "The bookshelf door is open.");
        translation.put("escape.open.4", "The frig is open.");
        translation.put("escape.open.5", "The ice melt.");

        translation.put("escape.nopen.0", "-");
        translation.put("escape.nopen.1", "-");
        translation.put("escape.nopen.2", "There is a mouse.");
        translation.put("escape.nopen.3", "The bookshelf door is locked.");
        translation.put("escape.nopen.4", "-");
        translation.put("escape.nopen.5", "There is a stove.");

        translation.put("escape.start", "You realize you are in a strange room, maybe try to escape from here.");
        translation.put("escape.pause", "Pause");
        translation.put("escape.restart", "Restart");
        translation.put("escape.exit", "Exit game");
        translation.put("escape.nothing", "Nothing happened.");
        translation.put("escape.move", "You move to room ");
        translation.put("escape.nopen", "");
        translation.put("escape.open", "");
        translation.put("escape.escape", "You try to escape from this room.");
        translation.put("escape.help", "Everything in the room is poisonous, don't touch too many times.\nPay attention of colors.");
        translation.put("escape.resume", "Resume");
        translation.put("escape.touch", "Touch: ");
        translation.put("escape.death", "Death: ");
        translation.put("escape.incorrect", "Password Incorrect");


        translation.put("escape.end.escape", "You Escape!");
        translation.put("escape.end.time", "\nTime: ");
        translation.put("escape.end.death", "\nDeath: ");
        translation.put("escape.end.score", "\nScore: ");
        translation.put("escape.end.continue", "\nClick to continuous...");

        translation.put("maze.pause.seed", "Seed: ");
        translation.put("maze.pause.time", "Time: ");
        translation.put("maze.pause.distance", "Distance: ");
        translation.put("maze.pause.coin", "Coin: ");
        translation.put("maze.pause.retry", "Retry: ");
        translation.put("maze.pause.giveup", "Give up");
        translation.put("maze.pause.exit", "Exit");

        translation.put("maze.end.win", "You Found The Exit!");
        translation.put("maze.end.continue", "Click anywhere to continue.");
        translation.put("maze.end.seed", "Seed: ");
        translation.put("maze.end.time", "Time: ");
        translation.put("maze.end.distance", "Distance: ");
        translation.put("maze.end.coin", "Coin: ");
        translation.put("maze.end.retry", "Failed: ");
        translation.put("maze.end.score", "Score: ");

        translation.put("maze.hint.title", "Here is some hint!");
        translation.put("maze.hint.continue", "Click anywhere to continue.");
        translation.put("maze.hint.text0", "Drag on screen to move you player.");
        translation.put("maze.hint.text1", "Search for a exit to escape this maze.");
        translation.put("maze.hint.text2", "Saw a coin? Pick it up, they are helpful.");
        translation.put("maze.hint.text3", "This is the colour of exit");
        translation.put("maze.hint.text4", "This is the colour of coin");
        translation.put(
                "maze.hint.text5",
                "Can't find exit? Try to click on '" +
                        translation.get("maze.pause.giveup") + "'."
        );
        translation.put(
                "maze.hint.text6",
                "Don't want to play anymore? Click '" +
                        translation.get("maze.pause.exit") + "'."
        );
        // Frogger
        translation.put("frogger.view.death", "Death:");
        translation.put("frogger.pause.hint1", "Note: Don't let the dog see you.");
        translation.put("frogger.pause.hint2", "And don't jump on the dark cloud.");
        translation.put("frogger.pause.hint3", "Golden cloud may bring you luck.");
        translation.put("frogger.pause.resume", "Resume");
        translation.put("frogger.pause.exit", "Exit");
        translation.put("frogger.level.title", "Level Up?");
        translation.put("frogger.level.yes", "Yes");
        translation.put("frogger.level.no", "No");
        translation.put("frogger.win.title", "You Win!");
        translation.put("frogger.win.time", "Using Time: ");
        translation.put("frogger.win.death", "Death: ");
        translation.put("frogger.win.score", "Final Score: ");
        translation.put("frogger.win.continuous", "Continuous");
        translation.put("frogger.lose.restart", "Restart");
        translation.put("frogger.lose.exit", "Exit");


        translation.put("fail", "You Were Trapped Forever!");

    }

    /**
     * Get the text correspond to target in the language.
     *
     * @param text_target the target text, format "a.b.c"
     * @return The translated version of text. If text not found, original text will be returned.
     */
    public String getText(String text_target) {
        if (translation.containsKey(text_target))
            return translation.get(text_target);
        else
            return text_target;
    }
}

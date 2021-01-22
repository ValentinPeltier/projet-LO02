package fr.utt.lo02.tdvp;

import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.view.cli.ConsoleView;

/**
 * The main class of the application, from which the application is started.
 */
@SuppressWarnings("deprecation")
public class Main {
    /**
     * The first method to be executed when launching the application.
     * It initialize the game and launch it.
     * @param args The arguments to pass to the application. It will be ignored as no argument is supported.
     */
	public static void main(String[] args) {
		ConsoleView consoleView = new ConsoleView();
        GameManager.getInstance().addObserver(consoleView);

        GameManager.getInstance().initializeGame();
	}
}

package fr.utt.lo02.tdvp;

import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.view.ConsoleView;

@SuppressWarnings("deprecation")
public class Main {
	public static void main(String[] args) {
		// Thread t = new Thread(GameManager.getInstance());
        // t.start();

		ConsoleView consoleView = new ConsoleView();
        GameManager.getInstance().addObserver(consoleView);

        GameManager.getInstance().initializeGame();
	}
}

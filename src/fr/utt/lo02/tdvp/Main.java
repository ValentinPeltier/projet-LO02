package fr.utt.lo02.tdvp;

import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.view.ConsoleView;

public class Main {
	public static void main(String[] args) {

		ConsoleView consoleView = new ConsoleView();

		GameManager.getInstance().addObserver(consoleView);

        GameManager.getInstance().initializeGame();
        GameManager.getInstance().playGame();
	}
}

package fr.utt.lo02.tdvp;

import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.view.ConsoleView;
import fr.utt.lo02.tdvp.view.gui.GamePanelView;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;

public class Main {
	public static void main(String[] args) {

		GameManager gameManager = GameManager.getInstance();

		// ConsoleView consoleView = new ConsoleView();


		// gameManager.addObserver(consoleView);
		// gameManager.addObserver(SettingsPanelView.getInstance());
		// gameManager.addObserver(GamePanelView.getInstance());


		gameManager.initializeGame();
		// gameManager.playGame();
	}
}

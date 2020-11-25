package fr.utt.lo02.tdvp;

import fr.utt.lo02.tdvp.core.GameManager;

public class Main {
	public static void main(String[] args) {
        GameManager.getInstance().initializeGame();
        GameManager.getInstance().playGame();
	}
}

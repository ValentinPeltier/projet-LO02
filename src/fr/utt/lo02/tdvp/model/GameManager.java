package fr.utt.lo02.tdvp.model;

import java.util.Observable;
import java.awt.EventQueue;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.controller.SettingsPanelController;
import fr.utt.lo02.tdvp.model.layout.LayoutVisitor;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.view.gui.SettingsPanelView;
import fr.utt.lo02.tdvp.view.gui.Window;

public class GameManager extends Observable {

    private static GameManager instance = new GameManager();
    private Settings settings = Settings.getInstance();


    private LayoutVisitor layoutVisitor = new LayoutVisitor();


    //MVC NEED IT
    private int round;
    private int playerIndex;

    private GameManager() {}

    /**
     * Get the single instance of the class
     * @return the single instance of GameManager
     */
    public static GameManager getInstance() {
        return instance;
    }

    @Override
    public void notifyObservers(Object arg){
    	this.setChanged();
        super.notifyObservers(arg);
    }

    public Player getPlayerAtIndex(int i) {
        return settings.getPlayers().get(i);
    }

    public int getRound() {
    	return this.round;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Initialize a new game.
     * Let the user choose the variant, the players and the desired layout.
     */
    public void initializeGame() {
        Thread t = new Thread(new Window());
        t.start();

        notifyObservers(Events.DisplayGameSettingsHeader);

        // Initialize the variant
        notifyObservers(Events.AskVariant);
    }

    /**
     * Play a 4 round game.
     * {@code initializeVariant}, {@code initializePlayers} and {@code initializeLayout} must have been called.
     */
    public void playGame() {
        Stack stack = Stack.getInstance();

        this.notifyObservers(Events.DisplayStartGameMsg);

        // The game must be in 4 rounds
        for(round = 0; round < 4; round++) {
        	this.notifyObservers(Events.DisplayRoundNumber);

            boolean roundOver = false;

            // If the stack is empty or the layout is full then the round is over
            for (int turn = 0; !roundOver; turn++) {
                // Loop for each player
                for (playerIndex = 0; playerIndex < settings.getPlayers().size(); playerIndex++) {
                    Player player = settings.getPlayers().get(playerIndex);

                    // Display name
                    this.notifyObservers(Events.DisplayNameAtTurn);

                    // Should the variant be executed ?
                    if (settings.getVariant().shouldExecute(turn, playerIndex)) {
                        // Then execute it
                        settings.getVariant().execute(player);
                    }

                    // Player turn
                    player.play();

                    // Is the round over ?
                    roundOver = settings.getLayout().isFull();
                    if (roundOver) {
                        break;
                    }
                }
            }

            // Count Points
            settings.getLayout().countPointsAccept(this.layoutVisitor);

            // TODO: change start player


            // Distribute points

            for (Player player: settings.getPlayers()) {
            	// Get player current score
                int playerScore = player.getScore();

            	// Add color points
                playerScore += this.layoutVisitor.getPoints().get(player.getVictoryCard().getColor().toString().toLowerCase());

            	// Add shape points
                playerScore += this.layoutVisitor.getPoints().get(player.getVictoryCard().getShape().toString().toLowerCase());

            	// Add filled points
                playerScore += this.layoutVisitor.getPoints().get(player.getVictoryCard().getFilled().toString().toLowerCase());

                player.setScore(playerScore);


            }

            displayScores();

            // Reset stack, layout and variant at the end of each round
            stack.reset();
            settings.getLayout().reset();
            settings.getVariant().reset();

            // Draw new victory card for each player
            for (Player player: settings.getPlayers()) {
                player.drawVictoryCard();
            }
        }
    }

    private void displayScores() {
    	//Display Scores
    	this.notifyObservers(Events.DisplayScoresHeader);

        for (playerIndex = 0; playerIndex < settings.getPlayers().size(); playerIndex++) {
        	// Display score
        	this.notifyObservers(Events.DisplayScoreForPlayerOnRow);
        }

        this.notifyObservers(Events.DisplaySimpleFooter);
    }
}

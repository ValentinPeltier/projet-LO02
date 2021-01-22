package fr.utt.lo02.tdvp.model;

import java.util.Observable;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.layout.LayoutVisitor;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.view.gui.HomePanelView;
import fr.utt.lo02.tdvp.view.gui.Window;

/**
 * The main class of the game, it handle turns and rounds
 * The class extends a Observable class to communicate with the view
 * @see Observable
 */

@SuppressWarnings("deprecation")
public class GameManager extends Observable {

	 /**
     * The instance of the game manager, in order to make the singleton work
     */
    private static GameManager instance = new GameManager();
    
    /**
     * Instance of the Settings Singleton class, in order to get all the infos of the game
     */
    private Settings settings = Settings.getInstance();

    /**
     * Instance of a Layout Visitor from the Visitor design pattern, to count point
     */
    private LayoutVisitor layoutVisitor = new LayoutVisitor();


    /**
     * The round index
     */
    private int round;
    
    /**
     * The index of the player currently playing in the game
     */
    private int playerIndex;
    

    /**
     * Constructor of the class
     */
    private GameManager() {}

    /**
     * Get the single instance of the class
     * @return the single instance of GameManager
     */
    public static GameManager getInstance() {
        return instance;
    }

    /**
     * Override the notifyObserver method, in order to call setChanged method every time we notify Observers 
     */
    @Override
    public void notifyObservers(Object arg){
    	this.setChanged();
        super.notifyObservers(arg);
    }

    /**
     * Return a player at a given index
     * @param index of the player
     * @return the Player at the index given in parameter, in to the player List
     */
    public Player getPlayerAtIndex(int i) {
        return settings.getPlayers().get(i);
    }

    /**
     * Method used by other class, in order to get current round
     * @return return the current round
     */
    public int getRound() {
    	return this.round;
    }

    /**
     * Method used by other class, in order to get the Index of the player currently playing
     * @return return currently playing Player index
     */
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

    /**
     * This method displays scores
     */
    private void displayScores() {
    	
    	this.notifyObservers(Events.DisplayScoresHeader);

        for (playerIndex = 0; playerIndex < settings.getPlayers().size(); playerIndex++) {
        	// Display score
        	this.notifyObservers(Events.DisplayScoreForPlayerOnRow);
        }

        this.notifyObservers(Events.DisplaySimpleFooter);
    }
}

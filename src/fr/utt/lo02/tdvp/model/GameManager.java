package fr.utt.lo02.tdvp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.layout.LayoutCircle;
import fr.utt.lo02.tdvp.model.layout.LayoutRectangle;
import fr.utt.lo02.tdvp.model.layout.LayoutVisitor;
import fr.utt.lo02.tdvp.model.player.PhysicalPlayer;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.model.player.VirtualPlayerEasy;
import fr.utt.lo02.tdvp.model.player.VirtualPlayerHard;
import fr.utt.lo02.tdvp.model.variant.Variant;
import fr.utt.lo02.tdvp.model.variant.VariantRandomSwitch;
import fr.utt.lo02.tdvp.model.variant.VariantSecondChance;
import fr.utt.lo02.tdvp.view.cli.Input;
import fr.utt.lo02.tdvp.view.gui.Window;

public class GameManager extends Observable{
	
    private static GameManager instance = new GameManager();

    private List<Player> players = new ArrayList<Player>();

    private Variant variant;

    private Layout layout;

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
    
    public int getRound() {
    	return this.round;
    }
    
    public int getPlayerIndex()
    {
    	return this.playerIndex;
    }
    
    public Player getPlayerAtIndex(int index)
    {
    	return this.players.get(index);
    }
    
    public int getPlayersNumber()
    {
    	return this.players.size();
    }
    
    public boolean isCardAjacent(int x, int y) {
    	
    	boolean noAdjacentCard = !this.layout.isEmpty()
        && layout.getCardAt(x - 1, y) == null
        && layout.getCardAt(x + 1, y) == null
        && layout.getCardAt(x, y - 1) == null
        && layout.getCardAt(x, y + 1) == null;
    	
    	return !noAdjacentCard;
    }

    /**
     * Initialize a new game.
     * Let the user choose the variant, the players and the desired layout.
     */
    public void initializeGame() {
        Window.initWindow();
        
    	this.notifyObservers(Events.DisplayGameSettingsHeader);

        // Initialize the variant
        this.notifyObservers(Events.AskVariant);
        
        // Initialize the players
        this.initializePlayers();
        

        // Initialize layout
        this.notifyObservers(Events.AskLayoutShape);  
    }

    /**
     * Asks the user to choose a variant
     */
    public void initializeVariant(int variantChoice) {

        // Set the variant
        switch (variantChoice) {
            case 0:
                // Create a variant that never executes and does nothing
                this.variant = new Variant() {
                    public boolean shouldExecute(int round, int playerIndex) {
                        return false;
                    }
                    public void execute(Player player) {}
                    public void reset() {}
					@Override
					public void makeChange() {
						// TODO Auto-generated method stub
						
					}
                };
                break;
            case 1:
                // RandomSwitch variant
                this.variant = new VariantRandomSwitch();
                break;
            case 2:
                this.variant = new VariantSecondChance();
                break;
        }
    }
    

    
    public void setPhysicalPlayers(int physicalPlayersCount) {
    	
    	// Create physical players
        for (this.playerIndex = 0; this.playerIndex < physicalPlayersCount; this.playerIndex++) {
            this.players.add(new PhysicalPlayer());
            this.notifyObservers(Events.AskPlayerName);
        }
    }
    
    public void setVirtualPlayer(int difficulty)
    {
    	// Create virtual player
        switch (difficulty) {
            case 1:
                this.players.add(new VirtualPlayerEasy());
                break;
            case 2:
                this.players.add(new VirtualPlayerHard());
                break;
        }
    }
    
    public void setPlayerName(String name) {
    	this.players.get(this.playerIndex).setName(name);
    }
    
    /**
     * Asks the user for the number of physical and virtual players and their names
     */
    public void initializePlayers() {
        
    	this.notifyObservers(Events.AskPhysicalPlayersNumber);
    	
    	this.notifyObservers(Events.AskVirtualPlayerSettings);
    }

    /**
     * Asks the user the desired layout
     */
    public void initializeLayout(int layoutChoice) {

        // Create the layout
        switch (layoutChoice) {
            case 1:
                this.layout = new LayoutRectangle();
                break;
            case 2:
                this.layout = new LayoutCircle();
                break;
        }
    }

    /**
     * Play a 3 round game.
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
                for (playerIndex = 0; playerIndex < this.players.size(); playerIndex++) {
                    Player player = this.players.get(playerIndex);

                    // Display name
                    this.notifyObservers(Events.DisplayNameAtTurn);

                    // Should the variant be executed ?
                    if (this.variant.shouldExecute(turn, playerIndex)) {
                        // Then execute it
                        this.variant.execute(player);
                    }

                    // Player turn
                    player.play();

                    // Is the round over ?
                    roundOver = this.layout.isFull();
                    if (roundOver) {
                        break;
                    }
                }
            }

            // Count Points
            this.layout.countPointsAccept(this.layoutVisitor);

            // TODO: change start player

            
            // Distribute points
            
            for (Player player: this.players) {
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
            this.layout.reset();
            this.variant.reset();

            // Draw new victory card for each player
            for (Player player: this.players) {
                player.drawVictoryCard();
            }
        }
    }
    
    private void displayScores() {
    	//Display Scores
    	this.notifyObservers(Events.DisplayScoresHeader);

        for (playerIndex = 0; playerIndex < this.players.size(); playerIndex++) {
        	// Display score
        	this.notifyObservers(Events.DisplayScoreForPlayerOnRow);
        }
        
        this.notifyObservers(Events.DisplaySimpleFooter);
    }
    

    /**
     * Returns the selected layout
     * @return The selected layout
     */
    public Layout getLayout() {
    	return this.layout;
    }

    /**
     * Returns the selected variant
     * @return The selected variant
     */
    public Variant getVariant() {
    	return this.variant;
    }
}

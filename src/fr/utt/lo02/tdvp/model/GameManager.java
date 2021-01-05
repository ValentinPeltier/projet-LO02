package fr.utt.lo02.tdvp.core;

import java.util.ArrayList;
import java.util.List;

import fr.utt.lo02.tdvp.core.cli.Input;
import fr.utt.lo02.tdvp.core.layout.Layout;
import fr.utt.lo02.tdvp.core.layout.LayoutCircle;
import fr.utt.lo02.tdvp.core.layout.LayoutRectangle;
import fr.utt.lo02.tdvp.core.layout.LayoutVisitor;
import fr.utt.lo02.tdvp.core.player.PhysicalPlayer;
import fr.utt.lo02.tdvp.core.player.Player;
import fr.utt.lo02.tdvp.core.player.VirtualPlayerEasy;
import fr.utt.lo02.tdvp.core.player.VirtualPlayerHard;
import fr.utt.lo02.tdvp.core.variant.Variant;
import fr.utt.lo02.tdvp.core.variant.VariantRandomSwitch;
import fr.utt.lo02.tdvp.core.variant.VariantSecondChance;

public class GameManager {
    private static GameManager instance = new GameManager();

    private List<Player> players = new ArrayList<Player>();

    private Variant variant;

    private Layout layout;

    private LayoutVisitor layoutVisitor = new LayoutVisitor();

    private GameManager() {}

    /**
     * Get the single instance of the class
     * @return the single instance of GameManager
     */
    public static GameManager getInstance() {
        return instance;
    }

    /**
     * Initialize a new game.
     * Let the user choose the variant, the players and the desired layout.
     */
    public void initializeGame() {
        System.out.println("###############################");
        System.out.println("### Parametres de la partie ###");
        System.out.println("###############################\n");

        // Initialize the variant
        initializeVariant();

        // Initialize the players
        initializePlayers();

        // Initialize layout
        initializeLayout();
    }

    /**
     * Asks the user to choose a variant
     */
    private void initializeVariant() {
        // Ask the user for a variant
        final int variantChoice = Input.promptChoice("Choix de la variante", new String[] {
            "Aucune",
            VariantRandomSwitch.getName() + " (" + VariantRandomSwitch.getDescription() + ")",
            VariantSecondChance.getName() + " (" + VariantSecondChance.getDescription() + ")"
        }, "Quelle variante choisis-tu ?", 0);

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

    /**
     * Asks the user for the number of physical and virtual players and their names
     */
    private void initializePlayers() {
        // Ask for the number of physical players
        final int physicalPlayersCount = Input.promptChoice(
            "Nombre de joueurs reels",
            new String[] { "1 joueur", "2 joueurs", "3 joueurs" },
            "Combien de joueurs reels vont jouer ?"
        );

        // Create physical players
        for (int i = 0; i < physicalPlayersCount; i++) {
            this.players.add(new PhysicalPlayer());
        }

        // Generate answers for the number of virtual players
        final int minVirtualPlayers = Math.max(2 - physicalPlayersCount, 0);
        final ArrayList<String> virtualPlayersAnswers = new ArrayList<String>();
        for (int i = minVirtualPlayers; physicalPlayersCount + i <= 3; i++) {
            virtualPlayersAnswers.add(String.valueOf(i) + " joueur" + (i > 1 ? "s" : ""));
        }

        int virtualPlayersCount = 0;
        if (virtualPlayersAnswers.size() > 1) {
            // Ask for the number of virtual players
            virtualPlayersCount = Input.promptChoice(
                "Nombre de joueurs virtuels",
                virtualPlayersAnswers.toArray(new String[virtualPlayersAnswers.size()]),
                "Combien de joueurs virtuels vont jouer ?",
                minVirtualPlayers
            );

            for (int i = 0; i < virtualPlayersCount; i++) {
                // Ask for the difficulty
                final int difficulty = Input.promptChoice(
                    "Difficulte du joueur virtuel " + (i + 1),
                    new String[] { "Facile", "Difficile" },
                    "Quelle sera la difficulte du joueur virtuel " + (i + 1) + " ?");

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
        }
    }

    /**
     * Asks the user the desired layout
     */
    private void initializeLayout() {
        // Ask the user
        final int layoutChoice = Input.promptChoice(
            "Choix du plateau de jeu",
            new String[] {
                "Plateau rectangulaire 5x3",
                "Plateau circulaire de diametre 5"
            },
            "Quel plateau de jeu choisis-tu ?");

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

        System.out.println("################################");
        System.out.println("### Que la partie commence ! ###");
        System.out.println("################################\n");

        // The game must be in 4 rounds
        for(int round = 0; round < 4; round++) {
            System.out.println("#################");
            System.out.println("### Round " + (round + 1) + " ! ###");
            System.out.println("#################\n");

            boolean roundOver = false;

            // If the stack is empty or the layout is full then the round is over
            for (int turn = 0; !roundOver; turn++) {
                // Loop for each player
                for (int playerIndex = 0; playerIndex < this.players.size(); playerIndex++) {
                    Player player = this.players.get(playerIndex);

                    // Display name
                    System.out.println("### A " + player.getName() + " de jouer ! ###\n");

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

            // Distribute points and display scores
            System.out.println("### Scores ###");
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

                // Display score
                System.out.println("### " + player.getName() + " : " + player.getScore() + " points");
            }
            System.out.println();

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

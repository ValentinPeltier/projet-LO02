package fr.utt.lo02.tdvp.view.cli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.tdvp.controller.Actions;
import fr.utt.lo02.tdvp.controller.Controller;
import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.layout.Location;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.model.variant.VariantRandomSwitch;
import fr.utt.lo02.tdvp.model.variant.VariantSecondChance;

/**
 * Represents the command-line interface.
 * The methods beginning with "ask" ask the user to make a choice, check its validity and inform the controller of this choice.
 * The methods beginning with "display" simply display corresponding information to the console.
 */
@SuppressWarnings("deprecation")
public class ConsoleView implements Observer {
    /**
     * The instance of the main game controller.
     */
    private Controller controller = Controller.getInstance();

    /**
     * The instance of the class that handle the settings.
     */
    private Settings settings = Settings.getInstance();

    /**
     * The class constructor.
     * Does nothing.
     */
	public ConsoleView() {}

    /**
     * Asks the user to choose a variant between the two available.
     */
	public void askVariant() {
		// Ask the user for a variant
        final int answer = Input.promptChoice("Choix de la variante", new String[] {
            "Aucune",
            VariantRandomSwitch.getName() + " (" + VariantRandomSwitch.getDescription() + ")",
            VariantSecondChance.getName() + " (" + VariantSecondChance.getDescription() + ")"
        }, "Quelle variante choisis-tu ?", 0);

        controller.setVariant(answer);
	}

    /**
     * Asks the user to choose a layout shape between the two available.
     */
	public void askLayoutShape() {
		// Ask the user
        final int answer = Input.promptChoice(
            "Choix du plateau de jeu",
            new String[] {
                "Plateau rectangulaire 5x3",
                "Plateau circulaire de diametre 5"
            },
            "Quel plateau de jeu choisis-tu ?");

        controller.setLayoutShape(answer);
	}

    /**
     * Asks the user to choose the number of physical players.
     * It can be between 1 and 3 and will define the minimum and maximum number of virtual players.
     */
	public void askPhysicalPlayersNumber() {
		// Ask for the number of physical players
        final int answer = Input.promptChoice(
            "Nombre de joueurs reels",
            new String[] { "1 joueur", "2 joueurs", "3 joueurs" },
            "Combien de joueurs reels vont jouer ?"
        );

        controller.setPhysicalPlayersNumber(answer);
	}

    /**
     * Asks the user to choose a name for a physical player.
     * It should not be used on a virtual player but it is not forbidden.
     * @param playerIndex The index of the player (starting to 0) in the settings
     */
	public void askPhysicalPlayerName(int playerIndex) {
        final String answer = Input.promptString("Choisis un nom pour le joueur " + (playerIndex+1) + " :");
        controller.setPlayerName(playerIndex, answer);
	}

    /**
     * Asks the user to choose the number of virtual players.
     * There should be at least one physical player in the players attribute of the Settings instance.
     * The available options will depend on the number of physical players.
     * If the number of available options is equal to 1, then the user will not be asked and the option will be automatically selected.
     * @see Settings
     */
	public void askVirtualPlayersNumber() {
		int physicalPlayersCount = settings.getPhysicalPlayersCount();

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
                controller.setVirtualPlayer(1);
            }
        }
	}

	/**
     * Asks the player to play its turn according to its available actions, listed in the enumeration Actions.
     * @see Actions
     */
	public void askPlayerToPlay() {

		Player playingPlayer = GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex());
		List<Actions> availableActions = playingPlayer.getAvailableOptions();

		if (availableActions.size() > 0) {
			//Generate answers
			String[] answers = new String[availableActions.size()];

			for(int i = 0; i < availableActions.size(); i++) {
				answers[i] = actionEnumToString(availableActions.get(i));
			}

			final int answer = Input.promptChoice(
                "Tour de jeu",
                answers,
                "Que veux-tu faire ?"
            );

			Actions actionToMake = availableActions.get(answer-1);

			switch(actionToMake) {
				case PlaceCard:
					controller.askPlaceCard();
					break;
				case MoveCards:
					controller.askMoveCard();
					break;
				case ChangeVictoryCard:
					break;
				case SeeVictoryCard:
					this.displayVictoryCard();
					break;
				case MoveLayout:
					this.askToMoveLayout();
					break;
				case EndTurn:
					controller.endTurn();
					break;
				default:
					break;
			}
		}

	}

    /**
     * Returns the string representation of an action
     * @param action The action to execute
     * @return The string representation of the action. It returns an empty string if the action is not valid.
     */
	private String actionEnumToString(Actions action) {
		switch(action) {
			case PlaceCard:
				return "Poser ma carte";
			case MoveCards:
				return "Deplacer une carte";
			case ChangeVictoryCard:
				return "Changer de Victory Card";
			case SeeVictoryCard:
				return "Voir ma Victory Card";
			case MoveLayout:
				return "Bouger le Plateau";
			case EndTurn:
				return "Terminer le Tour";
			default:
				return "";
		}
	}

    /**
     * Asks the user to place the card that he/she drawn, until it is a suitable location.
     */
	public void askToPlaceCard() {
		Player playingPlayer = GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex());
		Card card = playingPlayer.getDrawnCard();

		Layout layout = settings.getLayout();

		while(true) {
            String response;
            do {
                response = Input.promptString("Ou veux-tu poser ta carte " + card + " ? (e.g. \"B2\")");

                if (response.length() != 2) {
                    System.out.println("Emplacement invalide.\n");
                }
            } while(response.length() != 2);
            int x = response.charAt(0) - 'A';
            int y = response.charAt(1) - '0';

            if (!layout.locationExists(x, y)) {
                System.out.println("Emplacement invalide.\n");
            }
            else if (layout.getCardAt(x, y) != null) {
                System.out.println("Il y a deja une carte ici !\n");
            }
            else if (!settings.getLayout().isCardAjacent(x,y))
            {
                System.out.println("Il faut que ta carte soit adjacente a une autre !\n");
            }
            else if (controller.placeCard(x,y,card)) {
                // Card has been placed, we are done
                return;
            }
            else {
                logError();
            }
        }
	}

    /**
     * Asks the user to choose two locations until they are suitable and swap these cards (or simply move a card if the second location is empty).
     */
	public void askToMoveCards() {
		Layout layout = settings.getLayout();
		// Ask for the card to move
        while (true) {
            String response;
            do {
                response = Input.promptString("Quelle carte veux-tu deplacer ? (e.g. \"B2\")");

                if (response.length() != 2) {
                    System.out.println("Emplacement invalide.\n");
                }
            } while(response.length() != 2);
            int x1 = response.charAt(0) - 'A';
            int y1 = response.charAt(1) - '0';

            if (layout.getCardAt(x1, y1) == null) {
                System.out.println("Emplacement invalide.\n");
            }
            else {
                // Ask for the destination
                while (true) {
                    response = Input.promptString("Ou veux-tu deplacer cette carte ? (e.g. \"B2\")");
                    int x2 = response.charAt(0) - 'A';
                    int y2 = response.charAt(1) - '0';

                    if (!layout.locationExists(x2, y2)) {
                        System.out.println("Emplacement invalide.\n");
                    }
                    else {
                        boolean error = false;

                        // Is there a card at the destination coordinates ?
                        if (layout.getCardAt(x2, y2) == null) {
                            // Remove the card
                            Card originCard = layout.getCardAt(x1, y1);
                            layout.setCardAt(x1, y1, null);

                            // Check if the destination has adjacent cards
                            if (
                                layout.getCardAt(x2 - 1, y2) == null
                                && layout.getCardAt(x2 + 1, y2) == null
                                && layout.getCardAt(x2, y2 - 1) == null
                                && layout.getCardAt(x2, y2 + 1) == null
                            ) {
                                System.out.println("La carte que tu deplaces doit etre adjacente a une autre.\n");
                                error = true;
                            }

                            // Replace the card
                            layout.setCardAt(x1, y1, originCard);
                        }

                        if (!error) {
                            if(controller.moveCards(x1, y1, x2, y2)) {
                                // Card has been moved, we are done
                                return;
                            }
                            else {
                            	logError();
                            }
                        }
                    }
                }
            }
        }
	}

    /**
     * Asks the user to move the game board in any suitable direction.
     */
	public void askToMoveLayout() {
		int answer2 = 0;
    	do {
    		this.displayLayout();

        	 answer2 = Input.promptChoice(
                    "Deplacer la grille",
                    new String[] { "Haut", "Bas","Gauche","Droite","Retour" },
                    "Quelle direction ?"
                );

        	boolean result;

        	switch(answer2)
        	{
        		case 1:
        			result = controller.moveVertically(-1);
        			break;
        		case 2:
        			result = controller.moveVertically(1);
        			break;
        		case 3:
        			result = controller.moveHorizontally(-1);
        			break;
        		case 4:
        			result = controller.moveHorizontally(1);
        			break;
        		default:
        			result = false;
        			break;
        	}

        	if (!result)
        		System.out.println("Impossible de d�placer le Layout ici");

    	} while(answer2 != 5);
	}

    /**
     * Asks the user if he/she wants to draw a card to replace its current victory card.
     */
	public void askVariantSecondChance() {
		final int drawNewVictoryCard = Input.promptChoice(
            "[Variante] Repiocher une Victory Card",
            new String[] { "Non", "Oui" },
            "Souhaites-tu repiocher une Victory Card et remettre la tienne (" + GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).getVictoryCard() + ") dans la pioche ?",
            0
        );

		if (drawNewVictoryCard == 1)
            controller.variantSecondChance();
	}

    /*-------------------DISPLAY-------------------------*/

    /**
     * Displays the game settings header to the console.
     */
	public void displayGameSettingsHeader() {
		System.out.println("###############################");
        System.out.println("### Parametres de la partie ###");
        System.out.println("###############################\n");
	}

    /**
     * Displays the game beginning message to the console.
     */
	public void displayStartGameMsg() {
		System.out.println("################################");
        System.out.println("### Que la partie commence ! ###");
        System.out.println("################################\n");
	}

    /**
     * Displays the round number to the console.
     * @param roundIndex The round index, so roundIndex+1 will be displayed.
     */
	public void displayRoundNumber(int roundIndex) {
		System.out.println("#################");
        System.out.println("### Round " + (roundIndex + 1) + " ! ###");
        System.out.println("#################\n");
	}

    /**
     * Displays the turn message with the name of the currently playing player to the console.
     */
	public void displayNameAtTurn() {
		System.out.println("### A " + GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).getName() + " de jouer ! ###\n");
	}

    /**
     * Displays the victory card of the currently playing player to the console.
     */
	public void displayVictoryCard() {
        System.out.println("Ta Victory Card est : " + GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).getVictoryCard() + "\n");
	}

    /**
     * Displays the random switch variant message to the console.
     */
	public void displayVariantRandomSwitch(){
		System.out.println("[Variante] 2 cartes aleatoires ont ete echangees !\n");
	}

    /**
     * Displays the second chance variant message to the console.
     */
	public void displayVariantSecondChance() {
		System.out.println("Tu as pioche ta nouvelle Victory Card : " + GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).getVictoryCard() + "\n");
	}

    /**
     * Displays the game board with every card and empty slots to the console.
     */
	public void displayLayout() {
		Layout layout = settings.getLayout();

		int minX = 0, minY = 0, maxX = 0, maxY = 0;
        Iterator<Location> mapIterator = layout.getLocations().keySet().iterator();

        while (mapIterator.hasNext()) {
            Location location = mapIterator.next();

            if (location.getX() < minX) {
                minX = location.getX();
            }

            if (location.getY() < minY) {
                minX = location.getX();
            }

            if (location.getX() > maxX) {
                maxX = location.getX();
            }

            if (location.getY() > maxY) {
                maxY = location.getY();
            }
        }

        // Display the letters
        for (int x = minX; x <= maxX; x++) {
            System.out.print("\t " + (char)('A' + x));
        }

        // Display grid row by row
        for (int y = minY; y <= maxY; y++) {
            System.out.print("\n\n" + y + "\t");

            for(int x = minX; x <= maxX; x++) {
                boolean locationExists = layout.locationExists(x, y);

                if (locationExists) {
                    Card card = layout.getCardAt(x, y);

                    if (card != null) {
                        System.out.print(card + "\t");
                    }
                    else {
                        System.out.print(" x \t");
                    }
                }
                else {
                    System.out.print("   \t");
                }
            }
        }

        System.out.println("\n");
	}

    /**
     * Displays the score header to the console.
     */
	public void displayScoresHeader() {
		System.out.println("### Scores ###");
	}

    /**
     * Displays the currently playing player's name and its score on the same row on the console.
     */
	public void displayScoreForPlayerOnRow() {
		Player player = GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex());
		System.out.println("### " + player.getName() + " : " + player.getScore() + " points");
	}

    /**
     * Displays a newline character to the console.
     */
	public void displaySimpleFooter() {
		System.out.println();
	}

    /**
     * Displays "An error occured" to the console.
     */
	public void logError() {
		System.out.println("Une erreur est survenue...\n");
	}

    /**
     * Trigger an action according to the event value of arg.
     * @param o The observable object that called notifyObservers()
     * @param arg The argument passed in the notifyObservers() method. It will trigger an action if it is in the list of the supported events for this class.
     * @see Observer
     * @see Observable
     */
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Events && o instanceof GameManager) {
            switch ((Events) arg) {
            	//SETTINGS
                case AskVariant:
                	this.askVariant();
                	break;
                case AskPhysicalPlayersNumber:
                	this.askPhysicalPlayersNumber();
                	break;
                case AskLayoutShape:
                	this.askLayoutShape();
                	break;
                case AskPlayerName1:
                	this.askPhysicalPlayerName(0);
                	break;
                case AskPlayerName2:
                    this.askPhysicalPlayerName(1);
                    break;
                case AskPlayerName3:
                    this.askPhysicalPlayerName(2);
                    break;
                case AskVirtualPlayerSettings:
                	this.askVirtualPlayersNumber();
                	break;

                //PLAY
                case AskPlayerToPlay:
                	askPlayerToPlay();
                	break;
                case AskToPlaceCard:
                	askToPlaceCard();
                	break;
                case AskToMoveCards:
                	askToMoveCards();
                	break;

                //VARIANT
                case AskVariantSecondChance:
                	askVariantSecondChance();
                	break;

                //DISPLAYS
                case DisplayGameSettingsHeader:
                	displayGameSettingsHeader();
                	break;
                case DisplayStartGameMsg:
                	displayStartGameMsg();
                	break;
                case DisplayRoundNumber:
                	displayRoundNumber(GameManager.getInstance().getRound());
                	break;
                case DisplayNameAtTurn:
                	displayNameAtTurn();
                	break;

                case DisplayLayout:
                	displayLayout();
                	break;

                case DisplayVariantRandomSwitch:
                	displayVariantRandomSwitch();
                	break;
                case DisplayVariantSecondChance:
                	displayVariantSecondChance();
                	break;

                case DisplayScoresHeader:
                	displayScoresHeader();
                	break;
                case DisplayScoreForPlayerOnRow:
                	displayScoreForPlayerOnRow();
                    break;

                case DisplaySimpleFooter:
                	displaySimpleFooter();
                	break;

                case LogError:
                	logError();
                	break;
                default:
                	break;
            }
        }

	}
}

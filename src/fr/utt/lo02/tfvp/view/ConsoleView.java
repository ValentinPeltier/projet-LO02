package fr.utt.lo02.tfvp.view;

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
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.layout.Location;
import fr.utt.lo02.tdvp.model.player.Player;
import fr.utt.lo02.tdvp.model.player.VirtualPlayerEasy;
import fr.utt.lo02.tdvp.model.player.VirtualPlayerHard;
import fr.utt.lo02.tdvp.model.variant.VariantRandomSwitch;
import fr.utt.lo02.tdvp.model.variant.VariantSecondChance;
import fr.utt.lo02.tdvp.view.cli.Input;

public class ConsoleView implements Observer{
	
	public ConsoleView() {}
	
	private Controller controller = new Controller();
	GameManager gameManager = GameManager.getInstance();
	
	
	public void askVariant()
	{
		// Ask the user for a variant
        final int answer = Input.promptChoice("Choix de la variante", new String[] {
            "Aucune",
            VariantRandomSwitch.getName() + " (" + VariantRandomSwitch.getDescription() + ")",
            VariantSecondChance.getName() + " (" + VariantSecondChance.getDescription() + ")"
        }, "Quelle variante choisis-tu ?", 0);
        
        controller.setVariant(answer);
	}
	
	public void askLayoutShape()
	{
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
	
	public void askPhysicalPlayersNumber() {
		// Ask for the number of physical players
        final int answer = Input.promptChoice(
            "Nombre de joueurs reels",
            new String[] { "1 joueur", "2 joueurs", "3 joueurs" },
            "Combien de joueurs reels vont jouer ?"
        );
        
        controller.setPhysicalPlayersNumber(answer);
	}
	
	public void askPhysicalPlayerName() {
		final String answer = Input.promptString("Choisis un nom pour le joueur " + (gameManager.getPlayerIndex()+1) + " :");
		
		controller.setPlayerName(answer);
	}
	
	public void askVirtualPlayersNumber() {
		
		int physicalPlayersCount = gameManager.getPlayersNumber();
		
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
                
                controller.setVirtualPlayer(difficulty);
            }
        }
	}
	
	public void askVirtualPlayerDifficulty() {
		
	}
	
	//PLAY
	public void askPlayerToPlay() {
		
		Player playingPlayer = gameManager.getPlayerAtIndex(gameManager.getPlayerIndex());
		List<Actions> availableActions = playingPlayer.getAvailableOptions();
		
		if (availableActions.size()>0)
		{
			//Generate answers
			String[] answers = new String[availableActions.size()];
			
			for(int i = 0; i < availableActions.size(); i++)
			{
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
	
	private String actionEnumToString(Actions action)
	{
		switch(action)
		{
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
	
	
	
	
	public void askToPlaceCard()
	{
		Player playingPlayer = gameManager.getPlayerAtIndex(gameManager.getPlayerIndex());
		Card card = playingPlayer.getDrawnCard();
		
		
		Layout layout = gameManager.getLayout();
		
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
            else if (
            		!gameManager.isCardAjacent(x,y))
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
	
	
	public void askToMoveCards()
	{
		Layout layout = gameManager.getLayout();
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
        	
        	if(!result)
        		System.out.println("Impossible de déplacer le Layout ici");
    	
    	}while(answer2 != 5);
	}
	
	public void askVariantSecondChance()
	{
		final int drawNewVictoryCard = Input.promptChoice(
	            "[Variante] Repiocher une Victory Card",
	            new String[] { "Non", "Oui" },
	            "Souhaites-tu repiocher une Victory Card et remettre la tienne (" + gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).getVictoryCard() + ") dans la pioche ?",
	            0
	        );
		
		if (drawNewVictoryCard == 1)
				controller.variantSecondChance();
	}
	
	
	
	/*-------------------DISPLAY-------------------------*/
	public void displayGameSettingsHeader() {
		System.out.println("###############################");
        System.out.println("### Parametres de la partie ###");
        System.out.println("###############################\n");
	}
	
	public void displayStartGameMsg() {
		System.out.println("################################");
        System.out.println("### Que la partie commence ! ###");
        System.out.println("################################\n");
	}
	
	public void displayRoundNumber(int round) {
		System.out.println("#################");
        System.out.println("### Round " + (round + 1) + " ! ###");
        System.out.println("#################\n");
	}
	
	public void displayNameAtTurn()
	{
		System.out.println("### A " + gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).getName() + " de jouer ! ###\n");
	}
	
	public void displayVictoryCard()
	{
		// Display victory card
        System.out.println("Ta Victory Card est : " + gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).getVictoryCard() + "\n");
	}
	
	public void displayVariantRandomSwitch(){
		System.out.println("[Variante] 2 cartes aleatoires ont ete echangees !\n");
	}
	
	public void displayVariantSeconChance()
	{
		System.out.println("Tu as pioche ta nouvelle Victory Card : " + gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).getVictoryCard() + "\n");
	}
	
	public void displayLayout()
	{
		Layout layout = gameManager.getLayout();
		
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
	
	
	
	public void displayScoresHeader() {
		System.out.println("### Scores ###");
	}
	
	public void displayScoreForPlayerOnRow()
	{
		Player player = gameManager.getPlayerAtIndex(gameManager.getPlayerIndex());
		System.out.println("### " + player.getName() + " : " + player.getScore() + " points");
	}
	
	public void displaySimpleFooter()
	{
		System.out.println();
	}
	
	public void logError() {
		System.out.println("Une erreur est survenue...\n");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Events && o instanceof GameManager)
        {
            switch ((Events) arg)
            {
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
                case AskPlayerName:
                	this.askPhysicalPlayerName();
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
                	displayRoundNumber(gameManager.getRound());
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
                case DisplayVariantSeconChance:
                	displayVariantSeconChance();
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

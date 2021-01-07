package fr.utt.lo02.tfvp.view;

import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.tdvp.controller.Controller;
import fr.utt.lo02.tdvp.controller.Events;
import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.layout.Layout;
import fr.utt.lo02.tdvp.model.player.Player;
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
		
	}
	
	public void askVirtualPlayerDifficulty() {
		
	}
	
	//PLAY
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
            		gameManager.isCardAjacent(x,y))
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
                
                //PLAY
                case AskToPlaceCard:
                	askToPlaceCard();
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

package fr.utt.lo02.tdvp.core.player;

import fr.utt.lo02.tdvp.core.cli.Input;

public class PhysicalPlayer extends Player {
    /**
     * Count the number of initialized physical players
     */
    private static int initializedCount = 0;

    /**
     * Plays a turn
     */
    Scanner sc = new Scanner(System.in);
	GameManager gameManager = GameManager.getInstance();
	Stack stack = Stack.getInstance();
	
	//TEST
	private List<String> choices = new ArrayList<String>();
	
	public void play() {
		
		Card drawnCard;
		
		int answer;
		boolean isTurnFinished = false;
		
		drawnCard = stack.drawCard();
		
		
		choices.add("Voir ma victory Card");
		choices.add("Poser la carte");
		choices.add("D�placer des cartes");
		choices.add("Voir la carte pioch�e");
		
		
		while(!isTurnFinished)
		{			
			System.out.println("C'est � "+name+" de jouer ! Tu as pioch� une carte !");
	    	
			for(int i=0; i < choices.size(); i++)
			{
				System.out.println(i+1+" - "+choices.get(i));
			}
			
			System.out.println("0 - Finir Le Tour");
	    	
	    	answer = sc.nextInt();
	    	
		   //IF STATEMENTS
	    	if(answer == 0 && !choices.contains("Poser la carte"))
	    	{
	    		isTurnFinished = true;
	    	}
	    	else if(answer == 0 && choices.contains("Poser la carte"))
	    	{
	    		System.out.println("Vous devez d'abord Poser votre carte pour finir le tour !");
	    	}
	    	else if(answer == choices.indexOf("Voir ma victory Card"))
			{
	    		displayVictoryCard();
			}
	    	else if(answer == choices.indexOf("Poser la carte")) 
	    	{
	    		//TODO PlaceCard
	    		choices.remove(choices.indexOf("Poser la carte"));
	    		choices.remove(choices.indexOf("Voir la carte pioch�e"));
	    	}
	    	else if(answer == choices.indexOf("D�placer des cartes"))
	    	{
	    		//TODO MoveCard
	    		choices.remove(choices.indexOf("D�placer des cartes"));
	    	}
	    	else if (answer == choices.indexOf("Voir la carte pioch�e"))
	    	{
	    		System.out.println("La carte pioch�e est " + drawnCard);	
	    	}
	    	else
	    	{
	    		System.out.println("Ceci n'est pas une option ! Veuillez choisir une option disponible");
	    	}	
		}
	}

    /**
     * Display the victory card
     */
    public void displayVictoryCard() {
        System.out.println("Votre carte Victoire est " + this.victoryCard);
    }

    /**
     * Prompt the name of the player
     */
    protected void initName() {
        // Ask to type in the player's name
        this.name = Input.promptString("Choisissez un nom pour le joueur " + (++initializedCount) + " :");
    }
}

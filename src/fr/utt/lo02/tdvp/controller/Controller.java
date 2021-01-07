package fr.utt.lo02.tdvp.controller;


import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;

public class Controller {
	
	GameManager gameManager = GameManager.getInstance();
	
	public void Controller() {}

	public void setVariant(int answer)
	{
		gameManager.initializeVariant(answer);
	}
	
	public void setLayoutShape(int answer)
	{
		gameManager.initializeLayout(answer);
	}
	
	public void setPhysicalPlayersNumber(int answer)
	{
		gameManager.setPhysicalPlayers(answer);
	}
	
	public void setPlayerName(String name) {
		gameManager.setPlayerName(name);
	}
	
	public boolean placeCard(int x, int y, Card card) {
		return gameManager.getLayout().placeCard(x, y, card);
	}
	
	public void askPlaceCard()
	{
		gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).placeCard();
	}
	
	public void askMoveCard() {
		gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).moveCard();
	}
	
	public void endTurn()
	{
		gameManager.getPlayerAtIndex(gameManager.getPlayerIndex()).endTurn();
	}
	
	public boolean moveHorizontally(int offset)
	{
		return gameManager.getLayout().moveHorizontally(offset);
	}
	
	public boolean moveVertically(int offset)
	{
		return gameManager.getLayout().moveVertically(offset);
	}
	
	public void setVirtualPlayer(int difficulty)
	{
		gameManager.setVirtualPlayer(difficulty);
	}

	public void variantSecondChance() 
	{
		gameManager.getVariant().makeChange();
	}
	
}

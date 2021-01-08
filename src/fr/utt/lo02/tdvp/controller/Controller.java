package fr.utt.lo02.tdvp.controller;


import fr.utt.lo02.tdvp.model.Card;
import fr.utt.lo02.tdvp.model.GameManager;
import fr.utt.lo02.tdvp.model.Settings;

public class Controller {
    private static Controller instance = new Controller();

    Settings settings = Settings.getInstance();

    public static Controller getInstance() {
        return instance;
    }

	private Controller() {}

	public void setVariant(int answer)
	{
		settings.setVariant(answer);
	}

	public void setLayoutShape(int answer)
	{
		settings.setLayoutShape(answer);
	}

	public void setPhysicalPlayersNumber(int answer)
	{
		settings.setPhysicalPlayers(answer);
	}

	public void setPlayerName(int i, String name) {
		settings.setPlayerName(i, name);
    }

    public void setVirtualPlayer(int count)
	{
		settings.setVirtualPlayers(count);
	}

	public boolean placeCard(int x, int y, Card card) {
		return settings.getLayout().placeCard(x, y, card);
	}

	public boolean moveCards(int x1, int y1, int x2, int y2)
	{
		return settings.getLayout().moveCard(x1, y1, x2, y2);
	}

	public void askPlaceCard()
	{
		GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).placeCard();
	}

	public void askMoveCard() {
		GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).moveCard();
	}

	public void endTurn()
	{
		GameManager.getInstance().getPlayerAtIndex(GameManager.getInstance().getPlayerIndex()).endTurn();
	}

	public boolean moveHorizontally(int offset)
	{
		return settings.getLayout().moveHorizontally(offset);
	}

	public boolean moveVertically(int offset)
	{
		return settings.getLayout().moveVertically(offset);
	}

	public void variantSecondChance()
	{
		settings.getVariant().makeChange();
	}

}

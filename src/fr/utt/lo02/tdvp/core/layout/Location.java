package fr.utt.lo02.tdvp.core.layout;

import fr.utt.lo02.tdvp.core.Card;

public class Location {
    
	private int x;

    private int y;

    //private Card card;

    public int getX() {
    	return this.x;
    }

    public int getY() {
    	return this.y;
    }

    /*public Card getCard() {
    	return this.card;
    }*/

    public void setX(int x) {
    	this.x = x;
    }

    public void setY(int y) {
    	this.y = y;
    }

    /*public void setCard(Card card) {
    	this.card = card;
    }*/

    public Location(int x, int y) {
    	this.x = x;
    	this.y = y;
    	//this.card = card;
    }

}

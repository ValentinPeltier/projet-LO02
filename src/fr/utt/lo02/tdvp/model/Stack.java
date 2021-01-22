package fr.utt.lo02.tdvp.model;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Collections;

/**
* The class is the stack of the game, containing all the unused cards...
*/

public class Stack {
	
	/**
     * The instance of the stack, in order to make the singleton work
     */
    private static Stack instance = new Stack();

    /**
     * The actual stack
     */
    private LinkedList<Card> cards = new LinkedList<Card>();

    /**
     * Constructor of the class
     */
    private Stack() {
        // Initialize the stack
        this.reset();
    }

    /**
     * Gets the single instance of the class
     * @return the single instance of Stack
     */
    public static Stack getInstance() {
        return instance;
    }

    /**
     * Draws a card from the stack. Throw an exception if the stack is empty
     * @return the drawn card
     * @throws NoSuchElementException
     */
    public Card drawCard() throws NoSuchElementException {
        return this.cards.pop();
    }

    /**
     * Place a card in the stack, and shuffle the cards
     * @param Card to place in the stack
     **/
    public void put(Card card) {
        this.cards.push(card);

        Collections.shuffle(this.cards);
    }

    /**
     * Fills the stack with all cards and shuffle it
     */
    public void reset() {
        // Add every possible card to the stack
        for (Card.Shape shape: Card.Shape.values()) {
            for (Card.Color color: Card.Color.values()) {
                for (Card.Filled filled: Card.Filled.values()) {
                    this.cards.add(new Card(shape, color, filled));
                }
            }
        }

        // Shuffle cards
        Collections.shuffle(this.cards);
    }
}

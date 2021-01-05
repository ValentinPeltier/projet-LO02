package fr.utt.lo02.tdvp.model;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Collections;

public class Stack {
    private static Stack instance = new Stack();

    private LinkedList<Card> cards = new LinkedList<Card>();

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
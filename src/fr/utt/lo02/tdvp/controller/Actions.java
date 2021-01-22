package fr.utt.lo02.tdvp.controller;

/**
 * Enumerates all possible player actions.
 */
public enum Actions {
    /**
     * The player wants to place the card he drawn.
     */
    PlaceCard,

    /**
     * The player wants to move a card or swap two cards.
     */
    MoveCards,

    /**
     * The player wants to see its victory card.
     */
    SeeVictoryCard,

    /**
     * The player wants to move the game board.
     */
    MoveLayout,

    /**
     * The player wants to change its victory card (due to the second chance variant).
     */
    ChangeVictoryCard,

    /**
     * The player wants to end its turn.
     */
    EndTurn
}

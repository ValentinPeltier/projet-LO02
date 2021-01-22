package fr.utt.lo02.tdvp.controller;

/**
 * Enumerates all dispatchable events.
 */
public enum Events {
    // ------- SETTINGS -------

    /**
     * Asks the user to choose a variant.
     */
    AskVariant,

    /**
     * Asks the user to choose the number of physical players for this game.
     */
    AskPhysicalPlayersNumber,

    /**
     * Asks the user to choose the name of the player 1.
     */
    AskPlayerName1,

    /**
     * Asks the user to choose the name of the player 2.
     */
    AskPlayerName2,

    /**
     * Asks the user to choose the name of the player 3.
     */
    AskPlayerName3,

    /**
     * Asks the user to choose a layout.
     */
    AskLayoutShape,

    /**
     * Asks the user to choose the number of virtual players.
     */
    AskVirtualPlayerSettings,

    // ------- PLAY -------

    /**
     * Asks the player to play its turn.
     */
    AskPlayerToPlay,

    /**
     * Asks the player to place the card he drawn.
     */
    AskToPlaceCard,

    /**
     * Asks the player to move a card (or swap two cards).
     */
    AskToMoveCards,

    // ------- DISPLAY -------

    /**
     * Displays the game settings header.
     */
    DisplayGameSettingsHeader,

    /**
     * Displays the game beginning message header.
     */
    DisplayStartGameMsg,

    /**
     * Displays the round number.
     */
    DisplayRoundNumber,

    /**
     * Displays the turn message with the name of the currently playing player.
     */
    DisplayNameAtTurn,

    /**
     * Displays the random switch variant message.
     */
    DisplayVariantRandomSwitch,

    /**
     * Displays the second chance variant message.
     */
    DisplayVariantSecondChance,

    /**
     * Asks the user if he/she wants to draw a card to replace its current victory card.
     */
    AskVariantSecondChance,

    /**
     * Displays the score header.
     */
    DisplayScoresHeader,

    /**
     * Displays the name and the score of the currently playing player on a row.
     */
    DisplayScoreForPlayerOnRow,

    /**
     * Displays the board game.
     */
	DisplayLayout,

    /**
     * Displays a newline to the console.
     */
	DisplaySimpleFooter,

    /**
     * Displays "an error occured" message.
     */
	LogError
}

package fr.utt.lo02.tdvp.controller;

public enum Events {

	//SETTINGS
	AskVariant, AskPhysicalPlayersNumber, AskPlayerName1, AskPlayerName2, AskPlayerName3, AskLayoutShape, AskVirtualPlayerSettings,

	//PLAY
	AskPlayerToPlay, AskToPlaceCard, AskToMoveCards,

	//DISPLAY
	DisplayGameSettingsHeader,DisplayStartGameMsg, DisplayRoundNumber, DisplayNameAtTurn,
	DisplayVariantRandomSwitch,DisplayVariantSecondChance,AskVariantSecondChance,

	DisplayScoresHeader,DisplayScoreForPlayerOnRow,

	DisplayLayout,

	DisplaySimpleFooter,

	LogError

}

package fr.utt.lo02.tdvp.controller;

public enum Events {
	
	//SETTINGS
	AskVariant, AskPhysicalPlayersNumber, AskPlayerName, AskLayoutShape, AskVirtualPlayerSettings,
	
	//PLAY
	AskPlayerToPlay, AskToPlaceCard, AskToMoveCards,
	
	//DISPLAY
	DisplayGameSettingsHeader,DisplayStartGameMsg, DisplayRoundNumber, DisplayNameAtTurn,
	DisplayVariantRandomSwitch,DisplayVariantSeconChance,AskVariantSecondChance,
	
	DisplayScoresHeader,DisplayScoreForPlayerOnRow,
	
	DisplayLayout, 
	
	DisplaySimpleFooter,
	
	LogError

}

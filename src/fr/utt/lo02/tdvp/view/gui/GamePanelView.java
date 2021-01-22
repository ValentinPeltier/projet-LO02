package fr.utt.lo02.tdvp.view.gui;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.variant.VariantSecondChance;
import fr.utt.lo02.tdvp.view.gui.utils.ImageButton;
import fr.utt.lo02.tdvp.view.gui.utils.ImageUtil;

/**
 * Represents the view of a game panel in the MVC architecture.
 * Displays the game interface.
 * This class uses the singleton design pattern.
 */
@SuppressWarnings("deprecation")
public class GamePanelView extends GridPane implements Observer {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static GamePanelView instance = new GamePanelView();

    /**
     * The "move" action button.
     * @see #getActionButtonMove()
     */
    private ImageButton actionButtonMove;

    /**
     * The "display victory card" button.
     * @see #getActionButtonDisplayVictoryCard()
     */
    private ImageButton actionButtonDisplayVictoryCard;

    /**
     * The "change victory card" button.
     * @see #getActionButtonChangeVictoryCard()
     */
    private ImageButton actionButtonChangeVictoryCard;

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static GamePanelView getInstance() {
        return instance;
    }

    /**
     * Instantiate a new GamePanelView.
     * Create and place all children elements.
     */
    private GamePanelView() {
        Settings.getInstance().addObserver(this);

        ImageView[] cards = {
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/TRH.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/CBH.png"),
            ImageUtil.getImageView("cards/SGF.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
            ImageUtil.getImageView("cards/empty.png"),
        };

        actionButtonMove = new ImageButton("buttons/action-move.png");
        actionButtonDisplayVictoryCard = new ImageButton("buttons/action-display-victory-card.png");
        actionButtonChangeVictoryCard = new ImageButton("buttons/action-change-victory-card.png");
        updateActionButtonChangeVictoryCard();

        // Player label
        Label playerLabel = new Label("À Valentin de jouer");
        playerLabel.setStyle("-fx-background-color: #98d2ff29;" +
            "-fx-background-radius: 500px;" +
            "-fx-border-width: 3px;" +
            "-fx-border-color: #98d2ff;" +
            "-fx-border-radius: 500px;" +
            "-fx-padding: 16px 50px");
        GridPane.setHalignment(playerLabel, HPos.CENTER);

        // Stack button
        ImageButton stackButton = new ImageButton("cards/stack.png");

        // Actions pane
        VBox actionsPane = new VBox(actionButtonMove, actionButtonDisplayVictoryCard, actionButtonChangeVictoryCard);
        actionsPane.setStyle("-fx-padding: 120px 44px 0px 44px; -fx-background-image: url(\"/resources/panels/game.png\"); -fx-background-repeat: no-repeat");

        // Layout pane
        TilePane layoutPane = new TilePane(cards);
        layoutPane.setPrefColumns(5);

        // Round label
        Label roundLabel = new Label("Round 2");

        // Score label
        Label scoreLabel = new Label("Score : 8");
        GridPane.setHalignment(scoreLabel, HPos.RIGHT);

        // Help button
        Button helpButton = new Button("Aide");
        GridPane.setHalignment(helpButton, HPos.CENTER);

        // End of turn button
        Button endOfTurnButton = new Button("Fin de tour");
        GridPane.setHalignment(endOfTurnButton, HPos.CENTER);

        add(playerLabel, 0, 0, 2, 1);
        add(stackButton, 2, 0, 1, 2);
        add(actionsPane, 3, 0, 1, 2);
        add(layoutPane, 0, 1, 2, 1);
        add(roundLabel, 0, 2);
        add(scoreLabel, 1, 2);
        add(helpButton, 2, 2);
        add(endOfTurnButton, 3, 2);

        setHgap(40);
        setVgap(60);
        setAlignment(Pos.CENTER);
    }

    /**
     * Check if the variant has changed and update the actions buttons according to it.
     * @param o The observable object that called notifyObservers()
     * @param arg The argument passed in the notifyObservers() method. It will be ignored here.
     * @see Observer
     * @see Observable
     */
    @Override
    public void update(Observable o, Object arg) {
        updateActionButtonChangeVictoryCard();
    }

    /**
     * Check if the variant is "second chance" and display or hide the "change victory card" according to it.
     */
    public void updateActionButtonChangeVictoryCard() {
        if (Settings.getInstance().getVariant() instanceof VariantSecondChance) {
            actionButtonChangeVictoryCard.setVisible(true);
        }
        else {
            actionButtonChangeVictoryCard.setVisible(false);
        }
    }

    /**
     * Returns the "move" button of the game panel.
     * @return The "move" button
     */
    public ImageButton getActionButtonMove() {
        return actionButtonMove;
    }

    /**
     * Returns the "display victory card" of the panel.
     * @return The "display victory card" button
     */
    public ImageButton getActionButtonDisplayVictoryCard() {
        return actionButtonDisplayVictoryCard;
    }

    /**
     * Returns the "change victory card" button of the panel.
     * @return The "change victory card" button
     */
    public ImageButton getActionButtonChangeVictoryCard() {
        return actionButtonChangeVictoryCard;
    }
}

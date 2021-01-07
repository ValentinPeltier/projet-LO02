package fr.utt.lo02.tdvp.view.gui;

import java.util.Observable;
import java.util.Observer;

import fr.utt.lo02.tdvp.model.Settings;
import fr.utt.lo02.tdvp.model.variant.Variant;
import fr.utt.lo02.tdvp.view.gui.utils.ImageButton;
import fr.utt.lo02.tdvp.view.gui.utils.ImageUtil;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class GamePanelView extends GridPane implements Observer {
    private static GamePanelView instance = new GamePanelView();

    private TilePane layoutPane;
    private ImageButton actionButtonMove;
    private ImageButton actionButtonDisplayVictoryCard;
    private ImageButton actionButtonChangeVictoryCard;

    public static GamePanelView getInstance() {
        return instance;
    }

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
        actionsPane.setStyle("-fx-padding: 120px 44px 0px 44px; -fx-background-image: url(\"/assets/panels/game.png\"); -fx-background-repeat: no-repeat");

        // Layout pane
        layoutPane = new TilePane(cards);
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

    @Override
    public void update(Observable gamePanel, Object arg) {
        // TODO
        updateActionButtonChangeVictoryCard();
    }

    public void updateActionButtonChangeVictoryCard() {
        if (Settings.getInstance().getVariant() == Variant.Name.SecondChance) {
            actionButtonChangeVictoryCard.setVisible(true);
        }
        else {
            actionButtonChangeVictoryCard.setVisible(false);
        }
    }

    public ImageButton getActionButtonMove() {
        return actionButtonMove;
    }

    public ImageButton getActionButtonDisplayVictoryCard() {
        return actionButtonDisplayVictoryCard;
    }

    public ImageButton getActionButtonChangeVictoryCard() {
        return actionButtonChangeVictoryCard;
    }
}
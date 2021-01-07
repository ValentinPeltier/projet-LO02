package fr.utt.lo02.tdvp.view.gui;

import fr.utt.lo02.tdvp.view.gui.utils.ImageUtil;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.image.ImageView;

public class HomePanelView extends HBox {
    private static HomePanelView instance = new HomePanelView();

    private Button playButton;

    public static HomePanelView getInstance() {
        return instance;
    }

    private HomePanelView() {
        ImageView leftCard = ImageUtil.getImageView("cards/TGF.png");
        ImageView rightCard = ImageUtil.getImageView("cards/SRF.png");
        leftCard.getTransforms().add(new Rotate(-45));
        rightCard.getTransforms().add(new Rotate(25));

        Label title = new Label("Shape Up !");
        title.setStyle("-fx-font-size: 60px");

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

        TilePane cardsPane = new TilePane(cards);
        cardsPane.setPrefColumns(5);
        cardsPane.setStyle("-fx-padding: 80px 140px");

        playButton = new Button("JOUER");
        playButton.getStyleClass().add("large-padding");
        playButton.setStyle("-fx-font-size: 36px");

        VBox vPane = new VBox(title, cardsPane, playButton);
        vPane.setAlignment(Pos.CENTER);

        getChildren().addAll(leftCard, vPane, rightCard);
        setAlignment(Pos.CENTER);
    }

    public Button getPlayButton() {
        return playButton;
    }
}

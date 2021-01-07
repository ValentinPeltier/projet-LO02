package fr.utt.lo02.tdvp.view.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.image.ImageView;

public class HomePanel extends HBox {
    private static HomePanel instance = new HomePanel();

    public static HomePanel getInstance() {
        return instance;
    }

    private HomePanel() {
        ImageView leftCard = Images.getImageView("cards/TGF.png");
        ImageView rightCard = Images.getImageView("cards/SRF.png");
        leftCard.getTransforms().add(new Rotate(-45));
        rightCard.getTransforms().add(new Rotate(25));

        Label title = new Label("Shape Up !");
        title.setStyle("-fx-font-size: 60px");

        ImageView[] cards = {
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/TRH.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/CBH.png"),
            Images.getImageView("cards/SGF.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
            Images.getImageView("cards/empty.png"),
        };

        TilePane cardsPane = new TilePane(cards);
        cardsPane.setPrefColumns(5);
        cardsPane.setStyle("-fx-padding: 80px 140px");

        Button button = new Button("JOUER");
        button.getStyleClass().add("large-padding");
        button.setStyle("-fx-font-size: 36px");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Window.setPanel(SettingsPanel.getInstance());
            }
        });

        VBox vPane = new VBox(title, cardsPane, button);
        vPane.setAlignment(Pos.CENTER);

        this.getChildren().addAll(leftCard, vPane, rightCard);
        this.setAlignment(Pos.CENTER);
    }
}

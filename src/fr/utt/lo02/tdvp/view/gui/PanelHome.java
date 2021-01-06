package fr.utt.lo02.tdvp.view.gui;

import java.io.FileInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanelHome {
    public static Pane getPane() {
        try {
            ImageView leftCard = new ImageView(new Image(new FileInputStream("src/assets/cards/TGF.png")));
            ImageView rightCard = new ImageView(new Image(new FileInputStream("src/assets/cards/SRF.png")));
            leftCard.getTransforms().add(new Rotate(-45));
            rightCard.getTransforms().add(new Rotate(35));

            Label title = new GameLabel("Shape Up !");
            title.setStyle("-fx-font-size: 60px");

            ImageView[] cards = {
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/TRH.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/CBH.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/SGF.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
                new ImageView(new Image(new FileInputStream("src/assets/cards/empty.png"))),
            };

            TilePane cardsPane = new TilePane(cards);
            cardsPane.setPrefColumns(5);
            cardsPane.setStyle("-fx-padding: 80px 140px");

            Button button = new GameButton("JOUER");
            button.setStyle("-fx-padding: 20px 180px; -fx-font-size: 36px");
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Window.setPanel(PanelSettings.getPane());
                }
            });

            VBox vPane = new VBox(title, cardsPane, button);
            vPane.setAlignment(Pos.CENTER);
            HBox hPane = new HBox(leftCard, vPane, rightCard);
            hPane.setAlignment(Pos.CENTER);

            return hPane;
        }
        catch(Exception e) {
            System.out.println("Cannot read images for home panel");
        }

        return new Pane();
    }
}

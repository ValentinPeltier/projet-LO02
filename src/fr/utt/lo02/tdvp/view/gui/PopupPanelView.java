package fr.utt.lo02.tdvp.view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PopupPanelView extends VBox {
    private static PopupPanelView instance = new PopupPanelView();

    private Button closeButton;
    private Label title, content;

    public static PopupPanelView getInstance() {
        return instance;
    }

    private PopupPanelView() {
        title = new Label();
        content = new Label();
        closeButton = new Button("Fermer");

        getChildren().addAll(title, content, closeButton);
        setAlignment(Pos.CENTER);
        setMargin(content, new Insets(10, 0, 40, 0));
        getStyleClass().add("popup-panel");
        setMaxHeight(300);
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public void setText(String title, String content) {
        this.title.setText(title);
        this.content.setText(content);
    }
}

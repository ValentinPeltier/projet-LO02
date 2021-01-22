package fr.utt.lo02.tdvp.view.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents the view of a popup panel in the MVC architecture.
 * Displays a red banner with a title, a text and a close button.
 * This class uses the singleton design pattern.
 */
public class PopupPanelView extends VBox {
    /**
     * The unique instance of the class.
     * @see #getInstance()
     */
    private static PopupPanelView instance = new PopupPanelView();

    /**
     * The close button of the popup.
     * @see #getCloseButton()
     */
    private Button closeButton;

    /**
     * The popup title.
     * @see #setText(String, String)
     */
    private Label title;

    /**
     * The popup content.
     * @see #setText(String, String)
     */
    private Label content;

    /**
     * Returns the unique instance of the class.
     * @return The unique instance of the class
     */
    public static PopupPanelView getInstance() {
        return instance;
    }

    /**
     * Instantiates a new PopupPanelView.
     * Create and place all children elements.
     */
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

    /**
     * Returns the close button of the popup panel.
     * @return The close button
     */
    public Button getCloseButton() {
        return closeButton;
    }

    /**
     Sets the title and the content of the popup panel.
     * @param title The title of the popup panel
     * @param content The content of the popup panel
     */
    public void setText(String title, String content) {
        this.title.setText(title);
        this.content.setText(content);
    }
}

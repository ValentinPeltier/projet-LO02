package fr.utt.lo02.tdvp.view.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import fr.utt.lo02.tdvp.model.panel.HomePanel;
import fr.utt.lo02.tdvp.model.panel.SettingsPanel;
import fr.utt.lo02.tdvp.model.panel.GamePanel;
import fr.utt.lo02.tdvp.model.panel.PopupPanel;
import fr.utt.lo02.tdvp.view.gui.utils.ImageUtil;

/**
 * The main class of the graphical user interface.
 * It represents a graphical window, non-resizable, with a popup panel to display error messages and a main panel.
 * The main panel is initialized to the home panel view.
 * The class implements the Runnable interface so it can be started in a thread.
 * @see HomePanelView
 * @see Runnable
 */
public class Window extends Application implements Runnable {
    /**
     * The root element on which are added popup and main panels.
     */
    private static StackPane root;

    /**
     Sets the panel displayed in the window, below the popup.
     * @param panel The panel to display in the window.
     */
    public static void setPanel(Pane panel) {
        if (root.getChildren().size() > 1) {
            root.getChildren().remove(1);
        }
        root.getChildren().add(panel);
    }

    /**
     * Initialize the window by calling initWindow().
     */
    @Override
    public void run() {
        initWindow();
    }

    /**
     * Launch the window by calling Application.launch().
     * @see javafx.application.Application.launch()
     */
    public static void initWindow() {
        launch();
    }

    /**
     * Display the popup panel with a message above the main panel.
     * @param content The message to display on the popup.
     */
    public static void displayPopup(String content) {
        displayPopup("", content);
    }

    /**
     * Display the popup panel with a title and a message above the main panel.
     * @param title The title of the popup.
     * @param content The message to display on the popup.
     */
    public static void displayPopup(String title, String content) {
        PopupPanelView.getInstance().setText(title, content);
        root.getChildren().get(0).setVisible(true);
    }

    /**
     * Hide the popup panel.
     */
    public static void hidePopup() {
        root.getChildren().get(0).setVisible(false);
    }

    /**
     * Construct the window by setting it up (title, icon, stylesheet, ...), adding its content (popup and main panels) and display it.
     */
    @Override
    public void start(Stage window) {
        HomePanel.getInstance().init();
        SettingsPanel.getInstance().init();
        GamePanel.getInstance().init();
        PopupPanel.getInstance().init();

        // Display the pop up panel above everything
        root = new StackPane(PopupPanelView.getInstance());
        root.getChildren().get(0).setViewOrder(-1);
        root.getChildren().get(0).setVisible(false);

        setPanel(HomePanelView.getInstance());

        Scene scene = new Scene(root, 800, 400);
        scene.getStylesheets().add("/styles/global.css");

        window.setTitle("Shape Up !");
        window.getIcons().add(ImageUtil.getImage("icon.png"));
        window.setMaximized(true);
        window.setResizable(false);
        window.setScene(scene);
        window.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        window.show();
    }
}

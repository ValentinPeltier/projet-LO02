package fr.utt.lo02.tdvp.view.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import fr.utt.lo02.tdvp.model.panel.*;
import fr.utt.lo02.tdvp.view.gui.utils.ImageUtil;

public class Window extends Application implements Runnable {
    private static StackPane root;

    public static void setPanel(Pane panel) {
        if (root.getChildren().size() > 1) {
            root.getChildren().remove(1);
        }
        root.getChildren().add(panel);
    }

    @Override
    public void run() {
        initWindow();
    }

    public static void initWindow() {
        launch();
    }

    public static void displayPopup(String content) {
        displayPopup("", content);
    }

    public static void displayPopup(String title, String content) {
        PopupPanelView.getInstance().setText(title, content);
        root.getChildren().get(0).setVisible(true);
    }

    public static void hidePopup() {
        root.getChildren().get(0).setVisible(false);
    }

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

package fr.utt.lo02.tdvp.view.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window extends Application {
    private static Scene scene;

    public static void setPanel(Pane panel) {
        scene.setRoot(panel);
    }

    public static void initWindow() {
        launch();
    }

    @Override
    public void start(Stage window) {
        scene = new Scene(HomePanel.getInstance(), 800, 400);
        scene.getStylesheets().add("/styles/global.css");

        window.setTitle("Shape Up !");
        window.getIcons().add(Images.getImage("icon.png"));
        window.setMaximized(true);
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

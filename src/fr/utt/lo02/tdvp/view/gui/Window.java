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
        scene = new Scene(PanelHome.getPane(), 800, 400);
        scene.getStylesheets().add("/styles/global.css");

        window.setMaximized(true);
        window.setTitle("Shape Up !");
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

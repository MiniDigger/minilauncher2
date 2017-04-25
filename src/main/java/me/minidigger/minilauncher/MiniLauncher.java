package me.minidigger.minilauncher;/**
 * Created by mbenndorf on 25.04.2017.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import me.minidigger.minilauncher.dtos.VersionsManifest;
import me.minidigger.minilauncher.manager.VersionManager;
import me.minidigger.minilauncher.tasks.UpdateVersionsTask;
import me.minidigger.minilauncher.util.SystemManager;

import java.util.logging.Logger;

public class MiniLauncher extends Application {

    public static SystemManager systemManager = new SystemManager();
    private Logger logger = Logger.getLogger(MiniLauncher.class.getName());

    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %4$-6s %2$s %5$s%6$s%n");

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        debugInfo();

        new UpdateVersionsTask(VersionManager::init);

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(event -> logger.info("Hello World!"));

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void debugInfo() {
        SystemManager.Platform platform = systemManager.getPlatform();
        SystemManager.OS os = platform.getOS();
        String arch = platform.getArch().getName();
        logger.info("Debug info :");
        logger.info("OS : " + os.getName());
        logger.info("Architecture : " + arch);
        logger.info("Java version : " + System.getProperty("java.version"));
    }
}

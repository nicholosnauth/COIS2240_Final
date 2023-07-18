package Menus;

import core.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import levels.Loader;

import java.util.Objects;

public class Launcher {

    private static ObjectHandler handler;
    private static GameLoop loop;
    public static Scene scene;
    public static int scoreKeep;

    public Launcher(){

    }

    public void game() throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        scene.setRoot(root);

        Player.PlayerInput.getInput().setScene(scene);
    }

    public void score() throws  Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Menus/HighScore.fxml")));
        scene.setRoot(root);
        if(!DBHandler.isDbExists()){
            Alert warning = new Alert(Alert.AlertType.WARNING,
                    "The database directory path is missing or incorrect." +
                            "\n To add a directory, check the method: Menus > DBHandler > connectToDataBase", ButtonType.CLOSE );
            warning.show();
        }
    }

    public void mainMenu() throws  Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Menus/MainMenu.fxml")));
        scene.setRoot(root);
    }

    public void gameOver() throws  Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Menus/GameOver.fxml")));
        scene.setRoot(root);

    }

    public void gameEnd() throws Exception{
        if(Launcher.scoreKeep == 0) {
            Launcher.scoreKeep = PlayerStats.getScore();
        }
        loop.stop();
        PlayerStats.reset();
        handler.clearObjects();
        Loader.enemyCount = 0;

        gameOver();
    }

    public static void setHandler(ObjectHandler handler) {
        Launcher.handler = handler;
    }

    public static void setLoop(GameLoop loop) {
        Launcher.loop = loop;
    }
}

package Menus;

import core.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Launcher {

    public static Scene scene;

    public void game() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/sample.fxml"));
        scene.setRoot(root);

        Player.PlayerInput.newInput().setScene(scene);
    }

    public void score() throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Menus/HighScore.fxml"));
        scene.setRoot(root);
    }

    public void mainMenu() throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Menus/MainMenu.fxml"));
        scene.setRoot(root);
    }

}

package Menus;

import Menus.Launcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** Basically just runs the game, shouldn't need to change this.*/
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Menus/MainMenu.fxml"));
        Launcher.scene = new Scene(root, 1000, 600, Color.SLATEGRAY);


        primaryStage.setTitle("TDGame");
        primaryStage.setScene(Launcher.scene);
        primaryStage.setResizable(false);




        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

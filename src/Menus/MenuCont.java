package Menus;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MenuCont {

    private final Launcher launch = new Launcher();


    // Main menu

    @FXML
    private Button returnToMainMenu;
    @FXML
    private Button startGame;

    public void startGame() throws Exception {
        startGame.setDisable(true);
        scoreBoard.setDisable(true);

        launch.game();

    }

    public void scoreBoard() throws Exception {
        startGame.setDisable(true);
        scoreBoard.setDisable(true);

        launch.score();

    }

    public void endGame() {
        Platform.exit();
    }



    // Score Screen

    @FXML
    private Button scoreBoard;

    public void mainMenu(ActionEvent actionEvent) throws Exception {
        returnToMainMenu.setDisable(true);

        launch.mainMenu();
    }


}

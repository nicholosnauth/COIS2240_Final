package Menus;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;


public class MenuCont {

    private final Launcher launch = new Launcher();
    private final DBHandler dbHandler = new DBHandler();


    // Main menu

    @FXML
    private Button returnToMainMenu;
    @FXML
    private Button startGame;

    public void startGame() throws Exception {
        launch.game();
    }

    public void scoreBoard() throws Exception {
        launch.score();
    }

    public void endGame() {
        Platform.exit();
    }



    // Score Board

    @FXML
    private Button scoreBoard;
    @FXML
    private Text name1;
    @FXML
    private Text name2;
    @FXML
    private Text name3;
    @FXML
    private Text name4;
    @FXML
    private Text name5;
    @FXML
    private Text name6;
    @FXML
    private Text name7;
    @FXML
    private Text name8;
    @FXML
    private Text name9;
    @FXML
    private Text name10;

    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    private Text score3;
    @FXML
    private Text score4;
    @FXML
    private Text score5;
    @FXML
    private Text score6;
    @FXML
    private Text score7;
    @FXML
    private Text score8;
    @FXML
    private Text score9;
    @FXML
    private Text score10;

    public void mainMenu() throws Exception {
        launch.mainMenu();
    }


    // Game Over

    @FXML
    private CappedTextField name;
    @FXML
    private Button submitScore;
    @FXML
    private Text score;

    public void submitScore() {

        String n = name.getText().toUpperCase();

        if(DBHandler.validateName(n)){
            name.setDisable(true);
            submitScore.setDisable(true);

            dbHandler.onSubmit(n);

        }else{
            Alert warning = new Alert(Alert.AlertType.WARNING,
                    "Must be exactly three letters and/or numbers!", ButtonType.CLOSE );
            warning.show();
        }
    }

    private void fillGUITable(){

    }


    // All purpose initialize
    public void initialize(){
        if(score != null){
        String rScore = "SCORE: " + String.format("%07d", Launcher.scoreKeep);
        score.setText(rScore);
        }

        dbHandler.onInit();

        if(name10 != null && !dbHandler.scores.isEmpty()) {
            try {
                name1.setText(dbHandler.scores.get(0).getName());
                score1.setText(String.format("%07d", dbHandler.scores.get(0).getScore()));

                name2.setText(dbHandler.scores.get(1).getName());
                score2.setText(String.format("%07d", dbHandler.scores.get(1).getScore()));

                name3.setText(dbHandler.scores.get(2).getName());
                score3.setText(String.format("%07d", dbHandler.scores.get(2).getScore()));

                name4.setText(dbHandler.scores.get(3).getName());
                score4.setText(String.format("%07d", dbHandler.scores.get(3).getScore()));

                name5.setText(dbHandler.scores.get(4).getName());
                score5.setText(String.format("%07d", dbHandler.scores.get(4).getScore()));

                name6.setText(dbHandler.scores.get(5).getName());
                score6.setText(String.format("%07d", dbHandler.scores.get(5).getScore()));

                name7.setText(dbHandler.scores.get(6).getName());
                score7.setText(String.format("%07d", dbHandler.scores.get(6).getScore()));

                name8.setText(dbHandler.scores.get(7).getName());
                score8.setText(String.format("%07d", dbHandler.scores.get(7).getScore()));

                name9.setText(dbHandler.scores.get(8).getName());
                score9.setText(String.format("%07d", dbHandler.scores.get(8).getScore()));

                name10.setText(dbHandler.scores.get(9).getName());
                score10.setText(String.format("%07d", dbHandler.scores.get(9).getScore()));

            }catch (Exception ignored){}

        }
    }

}

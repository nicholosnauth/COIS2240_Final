package core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import levels.Loader;


public class HUD {

    //private Bounds sceneBounds = Launcher.scene.getCamera().getBoundsInLocal();

    private final PlayerStats stats = PlayerStats.getInstance();
    private final GraphicsContext context;
    private ObjectHandler handler;
    private static GameObject player;

    private final Image hFull = new Image("resources/hFull.png");
    private final Image hEmpty = new Image("resources/hEmpty.png");

    private double timeRatio;

    public HUD(GraphicsContext context, ObjectHandler handler) {
        this.context = context;
        this.handler = handler;
    }

    public void render(){
            double x = player.getPosition().getX() + 150;
            double y = player.getPosition().getY() + 275;
            backGround(x, y);
            timer(x, y);
            hp(x, y);
            levelAndScore(x, y);


    }

    public void hp(double x, double y){
        for(int i = 0; i < stats.getMaxHealth(); i++){
            context.drawImage(hEmpty, x + 5 +i*22, y + 5, 20, 20);
            if(i < stats.getHealth())
                context.drawImage(hFull, x + 5 +i*22, y + 5, 20, 20);
        }

    }

    public void timer(double x, double y){
        context.setFill(Color.WHITE);
        context.setFont(new Font("times", 18));
        if(Loader.getTimer() > 0){
            if(!Loader.isGrace()){
                timeRatio = (double)Loader.getTimer()/Loader.getLevelTime();
                //context.fillText("Time: " + String.valueOf((int)timeLeft), x + 5, y + 48);
                context.fillRect(x+5, y+30, timeRatio*220,15 );
            }
            else {
                timeRatio = (double)Loader.getTimer()/Loader.getGraceTime();
                context.setFill(Color.GREEN);
                context.fillRect(x + 5, y + 30, timeRatio*50, 15);
            }
        }else if(Loader.enemyCount != 0){
            context.fillText("Enemies remaining: " + Loader.enemyCount, x+5, y +45);

        }
    }

    public void levelAndScore(double x, double y){
        context.setFill(Color.WHITE);
        context.setFont(new Font("times", 18));

        context.fillText("Level: " + Loader.getLevel(), x + 235, y + 25);

        String score = String.format("%07d", PlayerStats.getScore());
        context.fillText("Score: " + score, x + 235, y + 45);


    }

    public void backGround(double x, double y){
        context.setFill(Color.BLACK);
        context.fillRoundRect(x, y, 400, 60, 15, 15);
    }



    public static void setPlayer(GameObject player) {
        HUD.player = player;
    }

}

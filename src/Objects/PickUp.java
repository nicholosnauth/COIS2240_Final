package Objects;

import core.*;
import javafx.scene.image.Image;

public class PickUp extends GameObject {

    private final PlayerStats stats = PlayerStats.getInstance();

    private int pickUpID;

    private final Image hUp = new Image("resources/hMaxUp.png");
    private final Image speedUp = new Image("resources/speedUp.png");
    private final Image fireRateUp = new Image("resources/fireRateUp.png");

    public PickUp(int posX, int posY, ObjectHandler handler) {
        super(posX, posY, handler);
        createPickUp();
    }


    private void createPickUp(){
        double checker = Math.random()*3;

        if( checker < 1 && stats.getMaxHealth() < stats.getHealthCap()){
            this.setImage(hUp);
            pickUpID = 1;
        }else if ( checker < 2 && stats.getSpeedMod() < stats.getSpeedCap()){
            this.setImage(speedUp);
            pickUpID = 2;
        }else if ( checker < 3 && stats.getFireRate() > stats.getFireRateCap()){
            this.setImage(fireRateUp);
            pickUpID = 3;
        }else{
            handler.removeObject(this);
        }

        if(getImage() != null){
        height = (int) getImage().getHeight();
        width = (int) getImage().getWidth();
        }

    }

    private void createHealth(){

    }

    @Override
    public void tick() {

    }

    @Override
    public void collisionCode(ID id, GameObject object) {
        if(id == ID.Player){
            if( pickUpID == 1 ){
                stats.setMaxHealth(stats.getMaxHealth() + 1);
                handler.removeObject(this);
            }else if ( pickUpID == 2 ){
                stats.setSpeedMod(stats.getSpeedMod() + 0.1);
                handler.removeObject(this);
            }else if ( pickUpID == 3 ){
                stats.improveFireRate();
                handler.removeObject(this);
            }
        }

    }

}

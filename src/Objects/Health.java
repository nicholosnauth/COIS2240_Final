package Objects;

import core.GameObject;
import core.ID;
import core.ObjectHandler;
import core.PlayerStats;
import javafx.scene.image.Image;

public class Health extends GameObject {

    private final Image hFull = new Image("resources/hFull.png");

    public Health(int posX, int posY, ObjectHandler handler) {
        super(posX, posY, handler);
        this.setImage(hFull);
        height = (int) getImage().getHeight();
        width = (int) getImage().getWidth();
    }

    @Override
    public void tick() {

    }

    @Override
    public void collisionCode(ID id, GameObject object) {
        if(id == ID.Player){
            if(PlayerStats.getInstance().getHealth() < PlayerStats.getInstance().getMaxHealth()) {
                PlayerStats.getInstance().setHealth(PlayerStats.getInstance().getHealth() + 1);
                handler.removeObject(this);
            }

        }
    }

}

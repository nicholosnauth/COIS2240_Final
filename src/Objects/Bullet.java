package Objects;

import core.GameObject;
import core.ID;
import core.ObjectHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Bullet extends GameObject {
    GameObject player;
    Image bulletImage = new Image("resources/man.png");

    public Bullet(ObjectHandler handler, double velX, double velY) {
        this.handler = handler;
        this.player = handler.findPlayer();
        this.position = player.getPosition();
        this.setId(ID.Bullet);

        this.setImage(bulletImage);
        width = (int) bulletImage.getWidth();
        height = (int) bulletImage.getHeight();

        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void tick() {
        position = new Point2D(position.getX() + velX, position.getY() + velY);
    }

    @Override
    public void collisionCode(ID id) {

    }

}

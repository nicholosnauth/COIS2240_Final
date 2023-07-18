package Objects;

import core.GameObject;
import core.ID;
import core.ObjectHandler;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Bullet extends GameObject {
    GameObject player;
    Image bulletImage = new Image("resources/bullet.png");

    int timer = 300;


    public Bullet(ObjectHandler handler, double velX, double velY) {
        this.handler = handler;
        this.player = handler.findPlayer();

        this.setId(ID.Bullet);

        this.setImage(bulletImage);
        width = (int) bulletImage.getWidth();
        height = (int) bulletImage.getHeight();

        this.position = new Point2D(player.getPosition().getX() + (double)(player.getWidth() - this.width)/2,
                player.getPosition().getY() + (double) (player.getHeight() - this.height)/2 );

        this.velX = velX;
        this.velY = velY;
    }

    @Override
    public void tick() {
        position = new Point2D(position.getX() + velX, position.getY() + velY);
        timer--;
        if (timer == 0) handler.removeObject(this);
    }

    @Override
    public void collisionCode(ID id, GameObject object) {

    }

}

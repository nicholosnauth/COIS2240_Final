package Objects;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import core.*;
import levels.Loader;

//https://www.youtube.com/watch?v=JBGCCAv76YI
public class BasicEnemy extends GameObject{
    private double basicSpeed;
    private GameObject player;
    private double health;

    Image basicImage = new Image("resources/Enemy.png");


    public BasicEnemy(int posX, int posY, ObjectHandler handler) {
        super(posX, posY, handler);
        this.setId(ID.BasicEnemy);
        this.setImage(basicImage);
        width = (int) basicImage.getWidth();
        height = (int) basicImage.getHeight();
        this.player = handler.findPlayer();
        this.basicSpeed = 8;
        this.health = 1;
    }

    @Override
    public void tick() {
        move();
        position = new Point2D(position.getX() + velX, position.getY() + velY);


    }

    @Override
    public void collisionCode(ID id, GameObject gameObj) {
        if(id == ID.Bullet){
            handler.removeObject(gameObj);
            health = health - PlayerStats.getInstance().getDamage();
            if(health <= 0){
                Loader.enemyCount--;
                PlayerStats.setScore(PlayerStats.getScore() + 2);
                handler.removeObject( this);

                if(Loader.getTimer() == 0 && Loader.enemyCount == 0 || Math.random() < 0.01) {
                    handler.addObject(new PickUp((int) this.getPosition().getX(), (int) this.getPosition().getY(), handler));
                }else if (Math.random() < 0.05){
                    handler.addObject(new Health((int) this.getPosition().getX(), (int) this.getPosition().getY(), handler));
                }
            }
        }else if(id == ID.BasicEnemy || id == ID.BossEnemy || id == ID.SingleFireEnemy){
            nonStaticBarrier(gameObj, this);
        }

    }

    public void move(){

        double mdX = player.getPosition().getX() - this.getPosition().getX();
        double mdY = player.getPosition().getY() - this.getPosition().getY();
        double mHyp = Math.sqrt(mdX * mdX + mdY * mdY);
        //Use mnx and mny to modify velocity direction.
        double mux = mdX / mHyp;
        double muy = mdY / mHyp;

        velX = mux*basicSpeed;
        velY = muy*basicSpeed;
    }


}

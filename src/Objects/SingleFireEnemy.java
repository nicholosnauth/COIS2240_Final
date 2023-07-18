package Objects;

import core.*;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import levels.Loader;

public class SingleFireEnemy extends GameObject {
    private double singleSpeed;
    private GameObject player;
    private double health;

    private int fireRate = 180;
    private int bulletVel = 20;
    private int timer = 0;

    Image singleImage = new Image("resources/Enemy2.png");
    Image firingImage = new Image("resources/Enemy2F.png");

    public SingleFireEnemy(int posX, int posY, ObjectHandler handler) {
        super(posX, posY, handler);
        this.setId(ID.SingleFireEnemy);
        this.setImage(singleImage);
        width = (int) singleImage.getWidth();
        height = (int) singleImage.getHeight();
        this.player = handler.findPlayer();
        this.singleSpeed = 2;
        this.health = 3;
    }

    @Override
    public void tick() {
        fire();
        move();
        position = new Point2D(position.getX() + velX, position.getY() + velY);
    }

    @Override
    public void collisionCode(ID id, GameObject gameObj) {
        if(id == ID.Bullet){

            health = health - PlayerStats.getInstance().getDamage();
            handler.removeObject(gameObj);
            if(health <= 0){
                Loader.enemyCount--;
                PlayerStats.setScore(PlayerStats.getScore() + 4);
                handler.removeObject( this);

                if(Loader.getTimer() == 0 && Loader.enemyCount == 0 || Math.random() < 0.01) {
                    handler.addObject(new PickUp((int) this.getPosition().getX(), (int) this.getPosition().getY(), handler));
                }else if (Math.random() < 0.10){
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

        velX = mux*singleSpeed;
        velY = muy*singleSpeed;
        // if enemy collides with the barrier then adjust the movement

    }

    public void fire(){
        // --- Player firing
        // Calculates the unit vector to allow for consistent bullet velocity.
        // Note that since PlayerInput is static, this code can be copied anywhere it's needed.
        double mdX = player.getPosition().getX() - this.getPosition().getX();
        double mdY = player.getPosition().getY() - this.getPosition().getY();
        double mHyp = Math.sqrt(mdX * mdX + mdY * mdY);
        //Use mnx and mny to modify velocity direction.
        double mux = mdX / mHyp;
        double muy = mdY / mHyp;

        timer ++;
        if (timer == fireRate - 40) this.setImage(firingImage);

        if (timer >= fireRate){
            handler.addObject(new EnemyBullet(this.handler, bulletVel*mux, bulletVel*muy, this));
            timer = 0;
            this.setImage(singleImage);
        }
    }

}

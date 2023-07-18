package Objects;
import core.GameObject;
import core.ID;
import core.ObjectHandler;
import core.PlayerStats;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import levels.Loader;

public class BossEnemy extends GameObject {
    private double bossSpeed;
    private int chargeCounter;
    private final GameObject player;

    private double health;

    private int charge = 300, fireRate = 4;
    private int bulletVel = 5;

    private int timer = (int)(Math.random()*charge);

    Image bossImage = new Image("resources/Boss.png");
    Image bossFire1 = new Image("resources/BossF1.png");
    Image bossFire2 = new Image("resources/BossF2.png");
    Image bossFire3 = new Image("resources/BossF3.png");

    public BossEnemy(int posX, int posY, ObjectHandler handler){
        super(posX, posY, handler);
        this.setId(ID.BossEnemy);
        this.setImage(bossImage);
        width = (int) bossImage.getWidth();
        height = (int) bossImage.getHeight();
        this.player = handler.findPlayer();

        this.health = 50 + Loader.getLevel()*2;
        if (health >= 100) health = 100;

        this.chargeCounter = -(int)(Math.random()*200);

        this.bossSpeed = 9;
    }

    @Override
    public void tick() {
        move();
        fire();
        position = new Point2D(position.getX() + velX, position.getY() + velY);
    }



    @Override
    public void collisionCode(ID id, GameObject gameObj) {
        if(id == ID.Bullet){
            health = health - PlayerStats.getInstance().getDamage();
            handler.removeObject(gameObj);//remove the bullet
            if(health <= 0){
                Loader.enemyCount--;
                PlayerStats.setScore(PlayerStats.getScore() + 200);
                handler.removeObject( this);//Boss is dead remove it from the game



                if(Loader.getTimer() == 0 && Loader.enemyCount == 0 || Math.random()*Loader.getLevel() > 100) {
                    handler.addObject(new PickUp((int) this.getPosition().getX(), (int) this.getPosition().getY(), handler));
                }else{
                    handler.addObject(new Health((int) this.getPosition().getX(), (int) this.getPosition().getY(), handler));
                }
            }
        }else if(id == ID.BossEnemy ){
            nonStaticBarrier(this, gameObj);
        }else if(id == ID.Barrier){
            nonStaticBarrier(this, gameObj);
        }

    }




    public void move(){
        double mdX = player.getPosition().getX() - this.getPosition().getX();
        double mdY = player.getPosition().getY() - this.getPosition().getY();
        double mHyp = Math.sqrt(mdX * mdX + mdY * mdY);
        //Use mnx and mny to modify velocity direction.
        double mux = mdX / mHyp;
        double muy = mdY / mHyp;



        if (!firing)
            chargeCounter++;


        if(chargeCounter < 310){
            bossSpeed = 6;
            velX = mux*bossSpeed;
            velY = muy*bossSpeed;
        }else if(chargeCounter < 370){
            velX = 0; velY = 0;
        } else if(chargeCounter == 370){
            bossSpeed = 25;

            velX = mux*bossSpeed;
            velY = muy*bossSpeed;
        }else if (chargeCounter > 400){
            chargeCounter = 0;
        }



    }






    // --- Boss firing mechanics --- //
    private int ran1, ran2;
    private boolean firing = false;
    private double rng;
    public void fire(){
        if(timer <= 0) {
            firing = false;
            rng = Math.random();
            this.setImage(bossImage);
        }
        else if (timer == charge - 120) this.setImage(bossFire1);
        else if (timer == charge - 100) this.setImage(bossFire2);
        else if (timer >= charge - 30) {
            velX = 0; velY = 0;
            this.setImage(bossFire3);

        }

        if(!firing) timer ++;


        if (timer >= charge || firing){
            if (rng < 0.5)
                firePattern1();
            else
                firePattern2();

            velX = 0; velY = 0;
            firing = true;
            timer = timer - 2;
            this.setImage(bossFire3);

        }


    }


    private void firePattern1(){
        bulletVel = 10;
        ran1 = (int)(Math.random()*this.getPosition().getX());
        ran2 = (int)(Math.random()*this.getPosition().getY());

        if(Math.random() <= 0.5){
            ran1 = -ran1;
        }
        if(Math.random() <= 0.5){
            ran2 = -ran2;
        }

            // Calculates the unit vector to allow for consistent bullet velocity.
            // Note that since PlayerInput is static, this code can be copied anywhere it's needed.

        double mdX = ran1;
        double mdY = ran2;
        double mHyp = Math.sqrt(mdX * mdX + mdY * mdY);
        //Use mnx and mny to modify velocity direction.
        double mux = mdX / mHyp;
        double muy = mdY / mHyp;


        if(timer % 2 == 0) {

            handler.addObject(new EnemyBullet(this.handler, bulletVel * mux, bulletVel * muy, this));
        }
    }

    private void firePattern2(){
        bulletVel = 15;
        ran1 = (int)(Math.random()*this.getPosition().getX())/4;
        ran2 = (int)(Math.random()*this.getPosition().getY())/4;

        if(Math.random() <= 0.5){
            ran1 = -ran1;
        }
        if(Math.random() <= 0.5){
            ran2 = -ran2;
        }

        // Calculates the unit vector to allow for consistent bullet velocity.
        // Note that since PlayerInput is static, this code can be copied anywhere it's needed.
        double mdX = player.getPosition().getX() - this.getPosition().getX() +ran1;
        double mdY = player.getPosition().getY() - this.getPosition().getY() +ran2;
        double mHyp = Math.sqrt(mdX * mdX + mdY * mdY);
        // Use mnx and mny to modify velocity direction.
        double mux = mdX / mHyp;
        double muy = mdY / mHyp;

        if(timer % fireRate == 0) {
            handler.addObject(new EnemyBullet(this.handler, bulletVel * mux, bulletVel * muy, this));
        }
    }

}

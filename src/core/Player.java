package core;

import Menus.Launcher;
import Objects.Bullet;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/** The player, as well as the player's controls.
 * Only reason I can think of to change this would be to adjust player speed
 * */
public class Player extends GameObject {

    private boolean immune = false;//when true the player is immune
    private int IFrames;//counter for the length the player is immune for
    Image playerImage = new Image("resources/man.png");//booger man image
    Image playerImageD = new Image("resources/ManD.png");
    PlayerStats user = PlayerStats.getInstance();

    private final int baseSpeed = 10;

    public Player(int posX, int posY, ObjectHandler handler) {
        super(posX, posY, handler);
        this.setId(ID.Player);
        this.setImage(playerImage);
        width = (int) playerImage.getWidth();
        height = (int) playerImage.getHeight();

    }


    public void collisionCode(ID id, GameObject gameObj) {
        if(id == ID.EnemyBullet){
            handler.removeObject(gameObj);//removes enemy bullet
            damagePlayer();
        }else if(id == ID.BossEnemy || id == ID.BasicEnemy || id == ID.SingleFireEnemy){
            damagePlayer();
        }

        if(id == ID.Barrier){
            nonStaticBarrier(this, gameObj);
        }


    }

    public void damagePlayer(){
        if(IFrames <= 0) {
            user.setHealth(user.getHealth() - 1);
            IFrames = user.getImmuneTime();//counter starts for the immunity period
            this.setImage(playerImageD);
        }
    }

    @Override
    public void tick() {
        playerInput();

        if (IFrames > 0){
            IFrames--;

            if(IFrames % 10 == 0){
                if(this.getImage() == playerImage)
                    this.setImage(playerImageD);
                else
                    this.setImage(playerImage);
            }

            if(IFrames <= 0)
                this.setImage(playerImage);
        }

        playerInput();//take in player input
        position = new Point2D(position.getX() + velX, position.getY() + velY);

    }




    // --- Below here is all information for player input --- //
    private int timer = 0;

    public void playerInput(){
        PlayerInput e = PlayerInput.getInput();
        e.press();

        double mX = 0, mY = 0;
        //mX = 0; mY = 0;

        if(PlayerInput.up) mY -= 1;
        if(PlayerInput.down) mY +=1;
        if(PlayerInput.left) mX -= 1;
        if(PlayerInput.right) mX += 1;

        double Hyp = Math.sqrt(mX * mX + mY * mY);
        if (Hyp == 0) Hyp = 1;
        //Use mnx and mny to modify velocity direction.
        double uX = mX / Hyp;
        double uY = mY / Hyp;

        velX = baseSpeed*uX*user.getSpeedMod(); velY = baseSpeed*uY*user.getSpeedMod();



        // --- Player firing
        // Calculates the unit vector to allow for consistent bullet velocity.
        // Note that since PlayerInput is static, this code can be copied anywhere it's needed.
        double mdX = PlayerInput.mouseX - 500;
        double mdY = PlayerInput.mouseY - 300;
        double mHyp = Math.sqrt(mdX * mdX + mdY * mdY);
        //Use mnx and mny to modify velocity direction.
        double mux = mdX / mHyp;
        double muy = mdY / mHyp;

        timer ++;
        if (timer >= user.getFireRate()){
            if (PlayerInput.firing){
            handler.addObject(new Bullet(this.handler, user.getBulletVel()*mux, user.getBulletVel()*muy));
            timer = 0;
            }
        }

        // Exits the game upon registering that the escape key has been pressed.
        // This may be changed later to a pause rather than a hard exit
        if (PlayerInput.exit){
            Launcher launch = new Launcher();
            try {
                launch.gameEnd();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            handler.clearObjects();
        }

    }

    /** Nested class for inputs, most of the properties are static as there's no need to worry
     * about multiple instances of player input, and it's easier to set the scene from Main that way.
     * */
    public static class PlayerInput{
        private static boolean up, down, left, right;
        private static boolean exit;
        private static boolean firing;
        private static Scene scene;
        public static double mouseX, mouseY;

        public static PlayerInput getInput(){
            return new PlayerInput();
        }
        public PlayerInput() {
        }

        public void setScene(Scene scene) {
            PlayerInput.scene = scene;
        }

        // Uses the key event checkers to set directional values to true when keys are pressed,
        // then to false when released
        public void press(){
            scene.setOnKeyPressed(keyDown);
            scene.setOnKeyReleased(keyUp);
            scene.setOnMousePressed(mouseDown);
            scene.setOnMouseDragged(mouseDown);
            scene.setOnMouseReleased(mouseUp);
        }


        // Sets the directional values to true upon key event
        EventHandler<KeyEvent> keyDown = e -> {
            if (e.getCode().equals(KeyCode.W)) {
                up = true;
            }

            if (e.getCode().equals(KeyCode.A)) {
                left = true;
            }

            if (e.getCode().equals(KeyCode.S)) {
                down = true;
            }

            if (e.getCode().equals(KeyCode.D)) {
                right = true;
            }

            if (e.getCode().equals(KeyCode.ESCAPE)){
                exit = true;
            }
        };

        // Sets the directional values to false upon key event
        EventHandler<KeyEvent> keyUp = e -> {
            if (e.getCode().equals(KeyCode.W)) {
                up = false;
            }

            if (e.getCode().equals(KeyCode.A)) {
                left = false;
            }

            if (e.getCode().equals(KeyCode.S)) {
                down = false;
            }

            if (e.getCode().equals(KeyCode.D)) {
                right = false;
            }

            if (e.getCode().equals(KeyCode.ESCAPE)){
                exit = false;
            }

        };

        // Mouse events, implemented the same way as above
        EventHandler<MouseEvent> mouseDown = e ->{
          if (e.getButton() == MouseButton.PRIMARY){
              firing = true;
              mouseX = e.getX();
              mouseY = e.getY();
          }
        };
        EventHandler<MouseEvent> mouseUp = e ->{
            if (e.getButton() == MouseButton.PRIMARY){
                firing = false;
            }
        };

    }



}

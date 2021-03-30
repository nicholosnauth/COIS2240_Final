package core;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/** The player, as well as the player's controls.
 * Only reason I can think of to change this would be to adjust player speed
 * */
public class Player extends GameObject {

    private double speedMod;
    Image playerImage = new Image("resources/man.png");

    public Player(int posX, int posY, ObjectHandler handler) {
        super(posX, posY, handler);
        this.setId(ID.Player);
        this.setImage(playerImage);
        width = (int) playerImage.getWidth();
        height = (int) playerImage.getHeight();
        this.speedMod = 1;
    }

    @Override
    public void collisionCode(ID id) {
        // Set an if statement like the one below for the ID of the object you want.
        if (id == ID.Player){
            // Code for what happens to the object when colliding with something with the corresponding ID
            System.out.println("Hello");
        }
    }

    @Override
    public void tick() {
        playerInput();
        position = new Point2D(position.getX() + velX*speedMod, position.getY() + velY*speedMod);
    }

    public void playerInput(){
        PlayerInput e = PlayerInput.newInput();

        e.press();
        if(e.up) velY = -5;
        else if(!e.down) velY = 0;
        if(e.left) velX = -5;
        else if(!e.right) velX = 0;
        if(e.down) velY = 5;
        if(e.right) velX = 5;

        if(e.up && e.down) velY = 0;
        if(e.left && e.right) velX = 0;

    }

    /** Nested class for inputs, most of the properties are static as there's no need to worry about
     * multiple instances of player input, and it's easier to set the scene from Main that way.
     * */
    static class PlayerInput{
        private static boolean up, down, left, right;
        private static Scene scene;

        public static PlayerInput newInput(){
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
        };

    }

    // --- Getters and setters --- //
    public void setSpeedMod(int speedMod) {
        this.speedMod = speedMod;
    }
}

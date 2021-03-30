package core;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.lang.reflect.Constructor;

/** There shouldn't be any need to change this class, but if there is let me know */
public abstract class GameObject {

    protected Point2D position;
    protected int velX, velY;
    protected int height, width;
    protected ObjectHandler handler;

    private ID id;
    private Image image;

    /** Constructor for visible objects. The image should be defined in the respective class */
    public GameObject(int posX, int posY, ObjectHandler handler) {
        this.position = new Point2D(posX, posY);
        this.handler = handler;
    }

    /** Constructor for invisible objects (triggers, walls, etc,
     *  anything that doesn't need to be rendered on the canvas) */
    public GameObject(int posX, int posY, int height, int width, ObjectHandler handler){
        this.position = new Point2D(posX, posY);
        this.width = width;
        this.height = height;
        this.handler = handler;
    }

    /** This tick method is overridden and called every frame by the ObjectHandler. */
    public abstract void tick();

    /**  These methods are for collision, you can see some sample code for them in the player class.
     * This might not be the most effective way to do this, but it seemed like the cleanest to me,
     * since it means we don't have to constantly rewrite the same code in different places.
     * */
    public abstract void collisionCode(ID id);

    public Rectangle2D getBounds(){
        return new Rectangle2D(position.getX(), position.getY(), height, width);
    }


    // --- Getters and setters --- //
    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public Point2D getPosition(){
        return position;
    }

    public void setImage(Image image){
        this.image = image;
    }

    public Image getImage(){
        return image;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

}

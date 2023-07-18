package core;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.lang.reflect.Constructor;

/** There shouldn't be any need to change this class, but if there is let me know */
public abstract class GameObject {

    protected Point2D position;
    protected double velX, velY;
    protected int height, width;
    protected ObjectHandler handler;

    private ID id;
    private Image image;

    public GameObject() {
    }

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
    public abstract void collisionCode(ID id, GameObject object);

    public Rectangle2D getBounds(){
        return new Rectangle2D(position.getX(), position.getY(), width, height);
    }




    // --- Code for collision handling --- //

    public Rectangle2D bStepX(){
        return new Rectangle2D(position.getX()-velX, position.getY(), width, height);
    }

    public Rectangle2D bStepY(){
        return new Rectangle2D(position.getX(), position.getY()-velY, width, height);
    }


    /**Barrier code, prevents objects from overlapping with one another*/
    public void barrier(GameObject object){
        if (!this.getBounds().intersects(object.bStepX()))
            object.setPosition(new Point2D(object.getPosition().getX() - object.getVelX(), object.getPosition().getY()));
        else if (!this.getBounds().intersects(object.bStepY()))
            object.setPosition(new Point2D(object.getPosition().getX(), object.getPosition().getY() - object.getVelY()));
    }

    public void nonStaticBarrier(GameObject object, GameObject object2){
        if (!object2.getBounds().intersects(object.bStepX()))
            object.setPosition(new Point2D(object.getPosition().getX() - object.getVelX(), object.getPosition().getY()));
        else if (!object2.getBounds().intersects(object.bStepY()))
            object.setPosition(new Point2D(object.getPosition().getX(), object.getPosition().getY() - object.getVelY()));

        nonStaticBarrier2(object2, object);
    }

    private void nonStaticBarrier2(GameObject object, GameObject object2){
        if (!object2.getBounds().intersects(object.bStepX()))
            object.setPosition(new Point2D(object.getPosition().getX() - object.getVelX(), object.getPosition().getY()));
        else if (!object2.getBounds().intersects(object.bStepY()))
            object.setPosition(new Point2D(object.getPosition().getX(), object.getPosition().getY() - object.getVelY()));
        else
            object.setPosition(new Point2D(object.getPosition().getX() -object.getVelX(),
                    object.getPosition().getY() - object.getVelY()));
    }



    // --- Getters and setters --- //
    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setPosition(Point2D position) {
        this.position = position;
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

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }
}

package core;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.LinkedList;

/** This is the class that contains and handles all the game objects. This includes rendering
 * and ticking their methods.
 * There shouldn't be any major reason to change it unless you need to replace the placeholder
 * background as mentioned below */
public class ObjectHandler {
    Canvas canvas;
    GraphicsContext context;

    //Background temp
    Image bkg = new Image("resources/Level1.png");

    LinkedList<GameObject> object = new LinkedList<>();

    public ObjectHandler(Canvas canvas){
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    /** This method contains a loop that checks the tick method of every object in the list and runs it
     * There shouldn't be any reason to modify it */
    public void tick() {

        for (int i = 0; i < object.size(); i++) {
                object.get(i).tick();
        }
        for (int i = 0; i < object.size(); i++) {
                collision(object.get(i));
        }
        

        render();
    }


    public void collision(GameObject temp){
        for(int i = 0; i < object.size(); i++){
                if(temp.getBounds().intersects(object.get(i).getBounds())) {
                    if (temp != object.get(i))
                        temp.collisionCode(object.get(i).getId(), object.get(i));
                }
            }


        }


    public void render(){
        context.save();

        // !! These lines render the background, they can be replaced if a new class or method is made to
        // define the background. They're just a placeholder for now.
        context.setFill(Color.SLATEGRAY);
        context.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        context.drawImage(bkg, 0, 0);

        // End of background space

        // Renders object by their respective images and locations


        for(int i = 0; i < object.size(); i++){

            // This if statement can be removed if game boundaries are not defined by objects.
            if (object.get(i).getImage() != null) {
                context.drawImage(
                        object.get(i).getImage(),
                        object.get(i).getPosition().getX(),
                        object.get(i).getPosition().getY());
            }
        }


        context.restore();

    }


    // --- Object Handling --- //
    public void addObject(GameObject temp){
        this.object.add(temp);
    }

    public void removeObject(GameObject temp){
        this.object.remove(temp);
    }

    public void clearObjects(){
        object.clear();
    }

    public void removeObjectType(ID id){
        for(int i = 0; i < object.size(); i++) {
            if (this.object.get(i).getId() == id) this.object.remove(this.object.get(i));
        }
    }

    public boolean objectHandled(GameObject temp){
        for(int i = 0; i < object.size(); i++) {
            if (this.object.get(i) == temp) return true;
        }
        return false;
    }

    public GameObject findPlayer(){
        GameObject temp = null;

        for(int i = 0; i < object.size(); i++) {
            if (this.object.get(i).getId() == ID.Player)
                temp = this.object.get(i);
        }

        return temp;
    }


}

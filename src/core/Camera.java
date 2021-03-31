package core;

import javafx.scene.layout.Pane;

public class Camera {

    private GameObject target;
    private ObjectHandler handler;
    private Pane pane;

    public Camera(ObjectHandler handler, Pane pane) {
        this.handler = handler;
        this.pane = pane;
    }

    public void tick(){
        if(target != null) {
            pane.setTranslateX(-target.getPosition().getX() + 475);
            pane.setTranslateY(-target.getPosition().getY() + 275);
        }
    }

    public void findTarget(){
        this.target = handler.findPlayer();
    }
}

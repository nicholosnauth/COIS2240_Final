package core;

import Menus.Launcher;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import levels.Loader;

/** This is where the game is drawn, there shouldn't really be any need to change anything, though you might
 * if you need to  make a new group of some sort that needs to be updated every frame.
 * See the note above the gameLoop for more details. */
public class Controller {

    public Canvas gameCanvas;
    public Pane pane;

    /** This is where the Canvas is drawn */
    public void initialize(){
        ObjectHandler handler = new ObjectHandler(gameCanvas);
        Camera camera = new Camera(handler, pane);
        Spawner spawner = new Spawner(handler, camera);
        Loader loader = new Loader(handler, spawner);
        HUD hud = new HUD(gameCanvas.getGraphicsContext2D(), handler);

        loader.loadFirst();

        /* This is where the game ticks every frame. If you need something to happen every frame, put it here.
        If it extends GameObject, it should already be doing this within it's tick method.

        Remember that the order these things are put in are the order they're rendered/ticked.
         */
        GameLoop loop = new GameLoop() {

            @Override
            public void tick() {
                loader.tick();
                handler.tick();
                camera.tick();
                hud.render();
            }
        };
        Launcher.setHandler(handler);
        Launcher.setLoop(loop);
        loop.start();

    }



}

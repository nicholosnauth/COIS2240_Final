package core;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

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

        // This line is just to spawn the player for demo purposes.
        spawner.spawnPlayer(500, 300);

        /* This is where the game ticks every frame. If you need something to happen every frame, put it here.
        If it extends GameObject, it should already be doing this within it's tick method.
        If you do put something here, try to make it a clean method like the ones already in there. If we
        start coding right in here, it'll get messy really quick.

        some things that might need to be added:
        - Rendering the HUD
        - Rendering the background (at the moment this is being done in ObjectHandler, but it would make sense
        for it to have it's own Class)

        Remember that the order these things are put in are the order they're rendered, so if you add HUD, it
        should go after everything, and if you add Background, it should go before everything.
         */
        GameLoop loop = new GameLoop() {
            @Override
            public void tick(float frameTime) {
                handler.tick();
                spawner.tick();
                camera.tick();
            }
        };
        loop.start();

    }

}

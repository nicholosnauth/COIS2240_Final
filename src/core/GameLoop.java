package core;

import javafx.animation.AnimationTimer;

/** This class is for the game loop. Later, functionality such as pausing and starting the game will need to
 * be added here, but there shouldn't be any reason to touch it for now.
 * */
public abstract class GameLoop extends AnimationTimer {
    private long lastFrameNano;

    @Override
    public void handle(long thisFrameNano) {

        float frameTime = (float) ((thisFrameNano - lastFrameNano)/1e9);
        tick(frameTime);
        lastFrameNano=thisFrameNano;
    }

    public abstract void tick(float frameTime);
}

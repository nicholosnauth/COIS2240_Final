package core;

import javafx.animation.AnimationTimer;

/** This class is for the game loop. Later, functionality such as pausing and starting the game will need to
 * be added here, but there shouldn't be any reason to touch it for now.
 * */
public abstract class GameLoop extends AnimationTimer {
    private long lastFrameNano;
    private float spare = 0;
    private final float pulseRate = 17;

    @Override
    public void handle(long thisFrameNano) {

        // Frame times in milliseconds
        float frameTime = (float) ((thisFrameNano - lastFrameNano)/1e6);
        smoothing(frameTime);
        lastFrameNano=thisFrameNano;
    }

    public abstract void tick();

    private void smoothing(float frameTime){
        spare += frameTime;

        if (spare >= pulseRate){
            tick();
            spare = frameTime - pulseRate;
        }

    }
}

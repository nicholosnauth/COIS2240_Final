package core;

/** A placeholder spawner class. At the moment, does basically nothing */
public class Spawner {
    private ObjectHandler handler;

    public Spawner(ObjectHandler handler){
        this.handler = handler;
    }

    public void spawnPlayer(int x, int y){
        handler.addObject(new Player(x, y, handler));
    }

    public void tick(){

    }



}

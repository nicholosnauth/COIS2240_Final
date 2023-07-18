package levels;

import Objects.BasicEnemy;
import Objects.SingleFireEnemy;
import core.GameObject;
import core.ID;
import core.ObjectHandler;
import core.Spawner;
import javafx.scene.image.Image;

public class MobSpawner extends GameObject {

    private Spawner spawner;

    Image spawnerImage = new Image("resources/openSpawn.png");
    private final int offset;

    public MobSpawner(int posX, int posY, ObjectHandler handler, Spawner spawner) {
        super(posX, posY, handler);
        this.setImage(spawnerImage);
        width = (int) spawnerImage.getWidth();
        height = (int) spawnerImage.getHeight();
        this.setId(ID.MobSpawner);
        this.offset = (int)(Math.random()*200);
        this.spawner = spawner;
    }

    @Override
    public void tick() {
        spawning();
    }

    @Override
    public void collisionCode(ID id, GameObject object) {
        if(id == ID.MobSpawner) handler.removeObject(this);
        else if (id == ID.Barrier) handler.removeObject(this);
    }


    private void spawning(){
        int spawnRate = 200;

        if(Loader.getLevel() > 18){
            if(Loader.getLevel() % 5 != 0){
                spawnRate -= ( Loader.getLevel()*5 - 18);
            }
            if (spawnRate <= 100) spawnRate = 100;
        }

        if((Loader.getTimer() + offset) % spawnRate == 0 && Loader.enemyCount < 100){
            if(Math.random() > 0.2)
                spawner.spawnBasicEnemy((int)(this.position.getX() + this.width/2),
                        (int)(this.position.getY() + this.height/2));
            else
                spawner.spawnSingleEnemy((int)(this.position.getX() + this.width/2),
                        (int)(this.position.getY() + this.height/2));
        }

    }
}

package levels;

import core.GameObject;
import core.ID;
import core.ObjectHandler;

public class Walls extends GameObject {


    public Walls(int posX, int posY, int height, int width,  ObjectHandler handler) {
        super(posX, posY, height, width, handler);
        this.setId(ID.Barrier);
    }
    @Override
    public void tick() { }

    @Override
    public void collisionCode(ID id, GameObject object) {
        if(id == ID.Player || id == ID.BasicEnemy || id == ID.SingleFireEnemy || id == ID.BossEnemy){
            barrier(object);
        }else if(id == ID.Bullet || id == ID.EnemyBullet){
            handler.removeObject(object);
        }
    }



}
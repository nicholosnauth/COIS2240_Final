package levels;

import Menus.Launcher;
import core.*;

public class Loader {

    public static int enemyCount;
    private static int timer;
    private static int level;
    private static boolean grace;
    private static int levelTime;


    private static final int graceTime = 150;
    private static final int baseLevelTime = 1000;

    private final ObjectHandler handler;
    private final Spawner spawner;
    public static final int levelDimX = 1920, levelDimY = 1920;

    public Loader(ObjectHandler handler, Spawner spawner) {
        this.handler = handler;
        this.spawner = spawner;
    }

    public void tick(){
        levelTimer();
    }

    private void levelTimer(){
        if(timer > 0){
            timer --;
        }else if(!grace){
            handler.removeObjectType(ID.MobSpawner);
            if(enemyCount == 0){
                timer = graceTime;
                grace = true;
            }
        }else{
            grace = false;
            loadNext();
        }
    }


    public void loadFirst(){
        Launcher.scoreKeep = 0;
        level = 1;
        enemyCount = 0;
        levelTime = baseLevelTime;
        grace = false;

        spawnLevelWalls();
        randomSpawner(3);
        spawner.spawnPlayer(960, 960);

        //spawner.spawnBossEnemy(200, 400);

        timer = levelTime;
    }


    private void loadNext(){
        level++;
        System.out.println("Level " + level);
        GameObject player = handler.findPlayer();
        double x = player.getPosition().getX(); double y = player.getPosition().getY();
        handler.clearObjects();
        enemyCount = 0;
        int spawnBoss = 0;

        levelTime = baseLevelTime + level*50;
        if(levelTime > 2* baseLevelTime) levelTime = 2* baseLevelTime;

        spawnLevelWalls();

        if(level % 5 == 0){
            spawnBoss = 1;
            if(level >= 25){
                spawnBoss = 3;
            }else if (level >= 15) {
                spawnBoss = 2;
            }

            levelTime = 100;
            randomSpawner(level/3 - 2);
        }else{
            randomSpawner((3 + level/2));

            if(level > 10){
                if(Math.random()*50.0 < level - 5)
                    spawnBoss = 1;
            }
        }

        spawner.spawnPlayer((int)x, (int)y);

        if (spawnBoss > 3) spawnBoss = 3;
        while(spawnBoss > 0){
            spawner.spawnBossEnemy(700 + spawnBoss*150, 50);
            spawnBoss--;
        }

        timer = levelTime;
    }


    public void spawnLevelWalls(){
        handler.addObject(new Walls(-25,0, 1920,30, handler));
        handler.addObject(new Walls(0,-25, 30,1920,handler));
        handler.addObject(new Walls(-25,1920, 30,1920, handler));
        handler.addObject(new Walls(1920,-25, 1920,30, handler));
    }

    public void randomSpawner(int i){
        int numberToSpawn;

        if (i < 12)
            numberToSpawn = i;
        else
            numberToSpawn = 12;

        while(numberToSpawn > 0){
            GameObject object = new MobSpawner((int) (Math.random() * levelDimX), (int) (Math.random() * levelDimY), handler, spawner);
            handler.addObject(object);
            handler.collision(object);
            if(handler.objectHandled(object)) numberToSpawn--;
        }
    }

    public static int getTimer() {
        return timer;
    }

    public static int getLevel() {
        return level;
    }

    public static boolean isGrace() {
        return grace;
    }

    public static int getLevelTime() {
        return levelTime;
    }

    public static int getGraceTime() {
        return graceTime;
    }
}

package core;


import Menus.Launcher;

public final class PlayerStats {

    private static PlayerStats stats  = new PlayerStats();

    private static int score;

    private double speedMod;
    private final double speedCap = 1.5;

    private int health;
    private int maxHealth;
    private final int healthCap = 10;

    private int damage;
    private final int damageCap = 100;

    private int fireRate;
    private final int fireRateCap = 10;

    private int bulletVel;

    private int immuneTime;


    private PlayerStats(){
        this.speedMod = 1;

        this.health = 3;
        this.maxHealth = 3;

        this.damage = 1;
        this.fireRate = 60;
        this.bulletVel = 25;

        this.immuneTime = 30;

        score = 0;
    }

    public static PlayerStats getInstance(){
        return stats;
    }

    public static void reset(){
        stats = new PlayerStats();
    }

    public double getSpeedMod() {
        return speedMod;
    }

    public void setSpeedMod(double speedMod) {
        this.speedMod = speedMod;
        if(speedMod >= speedCap){
            this.speedMod = speedCap;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

        if(health >= maxHealth){
            this.health = maxHealth;
        }

        if(health <= 0){
            Launcher end = new Launcher();
            try {
                end.gameEnd();//when player dies go back to main menu
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {

        if(maxHealth < this.healthCap) {
            this.maxHealth = maxHealth;
            health++;
        }
        else
            this.maxHealth = healthCap;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if(damage < damageCap)
            this.damage = damage;
        else
            this.damage = damageCap;

    }



    public int getFireRate() {
        return fireRate;
    }

    public void improveFireRate() {
        if (fireRate <= fireRateCap){
            fireRate = fireRateCap;
        }else if(fireRate <= 15){
            fireRate = fireRateCap;
        }else if(fireRate <= 20) {
            fireRate =15;
        }else if(fireRate <= 30) {
            fireRate =20;
        }else if(fireRate <= 42) {
            fireRate =30;
        }else if(fireRate <= 60) {
            fireRate =42;
        }
    }

    public int getBulletVel() {
        return bulletVel;
    }

    public void setBulletVel(int bulletVel) {
        this.bulletVel = bulletVel;
    }

    public int getImmuneTime() {
        return immuneTime;
    }

    public void setImmuneTime(int immuneTime) {
        this.immuneTime = immuneTime;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        PlayerStats.score = score;
    }

    public double getSpeedCap() {
        return speedCap;
    }

    public int getHealthCap() {
        return healthCap;
    }

    public int getDamageCap() {
        return damageCap;
    }

    public int getFireRateCap() {
        return fireRateCap;
    }
}

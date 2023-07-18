package core;

/** All game objects need an ID, primarily for collision */
public enum ID {

    // --- PLAYER --- //
    Player(),

    // --- ENEMIES --- //
    BasicEnemy(),
    SingleFireEnemy(),
    BossEnemy(),

    // --- BULLET --- //
    Bullet(),
    EnemyBullet(),

    // --- OTHER --- //
    MobSpawner(),
    Barrier(),

}

package towerDefense.enemy;

import java.util.Random;

/**
 * This class is responsible for spawning enemies.
 * Not balanced at all.
 */
public class EnemySpawner {
    private final Random random;

    /**
     * Constructor for the enemy spawner.
     * Initializes the random generator.
     */
    public EnemySpawner() {
        this.random = new Random();
    }

    /**
     * Picks an enemy type.
     * @return EnemyTypes
     */
    public EnemyTypes pickEnemyType() {
        int basicChance = 10;
        int armoredChance = 2;
        int bossChance = 1;
        int randomNumber = this.random.nextInt(basicChance + armoredChance + bossChance);
        if (randomNumber < basicChance) {
            return EnemyTypes.BASIC;
        } else if (randomNumber < basicChance + armoredChance) {
            return EnemyTypes.ARMORED;
        } else {
            return EnemyTypes.BOSS;
        }
    }
}
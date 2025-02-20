package towerDefense.enemy;

import towerDefense.game.Type;

/**
 * Types of enemies.
 * Each type has its own image, health, damage and reward.
 * Number of images would be used for animation.
 */
public enum EnemyTypes implements Type {
    // ID, Name, image, number of images, health, damage, reward
    BASIC( "Basic", "enemies/basic/", 1, 100, 5, 100),
    ARMORED( "Armored", "enemies/armored/", 1, 400, 15, 100),
    BOSS( "Boss", "enemies/boss/", 1, 500, 50, 1000);

    private final String name;
    private final String imagePath;
    private final int numberOfImages;
    private final int health;
    private final int damage;
    private final int reward;

    EnemyTypes(String name, String imagePath, int numberOfImages, int health, int damage, int reward) {
        this.name = name;
        this.imagePath = imagePath;
        this.numberOfImages = numberOfImages;
        this.health = health;
        this.damage = damage;
        this.reward = reward;
    }

    /**
     * Returns the name of an enemy
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the path to the image of an enemy
     * @return String
     */
    @Override
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Returns the number of images of an enemy
     * @return int
     */
    public int getnumberOfImages() {
        return this.numberOfImages;
    }

    /**
     * Returns the health of an enemy
     * @return int
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Returns the damage of an enemy
     * @return int
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Returns the reward for killing an enemy
     * @return int
     */
    public int getReward() {
        return this.reward;
    }
}

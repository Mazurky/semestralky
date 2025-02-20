package towerDefense.game;

import fri.shapesge.Rectangle;

/**
 * HpBar is a class that represents a health bar for enemies.
 */
public class HpBar {
    private final Rectangle outside;
    private final Rectangle inside;
    private final int maxHealth;
    private int currentHealth;
    private int barSize;

    /**
     * Constructor for HpBar.
     * @param posX X coordinate of the health bar.
     * @param posY Y coordinate of the health bar.
     * @param health Health of the enemy.
     */
    public HpBar(int posX, int posY, int health) {
        this.maxHealth = health;
        this.currentHealth = health;
        this.outside = new Rectangle(posX, posY);
        this.outside.changeSize(50, 10);
        this.outside.changeColor("black");
        this.outside.makeVisible();
        this.inside = new Rectangle(posX + 1, posY + 1);
        this.barSize = 48;
        this.inside.changeSize(this.barSize, 8);
        this.inside.changeColor("green");
        this.outside.makeVisible();
        this.inside.makeVisible();
    }

    /**
     * Moves the health bar.
     * @param x int - X coordinate / not posiotion, just adds/substracts pixels.
     * @param y int - Y coordinate.
     */
    public void move(int x, int y) {
        this.outside.moveHorizontal(x);
        this.inside.moveHorizontal(x);
        this.outside.moveVertical(y);
        this.inside.moveVertical(y);
    }

    /**
     * Makes hpbar resize to the current health.
     * @param hpToReduce double - Health to reduce.
     */
    public void decreaseHp(double hpToReduce) {
        this.currentHealth -= hpToReduce; // 100 - 50 = 50
        double percentage = (double)this.currentHealth / this.maxHealth; // 50 / 100 = 0.5
        double barPercentage = percentage * 48; // 0.5 * 48 = 24
        this.barSize = (int)barPercentage; // 48 - 24 = 24
        this.inside.changeSize(this.barSize, 8);
    }

    /**
     * Makes the health bar invisible.
     */
    public void hide() {
        this.outside.makeInvisible();
        this.inside.makeInvisible();
    }
}
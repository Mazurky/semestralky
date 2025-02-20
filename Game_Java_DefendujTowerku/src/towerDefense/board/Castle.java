package towerDefense.board;

import towerDefense.enemy.Enemy;
import towerDefense.panel.Stats;

import java.util.Collection;

/**
 * Represents the castle on the board.
 */
public class Castle extends Tile {
    private Stats stats;
    private int health;
    private int coins;

    /**
     * Constructor.
     * @param posX - the x coordinate of the castle
     * @param posY - the y coordinate of the castle
     */
    public Castle(int posX, int posY) {
        super(posX, posY, 99);
        this.health = 500;
        this.coins = 1000;
    }

    /**
     * Checks if the castle is destroyed.
     * @return boolean
     */
    public boolean isDestroyed() {
        return this.health <= 0;
    }

    /**
     * Returns the castle's coins.
     * @return int
     */
    public int getCoins() {
        return this.coins;
    }

    /**
     * Returns the castle's health.
     * @return int
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Decreases the castle's health.
     * @param damage - the damage taken
     */
    public void decreaseHealth(int damage) {
        this.health -= damage;
        this.stats.setHealth(this.health);
    }

    /**
     * Decreases the castle's coins.
     * @param coins - the coins spent
     */
    public void decreaseCoins(int coins) {
        this.coins -= coins;
        this.stats.setCoins(this.coins);
    }

    /**
     * Increases the castle's health.
     * @param health - the health healen
     */
    public void increaseHealh(int health) {
        this.health += health;
        this.stats.setHealth(this.health);
    }

    /**
     * Increases the castle's coins.
     * @param coins - the coins earned
     */
    public void increaseCoins(int coins) {
        this.coins += coins;
        this.stats.setCoins(this.coins);
    }

    /**
     * Checks if enemies are on the castle's tile.
     * If they are, it decreases the castle's health.
     * @param enemies - the enemies on the board
     */
    public void effect(Collection<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (enemy.getColOnBoard() == this.getTileColumn() && enemy.getRowOnBoard() == this.getTileRow()) {
                this.decreaseHealth(enemy.getEnemyType().getDamage());
                enemy.setKilledByCastle();
                enemy.hide();
                this.stats.setHealth(this.health);
            }
        }
    }

    /**
     * Sets the stats panel.
     * Castle controls info on a stats panel.
     * @param stats - the stats panel
     */
    public void setStats(Stats stats) {
        this.stats = stats;
    }
}

package towerDefense.towers.types;

import towerDefense.enemy.Enemy;
import towerDefense.towers.IEffectTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

import java.util.Collection;
/**
 * Poison tower class
 * Type of tower.
 */
public class PoisonTower extends Tower implements Info, IEffectTower {

    private static final int EFFECT_DAMAGE = 10;
    private static final int EFFECT_RATE = 6;
    private static final int RANGE = 1;

    private static final TowerTypes ATRIBUTES = TowerTypes.POISONTOWER;

    /**
     * Constructor for the poison tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public PoisonTower(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Poison tower";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Poisons enemies in range";
    }

    /**
     * Returns the attributes of the tower
     * @return String
     */
    @Override
    public String getAttributes() {
        return String.format("Poison damage: %d per %ds, range: %d, cost: %d.", EFFECT_DAMAGE, EFFECT_RATE, RANGE, this.getCost());
    }

    /**
     * Method for apllying the effect of the tower
     * @param enemies Collection of enemies
     * @param time time of the game
     */
    @Override
    public void effect(Collection<Enemy> enemies, int time) {
        if (time % (EFFECT_RATE * 10) == 0) {
            for (Enemy enemy : enemies) {
                if (!enemy.isPoisoned()) {
                    if (Math.abs(enemy.getColOnBoard() - this.getCol()) <= RANGE && Math.abs(enemy.getRowOnBoard() - this.getRow()) <= RANGE) {
                        enemy.setPoisoned();
                    }
                }
            }
            for (Enemy enemy : enemies) {
                if (enemy.isPoisoned()) {
                    enemy.decreseHp(EFFECT_DAMAGE);
                }
            }
        }
    }
}

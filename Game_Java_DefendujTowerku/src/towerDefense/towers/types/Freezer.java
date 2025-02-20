package towerDefense.towers.types;

import towerDefense.enemy.Enemy;
import towerDefense.towers.IEffectTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

import java.util.Collection;
/**
 * Freezer tower class
 * Type of tower.
 */
public class Freezer extends Tower implements Info, IEffectTower {
    private static final TowerTypes ATRIBUTES = TowerTypes.FREEZER;
    private static final int FREEZE_RATE = 6;
    private static final int RANGE = 1;

    /**
     * Constructor for the freezer tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public Freezer(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Freezing tower";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Freezes enemies in range";
    }

    /**
     * Returns the attributes of the tower
     * @return String
     */
    @Override
    public String getAttributes() {
        return String.format("Freeze rate: %ds, range: %d, cost: %d.", FREEZE_RATE, RANGE, this.getCost());
    }

    /**
     * Method for apllying the effect of the tower
     * @param enemies Collection of enemies
     * @param time time of the game
     */
    @Override
    public void effect(Collection<Enemy> enemies, int time) {
        if (time % (FREEZE_RATE * 10) == 0) {
            for (Enemy enemy : enemies) {
                if (!enemy.isFreezed()) {
                    if (Math.abs(enemy.getColOnBoard() - this.getCol()) <= RANGE && Math.abs(enemy.getRowOnBoard() - this.getRow()) <= RANGE) {
                        enemy.setFreezed();
                    }
                }
            }
        }
    }
}

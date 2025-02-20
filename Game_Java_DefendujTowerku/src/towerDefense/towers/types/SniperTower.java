package towerDefense.towers.types;

import towerDefense.enemy.Enemy;
import towerDefense.towers.IAttackTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

import java.util.Collection;

/**
 * Sniper tower class
 * Type of tower.
 */
public class SniperTower extends Tower implements Info, IAttackTower {
    private static final int DAMAGE = 20;
    private static final int FIRERATE = 6;
    private static final int RANGE = 20;
    private static final TowerTypes ATRIBUTES = TowerTypes.SNIPERTOWER;

    /**
     * Constructor for the sniper tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public SniperTower(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Sniper Tower";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Long range slow shooting tower";
    }

    /**
     * Returns the attributes of the tower
     * @return String
     */
    @Override
    public String getAttributes() {
        return String.format("Damage: %d, fire rate: %ds, range: %d, cost: %d.", DAMAGE, FIRERATE, RANGE, this.getCost());
    }

    /**
     * Method for attacking the enemies
     * @param enemies Collection of enemies
     * @param time time of the game
     */
    @Override
    public void attack(Collection<Enemy> enemies, int time) {
        if (time % (FIRERATE * 10) == 0) {
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getColOnBoard() - this.getCol()) <= RANGE && Math.abs(enemy.getRowOnBoard() - this.getRow()) <= RANGE) {
                    int angle = super.calculateAngle(enemy.getRowOnBoard(), enemy.getColOnBoard(), this.getRow(), this.getCol());
                    if (angle > 0 && angle < 180) {
                        angle *= -1;
                    } else if (angle < 0) {
                        angle = Math.abs(angle);
                    }
                    this.getImage().changeAngle(angle + 180);
                    enemy.decreseHp(DAMAGE);
                    return;
                }
            }
        }
    }
}

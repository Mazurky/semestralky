package towerDefense.towers.types;

import towerDefense.enemy.Enemy;
import towerDefense.towers.IAttackTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Canon tower class
 * Type of tower.
 */
public class CanonTower extends Tower implements Info, IAttackTower {

    private static final int DAMAGE = 40;
    private static final int FIRERATE = 3;
    private static final int RANGE = 1;
    private static final TowerTypes ATRIBUTES = TowerTypes.CANONTOWER;

    /**
     * Constructor for the canon tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public CanonTower(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Canon tower";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Makes splash damage to enemies.";
    }

    /**
     * Returns the attributes of the tower
     * @return String
     */
    @Override
    public String getAttributes() {
        return String.format("Damage: %d, firerate: %ds, range: %d, cost: %d.", DAMAGE, FIRERATE, RANGE, this.getCost());
    }

    /**
     * Method for attacking the enemies
     * @param enemies Collection of enemies
     * @param time Time of the game
     */
    @Override
    public void attack(Collection<Enemy> enemies, int time) {
        if (time % (FIRERATE * 10) == 0) {
            ArrayList<Enemy> enemiesInRange = new ArrayList<>();
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getColOnBoard() - this.getCol()) <= RANGE && Math.abs(enemy.getRowOnBoard() - this.getRow()) <= RANGE) {
                    enemiesInRange.add(enemy);
                }
            }
            for (Enemy enemy : enemiesInRange) {
                int angle = super.calculateAngle(enemy.getRowOnBoard(), enemy.getColOnBoard(), this.getRow(), this.getCol());
                this.getImage().changeAngle(angle + 180);
                enemy.decreseHp(DAMAGE);
            }
        }
    }
}

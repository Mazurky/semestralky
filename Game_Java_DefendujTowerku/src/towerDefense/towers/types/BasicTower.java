package towerDefense.towers.types;

import towerDefense.enemy.Enemy;
import towerDefense.towers.IAttackTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

import java.util.Collection;

/**
 * Basic tower class
 * Type of tower.
 */
public class BasicTower extends Tower implements Info, IAttackTower {
    private static final int DAMAGE = 5;
    private static final int FIRERATE = 1;
    private static final int RANGE = 2;
    private static final TowerTypes ATRIBUTES = TowerTypes.BASICTOWER;

    /**
     * Constructor for the basic tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public BasicTower(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Basic Tower";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Cheap, weak and short range tower";
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
     * @param time time of the game to calculate the fire rate
     */
    @Override
    public void attack(Collection<Enemy> enemies, int time) {
        if (time % (FIRERATE * 10) == 0) {
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getColOnBoard() - this.getCol()) <= RANGE && Math.abs(enemy.getRowOnBoard() - this.getRow()) <= RANGE) {
                    int angle = super.calculateAngle(enemy.getRowOnBoard(), enemy.getColOnBoard(), this.getRow(), this.getCol());
                    this.getImage().changeAngle(angle + 180);
                    enemy.decreseHp(DAMAGE);
                    return;
                }
            }
        }
    }
}

package towerDefense.towers.types;

import towerDefense.board.Castle;
import towerDefense.towers.IPassiveTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

/**
 * Healer tower class
 * Type of tower.
 */
public class HealerTower extends Tower implements Info, IPassiveTower {
    private static final TowerTypes ATRIBUTES = TowerTypes.HEALERTOWER;
    private static final int HEAL_RATE = 4;
    private static final int HEAL_AMOUNT = 1;
    /**
     * Constructor for the healer tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public HealerTower(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Healer tower";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Heals castle.";
    }

    /**
     * Returns the attributes of the tower
     * @return String
     */
    @Override
    public String getAttributes() {
        return String.format("Heal amount: %d, heal rate: %ds, cost: %d.", HEAL_AMOUNT, HEAL_RATE, this.getCost());
    }

    /**
     * Method for passive action
     * @param time time
     * @param castle castle
     */
    @Override
    public void passiveAction(int time, Castle castle) {
        if (time % (HEAL_RATE * 10) == 0) {
            castle.increaseHealh(HEAL_AMOUNT);
        }
    }
}

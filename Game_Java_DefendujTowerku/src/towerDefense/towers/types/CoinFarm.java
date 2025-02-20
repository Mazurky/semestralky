package towerDefense.towers.types;

import towerDefense.board.Castle;
import towerDefense.towers.IPassiveTower;
import towerDefense.towers.Info;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;

/**
 * Coin farm class
 * Type of tower.
 */
public class CoinFarm extends Tower implements Info, IPassiveTower {
    private static final TowerTypes ATRIBUTES = TowerTypes.COINFARM;
    private static final int COIN_RATE = 5;
    private static final int COIN_AMOUNT = 2;

    /**
     * Constructor for the coin farm
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     */
    public CoinFarm(int posX, int posY) {
        super(posX, posY, ATRIBUTES.getCost(), ATRIBUTES.getImagePath());
    }

    /**
     * Returns the name of the tower
     * @return String
     */
    @Override
    public String getName() {
        return "Coin farm";
    }

    /**
     * Returns the description of the tower
     * @return String
     */
    @Override
    public String getDescription() {
        return "Generate coins.";
    }

    /**
     * Returns the attributes of the tower
     * @return String
     */
    @Override
    public String getAttributes() {
        return String.format("Coin amount: %d, coin rate: %d, cost: %d.", COIN_AMOUNT, COIN_RATE, this.getCost());
    }

    /**
     * Method for passive action
     * @param time time
     * @param castle castle
     */
    @Override
    public void passiveAction(int time, Castle castle) {
        if (time % (COIN_RATE * 10) == 0) {
            castle.increaseCoins(COIN_AMOUNT);
        }
    }
}

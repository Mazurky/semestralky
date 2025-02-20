package towerDefense.panel;
import towerDefense.board.Tile;
import towerDefense.towers.Tower;
import towerDefense.towers.TowerTypes;
import towerDefense.towers.types.*;

/**
 * TowerPicker class is used to create a panel with towers that can be placed on the board.
 */
public class TowerPicker {
    private final Tile[][] towersTiles;
    private final int startOfPanel;

    /**
     * Constructor for TowerPicker class.
     * @param startOfPanel int position of the panel on x-axis
     * @param y int
     */
    public TowerPicker(int startOfPanel, int y) {
        this.startOfPanel = startOfPanel;
        this.towersTiles = new Tile[12][2];

        TowerTypes[] towerTypes = TowerTypes.values();
        int row = 0;
        int col = 0;

        for (int i = 0; i < towerTypes.length && row < this.towersTiles.length; i++) {
            TowerTypes towerType = towerTypes[i];
            this.towersTiles[row][col] = new Tile(this.startOfPanel + col * 50, y + row * 50, towerType.getId());

            col++;
            if (col >= this.towersTiles[row].length) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Shows the panel with towers.
     */
    public void showTowerPicker() {
        for (Tile[] tower : this.towersTiles) {
            for (Tile tile : tower) {
                if (tile != null) {
                    tile.getImage().makeVisible();
                }
            }
        }
    }

    /**
     * Hides the panel with towers.
     */
    public void hideTowerPicker() {
        for (Tile[] tower : this.towersTiles) {
            for (Tile tile : tower) {
                if (tile != null) {
                    tile.getImage().makeInvisible();
                }
            }
        }
    }

    /**
     * Returns the id of the tower that was clicked on.
     * @param x int position of the mouse on x-axis
     * @param y int position of the mouse on y-axis
     * @return int id of the tower
     */
    public int pickTower(int x, int y) {
        int column = (x - this.startOfPanel) / 50;
        int row = (y - 150) / 50;
        if (this.towersTiles[row][column] != null) {
            return this.towersTiles[row][column].getTileID();
        }
        return 0;
    }

    /**
     * Creates a tower based on the id.
     * @param towerId int id of the tower
     * @param posX int position of the tower on x-axis
     * @param posY int position of the tower on y-axis
     * @return Tower
     */
    public Tower createTower(int towerId, int posX, int posY) {
        switch (towerId) {
            case 1000 -> {
                return new BasicTower(posX, posY);
            }
            case 1001 -> {
                return new SniperTower(posX, posY);
            }
            case 1002 -> {
                return new HealerTower(posX, posY);
            }
            case 1003 -> {
                return new CoinFarm(posX, posY);
            }
            case 1004 -> {
                return new PoisonTower(posX, posY);
            }
            case 1005 -> {
                return new Freezer(posX, posY);
            }
            case 1006 -> {
                return new CanonTower(posX, posY);
            }
        }
        return new BasicTower(posX, posY);
    }
}

package towerDefense.panel;

/**
 * The panel on the right side of the game.
 */
public class Panel {
    private final Stats stats;
    private final TowerPicker towers;

    /**
     * Creates a new panel.
     */
    public Panel() {
        int startOfPanel = 20 * 50;
        this.stats = new Stats(startOfPanel + 25, 0);
        this.towers = new TowerPicker(startOfPanel + 30, 150);
    }

    /**
     * Stats - part of the panel where user see castle health and coins.
     * @return Stats
     */
    public Stats getStats() {
        return this.stats;
    }

    /**
     * TowerPicker - part of the panel where user picks towers.
     * @return TowerPicker
     */
    public TowerPicker getTowerPicker() {
        return this.towers;
    }

    /**
     * Checks if the user clicked on the tower from panel
     * @param x x-coordinate of the click
     * @param y y-coordinate of the click
     * @return true if the user clicked on the tower picker
     */
    public boolean isTowerPicker(int x, int y) {
        return x >= 20 * 50 + 30 && x < 20 * 50 + 130 && y >= 150 && y < 600;
    }

    /**
     * Returns the id of the tower that the user clicked on.
     * @param x x-coordinate of the click
     * @param y y-coordinate of the click
     * @return int - id of the tower
     */
    public int pickTower(int x, int y) {
        return this.towers.pickTower(x, y);
    }
}

package towerDefense.panel;

import fri.shapesge.FontStyle;
import fri.shapesge.Text;
import towerDefense.board.Tile;

/**
 * Part of a panel on the right side of the game.
 * Castles health and coins.
 */
public class Stats {
    private final Tile imageHealth;
    private final Tile imageCoins;
    private final Text textCoins;
    private final Text textHealth;

    /**
     * Creates a new stats.
     * @param x x-coordinate of the stats
     * @param y y-coordinate of the stats
     */
    public Stats(int x, int y) {
        this.imageHealth = new Tile(x + 10, y, 50);
        this.imageCoins = new Tile(x + 10, y + 50, 51);
        this.textHealth = new Text("0", x + 50 + 14, y + 34);
        this.textCoins = new Text("0", x + 50 + 14, y + 84);
        this.textHealth.changeFont("Serif", FontStyle.BOLD, 26);
        this.textCoins.changeFont("Serif", FontStyle.BOLD, 26);
    }

    /**
     * Sets the health of the castle.
     * @param health int - health of the castle
     */
    public void setHealth(int health) {
        this.textHealth.changeText(String.valueOf(health));
    }

    /**
     * Sets the coins of the castle.
     * @param coins int - coins of the castle
     */
    public void setCoins(int coins) {
        this.textCoins.changeText(String.valueOf(coins));
    }

    /**
     * Shows the stats.
     */
    public void showStats() {
        this.textCoins.makeVisible();
        this.textHealth.makeVisible();
        this.imageCoins.getImage().makeVisible();
        this.imageHealth.getImage().makeVisible();
    }

    /**
     * Hides the stats.
     */
    public void hideStats() {
        this.textCoins.makeInvisible();
        this.textHealth.makeInvisible();
        this.imageCoins.getImage().makeInvisible();
        this.imageHealth.getImage().makeInvisible();
    }
}

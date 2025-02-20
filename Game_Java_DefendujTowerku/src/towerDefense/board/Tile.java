package towerDefense.board;

import fri.shapesge.Image;
import towerDefense.game.Type;
import towerDefense.towers.TowerTypes;

/**
 * Class representing a single tile on the board.
 */
public class Tile {
    private final int tileID;
    private final Image image;
    private final int x;
    private final int y;
    private final Image highligter;
    private boolean highlighted;

    /**
     * Constructor for the Tile class.
     * @param x - x coordinate of the tile
     * @param y - y coordinate of the tile
     * @param tileID - ID of the tile
     */
    public Tile(int x, int y, int tileID) {
        this.tileID = tileID;
        this.x = x;
        this.y = y;
        Type tileType;
        if (tileID >= 1000) {
            tileType = TowerTypes.getById(tileID);
        } else {
            tileType = TileType.getById(tileID);
        }
        this.image = new Image(tileType.getImagePath() , x, y);
        this.highligter = new Image("tiles/highlight.png", x, y);
        this.highlighted = false;
    }

    int getTileColumn() {
        return this.x / 50;
    }
    int getTileRow() {
        return this.y / 50;
    }

    /**
     * Getter for the tile ID.
     * @return int - tile ID
     */
    public int getTileID() {
        return this.tileID;
    }

    /**
     * Getter for the tile image.
     * @return Image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Makes the tile "image" visible.
     */
    public void makeVisible() {
        this.image.makeVisible();
    }

    /**
     * Makes the tile un/highlighted.
     * @param highlighted - true if the tile is highlighted, false otherwise
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
        if (this.highlighted) {
            this.highligter.makeVisible();
        } else {
            this.highligter.makeInvisible();
        }
    }

    /**
     * Check if the tile is highlighted.
     * @return boolean - true if the tile is highlighted, false otherwise
     */
    public boolean isHighlighted() {
        return this.highlighted;
    }
}

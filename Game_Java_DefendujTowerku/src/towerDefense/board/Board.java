package towerDefense.board;

import towerDefense.maps.MapGenerator;
import towerDefense.maps.MapReader;

import java.util.Random;

/**
 * Board class is responsible for creating the map and the tiles on it.
 * It also holds the castle coordinates.
 *
 */
public class Board {
    private Tile[][] tiles;
    private final int[][] map;
    private final int tileSize = 50;
    private int castleRow;
    private int castleCol;
    private boolean isHighlighted = false;

    /**
     * Constructor for the board class.
     * @param map - the number of a map of the game
     */
    public Board(int map) {
        this.map = MapReader.readMatrixFromFile("src/towerDefense/maps/map" + map + ".txt");
        this.initialize(this.map);
    }

    /**
     * Loads the map from the MapGenerator class.
     * Doeasn't work curently.
     * @param generatedMap - the generated map of the game
     */
    public Board(MapGenerator generatedMap) {
        this.map = generatedMap.getMap();
        this.initialize(this.map);
    }

    /**
     * Initializes the tiles on the board.
     * If the tile is a grass tile, it has a 6% chance to spawn a stone.
     * If the tile is a castle tile, it saves the coordinates of the castle.
     * @param map - ids from file for the tiles
     */
    private void initialize(int[][] map) {
        int id;
        int[] grassTypes = {1, 2, 3};
        Random rand = new Random();
        this.tiles = new Tile[15][20];
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                id = map[i][j];
                if (map[i][j] == 1) {
                    id = grassTypes[rand.nextInt(3)];
                    // 6% chance to spawn a stone
                    if (rand.nextInt(101) <= 6) {
                        id = 0;
                    }
                }
                if (id == 99) {
                    this.tiles[i][j] = new Castle(j * this.tileSize, i * this.tileSize);
                    this.castleRow = i;
                    this.castleCol = j;
                } else {
                    this.tiles[i][j] = new Tile(j * this.tileSize, i * this.tileSize, id);
                }
            }
        }
    }

    /**
     * Returns the first tile of the path.
     * @return int - position of the first tile of the path
     */
    public int getFirstTileLocation() {
        for (int i = 0; i < this.tiles.length; i++) {
            if (this.tiles[i][0].getTileID() == 10) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Changes the tile at the given coordinates.
     * @param row int - row of the tile
     * @param col int - column of the tile
     * @param tile Tile - the new tile
     */
    public void changeTile(int row, int col, Tile tile) {
        this.tiles[col][row] = tile;
        this.redrawTiles();
    }

    /**
     * Returns the tile at the given coordinates.
     * @param row int - row of the tile
     * @param col int - column of the tile
     * @return Tile - requested tile
     */
    public Tile getTile(int row, int col) {
        return this.tiles[row][col];
    }

    /**
     * Returns the castle tile.
     * @return Castle
     */
    public Castle getCastle() {
        return (Castle)this.tiles[this.castleRow][this.castleCol];
    }

    /**
     * Redraws all the tiles on the board.
     */
    public void redrawTiles() {
        for (Tile[] tile : this.tiles) {
            for (Tile t : tile) {
                t.getImage().makeVisible();
            }
        }
    }

    /**
     * Hides all the tiles on the board.
     */
    public void hideTiles() {
        for (Tile[] tile : this.tiles) {
            for (Tile t : tile) {
                t.getImage().makeInvisible();
            }
        }
    }

    /**
     * Returns the size of the one tile.
     * @return int - size of the tile in pixels
     */
    public int getTileSize() {
        return this.tileSize;
    }

    /**
     * Highlights all the placable tiles.
     * Placable tiles are the tiles where the player can place a tower.
     * Placable tiles: grass any type
     */
    public void highlightPlacableTile() {
        for (Tile[] tile : this.tiles) {
            for (Tile t : tile) {
                int id = t.getTileID();
                if (id == 1 || id == 2 || id == 3) {
                    t.setHighlighted(true);
                }
            }
        }
        this.isHighlighted = true;
    }

    /**
     * Unhighlights all the tiles.
     */
    public void unHighlightAll() {
        for (Tile[] tile : this.tiles) {
            for (Tile t : tile) {
                t.setHighlighted(false);
            }
        }
        this.isHighlighted = false;
    }

    /**
     * Returns if the tiles are highlighted or not.
     * @return boolean - true if the tiles are highlighted, false otherwise
     */
    public boolean isHighlighted() {
        return this.isHighlighted;
    }
}

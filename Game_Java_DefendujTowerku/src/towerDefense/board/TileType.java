package towerDefense.board;

import towerDefense.game.Type;

/**
 * TileType enum
 * each type represents a tile
 * each tile has an id and an image path
 */
public enum TileType implements Type {

    // BackGround tiles
    STONE(0, "tiles/stone.png"),
    GRASS(1, "tiles/grass.png"),
    GRASS1(2, "tiles/grass1.png"),
    GRASS2(3, "tiles/grass2.png"),
    // Tower base
    BASE(9, "tiles/base.png"),
    // Roads / paths
    ROADH(  10, "tiles/roadH.png"),
    ROADV(  11, "tiles/roadV.png"),
    ROADLD( 12, "tiles/roadLD.png"),
    ROADLU( 13, "tiles/roadLU.png"),
    ROADRD( 14, "tiles/roadRD.png"),
    ROADRU( 15, "tiles/roadRU.png"),
    // Panel
    HEART(50, "panel/heart.png"),
    COIN(51, "panel/coin.png"),
    CASTLE(99, "tiles/castle.png");

    private final int id;
    private final String imagePath;
    TileType(int id, String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    /**
     * Get the tile type by id
     * @param id int - id to check
     * @return TileType
     */
    public static TileType getById(int id) {
        for (TileType e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return GRASS;
    }

    /**
     * Gets the path of the image
     * @return String
     */
    @Override
    public String getImagePath() {
        return this.imagePath;
    }
}

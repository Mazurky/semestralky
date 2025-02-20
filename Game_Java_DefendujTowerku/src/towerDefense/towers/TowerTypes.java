package towerDefense.towers;

import towerDefense.game.Type;
/**
 * Enum for the tower types
 * Each tower type has an id, image and cost
 */
public enum TowerTypes implements Type {
    BASICTOWER(1000, "towers/basic.png", 50),
    SNIPERTOWER(1001, "towers/sniper.png", 250),
    HEALERTOWER(1002, "towers/healer.png", 200),
    COINFARM(1003, "towers/coinfarm.png", 200),
    POISONTOWER(1004, "towers/poisontower.png", 10),
    FREEZER(1005, "towers/freezer.png", 200),
    CANONTOWER(1006, "towers/canontower.png", 350);

    private final int id;
    private final String image;
    private final int cost;

    TowerTypes(int id, String image, int cost) {
        this.id = id;
        this.image = image;
        this.cost = cost;
    }

    /**
     * Returns the tower type by id
     * @param id id
     * @return TowerTypes
     */
    public static TowerTypes getById(int id) {
        for (TowerTypes e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return BASICTOWER;
    }

    /**
     * Returns the cost of the tower
     * @return int
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * Returns the id of the tower
     * @return int
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the image path of the tower
     * @return String
     */
    @Override
    public String getImagePath() {
        return this.image;
    }
}
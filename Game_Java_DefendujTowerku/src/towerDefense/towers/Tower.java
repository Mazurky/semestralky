package towerDefense.towers;

import fri.shapesge.Image;
import towerDefense.board.Tile;

/**
 * Class for the towers
 */
public class Tower extends Tile {
    private final Image image;
    private final int posX;
    private final int posY;
    private final int cost;

    /**
     * Constructor for the tower
     * Sets the position of the tower, the cost and the image path
     * @param posX position on the X axis
     * @param posY position on the Y axis
     * @param cost cost of the tower
     * @param image image path
     */
    public Tower(int posX, int posY, int cost, String image) {
        super(posX, posY, 9);
        super.makeVisible();

        this.image = new Image(image, posX, posY);
        this.image.makeVisible();
        this.posX = posX;
        this.posY = posY;
        this.cost = cost;
    }

    /**
     * Returns the image of the tower
     * @return Image
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Calculates the angle between the enemy and the tower
     * so the tower can rotate towards the enemy
     * @param enemyX enemy position in a matrix
     * @param enemyY enemy position in a matrix
     * @param towerX tower position in a matrix
     * @param towerY tower position in a matrix
     * @return int angle
     */
    public int calculateAngle(int enemyX, int enemyY, int towerX, int towerY) {
        int deltaX = enemyX - towerX;
        int deltaY = enemyY - towerY;
        double angle = (int)Math.atan2(deltaY, deltaX) * 180 / Math.PI;
        if (angle > 0 && angle < 180) {
            angle = angle * (-1);
        } else if (angle < 0) {
            angle = Math.abs(angle);
        }
        return (int)angle;
    }

    /**
     * Returns the row of the tower
     * @return int
     */
    public int getRow() {
        return this.posY / 50;
    }

    /**
     * Returns the column of the tower
     * @return int
     */
    public int getCol() {
        return this.posX / 50;
    }

    /**
     * Returns the cost of the tower
     * @return int
     */
    public int getCost() {
        return this.cost;
    }
}

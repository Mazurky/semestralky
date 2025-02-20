package towerDefense.enemy;

import fri.shapesge.Image;
import towerDefense.board.Board;
import towerDefense.game.HpBar;

/**
 * Enemy class.
 * Represents an enemy in the game.
 */
public class Enemy {
    private final Image image;
    private final HpBar healthBar;
    private final Board board;
    private final EnemyTypes enemyType;
    private Directions direction;
    private int posX;
    private int posY;
    private int rowOnBoard;
    private int colOnBoard;
    private int health;
    private boolean poisoned;
    private boolean freezed;
    private boolean killedByCastle;

    /**
     * Constructor for the enemy.
     * Sets the position of the enemy to the first tile on the board.
     * Sets the direction of the enemy to right.
     * Sets the health of the enemy to the health of the enemy type.
     *
     * @param enemyType type of the enemy
     * @param board board on which the enemy is placed
     */
    public Enemy(EnemyTypes enemyType, Board board) {
        this.posX = 0;
        this.posY = board.getFirstTileLocation() * board.getTileSize();
        this.rowOnBoard = board.getFirstTileLocation();
        this.colOnBoard = 0;

        this.board = board;
        this.direction = Directions.RIGHT;

        this.enemyType = enemyType;
        this.health = enemyType.getHealth();
        this.image = new Image(enemyType.getImagePath() + "1.png", this.posX, this.posY);
        this.image.makeVisible();

        this.healthBar = new HpBar(this.posX, this.posY, enemyType.getHealth());

        this.killedByCastle = false;
        this.poisoned = false;
        this.freezed = false;
    }

    /**
     * Returns the row position in a matrix of enemy
     * @return int - position in a matrix
     */
    public int getRowOnBoard() {
        return this.posY / 50;
    }

    /**
     * Returns the column position in a matrix of enemy
     * @return int - position in a matrix
     */
    public int getColOnBoard() {
        return this.posX / 50;
    }

    /**
     * Method that makes enemy move on a path of a board
     */
    public void move() {
        /*
        case 10 - horizontal
        case 11 - vertical
        case 12 - left down
        case 13 - left up
        case 14 - right down
        case 15 - right up
        */
        /*for (int i = 0; i < this.getEnemyType().getnumberOfImages(); i++) {
            this.image.changeImage(this.getEnemyType().getImagePath() + (i + 1) + ".png");
        }*/
        if (this.isFreezed()) {
            return;
        }
        int pixels = 1;
        int tileId;
        tileId = this.board.getTile(this.rowOnBoard, this.colOnBoard).getTileID();

        if (tileId != 10 && tileId != 11) {
            switch (tileId) {
                case 12 -> {
                    if (this.direction == Directions.RIGHT) {
                        this.direction = Directions.DOWN;
                    } else if (this.direction == Directions.UP) {
                        this.direction = Directions.LEFT;
                    }
                }
                case 13 -> {
                    if (this.direction == Directions.DOWN) {
                        this.direction = Directions.LEFT;
                    } else if (this.direction == Directions.RIGHT) {
                        this.direction = Directions.UP;
                    }
                }
                case 14 -> {
                    if (this.direction == Directions.LEFT) {
                        this.direction = Directions.DOWN;
                    } else if (this.direction == Directions.UP) {
                        this.direction = Directions.RIGHT;
                    }
                }
                case 15 -> {
                    if (this.direction == Directions.DOWN) {
                        this.direction = Directions.RIGHT;
                    } else if (this.direction == Directions.LEFT) {
                        this.direction = Directions.UP;
                    }
                }
            }
        }
        switch (this.direction) {
            case UP -> {
                this.posY -= pixels;
                this.image.moveVertical(-pixels);
                this.healthBar.move(0, -pixels);
                if (this.posY % 50 == 0) {
                    this.rowOnBoard--;
                }
            }
            case DOWN -> {
                this.posY += pixels;
                this.image.moveVertical(pixels);
                this.healthBar.move(0, pixels);
                if (this.posY % 50 == 0) {
                    this.rowOnBoard++;
                }
            }
            case LEFT -> {
                this.posX -= pixels;
                this.image.moveHorizontal(-pixels);
                this.healthBar.move(-pixels, 0);
                if (this.posX % 50 == 0) {
                    this.colOnBoard--;
                }
            }
            case RIGHT -> {
                this.posX += pixels;
                this.image.moveHorizontal(pixels);
                this.healthBar.move(pixels, 0);
                if (this.posX % 50 == 0) {
                    this.colOnBoard++;
                }
            }
        }
    }

    /**
     * Returns the type of the enemy
     * @return EnemyType
     */
    public EnemyTypes getEnemyType() {
        return this.enemyType;
    }

    /**
     * Decreases the health of the enemy
     * if enemy dies it hides the enemy
     * @param health int - amount of health to be decreased
     */
    public void decreseHp(int health) {
        this.health -= health;
        this.healthBar.decreaseHp(health);
        if (this.isDead()) {
            this.hide();
        }
    }

    /**
     * Hides the enemy and its health bar
     */
    public void hide() {
        this.image.makeInvisible();
        this.healthBar.hide();
    }

    /**
     * Returns true if the enemy is dead
     * @return boolean
     */
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * Returns if the enemy is killed by castle
     * @return boolean - true if killed by castle
     */
    public boolean isKilledByCastle() {
        return this.killedByCastle;
    }

    /**
     * Set true if castle killed the enemy
     */
    public void setKilledByCastle() {
        this.killedByCastle = true;
    }

    /**
     * If enemy was frozen by freezer tower
     * @return boolean - true if enemy is freezed
     */
    public boolean isFreezed() {
        return this.freezed;
    }

    /**
     * Set true if enemy is freezed
     */
    public void setFreezed() {
        this.freezed = true;
    }

    /**
     * Returns true if enemy is poisoned
     * @return boolean
     */
    public boolean isPoisoned() {
        return this.poisoned;
    }

    /**
     * Set true if enemy is poisoned
     * and changes the image to poisoned enemy
     */
    public void setPoisoned() {
        this.poisoned = true;
        this.image.changeImage(this.getEnemyType().getImagePath() + "poisoned.png");
    }
}
package towerDefense.game;

import fri.shapesge.FontStyle;
import fri.shapesge.Rectangle;
import fri.shapesge.Text;
import towerDefense.board.Board;
import towerDefense.board.Castle;
import towerDefense.enemy.Enemy;
import towerDefense.enemy.EnemySpawner;
import towerDefense.panel.Panel;
import towerDefense.towers.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Class that represents the game.
 * It contains the board, panel, enemies, towers and castle.
 * It's the "main" class of the game.
 */
public class Game {
    private final Board board;
    private final Panel panel;
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Tower> towers;
    private final Castle castle;
    private int time = 0;
    private final Random random;
    private int pickedTowerId;
    private boolean endGame;

    /**
     * Constructor of the game.
     * It creates the board, panel, enemies, towers and castle.
     * At the start of a game it shows the loading screen so everything can load.
     */
    public Game() {
        this.random = new Random();
        var initialText = new Text("Loading", 100, 250);
        initialText.changeFont("Serif", FontStyle.PLAIN, 50);

        this.enemies = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.panel = new Panel();

        initialText.makeVisible();
        this.board = new Menu().getBoard();
        if (this.board == null) {
            System.exit(0);
        }
        this.castle = this.board.getCastle();
        this.castle.setStats(this.panel.getStats());
        this.panel.getStats().setHealth(this.castle.getHealth());
        this.panel.getStats().setCoins(this.castle.getCoins());
        initialText.makeInvisible();

        this.board.redrawTiles();
        this.panel.getStats().showStats();
        this.panel.getTowerPicker().showTowerPicker();
        this.pickedTowerId = 0;
        this.endGame = false;
    }

    /**
     * Method that is controlled by ShapesGE.
     * It's called every time that left mouse is clicked.
     */
    public void chooseCoordinates(int x, int y) {
        if (this.board.isHighlighted() && x < 1000) {
            int row = y / 50;
            int column = x / 50;
            if (this.board.getTile(row, column).isHighlighted()) {
                Tower tower = this.panel.getTowerPicker().createTower(this.pickedTowerId, column * 50, row * 50);
                this.castle.decreaseCoins(tower.getCost());
                this.board.changeTile(column, row, tower);
                this.towers.add(tower);
            }
            this.board.unHighlightAll();
            this.pickedTowerId = 0;
        }
        if (this.panel.isTowerPicker(x, y)) {
            if (this.panel.pickTower(x, y) != 0) {
                this.pickedTowerId = this.panel.pickTower(x, y);
                TowerTypes type = TowerTypes.getById(this.pickedTowerId);
                if (type.getCost() <= this.castle.getCoins()) {
                    this.board.highlightPlacableTile();
                }
            }
        }
    }

    /**
     * Method that is controlled by ShapesGE.
     * It's called every time that right mouse button is clicked.
     */
    public void showTowerAtributes(int x, int y) {
        if (this.panel.isTowerPicker(x, y)) {
            if (this.panel.pickTower(x, y) != 0) {
                Tower tower = this.panel.getTowerPicker().createTower(this.panel.pickTower(x, y), -500, -500);
                tower.getImage().makeInvisible();
                if (tower instanceof Info towerInfo) {
                    String title = "Tower: " + towerInfo.getName();
                    String message = towerInfo.getDescription() + "<br><i>" + towerInfo.getAttributes() + "</i>";

                    JOptionPane.showMessageDialog(null, "<html>" + message + "</html>", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    /**
     * Called when castle is destroyed.
     */
    private void gameOver() {
        this.board.hideTiles();
        this.enemies.clear();
        this.towers.clear();
        this.panel.getStats().hideStats();
        this.panel.getTowerPicker().hideTowerPicker();
        this.pickedTowerId = -1;
        var cover = new Rectangle(0, 0);
        cover.changeSize(1200, 750);
        cover.changeColor("white");
        cover.makeVisible();
        var gameOverText = new Text("Game Over\npress ESC to exit", 100, 250);
        gameOverText.changeFont("Serif", FontStyle.PLAIN, 50);
        gameOverText.makeVisible();
        this.endGame = true;
    }

    /**
     * Method that is controlled by ShapesGE.
     * It's called every time that ESC is pressed.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Method that is controlled by ShapesGE.
     * Makes each enemy move.
     */
    public void enemyMove() {
        for (Enemy enemy : this.enemies) {
            enemy.move();
        }
    }

    /**
     * Method that is controlled by ShapesGE.
     * Makes each enemy attack.
     */
    public void tick() {
        this.removeEnemies();
        if (this.endGame) {
            return;
        }
        if (this.castle.isDestroyed()) {
            this.gameOver();
            return;
        }
        if (this.time == Integer.MAX_VALUE - 1000) {
            this.time = 0;
        }
        this.time++;
        if (this.random.nextInt(101) <= 13) {
            this.enemies.add(new Enemy(new EnemySpawner().pickEnemyType(), this.board));
        }
        Collection<Enemy> colEnemies = Collections.unmodifiableCollection(this.enemies);

        for (Tower tower : this.towers) {
            if (tower instanceof IAttackTower attactT) {
                attactT.attack(colEnemies, this.time);
            } else if (tower instanceof IEffectTower effectT) {
                effectT.effect(colEnemies, this.time);
            } else if (tower instanceof IPassiveTower passiveT) {
                passiveT.passiveAction(this.time, this.castle);
            }
        }
        this.castle.effect(colEnemies);
    }
    private void removeEnemies() {
        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        for (Enemy enemy : this.enemies) {
            if (enemy.isDead()) {
                this.castle.increaseCoins(enemy.getEnemyType().getReward());
                deadEnemies.add(enemy);
            } else if (enemy.isKilledByCastle()) {
                deadEnemies.add(enemy);
            }
        }
        this.enemies.removeAll(deadEnemies);
    }
}
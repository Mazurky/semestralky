package towerDefense.game;
import fri.shapesge.Manager;

/**
 * Main class of the game.
*/
public class Main {
    public static void main(String[] args) {
        var manager = new Manager();
        manager.manageObject(new Game());
    }
}

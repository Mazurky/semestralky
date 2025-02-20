package towerDefense.game;

import towerDefense.board.Board;

import javax.swing.JOptionPane;

/**
 * Creates a window to select a map.
 */
public class Menu {
    private int getLevel() {
        //String[] options = {"Map 1", "Map 2", "Map 3", "Map 4", "Map 5", "Generate Map"};
        //return JOptionPane.showOptionDialog(null, "Choose a Map or generate a map:", "Map Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String[] options = {"Map 1", "Map 2", "Map 3", "Map 4", "Map 5"};
        //return 0;
        return JOptionPane.showOptionDialog(null, "Choose a map:", "Map Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }

    /**
     * Returns a board based on the map selected.
     * @return Board
     */
    public Board getBoard() {
        int map = this.getLevel();
        if (map >= 0 && map <= 4) {
            return new Board(map + 1);
        }
        return null;
    }
}

package towerDefense.towers;

import towerDefense.board.Castle;

/**
 * Interface for the passive towers
 */
public interface IPassiveTower {
    void passiveAction(int time, Castle castle);
}

package towerDefense.towers;

import towerDefense.enemy.Enemy;

import java.util.Collection;

/**
 * Interface for the attack towers
 */
public interface IAttackTower {
    void attack(Collection<Enemy> enemies, int time);
}

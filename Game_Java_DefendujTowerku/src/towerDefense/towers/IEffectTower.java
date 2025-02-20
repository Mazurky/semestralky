package towerDefense.towers;

import towerDefense.enemy.Enemy;

import java.util.Collection;
/**
 * Interface for the effect towers
 */
public interface IEffectTower {
    void effect(Collection<Enemy> enemies, int time);
}

package model;

import utils.Pair;

public interface GhostSmartBehaviour extends GhostBehaviour {

    /**
     * @return the target of the Ghost in relax mode
     */
    Pair<Integer, Integer> getRelaxTarget();

    /**
     * Notify Inky the Blinky death.
     */
    void setBlinkyDead();
}
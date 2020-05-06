package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import utils.Pair;
import utils.PairImpl;
import static model.Directions.UP;
import static model.Directions.DOWN;
import static model.Directions.RIGHT;
import static model.Directions.LEFT;

public class GhostRandomBehaviourImpl extends GhostAbstractBehaviour {

    private final List<Pair<Integer, Integer>> ghostHouse;

    public GhostRandomBehaviourImpl(final Set<Pair<Integer, Integer>> walls,
            final List<Pair<Integer, Integer>> ghostHouse, final int xMapSize, final int yMapSize,
            final Pair<Integer, Integer> startPosition) {
        super(xMapSize, yMapSize, startPosition, ghostHouse, walls);
        this.ghostHouse = ghostHouse;
    }

    @Override
    public final void nextPosition(final boolean eatable, final boolean timeToTurn, final boolean oldLevel) {
        if (timeToTurn && !this.checkIfInside() 
                && !this.ghostHouse.contains(new PairImpl<>(this.getCurrentPosition().getX(), this.getCurrentPosition().getY() - 1))) {
            this.turnAround();
        } else {
            this.runAway();
        }
    }

    private void turnAround() {
        this.setAdj(this.getCurrentPosition());
        if (this.getCurrentDirection().equals(UP)) {
            this.setCurrentPosition(this.getAdj(DOWN));
            this.setCurrentDirection(DOWN);
        } else if (this.getCurrentDirection().equals(RIGHT)) {
            this.setCurrentPosition(this.getAdj(LEFT));
            this.setCurrentDirection(LEFT);
        } else if (this.getCurrentDirection().equals(DOWN)) {
            this.setCurrentPosition(this.getAdj(UP));
            this.setCurrentDirection(UP);
        } else {
            this.setCurrentPosition(this.getAdj(RIGHT));
            this.setCurrentDirection(RIGHT);
        }
    }

    private void runAway() {
        final Random r = new Random();
        final Pair<Integer, Integer> oldPosition = this.getCurrentPosition();
        this.setAdj(this.getCurrentPosition());
        final Map<Directions, Pair<Integer, Integer>> map = new HashMap<>();
        Map<Directions, Pair<Integer, Integer>> map2;
        map.put(UP, this.getAdj(UP));
        map.put(RIGHT, this.getAdj(RIGHT));
        map.put(DOWN, this.getAdj(DOWN));
        map.put(LEFT, this.getAdj(LEFT));
        while (this.getCurrentPosition().equals(oldPosition)) {
            for (final Directions dir : map.keySet()) {
                if (this.getCurrentDirection().equals(dir)) {
                    map2 = new HashMap<>(map);
                    map2.remove(this.oppositeDirection(dir));
                    final List<Directions> list = new ArrayList<>(map2.keySet());
                    final Directions randomDir = list.get(r.nextInt(3));
                    if (!this.getWalls().contains(map2.get(randomDir))) {
                        this.setCurrentPosition(map2.get(randomDir));
                       this.setCurrentDirection(randomDir);
                       return;
                    }
                }
            }
        }
    }

    private Directions oppositeDirection(final Directions dir) {
            if (dir.equals(UP)) {
                return DOWN;
            } else if (dir.equals(LEFT)) {
               return RIGHT;
            } else if (dir.equals(DOWN)) {
                return UP;
            } else {
                return LEFT;
            }
    } 
}
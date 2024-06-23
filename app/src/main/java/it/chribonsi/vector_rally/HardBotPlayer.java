package it.chribonsi.vector_rally;

import java.util.HashSet;
import java.util.Set;

public class HardBotPlayer extends BotPlayer {

    public HardBotPlayer(String name) {
        super(name);
    }

    @Override
    public Movement decideNextMove(SimpleRacetrack racetrack) {
        Position currentPosition = racetrack.getRacePositions().get(this);
        return dfs(currentPosition, racetrack, new HashSet<>());
    }

    private Movement dfs(Position currentPosition, SimpleRacetrack racetrack, Set<Position> visited) {
        // Mark current position as visited
        visited.add(currentPosition);

        // Check if we reached the finish line
        if (racetrack.getCell(currentPosition) == CellType.FINISH) {
            return Movement.MIDDLE_C; // Choose a default move when reaching the finish line
        }

        // Explore each possible movement
        for (Movement move : Movement.VALUES) {
            Vector offset = move.getOffset();
            Position nextPosition = offset.addTo(currentPosition);

            // Ensure the next position is within bounds and not visited
            if (racetrack.isCellFree(nextPosition) && !visited.contains(nextPosition)) {
                Movement result = dfs(nextPosition, racetrack, visited);
                if (result != null) {
                    return move; // Return the first valid move found
                }
            }
        }

        // Backtrack if no valid move found
        visited.remove(currentPosition);
        return null;
    }
}

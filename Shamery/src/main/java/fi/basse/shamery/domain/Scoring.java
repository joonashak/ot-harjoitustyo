package fi.basse.shamery.domain;

import java.util.ArrayList;
import java.util.List;

public class Scoring {
    private List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Add a player to scoring system.
     * @param player Player instance.
     */
    public void addPlayer(Player player) {
        // Max two players.
        if (players.size() > 1) {
            System.out.println("ERROR: This game can be played by at most two players!");
            System.exit(1);
        }

        players.add(player);
    }
}

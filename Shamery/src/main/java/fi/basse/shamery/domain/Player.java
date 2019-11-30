package fi.basse.shamery.domain;

public class Player {
    private String name;
    private int score;

    /**
     * Player of the Game.
     * @param name name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    /**
     * Increase score.
     * @param increase amount to increase.
     */
    public void incScore(int increase) {
        this.score += increase;
    }
}

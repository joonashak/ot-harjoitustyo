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
     * Score will not go below zero.
     * @param increase amount to increase.
     */
    public void incScore(int increase) {
        score = Math.max(0, score + increase);
    }
}

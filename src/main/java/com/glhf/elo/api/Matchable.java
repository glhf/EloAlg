package com.glhf.elo.api;

/**
 * Providing match functionality
 *
 * Set 2 Playing objects. Count probability of wins. Set winner change
 *
 * @author Mykola Polonskyi
 *         on 02.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public interface Matchable {

    public Playing getFirstPlayer();
    public Playing getSecondPlayer();

    /**
     * Set winner method. Provide setting of winner with recounting of ratings of playing instance
     * @param status can get DRAW HOME_WIN GUEST_WIN incoming values
     */
    //public void setWinner(MatchStatus status);

    /**
     * Get probability of first object win
     * "Home team"
     * @return probability
     */
    //public double getFirstWinProbability();

    /**
     * Get probability of first object win
     * "Guest team"
     * @return probability
     */
    //public double getSecondWinProbability();
}

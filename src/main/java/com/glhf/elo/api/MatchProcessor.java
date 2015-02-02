package com.glhf.elo.api;

/**
 * @author Mykola Polonskyi
 *         on 02.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public interface MatchProcessor {

    /**
     * loading dump with matches list
     * and provide them
     */
    public void load();

    /**
     * Add match with playings instances
     */
    public void addMatch(Playing firstPlayer, Playing secondPlayer, MatchStatus status);


}

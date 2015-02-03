package com.glhf.elo.api;

import com.glhf.elo.entities.MatchInfo;

import java.util.List;

/**
 * Instances that can be able to play match
 *
 * @see java.lang.Comparable implements compereTo to sorting set with instances
 *
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public interface Playing {

    public void addMatch(Playing opponent, String result);

    public List<MatchInfo> getPlayedMatchesReport();

    /**
     * Method that using to instance that had win the match
     * @param inc count of points that would be able
     */
    public void correctPoitns(double inc);

    /**
     * return count of points that are able
     * @return
     */
    public double getPoints();


    /**
     * Get count of wins
     */
    public int getCountOfWins();

    /**
     * Get count of loses
     */
    public int getCountOfLoses();

    /**
     * Get count of draws
     */
    public int getCountOfDraws();

    /**
     * get history of matches as string value
     * @return
     */
    public String historyToString();

    public int getId();

    public String getName();
}
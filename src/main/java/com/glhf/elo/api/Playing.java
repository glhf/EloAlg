package com.glhf.elo.api;

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
    /**
     * Method that using to instance that had win the match
     * @param inc count of points that would be able
     */
    public void increasePoitns(double inc);

    /**
     * Method that using to instance that had lose the match
     * @param dec count of points that would be able
     */
    public void decreasePoitns(double dec);

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
}
package com.glhf.elo.api;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * API for methods of work with players:
 * -loading data about them
 * -show statistic methods
 * -sorted playing inst by points, wins, loses
 *
 * for each sort operation must be implements appropriate @see java.lang.Comparator
 *
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public interface PlayersProcessor {

    /**
     * Loading set of playing instances from List of strings that come in constructor
     * To internal collection
     *
     */
    public void load();

    /**
     * add new player
     */
    public void addNewPlayer(int id, String name);

    /**
     *
     */
    public int getCountOfPlayers();

    /**
     * Get player instance by ID
     */
    public Playing getPlayer(int id) throws NoSuchElementException;

    /**
     * Sorted available players by
     */
    public List<Playing> sortByPoints();

    public List<Playing> sortByCountOfWins();

    public List<Playing> sortByCountOfLoses();

    /**
     * Printed list of player
     */
    public void print();
}

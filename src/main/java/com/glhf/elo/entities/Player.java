package com.glhf.elo.entities;

import com.glhf.elo.api.Playing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple player instance
 *
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class Player implements Playing {
    private static final Logger LOG = LogManager.getLogger(Player.class);
    private int wins = 0;
    private int loses = 0;
    private int id;
    private String name;
    private double points = 400;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void increasePoitns(double inc){
        this.points+=inc;
        this.wins++;
    }

    @Override
    public void decreasePoitns(double dec){
        this.points-=dec;
        this.loses--;
    }

    @Override
    public double getPoints(){
        return points;
    }

    @Override
    public int getCountOfWins() {
        return this.wins;
    }

    @Override
    public int getCountOfLoses() {
        return this.loses;
    }

    @Override
    public String toString() {
        return new StringBuffer().append(getId()).append(" ").append(getName()).append(" ").append(getPoints()).toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
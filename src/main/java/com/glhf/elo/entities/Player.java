package com.glhf.elo.entities;

import com.glhf.elo.api.Playing;

import java.util.LinkedList;
import java.util.List;

/**
 * Simple player instance
 *
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class Player implements Playing {
    private int wins = 0;
    private int draws = 0;
    private int loses = 0;
    private int id;
    private String name;
    private double points = 400;
    private List<MatchInfo> history = new LinkedList<>();

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Player(int id, String name, double points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    @Override
    public void correctPoitns(double inc){
        this.points+=inc;
        if (inc>0) {
            this.wins++;
        } else if (inc==0) {
            this.draws++;
        } else {
            this.loses++;
        }
    }

    @Override
    public void addMatch(Playing opponent, String result) {
        history.add(new MatchInfo(opponent, result));
    }

    @Override
    public String toString() {
        return new StringBuilder().append(getId()).append(" \t").append(getName()).append(" \t")
                .append(getPoints()).append(" \t").append(getCountOfWins()).append(" \t").append(getCountOfLoses()).toString();
    }

    @Override
    public String historyToString() {
        StringBuffer sb = new StringBuffer();
        history.forEach(el -> sb.append(el.toString()));
        return sb.toString();
    }

    @Override
    public List<MatchInfo> getPlayedMatchesReport() {
        return this.history;
    }

    @Override
    public double getPoints(){
        return this.points;
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
    public int getCountOfDraws(){
        return this.draws;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }


}
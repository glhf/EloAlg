package com.glhf.elo.entities;

import com.glhf.elo.api.Playing;

/**
 * Class for storing history of played matches
 * Information about match
 * -opponent
 * -result
 *
 * @author Mykola Polonskyi
 *         on 02.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class MatchInfo {
    private Playing opponent;
    private String result;

    public MatchInfo(Playing opponent, String result){
        this.opponent = opponent;
        this.result = result;
    }
    @Override
    public String toString(){
        return new StringBuffer().append(opponent.getName()).append(" ").append(result).append(" ").toString();
    }
}

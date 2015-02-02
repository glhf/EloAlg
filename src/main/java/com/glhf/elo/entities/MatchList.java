package com.glhf.elo.entities;

import com.glhf.elo.api.MatchProcessor;
import com.glhf.elo.api.MatchStatus;
import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.api.Playing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Processing incoming data about matches, play matches
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class MatchList implements MatchProcessor {
    private static final Logger LOG = LogManager.getLogger(MatchList.class);

    private PlayersProcessor playersProcessor;
    private List<String> incomMatchesStream;

    public MatchList(List<String> incomMatchesStream, PlayersProcessor pp){
        this.playersProcessor = pp;
        this.incomMatchesStream = incomMatchesStream;
        load();
    }


    /**
     * Parsing input stream of strings in the format
     * ID_first_player ID_second_player
     */
    @Override
    public void load() throws NullPointerException {
        if (incomMatchesStream==null) throw new NullPointerException();
        this.incomMatchesStream.forEach(el -> {
            String[] strings = el.split(" ");
            new Match(playersProcessor.getPlayer(Integer.valueOf(strings[0])), playersProcessor.getPlayer(Integer.valueOf(strings[1])), MatchStatus.HOME_WIN);
        });
        this.incomMatchesStream=null;
    }

    @Override
    public void addMatch(Playing firstPlayer, Playing secondPlayer, MatchStatus status) {
        Match.provideMatch(firstPlayer,secondPlayer,status);
    }

}

package com.glhf.elo.entities;

import com.glhf.elo.algorithm.Elo;
import com.glhf.elo.api.MatchProcessor;
import com.glhf.elo.api.MatchStatus;
import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.api.Playing;
import com.glhf.elo.engines.EloException;
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
    private static Elo algorithm;
    private PlayersProcessor playersProcessor;
    private List<String> incomMatchesStream;

    public MatchList(List<String> incomMatchesStream, PlayersProcessor pp) throws EloException{
        this.playersProcessor = pp;
        this.incomMatchesStream = incomMatchesStream;
        load();
    }


    /**
     * Parsing input stream of strings in the format
     * ID_first_player ID_second_player
     */
    @Override
    public void load() throws EloException {
        this.incomMatchesStream.forEach(el -> {
            String[] strings = el.split(" ");
            try {
                algorithm.eloProcessing(new Match(playersProcessor.getPlayer(Integer.valueOf(strings[0])), playersProcessor.getPlayer(Integer.valueOf(strings[1]))), MatchStatus.HOME_WIN);
            } catch (EloException e) {
                LOG.error(e);
            }
        });
        this.incomMatchesStream=null;
    }

    @Override
    public void addMatch(Playing firstPlayer, Playing secondPlayer, MatchStatus status) {
        algorithm.eloProcessing(new Match(firstPlayer, secondPlayer), MatchStatus.HOME_WIN);
    }

}

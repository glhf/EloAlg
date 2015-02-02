package com.glhf.elo.engines;


import com.glhf.elo.api.MatchProcessor;
import com.glhf.elo.api.MatchStatus;
import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.api.Playing;
import com.glhf.elo.entities.MatchList;
import com.glhf.elo.entities.PlayersList;

import java.util.List;

/**
 * Process input matches in txt file. Provide games that
 * Process players from txt file
 *
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class Engine {
    private PlayersProcessor playersProcessor;
    private MatchProcessor matchProcessor;

    public Engine(List<String> playersDataStrings, List<String> matchesDataString) {
        this.playersProcessor = new PlayersList(playersDataStrings);
        this.matchProcessor = new MatchList(matchesDataString, this.playersProcessor);
    }

    //add match with result method

    /**
     *
     * @param idFirst id of home/first player
     * @param idSecond id of guest/second player
     * @param result 0 if draw
     *               1 if home/first player win
     *               2 if guest/second player win
     */
    public void addMatch(int idFirst, int idSecond, int result){
        MatchStatus ms = null;
        if (result == 0) {
            ms = MatchStatus.DRAW;
        } else if (result == 1) {
            ms = MatchStatus.HOME_WIN;
        } else if (result == 2) {
            ms = MatchStatus.GUEST_WIN;
        }
        matchProcessor.addMatch(playersProcessor.getPlayer(idFirst),playersProcessor.getPlayer(idSecond), ms);
    }

    // get sorted player lists method
    public List<Playing> getSortedByPointsPlayersList() {
        return this.playersProcessor.sortByPoints();
    }

    public List<Playing> getSortedByWinsPlayersList() {
        return this.playersProcessor.sortByCountOfWins();
    }

    public List<Playing> getSortedByLosesPlayersList() {
        return this.playersProcessor.sortByCountOfLoses();
    }

    // get players stats
    public Playing getPlayerById(int id) {
        return playersProcessor.getPlayer(id);
    }

}

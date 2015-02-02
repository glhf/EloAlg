package com.glhf.elo.entities;

import com.glhf.elo.algorithm.Elo;
import com.glhf.elo.api.MatchStatus;
import com.glhf.elo.api.Matchable;
import com.glhf.elo.api.Playing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class applying result of match
 *
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class Match implements Matchable {
    private static final Logger LOG = LogManager.getLogger(Match.class);

    private static Elo algorithm;

    private MatchStatus status;
    private Playing first;
    private Playing second;

    public static void provideMatch(Playing first, Playing second, MatchStatus status){
        algorithm.eloProcessing(first, second, status);
        switch (status) {
            case HOME_WIN:
                first.addMatch(second, "win");
                second.addMatch(first, "lose");
                break;
            case DRAW:
                first.addMatch(second, "draw");
                second.addMatch(first, "draw");
                break;
            case GUEST_WIN:
                first.addMatch(second, "lose");
                second.addMatch(first, "win");
                break;
        }
    }

    public Match(Playing first, Playing second) {
        algorithm = new Elo(first, second);
        this.first = first;
        this.second = second;
    }

    public Match(Playing first, Playing second, MatchStatus status) {
        algorithm = new Elo(first, second);
        this.first = first;
        this.second = second;
        this.status = status;
        setWinner(status);
    }

    @Override
    public void setWinner(MatchStatus status) {
        algorithm.processMatchResult(status);
        switch (status) {
            case HOME_WIN:
                this.first.addMatch(second, "win");
                this.second.addMatch(first, "lose");
                break;
            case DRAW:
                this.first.addMatch(second, "draw");
                this.second.addMatch(first, "draw");
                break;
            case GUEST_WIN:
                this.first.addMatch(second, "lose");
                this.second.addMatch(first, "win");
                break;
        }
    }

    @Override
    public double getFirstWinProbability() {
        return algorithm.getFirstPlayerWinProbability();
    }

    @Override
    public double getSecondWinProbability() {
        return algorithm.getSecondPlayerWinProbability();
    }
}

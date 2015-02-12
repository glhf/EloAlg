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

    private Playing first;
    private Playing second;

    public Match(Playing first, Playing second) {
        this.first = first;
        this.second = second;
    }

    public Match(Playing first, Playing second, MatchStatus status) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Playing getFirstPlayer() {
        return first;
    }

    @Override
    public Playing getSecondPlayer() {
        return second;
    }
}

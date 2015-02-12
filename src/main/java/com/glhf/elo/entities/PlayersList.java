package com.glhf.elo.entities;

import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.api.Playing;
import com.glhf.elo.engines.EloException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Stream;

/**
 * Processing of players information
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class PlayersList implements PlayersProcessor {
    private static final Logger LOG = LogManager.getLogger(PlayersList.class);

    private List<String> incomPlayersStream;
    private Map<Integer, Playing> playersSet = new HashMap<>();

    public PlayersList(List<String> players){
        this.incomPlayersStream=players;
        load();
    }

    /**
     * Parsing input stream of strings in the format
     * ID Name
     */
    @Override
    public void load() {
        this.incomPlayersStream.forEach(el -> {
            String[] strings = el.split(" ");
            playersSet.put(Integer.parseInt(strings[0]), new Player(Integer.parseInt(strings[0]),strings[1]));
        });
        LOG.debug("loaded " + playersSet.size() + " records");

        this.incomPlayersStream=null;
    }

    @Override
    public int getCountOfPlayers() {
        return playersSet.size();
    }

    @Override
    public Playing getPlayer(int id) throws EloException {
        if (this.playersSet.containsKey(id)) {
            return this.playersSet.get(id);
        } else {
            throw new EloException(EloException.ERROR_TYPE.NO_PLAYER_WITH_SUCH_ID_INSYSTEM);
        }
    }

    @Override
    public void addNewPlayer(int id, String name) throws EloException {
        if (playersSet.containsKey(id)) throw new EloException(EloException.ERROR_TYPE.PLAYER_WITH_SUCH_ID_ALREADY_REGISTER);
        playersSet.put(id, new Player(id, name));
    }

    @Override
    public List<Playing> sortByPoints() {
        List temp = new LinkedList<>(playersSet.values());
        temp.sort(new ComparatorByPoints());
        return temp;
    }

    @Override
    public List<Playing> sortByCountOfWins() {
        List temp = new LinkedList<>(playersSet.values());
        temp.sort(new ComparatorByWins());
        return temp;
    }

    @Override
    public List<Playing> sortByCountOfLoses() {
        List temp = new LinkedList<>(playersSet.values());
        temp.sort(new ComparatorByLoses());
        return temp;
    }

    @Override
    public void print() {
        new LinkedList<>(this.playersSet.values()).forEach(el -> LOG.info(el.toString()));
    }

    class ComparatorByPoints implements Comparator<Playing> {
        @Override
        public int compare(Playing o1, Playing o2) {
            return o1.getPoints() < o2.getPoints() ? 1 : o1.getPoints() > o2.getPoints() ? -1 : 0;
        }
    }
    class ComparatorByWins implements Comparator<Playing> {
        @Override
        public int compare(Playing o1, Playing o2) {
            return o1.getCountOfWins() < o2.getCountOfWins() ? 1 : o1.getCountOfWins() > o2.getCountOfWins() ? -1 : 0;
        }
    }
    class ComparatorByLoses implements Comparator<Playing> {
        @Override
        public int compare(Playing o1, Playing o2) {
            return o1.getCountOfLoses() < o2.getCountOfLoses() ? 1 : o1.getCountOfLoses() > o2.getCountOfLoses() ? -1 : 0;
        }
    }
}

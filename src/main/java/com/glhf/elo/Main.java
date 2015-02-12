package com.glhf.elo;

import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.engines.EloException;
import com.glhf.elo.engines.Engine;
import com.glhf.elo.entities.PlayersList;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class Main {
    static Logger LOG = LogManager.getLogger(Main.class);
    static CommandLineParser parser = new PosixParser();
    static Options options = new Options();

    static void initParser() {
        options.addOption("h", "help", false,"Print help info");
        options.addOption("p", "players", true, "arg - is path to players file");
        options.addOption("m", "matches", true, "arg - is path to matches file");
        options.addOption("rp", "resultsPoints", false, "Show rating of players by scores");
        options.addOption("rw", "resultsWins", false, "Show rating of players by count of wins");
        options.addOption("rl", "resultsLoses", false, "Show rating of players by count of loses");
        Option suggested  = OptionBuilder.hasArgs(2)
                .withValueSeparator(' ')
                .withLongOpt("suggested")
                .withDescription("Generate suggestion between two players. Args: <first_player_ID> <second_player_ID>.")
                .create("d");
        options.addOption(suggested);
    }

    public static void main(String[] args) {
        initParser();
        CommandLine cl = null;
        try {
            cl = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (cl.hasOption("help")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp( "ant", options );
        }

        String matchesPath;
        String playersPath;
        if (!cl.hasOption('p') | !cl.hasOption('m')){
            System.out.println("No required vars!");
            return;
        } else {
            matchesPath = cl.getOptionValue('m');
            playersPath = cl.getOptionValue('p');
        }

        Scanner names = null;
        Scanner matches = null;
        try {
            names = new Scanner(new File(playersPath));
            matches = new Scanner(new File(matchesPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> namesLines = new ArrayList<String>();
        while (names.hasNextLine()) {
            namesLines.add(names.nextLine());
        }
        List<String> matchLines = new ArrayList<>();
        while (matches.hasNextLine()) {
            matchLines.add(matches.nextLine());
        }
        try {
            Engine eng = new Engine(namesLines, matchLines);

            if (cl.hasOption("resultsPoints")) {
                //provide sort by points
                eng.getSortedByPointsPlayersList().forEach(els -> LOG.info(els.toString()));
            }
            if (cl.hasOption("resultsWins")) {
                //provide sort by wins
                eng.getSortedByWinsPlayersList().forEach(els -> LOG.info(els.toString()));
            }
            if (cl.hasOption("resultsLoses")) {
                //provide sort by loses
                eng.getSortedByLosesPlayersList().forEach(els -> LOG.info(els.toString()));
            }
            if (cl.hasOption("suggested")) {
                //provide chances for winind ID1 and ID2
                String[] vals = cl.getOptionValues("suggested");
                LOG.info(eng.getSuggestedChances(Integer.valueOf(vals[0]), Integer.valueOf(vals[1])));
            }
        } catch (EloException e) {
            LOG.error(e);
        }
    }
}

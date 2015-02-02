package com.glhf.elo;

import com.glhf.elo.api.PlayersProcessor;
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
        options.addOption("p", "players", true, "path to players file");
        options.addOption("m", "matches", true, "path to matches file");
        options.addOption("rp", "resultsPoints", false, "show rating of players by scores");
        options.addOption("rw", "resultsWins", false, "show rating of players by count of wins");
        options.addOption("rl", "resultsLoses", false, "show rating of players by count of loses");
        Option suggested  = OptionBuilder.hasArgs(2)
                .withValueSeparator(' ')
                .withDescription("Generate suggestion between two players")
                .create("d");
    }

    public static void main(String[] args) {
        initParser();
        CommandLine cl = null;
        try {
            cl = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String matchesPath;
        String playersPath;
        if (!cl.hasOption('p')&!cl.hasOption('m')){ return; } else {
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

        Engine eng = new Engine(namesLines, matchLines);

        if (cl.hasOption("resultsPoints")) {
            //provide sort by points
            eng.getSortedByPointsPlayersList().forEach(els -> System.out.println(els.toString()));
        } else if (cl.hasOption("resultsWins")) {
            //provide sort by wins
            eng.getSortedByWinsPlayersList().forEach(els -> System.out.println(els.toString()));
        } else if (cl.hasOption("resultsLoses")) {
            //provide sort by loses
            eng.getSortedByLosesPlayersList().forEach(els -> System.out.println(els.toString()));
        } else if (cl.hasOption("d")) {
            //provide chances for winind ID1 and ID2
        }

    }
}

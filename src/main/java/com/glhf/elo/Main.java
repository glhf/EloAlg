package com.glhf.elo;

import com.glhf.elo.api.PlayersProcessor;
import com.glhf.elo.engines.Engine;
import com.glhf.elo.entities.PlayersList;

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

    public static void main(String[] args) {
        Scanner names = null;
        Scanner matches = null;
        try {
            names = new Scanner(new File("/home/goodvin/IdeaProjects/EloAlg/src/main/resources/names.txt"));
            matches = new Scanner(new File("/home/goodvin/IdeaProjects/EloAlg/src/main/resources/matches.txt"));
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
        Engine eng = new Engine(namesLines,matchLines);
        eng.getSortedByPointsPlayersList().forEach(els -> System.out.println(els.toString()));
    }
}

package com.glhf.elo.api;

/**
 * Support logic for matches
 * @author Mykola Polonskyi
 *         on 30.01.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public interface Coeficients {

    /**
     * Coefficient that will be return depend form rank of playing instance
     * @param rank
     * @return
     */
    default double getCoefficients(double rank){
        //TODO rewrite logic of getting coefficient
        return 10;
    }
}

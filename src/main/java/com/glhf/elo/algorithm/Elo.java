package com.glhf.elo.algorithm;

import com.glhf.elo.api.MatchStatus;
import com.glhf.elo.api.Playing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class with Elo algorithm logic
 *
 * @author Mykola Polonskyi
 *         on 02.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 * @link <a href="https://metinmediamath.wordpress.com/2013/11/27/how-to-calculate-the-elo-rating-including-example/">example of realization Elo-Rating</>
 */
public class Elo {
    private static final Logger LOG = LogManager.getLogger(Elo.class);

    private Playing firstPlayer;
    private Playing secondPlayer;

    private final double firstPlayerTransformedRaiting;
    private final double secondPlayerTransformedRaiting;

    private final double firstPlayerWinProbability;
    private final double secondPlayerWinProbability;

    public Elo(Playing firstPlayer, Playing secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;

        // transformed rating that help compered objects like 1:10 ect.
        this.firstPlayerTransformedRaiting = Math.pow(10, firstPlayer.getPoints() / 400);
        this.secondPlayerTransformedRaiting = Math.pow(10, secondPlayer.getPoints() / 400);

        // counting coefficients that show statistic probability of wining and point coefficient
        this.firstPlayerWinProbability = firstPlayerTransformedRaiting / (firstPlayerTransformedRaiting + secondPlayerTransformedRaiting);
        this.secondPlayerWinProbability = secondPlayerTransformedRaiting / (firstPlayerTransformedRaiting + secondPlayerTransformedRaiting);


        LOG.debug("Match: "+ firstPlayer.toString() + " and " + secondPlayer.toString()
                + " \nfirstPlayerTransformedRaiting=" + firstPlayerTransformedRaiting + " secondPlayerTransformedRaiting=" + secondPlayerTransformedRaiting
                + " \nfirstPlayerWinProbability=" +firstPlayerWinProbability + " secondPlayerWinProbability=" + secondPlayerWinProbability);
    }

    public void processMatchResult(MatchStatus status) {
        /**
         * counting how much  points would be added or subtract
         * from specified match result
         */
        double newFirstPlayerRatingCorrection = Coeficients.getCoefficients(firstPlayer.getPoints()) * (status.getFirstPlayerCoefficient()-this.firstPlayerWinProbability);
        double newSecondPlayerRatingCorrection = Coeficients.getCoefficients(secondPlayer.getPoints()) * (status.getSecondPlayerCoefficient()-this.secondPlayerWinProbability);
        LOG.info("H=" + firstPlayer.getName() + " G=" + secondPlayer.getName() + " Match result: " + status.toString()
                                        + " H changed: " + newFirstPlayerRatingCorrection
                                        + " G changed: " + newSecondPlayerRatingCorrection );
        switch (status) {
            //s1=0.5    s2=0.5
            case DRAW :
                this.firstPlayer.correctPoitns(newFirstPlayerRatingCorrection);
                this.secondPlayer.correctPoitns(newSecondPlayerRatingCorrection);
                break;
            //s1=1      s2=0
            case HOME_WIN:
                this.firstPlayer.correctPoitns(newFirstPlayerRatingCorrection);
                this.secondPlayer.correctPoitns(newSecondPlayerRatingCorrection);
                break;
            //s1=0      s2=1
            case GUEST_WIN:
                this.firstPlayer.correctPoitns(newFirstPlayerRatingCorrection);
                this.secondPlayer.correctPoitns(newSecondPlayerRatingCorrection);
                break;
        }
    }

    public static void eloProcessing(Playing firstPlayer, Playing secondPlayer, MatchStatus status) {

        double firstPlayerTransformedRating = Math.pow(10, firstPlayer.getPoints() / 400);
        double secondPlayerTransformedRating = Math.pow(10, secondPlayer.getPoints() / 400);

        double firstPlayerWinProbability = firstPlayerTransformedRating / (firstPlayerTransformedRating + secondPlayerTransformedRating);
        double secondPlayerWinProbability = secondPlayerTransformedRating / (firstPlayerTransformedRating + secondPlayerTransformedRating);

        LOG.debug("Match: "+ firstPlayer.toString() + " and " + secondPlayer.toString() + "firstPlayerWinProbability="+firstPlayerWinProbability + " secondPlayerWinProbability="+secondPlayerWinProbability);
        double newFirstPlayerRatingCorrection = Coeficients.getCoefficients(firstPlayer.getPoints()) * (status.getFirstPlayerCoefficient()-firstPlayerWinProbability);
        double newSecondPlayerRatingCorrection = Coeficients.getCoefficients(secondPlayer.getPoints()) * (status.getSecondPlayerCoefficient()-secondPlayerWinProbability);

        LOG.debug("Match result: " + status.toString() + " H changed: " + newFirstPlayerRatingCorrection
                + " G changed: " + newSecondPlayerRatingCorrection );
        switch (status) {
            //s1=0.5    s2=0.5
            case DRAW :
                firstPlayer.correctPoitns(newFirstPlayerRatingCorrection);
                secondPlayer.correctPoitns(newSecondPlayerRatingCorrection);
                break;
            //s1=1      s2=0
            case HOME_WIN:
                firstPlayer.correctPoitns(newFirstPlayerRatingCorrection);
                secondPlayer.correctPoitns(newSecondPlayerRatingCorrection);
                break;
            //s1=0      s2=1
            case GUEST_WIN:
                firstPlayer.correctPoitns(newFirstPlayerRatingCorrection);
                secondPlayer.correctPoitns(newSecondPlayerRatingCorrection);
                break;
        }
    }

    public double getFirstPlayerWinProbability() {
        return firstPlayerWinProbability;
    }

    public double getSecondPlayerWinProbability() {
        return secondPlayerWinProbability;
    }
}

package com.glhf.elo.api;

/**
 * Constants for match processing
 * HOME_WIN - set as winner first team
 * GUEST_WIN - set as winner second team
 *
 * @author Mykola Polonskyi
 *         on 02.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public enum MatchStatus {
    DRAW {
        @Override
        public double getFirstPlayerCoefficient(){
            return 0.5;
        }

        @Override
        public double getSecondPlayerCoefficient(){
            return 0.5;
        }

        @Override
        public String toString(){
            return "Draw";
        }
    },
    HOME_WIN {
        @Override
        public double getFirstPlayerCoefficient(){
            return 1;
        }

        @Override
        public double getSecondPlayerCoefficient(){
            return 0;
        }

        @Override
        public String toString(){
            return "Home win";
        }
    },
    GUEST_WIN {
        @Override
        public double getFirstPlayerCoefficient(){
            return 0;
        }

        @Override
        public double getSecondPlayerCoefficient(){
            return 1;
        }

        @Override
        public String toString(){
            return "Guest win!";
        }
    };

    public abstract double getFirstPlayerCoefficient();
    public abstract double getSecondPlayerCoefficient();
}

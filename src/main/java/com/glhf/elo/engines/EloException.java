package com.glhf.elo.engines;

/**
 * Logic contains 2 types of exception
 * 1) exception when inputted id for count probability of win isn`t register in system
 * 2) exception when new trying create new player with already existing id
 *
 * @author Mykola Polonskyi
 *         on 12.02.15
 *         github.com/glhf
 *         goodvin4@gmail.com
 */
public class EloException extends Exception{
    /**
     *
     */
    public enum ERROR_TYPE {
        NO_PLAYER_WITH_SUCH_ID_INSYSTEM {
            @Override
            public String toString(){
                return "Player with such ID non-available!";
            }
        },
        PLAYER_WITH_SUCH_ID_ALREADY_REGISTER {
            @Override
            public String toString(){
                return "Player with such ID already register!";
            }
        }
    }

    private EloException(){

    }

    public EloException(ERROR_TYPE type) {
        super(type.toString());
    }

    public EloException(String message) {
        super(message);
    }
}

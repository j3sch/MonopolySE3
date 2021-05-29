package com.hdm.monopoly;

import com.hdm.monopoly.backend.player_money.DiceNumber;
import com.hdm.monopoly.backend.board.street_test.Map;
import com.hdm.monopoly.backend.player_money.Player;


import java.util.ArrayList;

/**
 * Class that starts and manages the game, is implemented as a singleton
 */
public class Game {

    private static Game game;
    public static int playerCount; // helper for constructor
    private static boolean isInitialized = false;

    // These two attributes enable a connection between a map and players
    private ArrayList<Player> players; /*we assume the game knows on its creation how many players there are.
    That could be achieved by a controller class that manages the network communication*/
    private Map board;

    private int currentPlayer;

    private boolean gameRun;

    private DiceNumber dice;

    private Game(){
        if(isInitialized) {
            currentPlayer=0;

            //why does the DiceNumber class needs an player array?
            dice= new DiceNumber((Player[]) players.toArray());

            //based on the playerCount the "Spielers" are created and gets put into the players ArrayList
        }
    }

    /**
     * method which implements the game loop
     */
    public void runGame(){
        // TODO Game Loop
        while(gameRun){
            //begin of turn
            //TODO player interaction
            /*
            example of a turn without player interaction
             */
            int diceResult = dice.diceRandomNumber();
            movePlayer(getCurrentPlayer(),diceResult);

            //end of turn
            endOfTurn();
        }
    }

    /**
     * This function is the only method to obtain a reference to the game that is currently running.
     * If there is no game running it creates a new one and makes it accessible.
     * @return Game that is currently running or the new created one.
     */
    public static Game getInstance(){
        if(game == null) {
            game = new Game();
        }
        return game;
    }

    /**
     * This method has to be called before creating a new game. This method sets the player count for the game.
     * @param initialPlayerCount An Integer with the player count.
     */
    public static void initialize(int initialPlayerCount){
        if(!isInitialized){
            playerCount = initialPlayerCount;
            isInitialized = true;
        }
    }

    /**
     * Standard getter for the board.
     * @return Board of the running game.
     */
    public Map getBoard(){
        return board;
    }

    /**
     * This method moves the player to his new position and executes the field action.
     * This method does not take the action of rolling the dice. It has to happen before.
     * @param playerToBeMoved Is the player that is getting moved
     * @param steps Number of fields the player should be moved
     */

    public void movePlayer(Player playerToBeMoved, int steps){
        //Calculating players new position and checking if he made a whole round around the map and is at the start again
        int newPosition = (playerToBeMoved.getPosition() + steps) % board.size();
        if(playerToBeMoved.getPosition()>newPosition){
            //TODO get money for crossing map start = yet to be implemented
        }
        playerToBeMoved.setPosition(newPosition);
        //activates the moveOnField function which is the field action
        board.getField(newPosition).moveOnField(playerToBeMoved);
    }

    /**
     * A getter for the player that is now on the turn. So he is the current player.
     * @return The current player.
     */
    public Player getCurrentPlayer(){
        return players.get(currentPlayer);
    }

    /**
     * method which controls the end of the turn and checks if the game is over
     */
    private void endOfTurn(){
        //TODO check if game has to end
        currentPlayer=++currentPlayer%playerCount;
    }
}


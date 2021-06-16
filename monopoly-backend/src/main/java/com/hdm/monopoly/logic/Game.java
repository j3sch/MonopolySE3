package com.hdm.monopoly.logic;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.board.Map;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class that starts and manages the game, is implemented as a singleton
 */
@Component("game")
public class Game {
    private static final Logger log = LogManager.getLogger(Game.class);

    // These two attributes enable a connection between a map and players
    private final Player[] players; /*we assume the game knows on its creation how many players there are.
    That could be achieved by a controller class that manages the network communication*/
    private final Map map;
    SendMessage sendMessage;
    String[] sessionIds;
    private int currentPlayer = 0;

    @Autowired
    public  Game(Player[] players, Map map, SendMessage sendMessage, String[] sessionIds){
        this.players = players;
        this.map = map;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        //based on the playerCount the Players are created and gets put into the players ArrayList
        log.info("New Object 'Game' created");
    }

    /**
     * Standard getter for the board.
     * @return Board of the running game.
     */
    public Map getMap(){
        return map;
    }

    /**
     * This method moves the player to his new position and executes the field action.
     * This method does not take the action of rolling the dice. It has to happen before.
     * @param steps Number of fields the player should be moved
     */

    public void movePlayer(int steps) {
        //Calculating players new position and checking if he made a whole round around the map and is at the start again
        int newPosition = (getCurrentPlayer().getPosition() + steps) % map.size();
        if (getCurrentPlayer().getPosition() > newPosition && newPosition != 0) {
            getCurrentPlayer().playerGetsMoney(2);
        }
        getCurrentPlayer().setPosition(newPosition);
        log.info(getCurrentPlayer().getName() + "moves to field number: " + newPosition);

        //activates the moveOnField function which is the field action
        map.getField(newPosition).moveOnField(getCurrentPlayer(), sendMessage, sessionIds);
    }


    /**
     * A getter for the player that is now on the turn. So he is the current player.
     * @return The current player.
     */
    public Player getCurrentPlayer(){
        return players[currentPlayer];
    }

    public int getCurrentPlayerIndex() { return currentPlayer; }

    /**
     * method which controls the end of the turn and sets the currentPlayer to the next
     */
    public void endOfTurn(){
        int playerCount = 4;

        log.info(getCurrentPlayer().getName() + " ends his turn");
        sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleBuyEstateBtn", "true" );
        //TODO check if game has to end
        // helper for constructor
        currentPlayer = ++currentPlayer % playerCount;

        log.info("Player " + getCurrentPlayer().getName() + " is on turn");

        sendMessage.sendToAll("/client/highlightPlayer", String.valueOf(getCurrentPlayerIndex()));
        sendMessage.sendToAll("/client/notification", "Player " + getCurrentPlayer().getName() + " is on turn");
        sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false" );
    }
}


package com.hdm.monopoly.backend.board.game_logic;

import com.hdm.monopoly.backend.board.send_message.SendMessage;
import com.hdm.monopoly.backend.board.streets.Map;
import com.hdm.monopoly.backend.player_money.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class that starts and manages the game, is implemented as a singleton
 */
@Component("game")
public class Game {
    private static Logger log = LogManager.getLogger(Game.class);

    private final int PLAYERCOUNT = 4; // helper for constructor

    // These two attributes enable a connection between a map and players
    private Player[] players; /*we assume the game knows on its creation how many players there are.
    That could be achieved by a controller class that manages the network communication*/
    private Map board;
    SendMessage sendMessage;
    String[] sessionIds;
    private int currentPlayer = 0;

    /**
     * Constructor for Game
     * @param players Array with players
     */
    @Autowired
    public  Game(Player[] players, Map map, SendMessage sendMessage, String[] sessionIds){
        this.players = players;
        this.board = map;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        //based on the playerCount the Players are created and gets put into the players ArrayList
        log.debug("New Object 'Game' created");
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
     * @param steps Number of fields the player should be moved
     */


    public void movePlayer(int steps) {
        //Calculating players new position and checking if he made a whole round around the map and is at the start again
        int newPosition = (getCurrentPlayer().getPosition() + steps) % board.size();
        if (getCurrentPlayer().getPosition() > newPosition) {
            //TODO get money for crossing map start = yet to be implemented
        }
        getCurrentPlayer().setPosition(newPosition);
        log.info(getCurrentPlayer().getName() + "moves to field number: " + newPosition);
        //activates the moveOnField function which is the field action

        //TODO empty methods are called
        board.getField(newPosition).moveOnField(getCurrentPlayer(), sendMessage, sessionIds);
    }

    public void teleport(Player playerToBeTeleported, int position) {
        if (position < board.size() && position >= 0) {
            playerToBeTeleported.setPosition(position);
        } else {
            //TODO Error correction
        }
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
        log.info(currentPlayer + " ends his turn");
        //TODO check if game has to end
        currentPlayer = ++currentPlayer % PLAYERCOUNT;
        sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleNextPlayerBtn", "false" );
        sendMessage.sendToAll("/client/notification", "Player " + getCurrentPlayer().getName() + " is on turn");
        sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false" );
    }
}


package com.hdm.monopoly.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.board.Board;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;
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
    private final Board board;
    SendMessage sendMessage;
    String[] sessionIds;
    private int currentPlayer = 0;

    @Autowired
    public  Game(Player[] players, Board board, SendMessage sendMessage, String[] sessionIds){
        this.players = players;
        this.board = board;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        //based on the playerCount the Players are created and gets put into the players ArrayList
        log.info("New Object 'Game' created");
    }

    /**
     * Standard getter for the board.
     * @return Board of the running game.
     */
    public Board getMap(){
        return board;
    }

    /**
     * This method moves the player to his new position and executes the field action.
     * This method does not take the action of rolling the dice. It has to happen before.
     * @param steps Number of fields the player should be moved
     */

    public void movePlayer(int steps) throws JsonProcessingException {
        //Calculating players new position and checking if he made a whole round around the map and is at the start again
        int newPosition = (getCurrentPlayer().getPosition() + steps) % board.size();
        if (getCurrentPlayer().getPosition() > newPosition && newPosition != 0) {
            getCurrentPlayer().playerGetsMoney(2);
        }
        getCurrentPlayer().setPosition(newPosition);
        log.info(getCurrentPlayer().getName() + "moves to field number: " + newPosition);
        sendMessage.sendToAll("/client/playerList", new ObjectMapper().writeValueAsString(players));

        //activates the moveOnField function which is the field action
        board.getField(newPosition).moveOnField(getCurrentPlayer(), sendMessage, sessionIds, board);
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

        if (getCurrentPlayer().getPlayerBankBalance() < 0){
            sendMessage.sendToAll("/client/notification", "Player: " + getCurrentPlayer().getName() + " ran out of money");
            int amountMoney = getCurrentPlayer().getPlayerBankBalance() ;
            int winner = 0;
            for(int i = 0; i <= 3; i++){
                if(players[i].getPlayerBankBalance() >= amountMoney ){
                    amountMoney = players[i].getPlayerBankBalance();
                    winner = i;
                }
            }
            sendMessage.sendToAll("/client/notification", "Player: " + players[winner].getName() + " won the game with an amount of $ " + players[winner].getPlayerBankBalance());
            log.info(currentPlayer + " ran out of money and lost the game");
            log.info( "Player: " + players[winner].getName() + " won the game with an amount of $ " + players[winner].getPlayerBankBalance());
        }else {
            log.info(currentPlayer + " ends his turn");
            sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleBuyEstateBtn", "true");

            currentPlayer = ++currentPlayer % playerCount;

            sendMessage.sendToAll("/client/highlightPlayer", String.valueOf(getCurrentPlayerIndex()));
            sendMessage.sendToAll("/client/notification", "Player " + getCurrentPlayer().getName() + " is on turn");
            sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false");
        }
    }
}


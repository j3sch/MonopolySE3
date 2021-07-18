package com.hdm.monopoly.gameplay;

import com.hdm.monopoly.MonopolyApplication;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import com.hdm.monopoly.utility.ConstantInteger;
import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.board.Board;
import com.hdm.monopoly.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class that starts and manages the game, is implemented as a singleton
 */
@Component
public class Game {
    private static final Logger log = LogManager.getLogger(Game.class);

    private final Player[] players;
    private final Board board;
    SendMessage sendMessage;
    String[] sessionIds;
    private int currentPlayer = 0;
    private final SendPlayerData sendPlayerData;


    @Autowired
    public  Game(Player[] players, Board board, SendMessage sendMessage, String[] sessionIds, SendPlayerData sendPlayerData){
        this.players = players;
        this.board = board;
        this.sendMessage = sendMessage;
        this.sessionIds = sessionIds;
        this.sendPlayerData = sendPlayerData;
        log.info("New Object 'Game' created");
    }

    /**
     * This method moves the player to his new position and executes the field action.
     * This method does not take the action of rolling the dice. It has to happen before.
     * @param steps Number of fields the player should be moved
     */
    protected void movePlayer(int steps) {
        //Calculating players new position and checking if he made a whole round around the map and is at the start again
        int newPosition = (getCurrentPlayer().getPosition() + steps) % board.getBoardSize();
        if (getCurrentPlayer().getPosition() > newPosition && newPosition != 0) {
            getCurrentPlayer().playerGetsMoney(2);
        }
        getCurrentPlayer().setPosition(newPosition);
        log.info(getCurrentPlayer().getName() + " moves to field number: " + newPosition);

        //activates the moveOnField function which is the field action
        board.getField(newPosition).moveOnField(getCurrentPlayer(), sendMessage, sessionIds, board, sendPlayerData);
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
    protected void endOfTurn() {
        if (getCurrentPlayer().getPlayerBankBalance() < 0) {
            sendMessage.sendToAll("/client/notification", "Player: " + getCurrentPlayer().getName() + " ran out of money");
            log.info(getCurrentPlayer().getName() + " ran out of money and lost the game");
            getWinner();
        } else {
            log.info(getCurrentPlayer().getName() + " ends his turn");
            sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleBuyEstateBtn", "true");

            currentPlayer = ++currentPlayer % ConstantInteger.PLAYER_COUNT;

            sendMessage.sendToAll("/client/highlightPlayer", String.valueOf(getCurrentPlayerIndex()));
            sendMessage.sendToAll("/client/notification", "Player " + getCurrentPlayer().getName() + " is on turn");
            sendMessage.sendToPlayer(sessionIds[getCurrentPlayerIndex()], "/client/toggleDiceNumberBtn", "false");
        }
    }

    /**
     * Look which player has the most money, and then name that player as the winner.
     */
    private void getWinner() {
        int amountMoney = getCurrentPlayer().getPlayerBankBalance() ;
        int winner = 0;
        for(int i = 0; i <= 3; i++){
            if(players[i].getPlayerBankBalance() >= amountMoney ){
                amountMoney = players[i].getPlayerBankBalance();
                winner = i;
            }
        }
        sendMessage.sendToAll("/client/notification", "Player: " + players[winner].getName() + " won the game with an amount of $ " + players[winner].getPlayerBankBalance());
        log.info( "Player: " + players[winner].getName() + " won the game with an amount of $ " + players[winner].getPlayerBankBalance());
        log.info("restarting server");
        MonopolyApplication.restart();
    }
}


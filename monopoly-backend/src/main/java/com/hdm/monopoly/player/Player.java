package com.hdm.monopoly.player;

import com.hdm.monopoly.board.Colour;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Player {
    private static final Logger log = LogManager.getLogger(Player.class);

    private int position;
    private final Account account;
    private final String name;
    private final String colour;

    private final int ID;
    private final ArrayList<Colour> ownedColours;
    private int jailTime = 0;

    /**
     * @param ID of the player
     * @param name player has given himself
     * @param colour default colour for every player
     */
    public Player(int ID, String name, String colour) {
        this.account = new Account(31);
        this.name = name;
        this.colour = colour;
        this.ID = ID;
        this.position = 0;
        this.ownedColours = new ArrayList<>();
        log.debug("New Object 'Player' created. Name: " + name);
    }

    /**
     * @return the players ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param position set new position after dicing a number
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return get current position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @return the players name
     */
    public String getName() {
        return name;
    }

    /**
     * @return save colours of the streets to maybe double the rent price
     */
    public ArrayList<Colour> getOwnedColors() {
        return ownedColours;
    }

    /**
     * @return this players colour to visualize on the board
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param amount money the players gets
     *               if he gets rent or money from EventFields, FreeParking or Go
     */
    public void playerGetsMoney(int amount){
        account.getMoney(amount);
        log.info(name + " new bank balance: " + account.getBankBalance());
    }

    /**
     * @param amount money the player had to pay
     *               if he has to pay rent, bought a street or from EventField
     */
    public void playerPaysMoney(int amount){
        account.payMoney(amount);
        log.info(name + " new bank balance: " + account.getBankBalance());
    }

    /**
     * @return money, the player has right now
     */
    public int getPlayerBankBalance() {
        return account.getBankBalance();
    }

    /**
     * @return jailTime, how many rounds the player has to stay in Jail
     */
    public int getJailTime(){
        return jailTime;
    }

    /**
     * to be called when the player gets arrested
     */
    public void setArrested(){
        jailTime = 3;
        log.info(name + " get arrested");
    }

    /**
     * used if player is in Jail
     */
    public void reduceJailTime(){
        --jailTime;
    }

    /**
     * if JailTime is over, player gets released
     */
    public void setReleased(){
        jailTime = 0;
        log.info(name + " get released from jail");
    }

    public void addStreet(Colour toBeAdded){
        ownedColours.add(toBeAdded);
    }
}

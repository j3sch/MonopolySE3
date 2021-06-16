package com.hdm.monopoly.backend.player_money;


import com.hdm.monopoly.backend.board.send_message.Notified;
import com.hdm.monopoly.backend.board.streets.Street;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Player {
    private static Logger log = LogManager.getLogger(Player.class);

    private int position;
    private Account account;
    private String name;
    private String colour;
    private int ID;
    private Player previousPlayer;
    private ArrayList<Street> ownedStreets;
    private int jailTime = 0;

    public Player(int ID, String name, String colour) {

        this.account = new Account(31);
        this.name = name;
        this.colour = colour;
        this.ID = ID;
        this.position = 0;
        this.ownedStreets = new ArrayList<>();
        log.debug("New Object 'Player' created. Name: " + name);
    }

    public int getID() {
        return ID;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Street> getOwnedStreets() {
        return ownedStreets;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    //Nach jeder Änderung des Kontostandes wird der neue Kontostand über das return statement zurückgegeben.
    //Falls wir später eine Anzeige des Kontostandes haben, kann man die Anzeige mit den Returnstatments aktualisieren

    public int PlayerGetsMoney(int amount){
        account.getMoney(amount);
        log.info(name + " new bank balance: " + account.getBankBalance());
        return account.getBankBalance();
    }

    public int PlayerPaysMoney(int amount){
        account.payMoney(amount);
        log.info(name + " new bank balance: " + account.getBankBalance());
        return account.getBankBalance();
    }

    public int getPlayerBankBalance() {
        return account.getBankBalance();
    }

    public int getJailTime(){
        return jailTime;
    }

    /**
     * to be called when the player gets arrested
     */
    public void getArrested(){
        jailTime = 3;
        log.info(name + " get arrested");
    }

    public void jailed(){
        --jailTime;
    }

    public void getReleased(){
        jailTime = 0;
        log.info(name + " get released from jail");
    }

    public void addStreet(Street toBeAdded){
        ownedStreets.add(toBeAdded);
    }
}

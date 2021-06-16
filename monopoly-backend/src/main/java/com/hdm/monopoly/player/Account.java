package com.hdm.monopoly.player;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account {
    private static final Logger log = LogManager.getLogger(Account.class);

    private int bankBalance;


    public Account(int bankBalance) {
        this.bankBalance = bankBalance;
        log.info("New Object 'Account' created");
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }

    public void getMoney(int amount){
        setBankBalance(getBankBalance() + amount);
    };

    public void payMoney(int amount){
        setBankBalance(getBankBalance() - amount);
    };

}

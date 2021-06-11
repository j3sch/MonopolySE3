package com.hdm.monopoly.player_money;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Account {
    private static Logger log = LogManager.getLogger(Account.class);

    private int BankBalance;


    public Account(int BankBalance) {
        this.BankBalance = BankBalance;
        log.debug("New Object 'Account' created");
    }

    public int getBankBalance() {
        return BankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.BankBalance = bankBalance;
    }

    public void getMoney(int amount){

        setBankBalance(getBankBalance()+ amount);
    };

    public void payMoney(int amount){
        setBankBalance(getBankBalance()-amount);
    };

}

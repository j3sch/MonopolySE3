package com.hdm.monopoly.backend.player_money;

public class Account {

    private int BankBalance;


    public Account(int BankBalance) {
        this.BankBalance = BankBalance;
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

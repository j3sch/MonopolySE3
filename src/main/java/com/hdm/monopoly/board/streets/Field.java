package com.hdm.monopoly.board.streets;

import com.hdm.monopoly.board.send_message.SendMessage;
import com.hdm.monopoly.player_money.Player;

public interface Field {
    public void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds); //function that defines if the player on the field steps on it

    public String getFieldName();
}

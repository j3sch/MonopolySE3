package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;

public interface Field {
    void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board); //function that defines if the player on the field steps on it

    String getFieldName();
}

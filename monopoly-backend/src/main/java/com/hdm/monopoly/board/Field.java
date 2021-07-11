package com.hdm.monopoly.board;

import com.hdm.monopoly.sendmessage.SendMessage;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendPlayerData;

public interface Field {
    void moveOnField(Player player, SendMessage sendMessage, String[] SessionIds, Board board, SendPlayerData sendPlayerData); //function that defines if the player on the field steps on it

    String getFieldName();
}

package com.hdm.monopoly.backend.board.streets;

import com.hdm.monopoly.backend.player_money.Player;

public interface Field {
    public void moveOnField(Player player); //function that defines if the player on the field steps on it

    public String getFieldName();
}

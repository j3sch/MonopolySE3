package com.hdm.monopoly.di;

import com.hdm.monopoly.logic.Game;
import com.hdm.monopoly.sendmessage.ActivateButton;
import com.hdm.monopoly.sendmessage.Notified;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import com.hdm.monopoly.board.Map;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hdm.monopoly")
public class GameConfig {

    private final Player[] players = new Player[4];
    private final String[] sessionIds = new String[4];
    private final SendMessage sendMessage = new SendMessage();
    private final Map map = new Map();
    private final Game game = new Game(players, map, sendMessage, sessionIds);

    //to send data to client
    SendPlayerData sendPlayerData = new SendPlayerData(sendMessage, players);
    Notified notified = new Notified(sendMessage, sessionIds, game);
    ActivateButton activateButton = new ActivateButton(sendMessage, sessionIds, game);

    @Bean
    public Game getGame(){
        return game;
    }

    @Bean
    public Player[] getPlayers() {
        return players;
    }

    @Bean
    public Map getMap() {
        return map;
    }

    @Bean
    public String[] getSessionIds() {
        return sessionIds;
    }

    @Bean
    public SendMessage getSendMessage() {
        return sendMessage;
    }

    //to send data to client
    @Bean
    public SendPlayerData getSendPlayerData() {
        return sendPlayerData;
    }

    @Bean
    public Notified getNotified() {
        return notified;
    }

    @Bean
    public ActivateButton getActivateButton() {
        return activateButton;
    }
}


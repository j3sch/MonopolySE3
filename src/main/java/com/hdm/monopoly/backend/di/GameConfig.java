package com.hdm.monopoly.backend.di;

import com.hdm.monopoly.backend.board.game_logic.Game;
import com.hdm.monopoly.backend.board.streets.Map;
import com.hdm.monopoly.backend.player_money.Player;
import com.hdm.monopoly.backend.player_money.SendMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hdm.monopoly.backend")
public class GameConfig {

    private final Player[] players = new Player[4];
    private final Map map = new Map();
    private final String[] sessionIds = new String[4];
    private final SendMessage sendMessage = new SendMessage();
    private final Game game = new Game(players, map);

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
    public SendMessage getSendMessage() { return sendMessage; }
}


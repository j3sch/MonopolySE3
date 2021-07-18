package com.hdm.monopoly.di;

import com.hdm.monopoly.gameplay.Game;
import com.hdm.monopoly.gameplay.Countdown;
import com.hdm.monopoly.sendmessage.Notify;
import com.hdm.monopoly.sendmessage.SendPlayerData;
import com.hdm.monopoly.board.Board;
import com.hdm.monopoly.player.Player;
import com.hdm.monopoly.sendmessage.SendMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.hdm.monopoly")
public class GameConfig {

    private final Player[] players = new Player[4];
    private final String[] sessionIds = new String[4];
    private final SendMessage sendMessage = new SendMessage();
    private final Board board = new Board();
    private final SendPlayerData sendPlayerData = new SendPlayerData(sendMessage, players);
    private final Game game = new Game(players, board, sendMessage, sessionIds, sendPlayerData);
    private final Notify notify = new Notify(sendMessage, sessionIds, game);

    @Bean
    public Game getGame(){
        return game;
    }

    @Bean
    public Player[] getPlayers() {
        return players;
    }

    @Bean
    public Board getBoard() {
        return board;
    }

    @Bean
    public String[] getSessionIds() {
        return sessionIds;
    }

    @Bean
    public SendMessage getSendMessage() {
        return sendMessage;
    }

    @Bean
    public SendPlayerData getSendPlayerData() {
        return sendPlayerData;
    }

    @Bean
    public Notify getNotify() {
        return notify;
    }

    @Bean
    public Countdown getCountdown() { return new Countdown(); }
}


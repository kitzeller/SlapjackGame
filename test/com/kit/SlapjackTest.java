package com.kit;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SlapjackTest {


    @Test
    public void play() throws Exception {
        List<String> jacks = new ArrayList<>();
        jacks.add("Jack");
        int players = 2;
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            playerList.add(new Player(i));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(("x\nx\nx\nx\nz\nx\nz\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx" +
                "\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx" +
                "\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\n").getBytes());

        System.setIn(in);

        Slapjack game = new Slapjack(players, playerList, jacks);
        assertEquals(game.play(), true);

    }

    @Test
    public void playCustomJacks() throws Exception {
        List<String> jacks = new ArrayList<>();
        jacks.add("Jack");
        jacks.add("Queen");
        jacks.add("King");
        int players = 2;
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            playerList.add(new Player(i));
        }

        ByteArrayInputStream in = new ByteArrayInputStream(("x\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx" +
                "\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx" +
                "\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\n").getBytes());

        System.setIn(in);

        Slapjack game = new Slapjack(players, playerList, jacks);
        assertEquals(game.play(), true);
    }

    @Test
    public void playThreePlayersAI() throws Exception {
        List<String> jacks = new ArrayList<>();
        jacks.add("Jack");
        int players = 3;
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < players; i++) {
            playerList.add(new Player(i));
        }


        ByteArrayInputStream in = new ByteArrayInputStream(("x\nx\nx\nx\nz\nx\nz\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx" +
                "\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx" +
                "\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nx\nyes").getBytes());

        System.setIn(in);

        Slapjack game = new Slapjack(players, playerList, jacks);
        assertEquals(game.play(), true);
    }

}
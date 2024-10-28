package com.bptn.ptg.performancetracker;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PTGTest {
	
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_BOLD = "\033[0;1m";
	
	private Player player;
	//private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	public void createPlayer() {
		//Initialize a test player
		player = new Forward("testPlayer", 20, "testTeam", 150, 160, "forward");
		player.setGamesPlayed(100);
		((Forward) player).setGoalCount(100);
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	@Test
	public void testHeightUpdate() {
		//test if uodating a player's height is accurate
		player.setWeight(170);
		assertTrue(player.getWeight() == 170);
	}
	
	@Test
	public void testStatOutput() {
		LogStats logStats = new LogStats();
		logStats.printStats(player);
		assertEquals("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Stats for testPlayer: " + ANSI_RESET + "\n" + ANSI_BOLD + "Games Played: " + ANSI_RESET + " 100\n" + 
				ANSI_BOLD + "Average Goals: " + ANSI_RESET + " 1.00\n" + ANSI_BOLD + "Average Shots: " + ANSI_RESET + " 0.00\n" , outputStreamCaptor.toString());
	}
}

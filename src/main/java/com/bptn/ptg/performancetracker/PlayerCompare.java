package com.bptn.ptg.performancetracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PlayerCompare {
	
	private List<Player> players;
	private Map<String, Player> playerMap;
	
	//Constant Variables To Format Output
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_BOLD = "\033[0;1m";
	
	//Class Constructor
	public PlayerCompare() {
		try {
			this.players = PlayerFiles.loadPlayer();
			//use a map to link player name to player, makes retrieving players by name easier
			if(this.players.isEmpty()) {
				throw new IOException();
			}
			this.playerMap = new HashMap<>();
			for(Player p : players) {
				playerMap.put(p.getName(), p);
			}
		}
		catch (IOException e) {
			this.players = new ArrayList<>();
			this.playerMap = new HashMap<>();
		}
	}
	//Method that outputs a message based on who has better stats for Forward objects
	public void compareMessageForward(Player p1, Player p2) {
		if(p1 instanceof Forward && p2 instanceof Forward) {
			if(((Forward) p1).getGoals() > ((Forward) p2).getGoals()) {
				System.out.println(ANSI_BOLD + p1.getName() + " has scored more goals." + ANSI_RESET);
			}
			else if(((Forward) p1).getGoals() < ((Forward) p2).getGoals()) {
				System.out.println(ANSI_BOLD + p2.getName() + " has scored more goals." + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_BOLD + p1.getName()+ " and " + p2.getName() + " has scored the same amount of goals." + ANSI_RESET);
			}
			if(((Forward) p1).getShots() > ((Forward) p2).getShots()) {
				System.out.println(ANSI_BOLD + p1.getName() + " has taken more shots." + ANSI_RESET);
			}
			else if(((Forward) p1).getShots() < ((Forward) p2).getShots()) {
				System.out.println(ANSI_BOLD + p2.getName() + " has taken more shots." + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_BOLD + p1.getName()+ " and " + p2.getName() + " has taken the same amount of shots." + ANSI_RESET);
			}
		}
	}
	
	//Method that outputs a message based on who has better stats for Forward objects
	public void compareMessageMidFielder(Player p1, Player p2) {
		if(p1 instanceof MidFielder && p2 instanceof MidFielder) {
			if(((MidFielder) p1).getAssist() > ((MidFielder) p2).getAssist()) {
				System.out.println(ANSI_BOLD + p1.getName() + " has made more assists." + ANSI_RESET);
			}
			else if(((MidFielder) p1).getAssist() < ((MidFielder) p2).getAssist()) {
				System.out.println(ANSI_BOLD + p2.getName() + " has made more assists." + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_BOLD + p1.getName()+ " and " + p2.getName() + " has made the same amount of assists." + ANSI_RESET);
			}
			if(((MidFielder) p1).getPasses() > ((MidFielder) p2).getPasses()) {
				System.out.println(ANSI_BOLD + p1.getName() + " has made more passes." + ANSI_RESET);
			}
			else if(((MidFielder) p1).getPasses() < ((MidFielder) p2).getPasses()) {
				System.out.println(ANSI_BOLD + p2.getName() + " has made more passes." + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_BOLD + p1.getName()+ " and " + p2.getName() + " has made the same amount of passes." + ANSI_RESET);
			}
		}
	}
	
	//Method that outputs a message based on who has better stats for Forward objects
	public void compareMessageDefender(Player p1, Player p2) {
		if(p1 instanceof Defender && p2 instanceof Defender) {
			if(((Defender) p1).getTackles() > ((Defender) p2).getTackles()) {
				System.out.println(ANSI_BOLD + p1.getName() + " has made more tackles." + ANSI_RESET);
			}
			else if(((Defender) p1).getTackles() < ((Defender) p2).getTackles()) {
				System.out.println(ANSI_BOLD + p2.getName() + " has made more tackles." + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_BOLD + p1.getName()+ " and " + p2.getName() + " has made the same amount of tackles." + ANSI_RESET);
			}
			if(((Defender) p1).getFouls() > ((Defender) p2).getFouls()) {
				System.out.println(ANSI_BOLD + p1.getName() + " has made more fouls." + ANSI_RESET);
			}
			else if(((Defender) p1).getFouls() < ((Defender) p2).getFouls()) {
				System.out.println(ANSI_BOLD + p2.getName() + " has made more fouls." + ANSI_RESET);
			}
			else {
				System.out.println(ANSI_BOLD + p1.getName()+ " and " + p2.getName() + " has made the same amount of fouls." + ANSI_RESET);
			}
		}
	}
	
	//Method to do the actual player comparison
	public void compareTwoPlayers(Player p1, Player p2) {
		//Check for invalid players
		if(p1 == null || p2 == null) {
			System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for one or more player, please try again!" + ANSI_RESET);
		}
		
		System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Comparing "+ p1.getName() + " and "+ p2.getName() + ANSI_RESET);
		System.out.println(ANSI_BOLD + "Games Played: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN + p1.getGamesPlayed() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN +p2.getGamesPlayed() + ANSI_RESET);
		if(p1.getGamesPlayed() == 0 || p2.getGamesPlayed() == 0) {
			System.out.println("One or more players has not played a game yet, nothing to compare at the moment");
			return;
		}
		if(p1 instanceof Forward && p2 instanceof Forward) {
			System.out.println(ANSI_BOLD + "Goals Scored: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN +((Forward) p1).getGoals() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN + ((Forward) p2).getGoals() + ANSI_RESET);
			System.out.println(ANSI_BOLD + "Shots On Target: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN + ((Forward) p1).getShots() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN + ((Forward) p2).getShots() + ANSI_RESET);
			compareMessageForward(p1,p2);
		}
		else if(p1 instanceof MidFielder && p2 instanceof MidFielder) {
			System.out.println(ANSI_BOLD + "Assists Made: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN +((MidFielder) p1).getAssist() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN +((MidFielder) p2).getAssist() + ANSI_RESET);
			System.out.println(ANSI_BOLD + "Passes Completed: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN + ((MidFielder) p1).getPasses() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN +((MidFielder) p2).getPasses() + ANSI_RESET);
			compareMessageMidFielder(p1,p2);
		}
		else if(p1 instanceof Defender && p2 instanceof Defender) {
			System.out.println(ANSI_BOLD + "Tackles Made: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN +((Defender) p1).getTackles() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN + ((Defender) p2).getTackles() + ANSI_RESET);
			System.out.println(ANSI_BOLD + "Fouls Conceded: " + ANSI_RESET + " " + p1.getName() + "=" + ANSI_GREEN +((Defender) p1).getFouls() + ANSI_RESET + ", " + p2.getName() + "=" + ANSI_GREEN +((Defender) p2).getFouls() + ANSI_RESET);
			compareMessageDefender(p1,p2);
		}
	}
	
	//Method for Player Compare menu
	public void compareMenu() {
		PlayerCompare myCompare = new PlayerCompare();
		
		//Check if there is existing player data
		if(players.isEmpty()) {
			System.out.println("There is no saved player data yet, please add player details!");
			System.out.println("\nReturning to previous menu...\n");
			return;
		}
		
		//Open Scanner
		Scanner myScan = new Scanner(System.in);
		
		//Prompt for user input
		System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Please select two players of the same position from the following list you would like to compare, or q to return: \n" + ANSI_RESET);
		for(String name : playerMap.keySet()) {
			System.out.println(name);
		}
		
		boolean loopGuard1 = true;
		boolean loopGuard2 = true;
		
		Player p1 = null;
		Player p2 = null;
		
		//begin loop for user input
		while(loopGuard1) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the first player's name from above list to compare, or q to return: " + ANSI_RESET);
			String myPlayerName = myScan.nextLine();
			System.out.println("\n");
			//check if the user wants to return to previous menu
			if(myPlayerName.equalsIgnoreCase("q")) {
				return;
			}
			//iterate through list of players to search for inputed player
			for(Player p : players) {
				//if the player exist, call the function to print the players details
				if(p.getName().equalsIgnoreCase(myPlayerName)) {
					p1 = p;
					loopGuard1 = false;
					break;
				}
			}
			//if updateHeight() is never called, display an error for an invalid player choice
			if(loopGuard1 == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
		}
		
		//variable to check if there is no other player in the same position as the first choice player
		int invalidPlayerCount = 0;
		
		//begin loop for user input
		while(loopGuard2) {
			for(String name: playerMap.keySet()) {
				if(playerMap.get(name).getPosition().equalsIgnoreCase(p1.getPosition())) {
					if(playerMap.get(name).getName().equalsIgnoreCase(p1.getName())) {
						continue;
					}
					System.out.println(name);
				}
				else {
					invalidPlayerCount++;
				}
			}
			//Check if there is no other player with the same position left as p1
			if(invalidPlayerCount == (playerMap.size() - 1) ) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "There is no other saved player of the same position, please try again!" + ANSI_RESET);
				return;
			}
			
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the second player's name from above list to compare, or q to return: " + ANSI_RESET);
			String myPlayerName = myScan.nextLine();
			System.out.println("\n");
			//check if the user wants to return to previous menu
			if(myPlayerName.equalsIgnoreCase("q")) {
				return;
			}
			//iterate through list of players to search for inputed player
			for(Player p : players) {
				//if the player exist, call the function to print the players details
				if(p.getName().equalsIgnoreCase(myPlayerName)) {
					p2 = p;
					loopGuard2 = false;
					break;
				}
			}
			//if updateHeight() is never called, display an error for an invalid player choice
			if(loopGuard2 == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
		}
		
		//Call method to start player comparison
		compareTwoPlayers(p1,p2);
	}
		
}

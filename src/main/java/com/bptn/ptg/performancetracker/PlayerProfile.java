package com.bptn.ptg.performancetracker;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class PlayerProfile {
	
	private List<Player> players;
	
	//Constant Variables To Format Output
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_BOLD = "\033[0;1m";
	
	//Class Constructor
	public PlayerProfile() {
		this.players = PlayerFiles.loadPlayer();
		if(this.players.isEmpty()) {
			this.players = new ArrayList<>();
		}
	}
	
	
	//Method To Add New Players
	public void addPlayer() {
		
		//Create a variable that will hold a player instance
		Player newPlayer = null;
		
		//Start a loop as we take in user input
		do {
			//Open scanner
			Scanner myScan = new Scanner(System.in);
			
			try {
				//prompt for user's name
				System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter player's name, or type q to return: " + ANSI_RESET);
				String name = myScan.nextLine();
				
				if(name.charAt(0) >= 48 && name.charAt(0) <= 57) {
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid name, try again!" + ANSI_RESET);
					continue;
				}
				
				if(name.equalsIgnoreCase("q")) {
					myScan.close();
					return;
				}
				
				//prompt for user's age
				System.out.println("\nEnter player's age: ");
				int age = myScan.nextInt();
				myScan.nextLine();
				
				//prompt for user's team name
				System.out.println("\nEnter player's team name: ");
				String teamName = myScan.nextLine();
				if(name.charAt(0) >= 48 && name.charAt(0) <= 57) {
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid team name, try again!" + ANSI_RESET);
					continue;
				}
				
				//prompt for user's height
				System.out.println("\nEnter player's height (in cm): ");
				double height = myScan.nextDouble();
				
				//prompt for user's weight
				System.out.println("\nEnter player's weight (in lbs): ");
				double weight = myScan.nextDouble();
				myScan.nextLine();
				
				//prompt for user's position
				System.out.println("\nEnter player's position (options are forward, midfielder, defender): ");
				String position = myScan.nextLine();
				
				//Check for valid position inputs
				if(position.equalsIgnoreCase("forward")) {
					newPlayer = new Forward(name, age, teamName, height, weight, position);
					//add new player to player list
					players.add(newPlayer);
					PlayerFiles.writePlayer(players);
					System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_GREEN + "Player added successfully!\n" + ANSI_RESET);
					System.out.println(ANSI_BOLD + "Returning to previous menu...\n" + ANSI_BOLD);
					break;
				}
				else if(position.equalsIgnoreCase("midfielder")) {
					newPlayer = new MidFielder(name, age, teamName, height, weight, position);
					//add new player to player list
					players.add(newPlayer);
					PlayerFiles.writePlayer(players);
					System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_GREEN + "Player added successfully!\n" + ANSI_RESET);
					System.out.println(ANSI_BOLD + "Returning to previous menu...\n" + ANSI_BOLD);
					break;
				}
				else if(position.equalsIgnoreCase("defender")) {
					newPlayer = new Defender(name, age, teamName, height, weight, position);
					//add new player to player list
					players.add(newPlayer);
					PlayerFiles.writePlayer(players);
					System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_GREEN + "Player added successfully!\n" + ANSI_RESET);
					System.out.println(ANSI_BOLD + "Returning to previous menu...\n" + ANSI_BOLD);
					break;
				}
				else {
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid position, try again!" + ANSI_RESET);
				}
			}
			catch(InputMismatchException e) {
				//Error Message If Input Is Not An Integer
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please try again!" + ANSI_RESET);
				myScan.reset();
				myScan.next();
				continue;
			}
			catch(Exception e) {
				//Error Message If Input Is Invalid
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please try again!" + ANSI_RESET);
				continue;
			}

			//close scanner
			myScan.close();
		}while(newPlayer == null);		//loop while newPlayer instance is null
	}
	
	public void printPlayerDetails(Player player) {
		
		//Print the details of the passed player
		System.out.println(ANSI_BOLD + "Player's name: " + ANSI_RESET + player.getName().toUpperCase());
		System.out.println(ANSI_BOLD + "Player's age: " + ANSI_RESET + player.getAge());
		System.out.println(ANSI_BOLD + "Player's team: " +ANSI_RESET + player.getTeamName().toUpperCase());
		System.out.println(ANSI_BOLD + "Player's height: " + ANSI_RESET + player.getHeight());
		System.out.println(ANSI_BOLD + "Player's weight: " + ANSI_RESET + player.getWeight());
		System.out.println(ANSI_BOLD +"Player's position: " + ANSI_RESET + player.getPosition().toUpperCase());
	}
	
	public void viewDetails(List<Player> players) {
		Scanner myScan = new Scanner(System.in);
		
		//Validate that there are players to view
		if(players.isEmpty()) {
			System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "No saved players yet, please add a player" + ANSI_RESET);
			return;
		}
		
		//Iterate and print available players you can view
		for(Player p : players) {
			System.out.println(p.getName());
		}
		//loop guard condition
		boolean loopGuard = true;
		
		//begin loop for user input
		while(loopGuard) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the player's name to view details, or q to return: " + ANSI_RESET);
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
					printPlayerDetails(p);
					loopGuard = false;
					break;
				}
			}
			//if we never called the printPlayerDetails() method, display an error for an invalid player choice
			if(loopGuard == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
		}
	}
		
	
	
	//Method to navigate sub-menu
	public void playerMenu() {
		
		PlayerProfile profile = new PlayerProfile();
		
		//Variable To Store User Input
		int choice = 0;
		
		//Open Scanner
		Scanner myScan = new Scanner(System.in);
		
		//Menu Loop
		do {
			//Menu Options
			System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Please select a following option: \n" + ANSI_RESET);
			System.out.println(ANSI_BOLD + "1. " + ANSI_RESET + "Create a new player");
			System.out.println(ANSI_BOLD + "2. " + ANSI_RESET + "View player details");
			System.out.println(ANSI_BOLD + "3. " + ANSI_RESET + "Return to menu\n");
			
			//Validate User Input
				if (myScan.hasNextInt()) {
					choice = myScan.nextInt();
					myScan.nextLine();
					if(choice < 1 || choice > 3) {
						System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 3!" + ANSI_RESET);
						continue;
					}
				}
				else {
					myScan.next();
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 3!" + ANSI_RESET);
					continue;
				}
				
				
				switch(choice) {
					case 1:
						//case for create a new player
						profile.addPlayer();
						break;
					case 2:
						//case for view player details
						profile.viewDetails(players);
						break;
					case 3:
						//case for return to previous menu
						System.out.println(ANSI_BOLD + "\nReturning to previous menu...\n" + ANSI_BOLD);
						return;
				}
		}while(choice != 3);
		myScan.close();
		
	}
	
}
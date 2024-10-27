package com.bptn.ptg.performancetracker;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class LogStats {
	
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
	public LogStats() {
		//TO-DO: not finished
		this.players = PlayerFiles.loadPlayer();
		if(this.players.isEmpty()) {
			this.players = new ArrayList<>();
		}
	}
	
	public void addStats(Player p) {
		Scanner myScan = new Scanner(System.in);
		
		boolean loopGuard = true;
		int games = 0;
		int goals = 0;
		int shots = 0;
		int assist = 0;
		int pass = 0;
		int tackle = 0;
		int foul = 0;
		
		while(loopGuard) {
			try {
				//prompt user to update gamesPlayed
				System.out.println("How many new games have you played? Enter an integer number: ");
				games = myScan.nextInt();
				if (games < 0) {
					throw new IllegalArgumentException("Your choice is out of range!");
				}
				
				if(p instanceof Forward) {
					System.out.println("\nHow many goals have you scored? Enter an integer number: ");
					goals = myScan.nextInt();
					if (goals < 0) {
						throw new IllegalArgumentException("Your choice is out of range!");
					}

					System.out.println("\nHow many shots did you attempt? Enter an integer number: ");
					shots = myScan.nextInt();
					if (shots < 0) {
						throw new IllegalArgumentException("Your choice is out of range!");
					}
				}
				else if(p instanceof MidFielder) {
					System.out.println("\nHow many assists have you made? Enter an integer number: ");
					assist = myScan.nextInt();
					if (assist < 0) {
						throw new IllegalArgumentException("Your choice is out of range!");
					}
					
					System.out.println("\nHow many passes have you made? Enter an integer number: ");
					pass = myScan.nextInt();
					if (pass < 0) {
						throw new IllegalArgumentException("Your choice is out of range!");
					}
				}
				else if(p instanceof Defender) {
					System.out.println("\nHow many tackles have you made? Enter an integer number: ");
					tackle = myScan.nextInt();
					if (tackle < 0) {
						throw new IllegalArgumentException("Your choice is out of range!");
					}
					
					System.out.println("\nHow many fouls did you conced? Enter an integer number: ");
					foul = myScan.nextInt();
					if (foul < 0) {
						throw new IllegalArgumentException("Your choice is out of range!");
					}
				}
				
			}catch(InputMismatchException e) {
				//Error Message If Input Is Not An Integer
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter an integer number!" + ANSI_RESET);
				myScan.reset();
				myScan.next();
				continue;
			}
			catch(IllegalArgumentException e) {
				//Error Message If User Input Is Out Of Range
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter an integer number!" + ANSI_RESET);
				continue;
			}
			catch(Exception e) {
				//Error Message If Input Is Not An Integer
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter an integer number!" + ANSI_RESET);
				continue;
			}
			p.setGamesPlayed(games);
			if(p instanceof Forward) {
				((Forward) p).setGoalCount(goals);
				((Forward) p).setShotOnTarget(shots);
			}
			else if(p instanceof MidFielder) {
				((MidFielder) p).setAssistCount(assist);
				((MidFielder) p).setPassesCompleted(pass);
			}
			else if(p instanceof Defender) {
				((Defender) p).setTacklesMade(tackle);
				((Defender) p).setFoulsConceded(foul);
			}
			loopGuard = false;
		}
		System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_GREEN + "Stats added successfully!\n" + ANSI_RESET);
	}
	
	public void playerStats() {
		Scanner myScan = new Scanner(System.in);
		
		//Validate that there are players to view
		if(players.isEmpty()) {
			System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "No saved players yet, please go back and add a player" + ANSI_RESET);
			return;
		}
		
		
		//Iterate and print available players you can view
		System.out.print("\n");
		for(Player p : players) {
			System.out.println(p.getName());
		}
		
		//loop guard condition
		boolean loopGuard = true;
		
		while(loopGuard) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the player's name to add stats, or q to return: " + ANSI_RESET);
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
					addStats(p);
					loopGuard = false;
					break;
				}
			}
			//if we never called the addStats() method, display an error for an invalid player choice
			if(loopGuard == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
			PlayerFiles.writePlayer(players);
		}
	}
	
	public void printStats(Player p) {
		System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Stats for " + p.getName() + ": " + ANSI_RESET);
		System.out.println(ANSI_BOLD + "Games Played: " + ANSI_RESET + "\n " + p.getGamesPlayed());
		if(p instanceof Forward) {
			System.out.print(ANSI_BOLD + "Average Goals: " + ANSI_RESET);
			System.out.printf(" %.2f\n", ((Forward) p).getAverageGoals());
			System.out.print(ANSI_BOLD + "Average Shots: " + ANSI_RESET);
			System.out.printf(" %.2f\n", ((Forward) p).getAverageShots());
		}
		else if(p instanceof MidFielder) {
			System.out.println(ANSI_BOLD + "Average Assists: " + ANSI_RESET);
			System.out.printf(" %.2f\n", ((MidFielder) p).getAverageAssists());
			System.out.println(ANSI_BOLD + "Average Passes: " + ANSI_RESET);
			System.out.printf(" %.2f\n", ((MidFielder) p).getAveragePasses());
		}
		else if(p instanceof Defender) {
			System.out.println(ANSI_BOLD + "Average Tackles: " + ANSI_RESET);
			System.out.printf(" %.2f\n", ((Defender) p).getAverageTackles());
			System.out.println(ANSI_BOLD + "Average Fouls: " + ANSI_RESET + ((Defender) p).getAverageFouls());
			System.out.printf(" %.2f\n", ((Defender) p).getAverageFouls());
		}
	}
	
	public void viewStats() {
		Scanner myScan = new Scanner(System.in);
		
		//Validate that there are players to view
		if(players.isEmpty()) {
			System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "No saved players yet, please go back and add a player" + ANSI_RESET);
			return;
		}
		
		//Iterate and print available players you can view
		System.out.print("\n");
		for(Player p : players) {
			System.out.println(p.getName());
		}
		
		//loop guard condition
		boolean loopGuard = true;
		
		while(loopGuard) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the player's name to add stats, or q to return: " + ANSI_RESET);
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
					printStats(p);
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
	
	
	public void logMenu() {
		
		LogStats logger = new LogStats();
		
		//Variable to store user input
		int choice = 0; 
		
		//Open Scanner
				Scanner myScan = new Scanner(System.in);
		
		//Menu Loop		
		do {
			//Menu Options
			System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Please select a following option: \n" + ANSI_RESET);
			System.out.println(ANSI_BOLD + "1. " + ANSI_RESET + "Update an existing player's stats");
			System.out.println(ANSI_BOLD + "2. " + ANSI_RESET + "View existing player stats");
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
					//case for adding new stats
					logger.playerStats();
					break;
				case 2:
					//case for view player stats
					logger.viewStats();
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
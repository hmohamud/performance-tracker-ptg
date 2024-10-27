package com.bptn.ptg.performancetracker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BMI {
	
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
	
	//Prompt user to choose their save file
	//Have an Option to either update height or weight
	//Another option to calculate BMI
	
	//Class Constructor
	public BMI() {
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
	
	//Method to navigate players and send them to update weight
	public void bmiWeight() {
		Scanner myScan = new Scanner(System.in);
		
		//List player data for user to choose
		System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Select a user to update their weight: " + ANSI_RESET);
		for(String name : playerMap.keySet()) {
			System.out.println(name);
		}
		
		//loop guard condition
		boolean loopGuard = true;
		
		//begin loop for user input
		while(loopGuard) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the player's name to update weight, or q to return: " + ANSI_RESET);
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
					updateWeight(p);
					loopGuard = false;
					break;
				}
			}
			//if updateWeight() is never called, display an error for an invalid player choice
			if(loopGuard == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
		}
	}
	
	
	//Method to navigate players and send them to update height
	public void bmiHeight() {
		Scanner myScan = new Scanner(System.in);
		
		//List player data for user to choose
		System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Select a user to update their height: " + ANSI_RESET);
		for(String name : playerMap.keySet()) {
			System.out.println(name);
		}
		
		//loop guard condition
		boolean loopGuard = true;
				
		//begin loop for user input
		while(loopGuard) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the player's name to update height, or q to return: " + ANSI_RESET);
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
					updateHeight(p);
					loopGuard = false;
					return;
				}
			}
			//if updateHeight() is never called, display an error for an invalid player choice
			if(loopGuard == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
		}
	}
	
	//Method to update user's weight
	public void updateWeight(Player p) {
		Scanner myScan = new Scanner(System.in);
		
		double weight = 0;
		boolean loopGuard = true;
		
		do {
			System.out.println("Please update your weight for our data, if applicable:.");
			System.out.println("New Weight (in lbs) : ");
			//Validate User Input
			if (myScan.hasNextDouble()) {
				weight = myScan.nextDouble();
				myScan.nextLine();
				if(weight < 1) {
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number to represent your weight in lbs!\n" + ANSI_RESET);
					continue;
				}
			}
			else {
				myScan.next();
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number to represent your weight in lbs!\n" + ANSI_RESET);
				continue;
			}
			loopGuard = false;
		}while(loopGuard);
		
		p.setWeight(weight);
		PlayerFiles.writePlayer(players);
		System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_GREEN + "Weight updated successfully!\n" + ANSI_RESET);
	}
	
	//Method to update user's height
	public void updateHeight(Player p) {
		Scanner myScan = new Scanner(System.in);
		
		double height = 0;
		boolean loopGuard = true;
		
		do {
			System.out.println("Please update your height for our data, if applicable:.");
			System.out.println("New Height (in cm) : ");
			//Validate User Input
			if (myScan.hasNextDouble()) {
				height = myScan.nextDouble();
				myScan.nextLine();
				if(height < 1) {
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number to represent your height in cm!\n" + ANSI_RESET);
					continue;
				}
			}
			else {
				myScan.next();
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number to represent your height in cm!\n" + ANSI_RESET);
				continue;
			}
			loopGuard = false;
		}while(loopGuard);
		
		p.setHeight(height);
		PlayerFiles.writePlayer(players);
		System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_GREEN + "Height updated successfully!\n" + ANSI_RESET);
	}
	
	//Method to calculate user's BMI
	public void checkPlayerBMI() {
		System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Body Mass Index(BMI) is a useful reference for athletes." + ANSI_RESET);
		System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Select a user to calculate their BMI: " + ANSI_RESET);
		
		Scanner myScan = new Scanner(System.in);
		
		//List player data for user to choose
		System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Select a user to update their height: " + ANSI_RESET);
		for(String name : playerMap.keySet()) {
			System.out.println(name);
		}
		
		//loop guard condition
		boolean loopGuard = true;
				
		//begin loop for user input
		while(loopGuard) {
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Enter the player's name to update height, or q to return: " + ANSI_RESET);
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
					calculateBMI(p);
					loopGuard = false;
					return;
				}
			}
			
			//if calculateBMI() is never called, display an error for an invalid player choice
			if(loopGuard == true) {
				System.out.println(ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid choice for player, please try again!" + ANSI_RESET);
			}
		}
	}
	
	//Method that calculates player's BMI
	public void calculateBMI(Player p) {
		//load players weight and height
		double weight = p.getWeight();
		double height = p.getHeight();
		
		//Display user's height and weight
		System.out.println(ANSI_BOLD + p.getName() + "'s height " + ANSI_RESET + height);
		System.out.println(ANSI_BOLD + p.getName() + "'s weight " + ANSI_RESET + weight + "\n");
		
		//convert weight from lbs to kg
		double weightInKg = weight * 0.45359237;
		
		//convert height for cm to m
		double heightInM = height / 100;
		
		//calculate the bmi
		double bMI = weightInKg / (heightInM * heightInM);
		
		//Display bmi to user
		System.out.print("\n" + ANSI_RESET + ANSI_BLACK_BACKGROUND + ANSI_BLUE + p.getName());
		System.out.printf("'s BMI is %.2f %s \n", bMI, ANSI_RESET);
		System.out.println(ANSI_BOLD + "'Note: a good BMI for soccer players is in between 21-23 " + ANSI_RESET);
		
	}
	
	//Method to navigate subMenu
	public void bmiMenu() {
		BMI myBMI = new BMI();
		
		//Check if there is existing player data
		if(players.isEmpty()) {
			System.out.println("There is no saved player data yet, please add player details!");
			System.out.println("\nReturning to previous menu...\n");
			return;
		}
		
		//Variable To Store User Input
		int choice = 0;
		
		//Open Scanner
		Scanner myScan = new Scanner(System.in);
		do {
		
			System.out.println("\n" + ANSI_RESET + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Please select a following option: \n" + ANSI_RESET);
			System.out.println(ANSI_BOLD + "1. " + ANSI_RESET + "Update height");
			System.out.println(ANSI_BOLD + "2. " + ANSI_RESET + "Update weight");
			System.out.println(ANSI_BOLD + "3. " + ANSI_RESET + "Calculate BMI");
			System.out.println(ANSI_BOLD + "4. " + ANSI_RESET + "Return to menu\n");
			
			//Validate User Input
			if (myScan.hasNextInt()) {
				choice = myScan.nextInt();
				myScan.nextLine();
				if(choice < 1 || choice > 4) {
					System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 4!" + ANSI_RESET);
					continue;
				}
			}
			else {
				myScan.next();
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 4!" + ANSI_RESET);
				continue;
			}
			
			switch(choice) {
			case 1:
				//Case for update height
				myBMI.bmiHeight();
				break;
			case 2:
				//Case for update weight
				myBMI.bmiWeight();
				break;
			case 3:
				//Case for Calculate BMI
				myBMI.checkPlayerBMI();
				break;
			case 4:
				//Case for return to previous menu
				return;
			}
		
		
		}while(choice != 4);
	}
}

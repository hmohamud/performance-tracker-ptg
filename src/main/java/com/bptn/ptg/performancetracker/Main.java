package com.bptn.ptg.performancetracker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Constant Variables To Format Output
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
		final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_BLUE = "\u001B[34m";
		
		//Variable To Store User Input
		int choice = 0;
		
		//Initial Startup Message
		System.out.println("\t*******************************************");
		System.out.println("\t***                                     ***");
		System.out.println("\t*** Path To Glory - Performance Tracker ***");
		System.out.println("\t***                                     ***");
		System.out.println("\t******************************************* \n");
		
		//Main Loop
		do {
			
			//Welcome Message
			System.out.println("\n" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Welcome to Path To Glory, please select an option to continue!" + ANSI_RESET);
			System.out.println("\t" + ANSI_WHITE_BACKGROUND + ANSI_BLUE + "(Enter a number corresponding to your choice)\n" + ANSI_RESET);
			
			
			//Open Scanner
			Scanner myScan = new Scanner(System.in);
			
			
			//Choices For User Input
			System.out.println("1. Player Profile");
			System.out.println("2. Stat Logging");
			System.out.println("3. Workout Tracking");
			System.out.println("4. BMI Calculator");
			System.out.println("5. Performance Comparison");
			System.out.println("6. Exit\n");
			
			try {
				//Take User Input
				choice = myScan.nextInt();
				//Free Up Next Scanner Line
				myScan.nextLine();
				
				//Check If Number User Provided Is Within Range
				if (choice < 1 || choice > 6) {
					throw new IllegalArgumentException("Your choice is out of range!");
				}
			}
			catch(InputMismatchException e) {
				//Error Message If Input Is Not An Integer
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 6!" + ANSI_RESET);
				myScan.reset();
				myScan.next();
				continue;
			}
			catch(IllegalArgumentException e) {
				//Error Message If User Input Is Out Of Range
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 6!" + ANSI_RESET);
				continue;
			}
			catch(Exception e) {
				//Error Message If Input Is Not An Integer
				System.out.println("\n" + ANSI_BLACK_BACKGROUND + ANSI_RED + "Invalid input, please enter a number in the range 1 - 6!" + ANSI_RESET);
				continue;
			}
			
			//Switch Cases To Determine What To Do Based On User Input
			switch (choice) {
				case 1:
					//Case To Go To Player Profile
					PlayerProfile p1 = new PlayerProfile();
					p1.playerMenu();
					break;
				case 2:
					//Case To Go To Stat Logging
					break;
				case 3:
					//Case To Go To Workout Tracking
					break;
				case 4:
					//Case To Go To BMI Calc
					break;
				case 5:
					//Case For Performance Comparison
					break;
				case 6:
					//Case For Exit Program
					System.out.println(ANSI_WHITE_BACKGROUND + ANSI_BLUE + "Thanks for using Path to Glory...");
					System.out.println("Exiting..." + ANSI_RESET);
					//Close the Scanner
					myScan.close();
					break;
			}
		} while (choice != 6);
		
		
		

	}

}

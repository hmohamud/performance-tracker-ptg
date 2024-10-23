package com.bptn.ptg.performancetracker;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class PlayerFiles {
	//Hold the file name as a constant variable
	public static final String FILE_NAME = "players.txt";
	
	//Method to write our created players to a file
	public static void writePlayer(List<Player> players) {
		try {
			//open writer to write our created players
			FileWriter writer = new FileWriter(FILE_NAME);
			
			//loop through list of players and write each instance variable
			for(Player player : players) {
				writer.write(player.getName() + "," + player.getAge() + "," + player.getTeamName() + "," +
						player.getHeight() + "," + player.getWeight() + "," + player.getPosition() + "\n");
			}
			//close writer
			writer.close();
		}
		catch(IOException e) {
			System.out.println("Something went wrong while creating the file: " + e.getMessage());
		}
		
	}
	
	//Method to load previous created players from a file
	public static List<Player> loadPlayer() {
		
		List<Player> players = new ArrayList<>();
		
		File myFile = new File(FILE_NAME);
		
		if(myFile.exists())
		
		try {
			FileReader reader = new FileReader(FILE_NAME);
			BufferedReader buffer = new BufferedReader(reader);
			
			String line;
			
			while((line = buffer.readLine()) != null) {
				String[] attributes = line.split(",");
				if(attributes.length == 6) {
					if(attributes[5].equalsIgnoreCase("forward")) {
						Forward player = new Forward(attributes[0], Integer.parseInt(attributes[1]), attributes[2], Double.parseDouble(attributes[3]),
								Double.parseDouble(attributes[4]), attributes[5]);
						players.add(player);
					}
					else if(attributes[5].equalsIgnoreCase("midfielder")) {
						MidFielder player = new MidFielder(attributes[0], Integer.parseInt(attributes[1]), attributes[2], Double.parseDouble(attributes[3]),
								Double.parseDouble(attributes[4]), attributes[5]);
						players.add(player);
					}
					else if(attributes[5].equalsIgnoreCase("defender")) {
						Defender player = new Defender(attributes[0], Integer.parseInt(attributes[1]), attributes[2], Double.parseDouble(attributes[3]),
								Double.parseDouble(attributes[4]), attributes[5]);
						players.add(player);
					}
				}
			}
			
			buffer.close();
			reader.close();
		}
		catch (IOException e) {
			System.out.println("Something went wrong while attempting to read: " + e.getMessage());
		}
		
		return players;
	}
}
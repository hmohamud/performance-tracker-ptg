package com.bptn.ptg.performancetracker;

abstract class Player {
	
	private String name;
	private int age;
	private String teamName;
	private double height;
	private double weight;
	private int gamesPlayed;
	
	public Player(String name, int age, String teamName, double height, double weight) {
		this.name = name;
		this.age = age;
		this.teamName = teamName;
		this.height = height;
		this.weight = weight;
		this.gamesPlayed = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	public void setGamesPlayed(int games) {
		gamesPlayed += games;
	}
	
	public abstract String getPosition();
	
}

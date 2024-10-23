package com.bptn.ptg.performancetracker;

public class Defender extends Player{
	
	private String position;
	private int tacklesMade;
	private int foulsConceded;
	
	public Defender(String name, int age, String teamName, double height, double weight, String position) {
		super(name, age, teamName, height, weight);
		this.position = position;
		this.tacklesMade = 0;
		this.foulsConceded = 0;
	}
	
	public String getPosition(){
		return position;
	}
	
	public double getAverageTackles() {
		double avg = 0;
		if(getGamesPlayed() != 0) {
			avg = (double) tacklesMade / (double) getGamesPlayed();
		}
		return avg;
	}
	
	public double getAverageFouls() {
		double avg = 0;
		if(getGamesPlayed() != 0) {
			avg = (double) foulsConceded / (double) getGamesPlayed();
		}
		return avg;
	}
	
	public void setTacklesMade(int newTackles) {
		tacklesMade += newTackles;
	}
	
	public void setFoulsConceded(int newFouls) {
		foulsConceded += newFouls;
	}
	
	public int getTackles() {
		return tacklesMade;
	}
	
	public int getFouls() {
		return foulsConceded;
	}

}

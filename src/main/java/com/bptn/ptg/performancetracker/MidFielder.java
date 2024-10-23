package com.bptn.ptg.performancetracker;

public class MidFielder extends Player{
	
	private String position;
	private int assistCount;
	private int passCompleted;
	
	public MidFielder(String name, int age, String teamName, double height, double weight, String position) {
		super(name, age, teamName, height, weight);
		this.position = position;
		this.assistCount = 0;
		this.passCompleted = 0;
	}
	
	public String getPosition(){
		return position;
	}
	
	public double getAverageAssists() {
		double avg = 0;
		if(getGamesPlayed() != 0) {
			avg = (double) assistCount / (double) getGamesPlayed();
		}
		return avg;
	}
	
	public double getAveragePasses() {
		double avg = 0;
		if(getGamesPlayed() != 0) {
			avg = (double) passCompleted / (double) getGamesPlayed();
		}
		return avg;
	}
	
	public void setAssistCount(int newAssist) {
		assistCount += newAssist;
	}
	
	public void setPassesCompleted(int newPasses) {
		passCompleted += newPasses;
	}
	
	public int getAssist() {
		return assistCount;
	}
	
	public int getPasses() {
		return passCompleted;
	}
}

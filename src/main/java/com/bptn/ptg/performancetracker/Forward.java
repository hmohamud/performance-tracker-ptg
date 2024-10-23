package com.bptn.ptg.performancetracker;

public class Forward extends Player{
	
	private String position;
	private int goalCount;
	private int shotsOnTarget;
	
	public Forward(String name, int age, String teamName, double height, double weight, String position) {
		super(name, age, teamName, height, weight);
		this.position = position;
		this.goalCount = 0;
		this.shotsOnTarget = 0;
	}
	
	public String getPosition(){
		return position;
	}
	
	public double getAverageGoals() {
		double avg = 0;
		if(getGamesPlayed() != 0) {
			avg = (double) goalCount / (double) getGamesPlayed();
		}
		return avg;
	}
	
	public double getAverageShots() {
		double avg = 0;
		if(getGamesPlayed() != 0) {
			avg = (double) shotsOnTarget / (double) getGamesPlayed();
		}
		return avg;
	}
	
	public void setGoalCount(int newGoals) {
		goalCount += newGoals;
	}
	
	public void setShotOnTarget(int newShots) {
		shotsOnTarget += newShots;
	}
	
	public int getGoals() {
		return goalCount;
	}
	
	public int getShots() {
		return shotsOnTarget;
	}

}

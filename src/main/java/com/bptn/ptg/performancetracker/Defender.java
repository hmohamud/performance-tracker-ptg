package com.bptn.ptg.performancetracker;

public class Defender extends Player{
	
	private String position;
	
	public Defender(String name, int age, String teamName, double height, double weight, String position) {
		super(name, age, teamName, height, weight);
		this.position = position;
	}
	
	public String getPosition(){
		return position;
	}

}

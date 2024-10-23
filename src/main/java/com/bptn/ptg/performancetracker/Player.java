package com.bptn.ptg.performancetracker;

abstract class Player {
	
	private String name;
	private int age;
	private String teamName;
	private double height;
	private double weight;
	
	public Player(String name, int age, String teamName, double height, double weight) {
		this.name = name;
		this.age = age;
		this.teamName = teamName;
		this.height = height;
		this.weight = weight;
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
	
	public abstract String getPosition();
	
}

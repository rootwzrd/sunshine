
package com.dat.mobile.sunshine.model.service;

public class City{
   	private Coord coord;
   	private String country;
   	private String id;
   	private String name;
   	private Number population;

 	public Coord getCoord(){
		return this.coord;
	}
	public void setCoord(Coord coord){
		this.coord = coord;
	}
 	public String getCountry(){
		return this.country;
	}
	public void setCountry(String country){
		this.country = country;
	}
 	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public Number getPopulation(){
		return this.population;
	}
	public void setPopulation(Number population){
		this.population = population;
	}
}

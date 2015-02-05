
package com.dat.mobile.sunshine.model.service;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse{
   	private City city;
   	private Number cnt;
   	private String cod;
    @SerializedName("list")
   	private List<Lst> list;
   	private Number message;

 	public City getCity(){
		return this.city;
	}
	public void setCity(City city){
		this.city = city;
	}
 	public Number getCnt(){
		return this.cnt;
	}
	public void setCnt(Number cnt){
		this.cnt = cnt;
	}
 	public String getCod(){
		return this.cod;
	}
	public void setCod(String cod){
		this.cod = cod;
	}
 	public List<Lst> getLst(){
		return this.list;
	}
	public void setLst(List<Lst> list){
		this.list = list;
	}
 	public Number getMessage(){
		return this.message;
	}
	public void setMessage(Number message){
		this.message = message;
	}

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "city=" + city +
                ", cnt=" + cnt +
                ", cod='" + cod + '\'' +
                ", list=" + list +
                ", message=" + message +
                '}';
    }
}

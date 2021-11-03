package com.smoothstack.utopia_spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {

	@Id
	@Column(name = "iata_id")
	private String airportCode;
	
	@Column(name = "city")
	private String city;

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Airport [airportCode=" + airportCode + ", city=" + city + "]";
	}
}

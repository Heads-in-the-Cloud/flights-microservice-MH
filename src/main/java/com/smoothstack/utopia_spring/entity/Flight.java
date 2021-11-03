package com.smoothstack.utopia_spring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;
	
	@ManyToOne
	@JoinColumn(name = "airplane_id")
	private Airplane airplane;
	
	@Column(name = "departure_time")
	private LocalDateTime departureTime;
	
	@Column(name = "reserved_seats")
	private int reservedSeats;
	
	@Column(name = "seat_price")
	private float seatPrice;

	public Flight() {
		this.route = new Route();
		this.airplane = new Airplane();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Airplane getAirplane() {
		return airplane;
	}

	public void setAirplane(Airplane airplane) {
		this.airplane = airplane;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public float getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(float seatPrice) {
		this.seatPrice = seatPrice;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", route=" + route + ", airplane=" + airplane + ", departureTime=" + departureTime
				+ ", reservedSeats=" + reservedSeats + ", seatPrice=" + seatPrice + "]";
	}
}

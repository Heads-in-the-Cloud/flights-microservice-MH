package com.smoothstack.utopia_spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "origin_id")
	private Airport originId;
	
	@ManyToOne
	@JoinColumn(name = "destination_id")
	private Airport destinationId;
	
	public Route() {
		this.originId = new Airport();
		this.destinationId = new Airport();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Airport getOriginId() {
		return originId;
	}

	public void setOriginId(Airport originId) {
		this.originId = originId;
	}

	public Airport getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Airport destinationId) {
		this.destinationId = destinationId;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", originId=" + originId + ", destinationId=" + destinationId + "]";
	}
}

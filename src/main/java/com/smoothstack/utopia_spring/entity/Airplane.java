package com.smoothstack.utopia_spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "airplane")
public class Airplane {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private AirplaneType type;
	
	public Airplane() {
		this.type = new AirplaneType();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AirplaneType getType() {
		return type;
	}

	public void setType(AirplaneType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Airplane [id=" + id + ", type=" + type + "]";
	}
}

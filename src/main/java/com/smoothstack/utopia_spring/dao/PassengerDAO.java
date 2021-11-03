package com.smoothstack.utopia_spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smoothstack.utopia_spring.entity.Passenger;

@Repository
public interface PassengerDAO extends JpaRepository<Passenger, Integer> {

}

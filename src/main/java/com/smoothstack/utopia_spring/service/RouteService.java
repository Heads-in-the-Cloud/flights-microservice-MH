package com.smoothstack.utopia_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.utopia_spring.dao.RouteDAO;
import com.smoothstack.utopia_spring.entity.Route;

@Service
public class RouteService {

	@Autowired
	private RouteDAO routedao;
	
	public RouteService(RouteDAO routedao) {
		this.routedao = routedao;
	}
	
	public List<Route> readAll() {
		
		List<Route> routes = new ArrayList<Route>();
		routedao.findAll().forEach(route -> routes.add(route));
		return routes;
	}
	
	public Optional<Route> readById(int id) {
		
		return routedao.findById(id);
	}
	
	public void save(Route route) {
		
		routedao.save(route);
	}
	
	public void delete(int id) {
		
		routedao.findById(id).ifPresent(routedao::delete);
	}
}

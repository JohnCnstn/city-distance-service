package com.itechart.citydistance.service;

import com.itechart.citydistance.generated.model.Route;

import java.util.List;

public interface RouteService {

    List<Route> getRoutes(String from, String to);

}

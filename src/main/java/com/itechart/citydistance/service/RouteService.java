package com.itechart.citydistance.service;

import com.itechart.citydistance.generated.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RouteService {

    Page<Route> getRoutes(String from, String to, Pageable pageable);

}

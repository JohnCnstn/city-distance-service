package com.itechart.citydistance.service.impl;

import com.itechart.citydistance.generated.model.Route;
import com.itechart.citydistance.repository.RoadRepository;
import com.itechart.citydistance.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.itechart.citydistance.mapper.RouteMapper.ROUTE_MAPPER;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RoadRepository roadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Route> getRoutes(String from, String to) {
        var roads = roadRepository.findRoads(from, to);
        return roads.stream().map(ROUTE_MAPPER::toModel).collect(Collectors.toList());
    }

}

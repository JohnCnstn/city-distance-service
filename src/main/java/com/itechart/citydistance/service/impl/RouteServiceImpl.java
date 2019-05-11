package com.itechart.citydistance.service.impl;

import com.itechart.citydistance.generated.model.Route;
import com.itechart.citydistance.repository.RoadRepository;
import com.itechart.citydistance.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.itechart.citydistance.mapper.RouteMapper.ROUTE_MAPPER;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RoadRepository roadRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Route> getRoutes(String from, String to, Pageable pageable) {
        var roads = roadRepository.findRoads(from, to, pageable);
        return roads.map(ROUTE_MAPPER::toModel);
    }

}

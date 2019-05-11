package com.itechart.citydistance.service.impl;

import com.itechart.citydistance.generated.model.Road;
import com.itechart.citydistance.repository.RoadRepository;
import com.itechart.citydistance.service.CityService;
import com.itechart.citydistance.service.RoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.itechart.citydistance.mapper.RoadMapper.ROAD_MAPPER;

@Service
@RequiredArgsConstructor
public class RoadServiceImpl implements RoadService {

    private final CityService cityService;
    private final RoadRepository roadRepository;

    @Override
    @Transactional
    public Road createRoad(Road road) {
        var from = cityService.findExistingOrElseCreate(road.getFrom());
        var to = cityService.findExistingOrElseCreate(road.getTo());
        var entity = ROAD_MAPPER.toEntity(road, from, to);
        var saved = roadRepository.save(entity);
        return ROAD_MAPPER.toModel(saved);
    }

}

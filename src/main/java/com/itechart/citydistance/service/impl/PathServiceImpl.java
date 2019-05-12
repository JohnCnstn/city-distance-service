package com.itechart.citydistance.service.impl;

import com.itechart.citydistance.exception.NoConnectionBetweenCitiesException;
import com.itechart.citydistance.generated.model.Path;
import com.itechart.citydistance.repository.CityRepository;
import com.itechart.citydistance.repository.RoadRepository;
import com.itechart.citydistance.service.PathService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.itechart.citydistance.mapper.RouteMapper.PATH_MAPPER;
import static com.itechart.citydistance.util.ExceptionUtils.notFoundEntity;

@Service
@RequiredArgsConstructor
public class PathServiceImpl implements PathService {

    private final CityRepository cityRepository;
    private final RoadRepository roadRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Path> getPaths(String from, String to, Pageable pageable) {
        var cityFrom = cityRepository.findByName(from).orElseThrow(() ->
                notFoundEntity(String.format("City with such name %s was not found.", from)));
        var cityTo = cityRepository.findByName(to).orElseThrow(() ->
                notFoundEntity(String.format("City with such name %s was not found.", to)));
        var roads = roadRepository.findRoads(cityFrom.getId(), cityTo.getId(), pageable);
        if (roads.getContent().isEmpty()) {
            throw new NoConnectionBetweenCitiesException(from, to);
        }
        return roads.map(PATH_MAPPER::toModel);
    }

}

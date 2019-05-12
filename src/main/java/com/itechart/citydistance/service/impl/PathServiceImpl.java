package com.itechart.citydistance.service.impl;

import com.itechart.citydistance.exception.NoConnectionBetweenCitiesException;
import com.itechart.citydistance.generated.model.Path;
import com.itechart.citydistance.repository.RoadRepository;
import com.itechart.citydistance.service.PathService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.itechart.citydistance.mapper.RouteMapper.PATH_MAPPER;

@Service
@RequiredArgsConstructor
public class PathServiceImpl implements PathService {

    private final RoadRepository roadRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Path> getPaths(String from, String to, Pageable pageable) {
        var roads = roadRepository.findRoads(from, to, pageable);
        if (roads.getContent().isEmpty()) {
            throw new NoConnectionBetweenCitiesException(from, to);
        }
        return roads.map(PATH_MAPPER::toModel);
    }

}

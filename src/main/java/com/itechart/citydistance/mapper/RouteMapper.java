package com.itechart.citydistance.mapper;

import com.itechart.citydistance.dto.PathDto;
import com.itechart.citydistance.generated.model.Path;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

import static com.itechart.citydistance.mapper.CityMapper.CITY_MAPPER;

@Mapper
public interface RouteMapper {

    RouteMapper PATH_MAPPER = Mappers.getMapper(RouteMapper.class);

    default Path toModel(PathDto dto) {
        var cities = dto.getCities().stream().map(CITY_MAPPER::toModel).collect(Collectors.toList());
        var path = new Path();
        path.setCities(cities);
        path.setTotalDistance(dto.getTotalDistance());
        return path;
    }

}

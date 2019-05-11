package com.itechart.citydistance.mapper;

import com.itechart.citydistance.dto.RouteDto;
import com.itechart.citydistance.generated.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

import static com.itechart.citydistance.mapper.CityMapper.CITY_MAPPER;

@Mapper
public interface RouteMapper {

    RouteMapper ROUTE_MAPPER = Mappers.getMapper(RouteMapper.class);

    default Route toModel(RouteDto dto) {
        var cities = dto.getPath().stream().map(CITY_MAPPER::toModel).collect(Collectors.toList());
        var route = new Route();
        route.setCities(cities);
        route.setTotalDistance(dto.getTotalDistance());
        return route;
    }

}

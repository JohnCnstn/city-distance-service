package com.itechart.citydistance.mapper;

import com.itechart.citydistance.entity.CityEntity;
import com.itechart.citydistance.entity.RoadEntity;
import com.itechart.citydistance.generated.model.Road;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static com.itechart.citydistance.mapper.CityMapper.CITY_MAPPER;

@Mapper
public interface RoadMapper {

    RoadMapper ROAD_MAPPER = Mappers.getMapper(RoadMapper.class);

    default RoadEntity toEntity(Road model, CityEntity from, CityEntity to) {
        var entity = new RoadEntity();
        entity.setFrom(from);
        entity.setTo(to);
        entity.setDistance(model.getDistance());
        return entity;
    }

    default Road toModel(RoadEntity entity) {
        var model = new Road();
        model.setDistance(entity.getDistance());
        model.setFrom(CITY_MAPPER.toModel(entity.getFrom()));
        model.setTo(CITY_MAPPER.toModel(entity.getTo()));
        return model;
    }

}

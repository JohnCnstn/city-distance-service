package com.itechart.citydistance.mapper;

import com.itechart.citydistance.entity.CityEntity;
import com.itechart.citydistance.generated.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper CITY_MAPPER = Mappers.getMapper(CityMapper.class);

    @Mapping(target = "id", ignore = true)
    CityEntity toEntity(City model);

    City toModel(CityEntity entity);

}

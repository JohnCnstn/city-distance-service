package com.itechart.citydistance.mapper;

import com.itechart.citydistance.entity.CityEntity;
import com.itechart.citydistance.generated.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper CITY_MAPPER = Mappers.getMapper(CityMapper.class);

    CityEntity toEntity(City model);

    City toModel(CityEntity entity);

}

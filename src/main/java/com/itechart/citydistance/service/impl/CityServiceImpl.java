package com.itechart.citydistance.service.impl;

import com.itechart.citydistance.entity.CityEntity;
import com.itechart.citydistance.generated.model.City;
import com.itechart.citydistance.repository.CityRepository;
import com.itechart.citydistance.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.itechart.citydistance.mapper.CityMapper.CITY_MAPPER;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public CityEntity findExistingOrElseCreate(City city) {
        return cityRepository.findByName(city.getName()).orElse(CITY_MAPPER.toEntity(city));
    }

}

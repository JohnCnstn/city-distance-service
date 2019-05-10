package com.itechart.citydistance.service;

import com.itechart.citydistance.entity.CityEntity;
import com.itechart.citydistance.generated.model.City;

public interface CityService {

    CityEntity findExistingOrElseCreate(City city);

}

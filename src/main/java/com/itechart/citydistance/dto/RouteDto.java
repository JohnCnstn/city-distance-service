package com.itechart.citydistance.dto;

import com.itechart.citydistance.entity.CityEntity;
import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.ArrayList;
import java.util.List;

@Data
@QueryResult
public class RouteDto {

    private Double totalDistance;
    private List<CityEntity> path = new ArrayList<>();

}

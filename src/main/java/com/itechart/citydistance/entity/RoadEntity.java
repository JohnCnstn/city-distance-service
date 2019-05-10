package com.itechart.citydistance.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

@Data
@RelationshipEntity(type = "ROAD_TO")
public class RoadEntity {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private CityEntity from;

    @EndNode
    private CityEntity to;

    @Index
    private double distance;

}

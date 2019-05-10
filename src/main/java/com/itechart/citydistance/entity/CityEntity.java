package com.itechart.citydistance.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NodeEntity("City")
public class CityEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

    @Relationship(type = "ROAD_TO", direction = Relationship.UNDIRECTED)
    private List<RoadEntity> roads = new ArrayList<>();

}

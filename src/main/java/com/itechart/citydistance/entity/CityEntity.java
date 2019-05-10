package com.itechart.citydistance.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity("City")
public class CityEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Index(unique = true)
    private String name;

}

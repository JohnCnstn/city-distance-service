package com.itechart.citydistance.repository;

import com.itechart.citydistance.entity.RoadEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends Neo4jRepository<RoadEntity, Long> {

}

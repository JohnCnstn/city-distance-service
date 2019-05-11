package com.itechart.citydistance.repository;

import com.itechart.citydistance.dto.RouteDto;
import com.itechart.citydistance.entity.RoadEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends Neo4jRepository<RoadEntity, Long> {

    @Query(value = "MATCH path = (from: City {name:{from}}) -[route:ROAD_TO*]- (to: City {name:{to}})\n" +
            "       RETURN EXTRACT(n IN nodes(path)| n) as path,\n" +
            "              REDUCE(dist = 0, r in route | dist + r.distance) AS totalDistance\n" +
            "           ORDER BY totalDistance ASC\n",
            countQuery = "MATCH path = (from: City {name:{from}}) -[route:ROAD_TO*]- (to: City {name:{to}})\n" +
                    "             RETURN COUNT(path)\n")
    Page<RouteDto> findRoads(@Param("from") String from, @Param("to") String to, Pageable pageable);

}

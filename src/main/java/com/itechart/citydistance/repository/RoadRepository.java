package com.itechart.citydistance.repository;

import com.itechart.citydistance.entity.RoadEntity;
import com.itechart.citydistance.dto.RouteDto;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadRepository extends Neo4jRepository<RoadEntity, Long> {

    @Query("MATCH path = (from: City {name:{from}}) -[route:ROAD_TO*]- (to: City {name:{to}})\n" +
            "RETURN EXTRACT(n IN nodes(path)| n) as path,\n" +
            "       REDUCE(dist = 0, r in route | dist + r.distance) AS totalDistance\n" +
            "   ORDER BY totalDistance ASC\n" +
            "   LIMIT 100")
    List<RouteDto> findRoads(@Param("from") String from, @Param("to") String to);

}

package com.itechart.citydistance.repository;

import com.itechart.citydistance.dto.PathDto;
import com.itechart.citydistance.entity.RoadEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadRepository extends Neo4jRepository<RoadEntity, Long> {

    @Query(value = "MATCH path = (from: City {name:{from}}) -[way:ROAD_TO*]- (to: City {name:{to}})\n" +
            "       RETURN extract(n IN nodes(path)| n) AS cities,\n" +
            "              reduce(dist = 0, w IN way | dist + w.distance) AS totalDistance\n" +
            "           ORDER BY totalDistance ASC\n",
            countQuery = "MATCH path = (from: City {name:{from}}) -[way:ROAD_TO*]- (to: City {name:{to}})\n" +
                    "             RETURN count(path)\n")
    Page<PathDto> findRoads(@Param("from") String from, @Param("to") String to, Pageable pageable);

}

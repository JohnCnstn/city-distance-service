package com.itechart.citydistance.repository;

import com.itechart.citydistance.entity.CityEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends Neo4jRepository<CityEntity, Long> {

    Optional<CityEntity> findByName(String name);

}

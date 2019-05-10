package com.itechart.citydistance.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public class AppProperties {

    private Url url = new Url();

    private Neo4j neo4j = new Neo4j();

    @Getter
    @Setter
    public static class Url {

        private String swaggerUi = "http://localhost:3000";

    }

    @Getter
    @Setter
    public static class Neo4j {

        private String uri = "bolt://localhost:7687/city-distance-service";
        private String username = "neo4j";
        private String password = "city-distance";

    }

}

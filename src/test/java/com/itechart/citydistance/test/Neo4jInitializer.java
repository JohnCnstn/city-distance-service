package com.itechart.citydistance.test;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class Neo4jInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    public static final int NEO4J_PORT = 7687;

    public static final String NEO4J_DB = "itecheart-test";
    public static final String NEO4J_USER = "neo4j";
    public static final String NEO4J_PASSWORD = "itecheart-test";

    public static final GenericContainer NEO4J =
            new GenericContainer("neo4j:3.5.5")
                    .withEnv("NEO4J_AUTH", "neo4j/" + NEO4J_PASSWORD)
                    .waitingFor(Wait.forListeningPort())
                    .withExposedPorts(NEO4J_PORT);

    static {
        NEO4J.start();
    }

    public static String getHost() {
        return NEO4J.getContainerIpAddress();
    }

    public static int getPort() {
        return NEO4J.getMappedPort(NEO4J_PORT);
    }

    public static String getUrl() {
        return "bolt:" + UriComponentsBuilder.newInstance()
                .host(getHost()).port(getPort()).path(NEO4J_DB)
                .toUriString();
    }

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        applyProperties(applicationContext);
    }

    private void applyProperties(ConfigurableApplicationContext applicationContext) {
        TestPropertyValues.of(
                "spring.data.neo4j.uri:" + getUrl(),
                "spring.data.neo4j.username:" + NEO4J_USER,
                "spring.data.neo4j.password:" + NEO4J_PASSWORD
        ).applyTo(applicationContext);
    }
    
}

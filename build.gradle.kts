import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.springframework.boot") version Versions.springBoot
}

apply(plugin = "io.spring.dependency-management")

group = "com.itechart"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.projectlombok:lombok")

    implementation("org.neo4j:neo4j-ogm-embedded-driver:${Versions.neo4jOgmEmbeddedDriver}")
    implementation("org.springframework.data:swagger-spring-data-neo4j:${Versions.springDataNeo4j}")

    implementation("io.swagger:swagger-annotations:${Versions.swaggerAnnotations}")
    implementation("org.apache.commons:commons-lang3")
    implementation("org.mapstruct:mapstruct-jdk8:${Versions.mapstruct}")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.yaml:snakeyaml")

    testImplementation("com.github.javafaker:javafaker:${Versions.javafaker}")
    testImplementation("org.neo4j:neo4j-ogm-test:${Versions.neo4jOgmTest}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:${Versions.testcontainers}")
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
    }
    implementation {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.bootRun {
    args = listOf("--spring.profiles.active=local")
    jvmArgs = listOf("-server", "-Xms256m", "-Xmx512m", "-Duser.country=US", "-Duser.language=en", "-Duser.timezone=UTC")
}

tasks.test {
    with(testLogging) {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}

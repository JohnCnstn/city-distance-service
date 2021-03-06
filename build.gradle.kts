import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    id("org.springframework.boot") version Versions.springBoot
}

apply(plugin = "io.spring.dependency-management")

apply(from = "gradle/generate-openapi.gradle.kts")

group = "com.itechart"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.mapstruct:mapstruct-processor:${Versions.mapstruct}")
    annotationProcessor("org.projectlombok:lombok")

    compile("org.springframework.boot:spring-boot-starter-data-neo4j:${Versions.springDataNeo4j}")
    implementation("org.springframework.boot:spring-boot-starter-web")

    compileOnly("org.projectlombok:lombok")

    implementation("org.neo4j:neo4j-ogm-bolt-driver:${Versions.neo4jOgmBoltDriver}")

    implementation("io.swagger:swagger-annotations:${Versions.swaggerAnnotations}")
    implementation("org.mapstruct:mapstruct-jdk8:${Versions.mapstruct}")

    implementation("org.yaml:snakeyaml")

    testImplementation("com.github.javafaker:javafaker:${Versions.javafaker}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.testcontainers:testcontainers:${Versions.testcontainers}")
}

configurations {
    compileOnly {
        extendsFrom(annotationProcessor.get())
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

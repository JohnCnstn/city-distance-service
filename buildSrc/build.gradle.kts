object Versions {

    // Do not forget to update buildSrc/main/Versions.kt
    const val javaxActivation = "1.1.1"       // https://mvnrepository.com/artifact/javax.activation/activation
    const val openapiGenerator = "3.3.4"      // https://mvnrepository.com/artifact/org.openapitools/openapi-generator
    const val testcontainers = "1.11.1"       // https://mvnrepository.com/artifact/org.testcontainers/testcontainers

}

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.activation:activation:${Versions.javaxActivation}")
    implementation("org.openapitools:openapi-generator:${Versions.openapiGenerator}")
    implementation("org.testcontainers:testcontainers:${Versions.testcontainers}")
}

kotlin.sourceSets["main"].kotlin.srcDirs("main")

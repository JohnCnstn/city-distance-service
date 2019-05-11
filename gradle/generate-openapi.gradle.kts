import GenerateOpenapiTask

val generateOpenapi by tasks.creating(GenerateOpenapiTask::class) {
    inputFile = project.rootDir.path + "/src/main/resources/openapi/openapi.yaml"
    outputDirectory = project.rootDir.path + "/src/main/java"
    apiPackage = "com.itechart.citydistance.generated.api"
    modelPackage = "com.itechart.citydistance.generated.model"
}

tasks {
    "clean" {
        doFirst {
            delete(project.rootDir.path + "/src/main/java/com/itechart/citydistance/generated/api")
            delete(project.rootDir.path + "/src/main/java/com/itechart/citydistance/generated/model")
        }
    }
    "compileJava" { dependsOn(generateOpenapi) }
}

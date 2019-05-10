PROJECT_NAME := city-distance
SERVICE_NAME := $(PROJECT_NAME)-service

NEO4J_DB := $(SERVICE_NAME)
NEO4J_PASSWORD := $(PROJECT_NAME)

clean:
	./gradlew clean


build: clean
ifndef skip-check
	./gradlew build
else
	./gradlew build -x check
endif


docker-neo4j:
	docker rm $(PROJECT_NAME)-neo4j --force || true
	docker run --detach \
		--publish 7687:7687 \
		--env NEO4J_AUTH=neo4j/$(NEO4J_PASSWORD) \
		--name $(PROJECT_NAME)-neo4j \
		neo4j:3.5.5 || true


docker-swagger-ui:
	docker rm $(PROJECT_NAME)-swagger-ui --force || true
	docker run --detach \
		--publish 3000:8080 \
		--env API_URL=http://localhost:8080/v1/openapi.json \
		--name $(PROJECT_NAME)-swagger-ui \
		swaggerapi/swagger-ui:v3.22.1 || true


docker-local: docker-neo4j docker-swagger-ui

# ========== MAKEFILE ==========

# ========== Docker ==========

docker-up:
	docker-compose -f ops/docker-compose.yaml build app

docker-down:
	docker compose -f ops/docker/docker-compose.yaml down

# ========== Run command ==========

run-and-send:
	docker-compose -f ops/docker-compose.yaml build app && \
	docker-compose -f ops/docker-compose.yaml up -d zookeeper kafka kafka-ui kafka-init && \
	docker-compose -f ops/docker-compose.yaml run --rm app send-message

run-and-send-file:
	docker-compose -f ops/docker-compose.yaml build app && \
	docker-compose -f ops/docker-compose.yaml up -d zookeeper kafka kafka-ui kafka-init && \
	docker-compose -f ops/docker-compose.yaml run --rm app send-message --file=src/main/resources/person-example.json

# CLI Proto Sender

## How to run

### Docker

Run Docker

```
docker compose up -d
```

Access Kafka UI and Add a topic

```
http://localhost:8090/
```

Stop Kafka using your terminal or cmd
```
docker compose down
```

### Local

Run local Kafka

```
docker run -p 9092:9092 apache/kafka:3.7.0
```

Set up Kafka

```
.\kafka-topics.bat --create --topic personTopic  --bootstrap-server localhost:9092

.\zookeeper-server-start.bat ..\..\config\zookeeper.properties

.\kafka-server-start.bat ..\..\config\server.properties

.\kafka-console-consumer.bat --topic personTopic --bootstrap-server localhost:9092
```

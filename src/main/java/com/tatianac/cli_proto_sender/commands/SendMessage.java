package com.tatianac.cli_proto_sender.commands;

import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tatianac.cli_proto_sender.kafka.KafkaPublisher;
import com.tatianac.cli_proto_sender.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@CommandLine.Command(
        name = "send-message",
        mixinStandardHelpOptions = true,
        description = "Send a person object to Kafka topic"
)
public class SendMessage implements Runnable {

    private final KafkaPublisher kafkaPublisher;

    @CommandLine.Option(
            names = "--file",
            required = false,
            description = "JSON file to send (default: person-example.json from resources)"
    )
    private File file;

    public SendMessage(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    public void run() {
        try {
            Object json = toObject();
            Person person = toPerson(json.toString());
            log.debug("Person object generated: {}", person);

            kafkaPublisher.publish(person);
        } catch (InvalidProtocolBufferException | ExecutionException | InterruptedException e) {
            log.error("Error when parsing file to proto.", e);
            throw new RuntimeException(e);
        }
    }

    private Person toPerson(String jsonString) throws InvalidProtocolBufferException {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Person.class);
    }

    private Object toObject() {
        JSONParser parser = new JSONParser();
        try {
            if (file == null) {
                log.info("No document found. Using default file from resources: person-example.json");
                InputStream stream = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("person-example.json");

                if (stream == null) {
                    log.error("Couldn't find default file person-example.json in resources.");
                    throw new RuntimeException("Default file not found.");
                }
                String json = streamToString(stream);
                return parser.parse(json);
            } else {
                log.info("Reading provided file: {}", file.getAbsolutePath());
                return parser.parse(new FileReader(file));  // <-- FIX: read file contents properly
            }
        } catch (IOException | ParseException e) {
            log.error("Error parsing JSON file.", e);
            throw new RuntimeException(e);
        }
    }

    private String streamToString(InputStream stream) {
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

package com.tatianac.cli_proto_sender.kafka;

import com.tatianac.cli_proto_sender.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaPublisher {

    private final KafkaTemplate<String, Person> kafkaTemplate;

    public KafkaPublisher(@Qualifier("personKafkaTemplate") KafkaTemplate<String, Person> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Person person) {
        log.debug("Starting publish action");
        if (person == null) {
            log.error("No data found to send message.");
            throw new RuntimeException("Person is null");
        }
        kafkaTemplate.send("personTopic", String.valueOf(person.id()), person);
        log.info("Message sent to topic `personTopic`");
    }
}

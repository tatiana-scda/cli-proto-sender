package com.tatianac.cli_proto_sender.kafka;

import com.tatianac.cli_proto_sender.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaController {
    private final KafkaTemplate<String, Person> kafkaTemplate;

    @Autowired
    public KafkaController(@Qualifier("userKafkaTemplate") KafkaTemplate<String, Person> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(Person person) {
        log.debug("Starting publish action");
        if (person == null) {
            log.error("No data fount to send message.");
            throw new RuntimeException();
        }
        kafkaTemplate.send("personTopic", String.valueOf(person.id()), person);
        log.info("Message send to topic `personTopic`");
    }
}
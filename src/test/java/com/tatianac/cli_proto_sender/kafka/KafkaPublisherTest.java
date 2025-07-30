package com.tatianac.cli_proto_sender.kafka;

import com.tatianac.cli_proto_sender.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KafkaPublisherTest {

    private KafkaTemplate<String, Person> kafkaTemplate;
    private KafkaPublisher kafkaPublisher;

    @BeforeEach
    void setUp() {
        kafkaTemplate = Mockito.mock(KafkaTemplate.class);
        kafkaPublisher = new KafkaPublisher(kafkaTemplate);
    }

    @Test
    void publish_ShouldSendMessage_WhenPersonIsNotNull() {
        Person person = new Person("Jane D", 1, "jane@d.com");
        kafkaPublisher.publish(person);

        verify(kafkaTemplate, times(1)).send(eq("personTopic"), eq(String.valueOf(person.id())), eq(person));
    }

    @Test
    void publish_ShouldThrowException_WhenPersonIsNull() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            kafkaPublisher.publish(null);
        });
        assertEquals("Person is null", exception.getMessage());
        verifyNoInteractions(kafkaTemplate);
    }
}

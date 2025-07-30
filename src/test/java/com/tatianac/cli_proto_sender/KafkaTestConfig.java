package com.tatianac.cli_proto_sender;

import com.tatianac.cli_proto_sender.model.Person;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

@TestConfiguration
@Profile("test")
public class KafkaTestConfig {

    @Primary
    @Bean("personKafkaTemplate")
    @SuppressWarnings("unchecked")
    public KafkaTemplate<String, Person> kafkaTemplate() {
        return (KafkaTemplate<String, Person>) Mockito.mock(KafkaTemplate.class);
    }
}

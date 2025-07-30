package com.tatianac.cli_proto_sender;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {CliProtoSenderApplication.class, KafkaTestConfig.class})
@ActiveProfiles("test")
public class CliProtoSenderApplicationTests {

	@Test
	void contextLoads() {}
}

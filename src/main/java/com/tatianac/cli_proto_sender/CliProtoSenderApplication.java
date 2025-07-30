package com.tatianac.cli_proto_sender;

import com.tatianac.cli_proto_sender.commands.SendMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import picocli.CommandLine;

@SpringBootApplication
public class CliProtoSenderApplication implements CommandLineRunner {

	private final ApplicationContext context;

	public CliProtoSenderApplication(ApplicationContext context) {
		this.context = context;
	}

	public static void main(String[] args) {
		SpringApplication.run(CliProtoSenderApplication.class, args);
	}

	@Override
	public void run(String... args) {
		SendMessage sendMessage = context.getBean(SendMessage.class);
		new CommandLine(sendMessage).execute(args);
	}
}

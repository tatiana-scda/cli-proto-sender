package com.tatianac.cli_proto_sender;

import com.tatianac.cli_proto_sender.commands.SendMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import picocli.CommandLine;

@SpringBootApplication
@CommandLine.Command(
		name = "cli-proto-sender",
		mixinStandardHelpOptions = true,
		version = "1.0",
		description = "CLI root command"
)
public class CliProtoSenderApplication implements Runnable {

	public static void main(String[] args) {
		// Inicializa o contexto Spring
		ApplicationContext ctx = SpringApplication.run(CliProtoSenderApplication.class, args);

		// Cria comando raiz
		CommandLine cmd = new CommandLine(new CliProtoSenderApplication());

		// Registra subcomando injetado pelo Spring
		SendMessage sendMessage = ctx.getBean(SendMessage.class);
		cmd.addSubcommand("send-message", sendMessage);

		// Executa com os argumentos da CLI
		int exitCode = cmd.execute(args);
		System.exit(exitCode);
	}

	@Override
	public void run() {
		System.out.println("Use a subcommand, for example: send-message");
	}
}

package com.tatianac.cli_proto_sender.commands;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;

import static picocli.CommandLine.*;

@Slf4j
@Command(
        name = "health",
        description = "Health check of command line application accepting count param."
)
public class HealthCheckCommand implements Runnable {

    @Option(
            names = "--name",
            required = false,
            description = "Prints [name]"
    )
    String name;

    @Override
    public void run() {
        log.debug("Command fot health check received.");
        System.out.println("It's alive!");
        if (name != null) {
            log.debug("Command fot health check with name {} received.", name);
            System.out.println("Hello " + name);
        }
    }

    public static void main(String... args) {
        new CommandLine(new HealthCheckCommand()).execute(args);
    }
}
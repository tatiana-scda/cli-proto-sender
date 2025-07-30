package com.tatianac.cli_proto_sender.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import static picocli.CommandLine.*;

@Component
@Command(
        name = "health",
        description = "Health check of command line application accepting count param."
)
@Slf4j
public class HealthCheckCommand implements Runnable {

    @Option(names = "--name", description = "Prints [name]")
    String name;

    @Override
    public void run() {
        log.debug("Command for health check received.");
        System.out.println("It's alive!");
        if (name != null) {
            log.debug("Command for health check with name {} received.", name);
            System.out.println("Hello " + name);
        }
    }
}

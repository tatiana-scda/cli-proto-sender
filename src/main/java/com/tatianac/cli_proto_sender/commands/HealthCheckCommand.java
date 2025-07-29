package com.tatianac.cli_proto_sender.commands;

import picocli.CommandLine;

import static picocli.CommandLine.*;

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
        System.out.println("It's alive!");
        if (name != null) {
            System.out.println("Hello " + name);
        }
    }

    public static void main(String... args) {
        new CommandLine(new HealthCheckCommand()).execute(args);
    }
}
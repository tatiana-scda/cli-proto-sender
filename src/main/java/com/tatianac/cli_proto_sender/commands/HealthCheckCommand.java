package com.tatianac.cli_proto_sender.commands;

import picocli.CommandLine;

import static picocli.CommandLine.*;

@Command(
        name = "health",
        description = "Health check of command line application"
)
public class HealthCheckCommand implements Runnable {
    public static void main(String[] args) {
        CommandLine.run(new HealthCheckCommand(), args);
    }

    @Override
    public void run() {
        System.out.println("It's alive!");
    }
}
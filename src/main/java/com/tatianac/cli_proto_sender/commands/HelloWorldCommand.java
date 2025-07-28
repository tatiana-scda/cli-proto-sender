package com.tatianac.cli_proto_sender.commands;

import picocli.CommandLine;

import static picocli.CommandLine.*;

@Command(
        name = "health",
        description = "Health check of command line"
)
public class HelloWorldCommand implements Runnable {
    public static void main(String[] args) {
        CommandLine.run(new HelloWorldCommand(), args);
    }

    @Override
    public void run() {
        System.out.println("It's alive!");
    }
}
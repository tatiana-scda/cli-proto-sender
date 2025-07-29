package com.tatianac.cli_proto_sender.commands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "protoExample"
)
public class ProtoCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Adding json file");
    }

    @CommandLine.Command(name = "add")
    public void addFileCommand() {
        System.out.println("Adding file");
    }

    @CommandLine.Command(name = "send")
    public void sendingProto() {
        System.out.println("Sending the proto using the file added");
    }
}
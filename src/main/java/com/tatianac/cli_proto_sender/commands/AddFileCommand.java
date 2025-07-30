package com.tatianac.cli_proto_sender.commands;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import picocli.CommandLine;

import java.io.File;

import java.io.InputStream;
import java.util.Scanner;

import static picocli.CommandLine.*;

@Slf4j
public class AddFileCommand implements Runnable {

    @Option(
            names = "--file",
            required = false,
            description = "Get a JSON file."
    )
    private File file;

    public void run() {
        try {
            var proto = toProto(toObject().toString());
            log.debug("Proto generated: {}", proto);

            System.out.println(proto); // TODO ENVIAR
        } catch (InvalidProtocolBufferException e) {
            log.error("Error when parsing file to proto.");
            throw new RuntimeException(e);
        }
    }

    private Struct toProto(String json) throws InvalidProtocolBufferException {
        Struct.Builder structBuilder = Struct.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(json, structBuilder);
        return structBuilder.build();
    }

    private Object toObject() {
        JSONParser parser = new JSONParser();
        Object object;

        if (file == null) {
            log.info("No document found. Continuing with default file.");

            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classloader.getResourceAsStream("person-example.json");

            if (stream != null) {
                try {
                    log.info("Default file parsed correctly.");
                    object = parser.parse(streamToString(stream));
                } catch (ParseException e) {
                    log.error("Error when parsing default file.");
                    throw new RuntimeException(e);
                }

            } else {
                log.error("Couldn't find default file.");
                throw new RuntimeException();
            }
        } else {
            log.info("Continuing with received file.");
            try {
                object = parser.parse(file.toString());

            } catch (ParseException e) {
                log.error("Error when parsing received file.");
                throw new RuntimeException(e);
            }
        }

        log.debug("Final parsed file: {}", object);
        return object;
    }

    private String streamToString(InputStream stream) {
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next():"";
    }

    public static void main(String[] args) {
        new CommandLine(new AddFileCommand()).execute(args);
    }
}
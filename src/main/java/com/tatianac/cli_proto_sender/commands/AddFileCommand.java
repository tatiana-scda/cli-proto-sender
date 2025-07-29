package com.tatianac.cli_proto_sender.commands;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import picocli.CommandLine;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import static picocli.CommandLine.*;

public class AddFileCommand implements Runnable {

    @Option(
            names = "--file",
            required = false,
            description = "Get a JSON file."
    )
    private File file;

    public void run() {
        JSONParser parser = new JSONParser();
        Object object;

        if (file == null) {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classloader.getResourceAsStream("person-example.json");

            if (stream != null) {
                try {
                    object = parser.parse(streamToString(stream));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            } else {
                throw new RuntimeException();
            }
        } else {
            try {
                object = parser.parse(file.toString());

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(object);
    }

    private String streamToString(InputStream stream) {
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static void main(String[] args) {
        new CommandLine(new AddFileCommand()).execute(args);
    }
}
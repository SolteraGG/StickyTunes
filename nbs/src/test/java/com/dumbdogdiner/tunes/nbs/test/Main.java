package com.dumbdogdiner.tunes.nbs.test;

import com.dumbdogdiner.tunes.nbs.NbsReader;

import java.io.File;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        var resource = Main.class.getClassLoader().getResource("song2.nbs");

        if (resource == null) {
            System.out.println("resource was not found.");
            return;
        }

        var reader = NbsReader.createFromFile(new File(resource.toURI()));

        if (reader == null) {
            System.out.println("reader was not constructed.");
            return;
        }

        System.out.println("--- song details ---");
        System.out.println(reader.getName());
        System.out.println(reader.getAuthor());
        System.out.println(reader.getDescription());

        System.out.println(reader.getNotes().size());
        var note = reader.getNotes().get(0);
        System.out.println(note.getKey());
    }
}

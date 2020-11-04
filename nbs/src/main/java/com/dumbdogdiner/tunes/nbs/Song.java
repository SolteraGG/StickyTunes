package com.dumbdogdiner.tunes.nbs;

import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A song read by the NBS reader.
 */
public class Song {
    /**
     * Create a song from a file reference.
     * @param file The NBS file
     * @return {@link Song}
     */
    private static Song createFromFile(File file) {
        var reader = NbsReader.createFromFile(file);
        return new Song(reader, reader.getNotes(), reader.getLayers());
    }

    /**
     * Holds details about a song.
     */
    public static class SongDetails {
        @Getter
        private String name;

        @Getter
        private String description;

        @Getter
        private String author;

        @Getter
        private String originalAuthor;

        public SongDetails() {}

    }

    /**
     * The array of notes that make up this song.
     */
    @Getter
    private ArrayList<Note> notes = new ArrayList<>();

    /**
     * The array of layers that make up this song.
     */
    @Getter
    private ArrayList<Layer> layers = new ArrayList<>();

    /**
     * Construct a new song with the given notes and layers.
     * @param notes The notes to use
     * @param layers The layers to use
     */
    public Song(NbsReader in, ArrayList<Note> notes, Layer[] layers) {
        this.notes.addAll(notes);
        this.layers.addAll(Arrays.asList(layers));
    }
}

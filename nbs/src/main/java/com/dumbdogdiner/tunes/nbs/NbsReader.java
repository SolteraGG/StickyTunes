package com.dumbdogdiner.tunes.nbs;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Reads NBS block files.
 */
public class NbsReader {
    /**
     * Read a string as it is stored in the NBS file.
     * @param in The data input
     * @return {@link String}
     */
    private static String readString(ByteArrayDataInput in) {
        var len = Integer.reverseBytes(in.readInt());
        byte[] bytes = new byte[len];

        // read bytes
        for (var i =0; i < len; i++) {
            bytes[i] = in.readByte();
        }

        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Create an NBS reader from a file.
     * @param file The file to read
     * @return {@link NbsReader}
     */
    public static NbsReader createFromFile(File file) {
        try {
            return new NbsReader(ByteStreams.newDataInput(Files.readAllBytes(file.toPath())));
        } catch(IOException err) {
            return null;
        }
    }

    /**
     * Create an NBS reader from a byte array.
     * @param bytes The bytes to read
     * @return {@link NbsReader}
     */
    public static NbsReader createFromBytes(byte[] bytes) {
        return new NbsReader(ByteStreams.newDataInput(bytes));
    }

    /**
     * Returns true if the NBS file read was in
     */
    @Getter
    private Boolean isOldFormat = false;

    /**
     * Returns the version of the NBS file.
     */
    @Getter
    private final Integer version;

    /**
     * Returns the number of default instruments.
     */
    @Getter
    private final Integer defaultInstrumentCount;

    /**
     * Returns the length of the song in ticks.
     */
    @Getter
    private final Short length;

    /**
     * Get the number of layers in this NBS file.
     */
    @Getter
    private final Short layerCount;

    /**
     * Get the name of the song.
     */
    @Getter
    private final String name;

    /**
     * Get the name of the author of the song.
     */
    @Getter
    private final String author;

    /**
     * Get the name of the original author of the song.
     */
    @Getter
    private final String originalAuthor;

    /**
     * Get the description of the song.
     */
    @Getter
    private final String description;

    /**
     * Get the tempo of the song.
     */
    @Getter
    private final Short tempo;

    /**
     * Get the time signature of the song. If this is 3, then the signature is 3/4.
     * Default is 4. This value ranges from 2-8.
     */
    @Getter
    private final Short timeSignature;

    /**
     * Get whether the song has looping enabled.
     */
    @Getter
    private Boolean loopingEnabled = false;

    /**
     * Get the number of times this song will loop.
     */
    @Getter
    private final Short maxLoopCount;

    /**
     * Get the tick this song loops back to.
     */
    @Getter
    private final Short loopStartTick;

    @Getter
    private ArrayList<Note> notes;

    @Getter
    private Layer[] layers;

    /**
     * Construct a new NBS reader from a byte array.
     * @param in {@link ByteArrayDataInput}
     */
    public NbsReader(ByteArrayDataInput in) {
        // The first 2 bytes are always zero. In the old NBS format, this used to be song length, which can never be zero.
        if (Short.reverseBytes(in.readShort()) != 0) {
            isOldFormat = true;
        }

        this.version = Integer.reverseBytes(in.readByte());
        this.defaultInstrumentCount = (int) Short.reverseBytes(in.readByte());
        this.length = Short.reverseBytes(in.readShort());
        this.layerCount = Short.reverseBytes(in.readShort());

        // Read in strings.
        this.name = readString(in);
        this.author = readString(in);
        this.originalAuthor = readString(in);
        this.description = readString(in);

        this.tempo = Short.reverseBytes(in.readShort());

        // skip auto-saving and auto-save length - not needed
        in.skipBytes(2);

        this.timeSignature = Short.reverseBytes(in.readByte());

        // skip minutes spent, left-clicks, right-clicks, noteblocks added, noteblocks removed
        in.skipBytes(20);

        // read in the midi file - this is useless
        readString(in);

        if (Integer.reverseBytes(in.readByte()) == 1) {
            this.loopingEnabled = true;
        }
        this.maxLoopCount = Short.reverseBytes(in.readByte());
        this.loopStartTick = Short.reverseBytes(in.readShort());

        var end = false;
        var tick = -1;
        this.notes = new ArrayList<>();

        while(true) {
            // The amount of "jumps" to the next tick with at least one note block in it. We start at tick -1.
            // If the amount of jumps is 0, the program will stop reading and proceed to the next part.
            var jumps = Short.reverseBytes(in.readShort());
            tick += jumps;

            if (jumps == 0) {
                continue;
            }

            // Once we have found an active tick, we read the amount of vertical jumps to the next layer.
            // We start at layer -1. If this is 0, we go back to step 1. If not, we have found a note block!
            var layerJumps = Short.reverseBytes(in.readShort());
            if (layerJumps == 0) {
                continue;
            }

            var layer = -1;
            layer += layerJumps;

            var note = new Note(in);
            notes.add(note);

            if (tick == this.getLength()) {
                break;
            }
        }
    }
}

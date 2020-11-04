package com.dumbdogdiner.tunes.nbs;

import com.google.common.io.ByteArrayDataInput;
import lombok.Getter;

/**
 * Represents a note in the NBS file.
 */
public class Note {
    @Getter
    private final Short key;

    @Getter
    private final Short velocity;

    @Getter
    private final Short panning;

    @Getter
    private final Short pitch;

    /**
     * Construct a new note from the byte input from the NBS reader
     * @param in The byte input
     */
    public Note(ByteArrayDataInput in) {
        this.key = Short.reverseBytes(in.readByte());
        this.velocity = Short.reverseBytes(in.readByte());
        this.panning = Short.reverseBytes(in.readByte());
        this.pitch = Short.reverseBytes(in.readShort());
    }
}

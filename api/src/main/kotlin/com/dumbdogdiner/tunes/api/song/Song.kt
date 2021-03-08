package com.dumbdogdiner.tunes.api.song

import com.dumbdogdiner.tunes.api.song.temperament.Temperament

data class Song(
	/**
	 * Metadata for this song.
	 */
	val metadata: SongMetadata,

	/**
	 * The temperament this song should be played with.
	 */
	val temperament: Temperament,

	/**
	 * The notes that make up this song.
	 */
	val notes: List<Note>
)

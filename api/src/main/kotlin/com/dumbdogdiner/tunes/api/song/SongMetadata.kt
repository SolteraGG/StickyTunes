package com.dumbdogdiner.tunes.api.song

/**
 * Represents song metadata.
 */
data class SongMetadata(
	/**
	 * The length of this song in ticks.
	 */
	val songLength: Int,

	/**
	 * The name of this song.
	 */
	val songName: String,

	/**
	 * The author of the song
	 */
	val songAuthor: String,

	/**
	 * The tempo of the song.
	 */
	val songTempo: Int,

	/**
	 * Whether or not this song loops.
	 */
	val looping: Boolean,

	/**
	 * The maximum number of loops this song will go through.
	 */
	val maxLoopCount: Int,

	/**
	 * The tick the player should return to when looping.
	 */
	val loopStartTick: Int
)

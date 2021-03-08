package com.dumbdogdiner.tunes.nbs

import com.dumbdogdiner.tunes.api.song.SongMetadata

/**
 * File metadata for an NBS file.
 */
data class NbsMetadata(
	val version: Int,
	val instrumentCount: Int,
	val songLength: Int,
	val layerCount: Int,
	val songName: String,
	val songAuthor: String,
	val songOriginalAuthor: String,
	val songDescription: String,
	val songTempo: Int,
	val timeSignature: Int,
	val looping: Boolean,
	val maxLoopCount: Int,
	val loopStartTick: Int
) {
	/**
	 * Cast this song metadata into a StickyTunes song metadata object.
	 */
	fun toMetadata(): SongMetadata {
		return SongMetadata(
			this.songLength,
			this.songName,
			this.songAuthor,
			this.songTempo,
			this.looping,
			this.maxLoopCount,
			this.loopStartTick
		)
	}
}

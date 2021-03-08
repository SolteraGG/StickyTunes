package com.dumbdogdiner.tunes.api.song.temperament

import com.dumbdogdiner.tunes.api.song.Pitch

interface Temperament {
	/**
	 * The name of this temperament.
	 */
	val name: String

	/**
	 * Initialize this temperament and compute appropriate values.
	 */
	fun initialize();

	/**
	 * Convert the target note into an MC pitch.
	 */
	fun keyToPitch(note: Pitch): Double
}

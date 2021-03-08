package com.dumbdogdiner.tunes.api.song.temperament

import com.dumbdogdiner.tunes.api.song.Pitch
import kotlin.math.pow

/**
 * An implementation of the tuning system used by most modern music.
 */
object EqualTemperament : Temperament {
	private val pitches = mutableMapOf<Pitch, Double>()

	override val name: String
		get() = "equal"

	/**
	 * Generates the equal temperament pitch multipliers using the 12th root of 2.
	 */
	override fun initialize() {
		var multiplier = 1.0
		val twelfthRoot2 = 2.0.pow(1.0 / 12)
		// F3 down to A0
		for (i in Pitch.F3.ordinal downTo 0) {
			multiplier /= twelfthRoot2
			pitches[Pitch.values()[i]] = multiplier
		}
		// G3 up to C8
		multiplier = 1.0
		for (i in Pitch.G3.ordinal..Pitch.C7.ordinal) {
			multiplier *= twelfthRoot2
			pitches[Pitch.values()[i]] = multiplier
		}
		// ensure F#3 doesn't feel left out :3
		pitches[Pitch.F3_SHARP] = 1.0
	}

	override fun keyToPitch(note: Pitch): Double {
		return pitches[note]!!
	}
}

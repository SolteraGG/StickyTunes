package com.dumbdogdiner.tunes.api.song

import org.bukkit.Sound

/**
 * Enum of possible instruments.
 */
enum class Instrument {
	PIANO,
	DOUBLE_BASS,
	BASS_DRUM,
	SNARE_DRUM,
	CLICK,
	GUITAR,
	FLUTE,
	BELL,
	CHIME,
	XYLOPHONE,
	IRON_XYLOPHONE,
	COW_BELL,
	DIDGERIDOO,
	BIT,
	BANJO,
	PLING;

	/**
	 * Convert the instrument into its respective Minecraft sound.
	 */
	fun toMinecraftSound(): Sound {
		return when (this) {
			PIANO -> Sound.BLOCK_NOTE_BLOCK_HARP
			DOUBLE_BASS -> Sound.BLOCK_NOTE_BLOCK_BASS
			BASS_DRUM -> Sound.BLOCK_NOTE_BLOCK_BASEDRUM
			SNARE_DRUM -> Sound.BLOCK_NOTE_BLOCK_SNARE
			CLICK -> Sound.BLOCK_NOTE_BLOCK_BASEDRUM
			GUITAR -> Sound.BLOCK_NOTE_BLOCK_GUITAR
			FLUTE -> Sound.BLOCK_NOTE_BLOCK_FLUTE
			BELL -> Sound.BLOCK_NOTE_BLOCK_BELL
			CHIME -> Sound.BLOCK_NOTE_BLOCK_CHIME
			XYLOPHONE -> Sound.BLOCK_NOTE_BLOCK_XYLOPHONE
			IRON_XYLOPHONE -> Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE
			COW_BELL -> Sound.BLOCK_NOTE_BLOCK_COW_BELL
			DIDGERIDOO -> Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO
			BIT -> Sound.BLOCK_NOTE_BLOCK_BIT
			BANJO -> Sound.BLOCK_NOTE_BLOCK_BANJO
			PLING -> Sound.BLOCK_NOTE_BLOCK_PLING
		}
	}
}

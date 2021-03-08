package com.dumbdogdiner.tunes.nbs

import com.google.common.io.ByteArrayDataInput
import com.google.common.io.ByteStreams
import java.io.File

object NbsReader {
	fun read(data: ByteArrayDataInput): NbsFile {
		val metadata = this.readMetadata(data)
		return NbsFile(metadata)
	}

	private fun readMetadata(data: ByteArrayDataInput): NbsMetadata {
		// The first 2 bytes are always zero. In the old NBS format, this used to be song length, which can never be zero.
		data.readShort()
		// NBS version
		val version = data.readByte().toInt()
		val instrumentCount = data.readByte().toInt()
		val songLength = data.readLEShort().toInt()
		val layerCount = data.readLEShort().toInt()
		val songName = data.readNbsString()
		val songAuthor = data.readNbsString()
		val songOriginalAuthor = data.readNbsString()
		val songDescription = data.readNbsString()
		val songTempo = data.readLEShort().toInt()

		// unused reads
		data.skipBytes(2)
		val timeSignature = data.readByte().toInt()

		data.skipBytes(20)
		data.skipBytes(data.readInt())

		val loopEnabled = data.readBoolean()
		val maxLoopCount = data.readByte().toInt()
		val loopStartTick = data.readLEShort().toInt()

		return NbsMetadata(
			version,
			instrumentCount,
			songLength,
			layerCount,
			songName,
			songAuthor,
			songOriginalAuthor,
			songDescription,
			songTempo,
			timeSignature,
			loopEnabled,
			maxLoopCount,
			loopStartTick
		)
	}

	private fun readSongData() {

	}

	fun readBytes(bytes: ByteArray): NbsFile {
		return this.read(ByteStreams.newDataInput(bytes))
	}

	/**
	 * Read the target NBS file.
	 */
	fun readFile(file: File): NbsFile {
		if (file.extension != "nbs") {
			throw IllegalArgumentException("Target file is not a .nbs file")
		}
		return this.readBytes(file.readBytes())
	}

	/**
	 * Read the target resource file.
	 */
	fun readResource(resourceName: String): NbsFile {
		val resource = javaClass.classLoader.getResource(resourceName)
			?: throw IllegalArgumentException("Resource '$resourceName' does not exist")
		return this.readBytes(resource.readBytes())
	}

	/**
	 * Read a string from the target data input.
	 * @return A {@link String} if the read was successful.
	 */
	private fun ByteArrayDataInput.readNbsString(): String {
		// read length of string
		val length = this.readLEInt()
		// initialize byte array of target length
		val out = ByteArray(length)
		// append each byte to array
		for (i in 0 until length) {
			out[i] = this.readByte()
		}
		// return the array as a unicode string
		return out.toString(Charsets.UTF_8)
	}

	private fun ByteArrayDataInput.readLEShort(): Short {
		val short = this.readShort().toInt()
		val v0 = ((short ushr 0) and 0xFF)
		val v1 = ((short ushr 8) and 0xFF)
		return ((v1 and 0xFF) or (v0 shl 8)).toShort()
	}

	private fun ByteArrayDataInput.readLEInt(): Int {
		val int = this.readInt()
		val v0 = ((int ushr 0) and 0xFF)
		val v1 = ((int ushr 8) and 0xFF)
		val v2 = ((int ushr 16) and 0xFF)
		val v3 = ((int ushr 24) and 0xFF)
		return (v0 shl 24) or (v1 shl 16) or (v2 shl 8) or (v3 shl 0)
	}
}

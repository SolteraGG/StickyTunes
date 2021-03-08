package com.dumbdogdiner.tunes.nbs.test

import com.dumbdogdiner.tunes.nbs.NbsReader
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NbsReaderTest {
	@Test
	fun testResource() {
		val metadata = NbsReader.readResource("song.nbs")
		println(metadata)
	}
}

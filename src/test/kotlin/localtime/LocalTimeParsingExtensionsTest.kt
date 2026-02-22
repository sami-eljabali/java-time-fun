package localtime

import javatimefun.localtime.extensions.toLocalTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class LocalTimeParsingExtensionsTest {

    @Test
    fun `given multiple formats, when first fails and second succeeds, then should parse using successful format`() {
        // given
        val timeInText = "01:30 PM"

        // when
        val timeParsed = timeInText.toLocalTime("HH:mm:ss", "hh:mm a")

        // then
        assertEquals("13:30", timeParsed.toString())
    }

    @Test
    fun `given multiple invalid formats, when parsing, then should return null`() {
        // given
        val timeInText = "01:30 PM"

        // when
        val timeParsed = timeInText.toLocalTime("HH:mm:ss", "HH:mm")

        // then
        assertNull(timeParsed)
    }
}

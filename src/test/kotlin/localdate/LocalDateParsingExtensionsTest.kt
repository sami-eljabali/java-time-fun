package localdate

import javatimefun.localdate.extensions.toLocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class LocalDateParsingExtensionsTest {

    @Test
    fun `given multiple formats, when first fails and second succeeds, then should parse using successful format`() {
        // given
        val dateInText = "07/26/2023"

        // when
        val dateParsed = dateInText.toLocalDate("yyyy-MM-dd", "MM/dd/yyyy")

        // then
        assertEquals("2023-07-26", dateParsed.toString())
    }

    @Test
    fun `given multiple invalid formats, when parsing, then should return null`() {
        // given
        val dateInText = "07/26/2023"

        // when
        val dateParsed = dateInText.toLocalDate("yyyy-MM-dd", "dd-MM-yyyy")

        // then
        assertNull(dateParsed)
    }
}

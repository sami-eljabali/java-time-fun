package localdatetime

import javatimefun.localdatetime.extensions.print
import javatimefun.localdatetime.extensions.toLocalDateTime
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import java.time.LocalDateTime

class LocalDateTimeParsingExtensionsTest {

    companion object {
        private const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val ISO_8601_S = "yyyy-MM-dd'T'HH:mm:ss.S'Z'"
        private const val ISO_8601_SS = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'"
        private const val ISO_8601_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        private const val ISO_8601_4S = "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"
        private const val ISO_8601_5S = "yyyy-MM-dd'T'HH:mm:ss.SSSSS'Z'"
        private const val ISO_8601_6S = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"
    }
    
    @Test
    fun `given date in ISO_8601, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56"
        val dateInTextWithZ = "${dateInText}Z"

        // when
        val dateParsed: LocalDateTime = dateInTextWithZ.toLocalDateTime() ?: throw RuntimeException("Failed to parse")
        val dateParsedWithZ: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInTextWithZ, dateParsed.print(ISO_8601))
        assertEquals(dateInTextWithZ, dateParsedWithZ.print(ISO_8601))
        assertEquals("2023-07-26T12:34:56.000000Z", dateParsed.print(ISO_8601_6S))
        assertEquals("2023-07-26T12:34:56.000000Z", dateParsedWithZ.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_S, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.1Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_S))
        assertEquals("2023-07-26T12:34:56.100000Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_SS, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.12Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_SS))
        assertEquals("2023-07-26T12:34:56.120000Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_SSS, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.123Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_SSS))
        assertEquals("2023-07-26T12:34:56.123000Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_4S, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.1234Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_4S))
        assertEquals("2023-07-26T12:34:56.123400Z", dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_5S, when parsed without format & converted to date, then should be null`() {
        // given
        val dateInText = "2023-07-26T12:34:56.12345"
        val dateInTextWithZ = "${dateInText}Z"

        // when
        val dateParsed: LocalDateTime = dateInTextWithZ.toLocalDateTime() ?: throw RuntimeException("Failed to parse")
        val dateParsedWithZ: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInTextWithZ, dateParsed.print(ISO_8601_5S))
        assertEquals(dateInTextWithZ, dateParsedWithZ.print(ISO_8601_5S))
        assertEquals("2023-07-26T12:34:56.123450Z", dateParsed.print(ISO_8601_6S))
        assertEquals("2023-07-26T12:34:56.123450Z", dateParsedWithZ.print(ISO_8601_6S))
    }

    @Test
    fun `given date in ISO_8601_6S, when parsed without format & converted to date, then should match when printed back to text`() {
        // given
        val dateInText = "2023-07-26T12:34:56.123456Z"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(ISO_8601_6S))
    }

    @Test
    fun `given multiple formats, when first fails and second succeeds, then should parse using successful format`() {
        // given
        val dateInText = "07/26/2023 12:34:56"

        // when
        val dateParsed: LocalDateTime = dateInText.toLocalDateTime(
            "yyyy-MM-dd HH:mm:ss",
            "MM/dd/yyyy HH:mm:ss"
        ) ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals("2023-07-26T12:34:56", dateParsed.toString())
    }

}

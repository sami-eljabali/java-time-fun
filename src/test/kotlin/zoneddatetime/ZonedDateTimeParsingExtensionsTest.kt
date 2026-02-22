package zoneddatetime

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import javatimefun.zoneddatetime.extensions.toZonedDateTime
import javatimefun.zoneddatetime.extensions.print
import java.lang.RuntimeException
import java.time.ZonedDateTime

class ZonedDateTimeParsingExtensionsTest {

    companion object {
        private const val YYYY_MM_DD_DASH_T_HH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssXXX"
        private const val YYYY_MM_DD_DASH_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss"
    }

    @Test
    fun `given date in ISO8601 Zulu format, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T12:34:56Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

    @Test
    fun `given date in ISO8601 with UTC offset, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T12:34:56+00:00"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals("2024-02-17T12:34:56", dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS))
    }

    @Test
    fun `given date in ISO8601 with negative offset, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T12:34:56-08:00"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

    @Test
    fun `given date with milliseconds precision, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T12:34:56.123Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))
    }

    @Test
    fun `given date with microseconds precision, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T12:34:56.123456Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
    }

    @Test
    fun `given date at midnight UTC, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T00:00:00Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

    @Test
    fun `given date at end of day UTC, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T23:59:59.999999Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"))
    }

    @Test
    fun `given date at end of day UTC #2, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-17T23:59:59.999999Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals("2024-02-17T23:59:59.999999", dateParsed.print("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"))
    }

    @Test
    fun `given leap year date, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-02-29T12:34:56Z"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

    @Test
    fun `given date with max timezone offset, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-12-31T23:59:59+14:00"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

    @Test
    fun `given date with min timezone offset, when parsed & converted, then should match when printed back`() {
        // given
        val dateInText = "2024-12-31T23:59:59-12:00"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime() ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals(dateInText, dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

    @Test
    fun `given multiple formats, when first fails and second succeeds, then should parse using successful format`() {
        // given
        val dateInText = "2024-02-17 12:34:56+0000"

        // when
        val dateParsed: ZonedDateTime = dateInText.toZonedDateTime(
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd HH:mm:ssZ"
        ) ?: throw RuntimeException("Failed to parse")

        // then
        assertEquals("2024-02-17T12:34:56Z", dateParsed.print(YYYY_MM_DD_DASH_T_HH_MM_SS_XXX))
    }

}

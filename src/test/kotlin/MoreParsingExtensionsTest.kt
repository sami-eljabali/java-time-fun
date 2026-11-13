import javatimefun.monthday.extensions.toMonthDay
import javatimefun.offsetdatetime.extensions.toOffsetDateTime
import javatimefun.year.extensions.toYear
import javatimefun.yearmonth.extensions.toYearMonth
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.MonthDay
import java.time.OffsetDateTime
import java.time.Year
import java.time.YearMonth
import java.time.ZoneOffset

class MoreParsingExtensionsTest {

    @Test
    fun `MonthDay parses ISO, falls through formats, keeps Feb 29, and nulls on garbage`() {
        assertEquals(MonthDay.of(12, 25), "--12-25".toMonthDay())
        assertEquals(MonthDay.of(2, 29), "02/29".toMonthDay("MM-dd", "MM/dd"))
        assertNull("13/01".toMonthDay("MM/dd"))
        assertNull("nope".toMonthDay())
    }

    @Test
    fun `YearMonth parses ISO and custom formats`() {
        assertEquals(YearMonth.of(2024, 11), "2024-11".toYearMonth())
        assertEquals(YearMonth.of(2024, 11), "11/2024".toYearMonth("yyyy-MM", "MM/yyyy"))
        assertNull("11/2024".toYearMonth("yyyy-MM"))
    }

    @Test
    fun `Year parses ISO and custom formats`() {
        assertEquals(Year.of(2024), "2024".toYear())
        assertEquals(Year.of(2024), "FY2024".toYear("'FY'yyyy"))
        assertNull("twenty".toYear())
    }

    @Test
    fun `OffsetDateTime parses ISO and custom formats, keeping the offset`() {
        val expected = OffsetDateTime.of(2024, 11, 15, 12, 34, 56, 0, ZoneOffset.ofHours(1))
        assertEquals(expected, "2024-11-15T12:34:56+01:00".toOffsetDateTime())
        assertEquals(expected, "2024-11-15 12:34:56 +0100".toOffsetDateTime("yyyy-MM-dd HH:mm:ss Z"))
        assertNull("2024-11-15T12:34:56".toOffsetDateTime())
    }

    @Test
    fun `invalid pattern returns null instead of throwing`() {
        assertNull("2024".toYear("{{bad"))
    }
}

import javatimefun.monthday.extensions.print
import javatimefun.offsetdatetime.extensions.compareDay
import javatimefun.offsetdatetime.extensions.getDayDifference
import javatimefun.offsetdatetime.extensions.getHourDifference
import javatimefun.offsetdatetime.extensions.isAfterTime
import javatimefun.offsetdatetime.extensions.isEqualDay
import javatimefun.offsetdatetime.extensions.isEqualTime
import javatimefun.offsetdatetime.extensions.print
import javatimefun.year.extensions.print
import javatimefun.yearmonth.extensions.print
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.MonthDay
import java.time.OffsetDateTime
import java.time.Year
import java.time.YearMonth
import java.time.ZoneOffset

class NewTypesExtensionsTest {

    @Test
    fun `print formats each new type`() {
        assertEquals("Dec 25", MonthDay.of(12, 25).print("MMM d"))
        assertEquals("November 2024", YearMonth.of(2024, 11).print("MMMM yyyy"))
        assertEquals("24", Year.of(2024).print("yy"))
        assertEquals(
            "2024-11-15 12:00 +0100",
            OffsetDateTime.of(2024, 11, 15, 12, 0, 0, 0, ZoneOffset.ofHours(1)).print("yyyy-MM-dd HH:mm Z"),
        )
    }

    @Test
    fun `OffsetDateTime comparisons convert param to context's offset`() {
        val utc = OffsetDateTime.of(2024, 11, 15, 23, 0, 0, 0, ZoneOffset.UTC)
        val plus3 = OffsetDateTime.of(2024, 11, 16, 1, 0, 0, 0, ZoneOffset.ofHours(3)) // 22:00Z on the 15th
        assertTrue(utc.isEqualDay(plus3))
        assertEquals(0, utc.compareDay(plus3))
        assertEquals(0, utc.getDayDifference(plus3))
        assertEquals(-1, utc.getHourDifference(plus3))
        assertTrue(utc.isAfterTime(plus3))
        assertFalse(utc.isEqualTime(plus3))
        assertTrue(utc.isEqualTime(utc.withOffsetSameInstant(ZoneOffset.ofHours(-5))))
    }
}

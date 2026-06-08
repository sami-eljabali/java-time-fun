package zoneddatetime

import javatimefun.localtime.extensions.toLocalTime
import javatimefun.localtime.extensions.print
import javatimefun.zoneddatetime.ZonedDateTimeFactory
import javatimefun.zoneddatetime.extensions.atEndOfDay
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import javatimefun.zoneddatetime.extensions.getLast
import javatimefun.zoneddatetime.extensions.getNext
import javatimefun.zoneddatetime.extensions.isEqualDay
import javatimefun.zoneddatetime.extensions.withLocalTime
import javatimefun.zoneddatetime.extensions.atStartOfMonth
import javatimefun.zoneddatetime.extensions.atEndOfMonth
import javatimefun.zoneddatetime.extensions.atStartOfDay
import javatimefun.zoneddatetime.extensions.minusMillis
import javatimefun.zoneddatetime.extensions.plusMillis
import java.time.DayOfWeek
import java.time.LocalTime

class ZonedDateTimeMutatingExtensionsTest {

    companion object {
        private const val HH_MM_SS_AM = "hh:mm:ss a"
    }

    @Test
    fun `given date, when adding milliseconds, then should advance nanos`() {
        // given
        val date = ZonedDateTimeFactory.new(2025, 6, 15, hourIn24 = 12)

        // when
        val result = date.plusMillis(500)

        // then
        Assertions.assertEquals(0, result.second)
        Assertions.assertEquals(500_000_000, result.nano)
    }

    @Test
    fun `given date near a second boundary, when adding milliseconds, then should roll over`() {
        // given
        val date = ZonedDateTimeFactory.new(2025, 6, 15, hourIn24 = 12, nano = 800_000_000)

        // when
        val result = date.plusMillis(500)

        // then
        Assertions.assertEquals(1, result.second)
        Assertions.assertEquals(300_000_000, result.nano)
    }

    @Test
    fun `given date, when subtracting after adding milliseconds, then should return original`() {
        // given
        val date = ZonedDateTimeFactory.new(2025, 6, 15, hourIn24 = 12)

        // when
        val result = date.plusMillis(750).minusMillis(750)

        // then
        Assertions.assertEquals(date, result)
    }

    @Test
    fun `given date, when adding negative milliseconds, then should behave like minusMillis`() {
        // given
        val date = ZonedDateTimeFactory.new(2025, 6, 15, hourIn24 = 12)

        // when & then
        Assertions.assertEquals(date.minusMillis(250), date.plusMillis(-250))
    }

    @Test
    fun `given date is on a Monday, when looking for last Monday inclusive, then should return same date`() {
        // given
        val dateA = ZonedDateTimeFactory.new(2021, 6, 7)

        // when
        val resultLastMondayInclusive = dateA.getLast(DayOfWeek.MONDAY, countingInThisDay = true)

        // then
        assertTrue(dateA.isEqualDay(resultLastMondayInclusive))
    }

    @Test
    fun `given date is on a Monday, when looking for last Monday exclusive, then should return date minus 1 week`() {
        // given
        val dateA = ZonedDateTimeFactory.new(2021, 6, 14)
        val expectedDate = ZonedDateTimeFactory.new(2021, 6, 7)

        // when
        val resultLastMondayExclusive = dateA.getLast(DayOfWeek.MONDAY)

        // then
        assertTrue(expectedDate.isEqualDay(resultLastMondayExclusive))
    }

    @Test
    fun `given date is on a Monday, when looking for next Monday inclusive, then should return same date`() {
        // given
        val dateA = ZonedDateTimeFactory.new(2021, 6, 7)

        // when
        val resultNextMondayInclusive = dateA.getNext(DayOfWeek.MONDAY, countingInThisDay = true)

        // then
        assertTrue(dateA.isEqualDay(resultNextMondayInclusive))
    }

    @Test
    fun `given date is on a Monday, when looking for next Monday exclusive, then should return date plus 1 week`() {
        // given
        val dateA = ZonedDateTimeFactory.new(2021, 6, 7)
        val expectedDate = ZonedDateTimeFactory.new(2021, 6, 14)

        // when
        val resultNextMondayExclusive = dateA.getNext(DayOfWeek.MONDAY)

        // then
        assertTrue(expectedDate.isEqualDay(resultNextMondayExclusive))
    }

    @Test
    fun `given date A, when adjusted time, then should properly apply time`() {
        // given
        var dateA = ZonedDateTimeFactory.new(2020, 3, 20)
        val timeText = "07:35:11 AM"
        val time: LocalTime = timeText.toLocalTime(HH_MM_SS_AM)  ?: throw RuntimeException("Failed to parse")

        // when
        dateA = dateA.withLocalTime(time)
        val resultTime = dateA.toLocalTime()
        val resultText = resultTime.print(HH_MM_SS_AM)

        // then
        Assertions.assertEquals(time, resultTime)
        Assertions.assertEquals(timeText, resultText)
    }

    @Test
    fun `given date, when getting start of month, then should return first day at start of day`() {
        // given
        val date = ZonedDateTimeFactory.new(2021, 6, 15, hourIn24 = 12)
        val expected = ZonedDateTimeFactory.new(2021, 6, 1).atStartOfDay()

        // when
        val result = date.atStartOfMonth()

        // then
        assertTrue(expected.isEqual(result))
    }

    @Test
    fun `given date, when getting end of month, then should return last day at end of day`() {
        // given
        val date = ZonedDateTimeFactory.new(2021, 6, 15)
        val expected = ZonedDateTimeFactory.new(2021, 6, 30).atEndOfDay()

        // when
        val result = date.atEndOfMonth()

        // then
        assertTrue(expected.isEqual(result))
    }
}
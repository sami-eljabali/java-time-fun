package localdatetime

import javatimefun.ZoneIds
import javatimefun.localdatetime.extensions.atEndOfMonth
import javatimefun.localdatetime.extensions.atStartOfMonth
import javatimefun.localdatetime.extensions.fromUtcToZone
import javatimefun.localdatetime.extensions.fromZoneToUtc
import javatimefun.localdatetime.extensions.fromZoneToZone
import javatimefun.localdatetime.extensions.minusMillis
import javatimefun.localdatetime.extensions.plusMillis
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LocalDateTimeMutatingTest {
    @Test
    fun `plusMillis adds milliseconds as nanos`() {
        val dateTime = LocalDateTime.of(2025, 6, 15, 12, 0, 0, 0)
        val result = dateTime.plusMillis(500)

        assertEquals(12, result.hour)
        assertEquals(0, result.minute)
        assertEquals(0, result.second)
        assertEquals(500_000_000, result.nano)
    }

    @Test
    fun `plusMillis rolls over into the next second`() {
        val dateTime = LocalDateTime.of(2025, 6, 15, 12, 0, 0, 800_000_000)
        val result = dateTime.plusMillis(500)

        assertEquals(1, result.second)
        assertEquals(300_000_000, result.nano)
    }

    @Test
    fun `minusMillis is the inverse of plusMillis`() {
        val dateTime = LocalDateTime.of(2025, 6, 15, 12, 0, 0, 0)

        assertEquals(dateTime, dateTime.plusMillis(750).minusMillis(750))
    }

    @Test
    fun `plusMillis with negative argument behaves like minusMillis`() {
        val dateTime = LocalDateTime.of(2025, 6, 15, 12, 0, 0, 0)

        assertEquals(dateTime.minusMillis(250), dateTime.plusMillis(-250))
    }

    @Test
    fun `atStartOfMonth sets time to first day of month at start of day`() {
        val dateTime = LocalDateTime.of(2025, 6, 15, 12, 30)
        val result = dateTime.atStartOfMonth()

        assertEquals(2025, result.year)
        assertEquals(6, result.monthValue)
        assertEquals(1, result.dayOfMonth)
        assertEquals(0, result.hour)
        assertEquals(0, result.minute)
        assertEquals(0, result.second)
        assertEquals(0, result.nano)
    }

    @Test
    fun `atEndOfMonth sets time to last day of month at end of day`() {
        val dateTime = LocalDateTime.of(2025, 6, 15, 12, 30)
        val result = dateTime.atEndOfMonth()

        assertEquals(2025, result.year)
        assertEquals(6, result.monthValue)
        assertEquals(30, result.dayOfMonth)
        assertEquals(23, result.hour)
        assertEquals(59, result.minute)
        assertEquals(59, result.second)
        assertEquals(999999999, result.nano)
    }

    @Test
    fun `fromZoneToZone returns same time if zones are identical`() {
        val time = LocalDateTime.of(2025, 6, 15, 12, 0)
        val zone = ZoneIds.AFRICA_CAIRO

        val converted = time.fromZoneToZone(zone, zone)
        assertEquals(time, converted, "When fromZoneId and toZoneId are the same, time should not change")
    }

    @Test
    fun `fromZoneToZone converts correctly between different zones`() {
        val timeInTokyo = LocalDateTime.of(2025, 6, 15, 12, 0) // Noon in Tokyo
        val fromZone = ZoneIds.AFRICA_CAIRO
        val toZone = ZoneIds.AMERICA_ARGENTINA_Catamarca

        val converted = timeInTokyo.fromZoneToZone(fromZone, toZone)

        // Convert via ZonedDateTime for expected result
        val expected = timeInTokyo.atZone(fromZone)
            .withZoneSameInstant(toZone)
            .toLocalDateTime()

        assertEquals(expected, converted)
    }

    @Test
    fun `fromUtcToZone converts correctly`() {
        val timeUtc = LocalDateTime.of(2025, 6, 15, 12, 0)
        val toZone = ZoneIds.AFRICA_CAIRO

        val converted = timeUtc.fromUtcToZone(toZone)
        val expected = timeUtc.atZone(ZoneIds.UTC)
            .withZoneSameInstant(toZone)
            .toLocalDateTime()

        assertEquals(expected, converted)
    }

    @Test
    fun `fromZoneToUtc converts correctly`() {
        val timeInTokyo = LocalDateTime.of(2025, 6, 15, 12, 0)
        val fromZone = ZoneIds.AFRICA_CAIRO

        val converted = timeInTokyo.fromZoneToUtc(fromZone)
        val expected = timeInTokyo.atZone(fromZone)
            .withZoneSameInstant(ZoneIds.UTC)
            .toLocalDateTime()

        assertEquals(expected, converted)
    }

    @Test
    fun `conversion handles DST transition correctly`() {
        // Example: US Eastern DST ends on Nov 2, 2014 at 2:00 am
        val fromZone = ZoneIds.AMERICA_NEW_YORK
        val toZone = ZoneIds.EUROPE_LONDON

        // 1:30 AM EDT (before DST ends)
        val timeBeforeDSTEnd = LocalDateTime.of(2014, 11, 2, 1, 30)

        val convertedBefore = timeBeforeDSTEnd.fromZoneToZone(fromZone, toZone)

        val expectedBefore = timeBeforeDSTEnd.atZone(fromZone)
            .withZoneSameInstant(toZone)
            .toLocalDateTime()

        assertEquals(expectedBefore, convertedBefore)

        // 2:30 AM EST (after DST ends - clocks moved back 1 hour)
        val timeAfterDSTEnd = LocalDateTime.of(2014, 11, 2, 2, 30)

        val convertedAfter = timeAfterDSTEnd.fromZoneToZone(fromZone, toZone)

        val expectedAfter = timeAfterDSTEnd.atZone(fromZone)
            .withZoneSameInstant(toZone)
            .toLocalDateTime()

        assertEquals(expectedAfter, convertedAfter)
    }
}
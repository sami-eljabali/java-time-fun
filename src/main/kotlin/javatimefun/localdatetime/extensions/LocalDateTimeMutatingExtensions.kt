package javatimefun.localdatetime.extensions

import javatimefun.ZoneIds
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

fun LocalDateTime.plusMillis(millis: Long): LocalDateTime = this.plus(millis, ChronoUnit.MILLIS)

fun LocalDateTime.minusMillis(millis: Long): LocalDateTime = this.minus(millis, ChronoUnit.MILLIS)

fun LocalDateTime.atStartOfDay(): LocalDateTime = this.withLocalTime(LocalTime.MIN)

fun LocalDateTime.atEndOfDay(): LocalDateTime = this.withLocalTime(LocalTime.MAX)

fun LocalDateTime.atStartOfMonth(): LocalDateTime =
    this.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay()

fun LocalDateTime.atEndOfMonth(): LocalDateTime =
    this.with(TemporalAdjusters.lastDayOfMonth()).atEndOfDay()

fun LocalDateTime.withLocalTime(localTime: LocalTime): LocalDateTime =
    this.withHour(localTime.hour)
        .withMinute(localTime.minute)
        .withSecond(localTime.second)
        .withNano(localTime.nano)

fun LocalDateTime.getLast(dayOfWeek: DayOfWeek, countingInThisDay: Boolean = false): LocalDateTime {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var mostRecentDay = this
    if (mostRecentDay.dayOfWeek == dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    while (mostRecentDay.dayOfWeek != dayOfWeek) {
        mostRecentDay = mostRecentDay.minusDays(1)
    }
    return mostRecentDay
}

fun LocalDateTime.getNext(dayOfWeek: DayOfWeek, countingInThisDay: Boolean = false): LocalDateTime {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var nextLocalDate = this
    if (nextLocalDate.dayOfWeek == dayOfWeek) {
        nextLocalDate = nextLocalDate.plusDays(1)
    }
    while (nextLocalDate.dayOfWeek != dayOfWeek) {
        nextLocalDate = nextLocalDate.plusDays(1)
    }
    return nextLocalDate
}

fun LocalDateTime.fromZoneToZone(fromZoneId: ZoneId, toZoneId: ZoneId): LocalDateTime {
    if (fromZoneId == toZoneId) return this
    return this
        .atZone(fromZoneId)
        .withZoneSameInstant(toZoneId)
        .toLocalDateTime()
}

fun LocalDateTime.fromUtcToZone(toZoneId: ZoneId): LocalDateTime =
    this.fromZoneToZone(ZoneIds.UTC, toZoneId)

fun LocalDateTime.fromZoneToUtc(fromZoneId: ZoneId): LocalDateTime =
    this.fromZoneToZone(fromZoneId, ZoneIds.UTC)
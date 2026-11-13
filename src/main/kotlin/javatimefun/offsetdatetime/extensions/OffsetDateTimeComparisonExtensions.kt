package javatimefun.offsetdatetime.extensions

import javatimefun.zoneddatetime.extensions.*
import java.time.OffsetDateTime

// ponytail: each delegates to its ZonedDateTime twin, which converts param to context's offset before comparing.
// See ZonedDateTimeComparisonExtensions for per-function docs.

fun OffsetDateTime.compareYear(offsetDateTimeB: OffsetDateTime): Int =
    toZonedDateTime().compareYear(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeYear(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeYear(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeEqualYear(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeEqualYear(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isEqualYear(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isEqualYear(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterEqualYear(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterEqualYear(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterYear(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterYear(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.compareMonth(offsetDateTimeB: OffsetDateTime): Int =
    toZonedDateTime().compareMonth(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeMonth(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeMonth(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeEqualMonth(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeEqualMonth(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isEqualMonth(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isEqualMonth(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterEqualMonth(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterEqualMonth(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterMonth(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterMonth(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.compareDay(offsetDateTimeB: OffsetDateTime): Int =
    toZonedDateTime().compareDay(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeDay(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeDay(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeEqualDay(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeEqualDay(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isEqualDay(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isEqualDay(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterEqualDay(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterEqualDay(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterDay(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterDay(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.compareTime(offsetDateTimeB: OffsetDateTime): Int =
    toZonedDateTime().compareTime(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeTime(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeTime(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isBeforeEqualTime(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isBeforeEqualTime(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isEqualTime(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isEqualTime(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterEqualTime(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterEqualTime(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.isAfterTime(offsetDateTimeB: OffsetDateTime): Boolean =
    toZonedDateTime().isAfterTime(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.getSecondDifference(offsetDateTimeB: OffsetDateTime): Long =
    toZonedDateTime().getSecondDifference(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.getMinuteDifference(offsetDateTimeB: OffsetDateTime): Long =
    toZonedDateTime().getMinuteDifference(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.getHourDifference(offsetDateTimeB: OffsetDateTime): Long =
    toZonedDateTime().getHourDifference(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.getDayDifference(offsetDateTimeB: OffsetDateTime): Long =
    toZonedDateTime().getDayDifference(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.getMonthDifference(offsetDateTimeB: OffsetDateTime): Long =
    toZonedDateTime().getMonthDifference(offsetDateTimeB.toZonedDateTime())

fun OffsetDateTime.getYearDifference(offsetDateTimeB: OffsetDateTime): Long =
    toZonedDateTime().getYearDifference(offsetDateTimeB.toZonedDateTime())

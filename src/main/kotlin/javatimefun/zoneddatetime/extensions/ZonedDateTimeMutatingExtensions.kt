package javatimefun.zoneddatetime.extensions

import java.time.DayOfWeek
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

/**
 * Works off of ZonedDateTime context.
 * @param millis  Number of milliseconds to add (may be negative).
 * @return  ZonedDateTime advanced by the given milliseconds.
 */
fun ZonedDateTime.plusMillis(millis: Long): ZonedDateTime = this.plus(millis, ChronoUnit.MILLIS)

/**
 * Works off of ZonedDateTime context.
 * @param millis  Number of milliseconds to subtract (may be negative).
 * @return  ZonedDateTime moved back by the given milliseconds.
 */
fun ZonedDateTime.minusMillis(millis: Long): ZonedDateTime = this.minus(millis, ChronoUnit.MILLIS)

/**
 * Works off of ZonedDateTime context.
 * @return  ZonedDateTime with its LocalTime set to the start of day.
 */
fun ZonedDateTime.atStartOfDay(): ZonedDateTime = this.withLocalTime(LocalTime.MIN)

/**
 * Works off of ZonedDateTime context.
 * @return  ZonedDateTime with its LocalTime set to the very last moment of the day.
 */
fun ZonedDateTime.atEndOfDay(): ZonedDateTime = this.withLocalTime(LocalTime.MAX)


/**
 * Works off of ZonedDateTime context.
 * @return ZonedDateTime set to the first day of the month at start of day.
 */
fun ZonedDateTime.atStartOfMonth(): ZonedDateTime =
    this.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay()

/**
 * Works off of ZonedDateTime context.
 * @return ZonedDateTime set to the last day of the month at end of day.
 */
fun ZonedDateTime.atEndOfMonth(): ZonedDateTime =
    this.with(TemporalAdjusters.lastDayOfMonth()).atEndOfDay()

/**
 * Works off of ZonedDateTime context.
 * @param localTime  LocalTime of which we want to copy its time to our contextual ZonedDateTime
 * @return  ZonedDateTime with its LocalTime updated to use param's.
 */
fun ZonedDateTime.withLocalTime(localTime: LocalTime): ZonedDateTime =
    this.withHour(localTime.hour)
        .withMinute(localTime.minute)
        .withSecond(localTime.second)
        .withNano(localTime.nano)

/**
 * Works off of ZonedDateTime context.
 * @param zonedDateTime  ZonedDateTime of whose time zone we want to convert context's time zone to.
 * @return  ZonedDateTime updated to new time zone if varying in time zone with param's.
 */
fun ZonedDateTime.withTimeZoneOf(zonedDateTime: ZonedDateTime): ZonedDateTime {
    if (this.zone != zonedDateTime.zone) {
        return this.withZoneSameInstant(zonedDateTime.zone)
    }
    return this
}

/**
 * Works off of ZonedDateTime context.
 * @param dayOfWeek  Monday, Tues, etc, which we want to find the last instance of when going back in time.
 * @param countingInThisDay  When true, & context's DayOfWeek matches param, then return context, else go back a week.
 * @return  ZonedDateTime of last DayOfWeek.
 */
fun ZonedDateTime.getLast(dayOfWeek: DayOfWeek, countingInThisDay: Boolean = false): ZonedDateTime {
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

/**
 * Works off of ZonedDateTime context.
 * @param dayOfWeek  Monday, Tues, etc, which we want to find the next instance of when going forward in time.
 * @param countingInThisDay  When true, & context's DayOfWeek matches param, then return context, else go next week.
 * @return  ZonedDateTime of next DayOfWeek.
 */
fun ZonedDateTime.getNext(dayOfWeek: DayOfWeek, countingInThisDay: Boolean = false): ZonedDateTime {
    if (countingInThisDay && this.dayOfWeek == dayOfWeek) {
        return this
    }
    var nextZonedDate = this
    if (nextZonedDate.dayOfWeek == dayOfWeek) {
        nextZonedDate = nextZonedDate.plusDays(1)
    }
    while (nextZonedDate.dayOfWeek != dayOfWeek) {
        nextZonedDate = nextZonedDate.plusDays(1)
    }
    return nextZonedDate
}
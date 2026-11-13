package javatimefun.monthday.extensions

import java.time.LocalDate
import java.time.MonthDay
import java.time.temporal.ChronoUnit

/**
 * Next date this MonthDay lands on, counting [from] itself (a birthday today returns today).
 * Feb 29 falls on Feb 28 in non-leap years, matching [MonthDay.atYear].
 *
 * @param from LocalDate to search from, defaults to today.
 * @return LocalDate of the next occurrence, on or after [from].
 */
fun MonthDay.nextOccurrence(from: LocalDate = LocalDate.now()): LocalDate =
    atYear(from.year).let { if (it < from) atYear(from.year + 1) else it }

/**
 * @param from LocalDate to count from, defaults to today.
 * @return Days until [nextOccurrence], 0 when [from] is the occurrence.
 */
fun MonthDay.daysUntil(from: LocalDate = LocalDate.now()): Long =
    ChronoUnit.DAYS.between(from, nextOccurrence(from))

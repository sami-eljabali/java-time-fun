package javatimefun.localtime.extensions

import java.time.LocalTime
import java.time.temporal.ChronoUnit

/**
 * Works off of LocalTime context. Wraps around midnight, e.g. 23:59:59.999 + 1ms -> 00:00:00.
 * @param millis  Number of milliseconds to add (may be negative).
 * @return  LocalTime advanced by the given milliseconds.
 */
fun LocalTime.plusMillis(millis: Long): LocalTime = this.plus(millis, ChronoUnit.MILLIS)

/**
 * Works off of LocalTime context. Wraps around midnight, e.g. 00:00:00 - 1ms -> 23:59:59.999.
 * @param millis  Number of milliseconds to subtract (may be negative).
 * @return  LocalTime moved back by the given milliseconds.
 */
fun LocalTime.minusMillis(millis: Long): LocalTime = this.minus(millis, ChronoUnit.MILLIS)

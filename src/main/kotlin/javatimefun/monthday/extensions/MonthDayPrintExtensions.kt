package javatimefun.monthday.extensions

import java.time.MonthDay
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

fun MonthDay.print(format: String, locale: Locale = Locale.US): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(locale))

package javatimefun.yearmonth.extensions

import java.time.YearMonth
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

fun YearMonth.print(format: String, locale: Locale = Locale.US): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(locale))

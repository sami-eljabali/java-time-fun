package javatimefun.year.extensions

import java.time.Year
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

fun Year.print(format: String, locale: Locale = Locale.US): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(locale))

package javatimefun.offsetdatetime.extensions

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatterBuilder
import java.util.Locale

fun OffsetDateTime.print(format: String, locale: Locale = Locale.US): String =
    this.format(DateTimeFormatterBuilder().appendPattern(format).toFormatter(locale))

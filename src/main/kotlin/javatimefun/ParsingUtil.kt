package javatimefun

import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Parses with [parse] when no formats are given, else tries each format in-order with [parseWith].
 * Null or empty formats fall back to [parse]. Returns null when nothing parses.
 */
internal fun <T> String.parseOrNull(
    formats: Array<out String?>,
    parse: (String) -> T,
    parseWith: (String, DateTimeFormatter) -> T,
): T? =
    if (formats.isEmpty()) {
        tryParse { parse(this) }
    } else {
        formats.firstNotNullOfOrNull { format ->
            tryParse { if (format.isNullOrEmpty()) parse(this) else parseWith(this, DateTimeFormatter.ofPattern(format)) }
        }
    }

private inline fun <T> tryParse(block: () -> T): T? =
    try {
        block()
    } catch (e: DateTimeParseException) {
        null
    } catch (e: IllegalArgumentException) {
        null
    }

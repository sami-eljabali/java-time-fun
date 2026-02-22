package javatimefun.zoneddatetime.extensions

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Works off of String representations of ZonedDateTime:
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this  String representation of ZonedDateTime.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return ZonedDateTime? Null means couldn't parse, else parsed ZonedDateTime.
 */
fun String.toZonedDateTime(vararg formats: String?): ZonedDateTime? =
    if (formats.isEmpty()) {
        parseZonedDateTimeOrNull(this)
    } else {
        formats.firstNotNullOfOrNull { format -> parseZonedDateTimeOrNull(this, format) }
    }

private fun parseZonedDateTimeOrNull(dateText: String, format: String? = null): ZonedDateTime? =
    if (format.isNullOrEmpty()) {
        try {
            ZonedDateTime.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    } else {
        try {
            ZonedDateTime.parse(dateText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    }

package javatimefun.localdate.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Works off of String representations of date, without time, nor time zone.
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of LocalDate.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return LocalDate? Null means couldn't parse, else parsed LocalDate.
 */
fun String.toLocalDate(vararg formats: String?): LocalDate? =
    if (formats.isEmpty()) {
        parseLocalDateOrNull(this)
    } else {
        formats.firstNotNullOfOrNull { format -> parseLocalDateOrNull(this, format) }
    }

private fun parseLocalDateOrNull(dateText: String, format: String? = null): LocalDate? =
    if (format.isNullOrEmpty()) {
        try {
            LocalDate.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    } else {
        try {
            LocalDate.parse(dateText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    }

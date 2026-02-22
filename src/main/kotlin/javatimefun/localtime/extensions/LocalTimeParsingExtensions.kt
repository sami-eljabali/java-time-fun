package javatimefun.localtime.extensions

import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.toLocalTime(vararg formats: String?): LocalTime? =
    if (formats.isEmpty()) {
        parseLocalTimeOrNull(this)
    } else {
        formats.firstNotNullOfOrNull { format -> parseLocalTimeOrNull(this, format) }
    }

private fun parseLocalTimeOrNull(timeText: String, format: String? = null): LocalTime? =
    if (format.isNullOrEmpty()) {
        try {
            LocalTime.parse(timeText)
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    } else {
        try {
            LocalTime.parse(timeText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    }

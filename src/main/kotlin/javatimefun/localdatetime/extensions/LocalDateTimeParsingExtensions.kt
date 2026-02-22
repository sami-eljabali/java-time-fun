package javatimefun.localdatetime.extensions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

private const val flexibleIso8601Format = "yyyy-MM-dd'T'HH:mm:ss[.SSSSSS][.SSSSS][.SSSS][.SSS][.SS][.S]['Z']"

/**
 * Works off of String representations of dateTime and parses through the following attempts in order when
 * no format is present:
 * <p><ul>
 * <li>First, tries parsing as LocalDateTime with default parser
 * <li>Lastly, if fails, tries parsing using a more flexible ISO 8601 format
 * </ul><p>
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of LocalDateTime.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return LocalDateTime? Null means couldn't parse, else parsed LocalDateTime.
 */
fun String.toLocalDateTime(vararg formats: String?): LocalDateTime? =
    if (formats.isEmpty()) {
        parseLocalDateTimeOrNull(this) ?: parseLocalDateTimeOrNull(this, flexibleIso8601Format)
    } else {
        formats.firstNotNullOfOrNull { format -> parseLocalDateTimeOrNull(this, format) }
    }

private fun parseLocalDateTimeOrNull(dateText: String, format: String? = null): LocalDateTime? =
    if (format.isNullOrEmpty())
        try {
            LocalDateTime.parse(dateText)
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    else {
        try {
            LocalDateTime.parse(dateText, DateTimeFormatter.ofPattern(format))
        } catch (e: DateTimeParseException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    }

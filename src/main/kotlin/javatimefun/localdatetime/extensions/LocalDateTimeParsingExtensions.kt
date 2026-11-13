package javatimefun.localdatetime.extensions

import javatimefun.parseOrNull
import java.time.LocalDateTime

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
    parseOrNull(
        formats.ifEmpty { arrayOf(null, flexibleIso8601Format) }, // null = default ISO parser
        { LocalDateTime.parse(it) },
        { text, formatter -> LocalDateTime.parse(text, formatter) },
    )

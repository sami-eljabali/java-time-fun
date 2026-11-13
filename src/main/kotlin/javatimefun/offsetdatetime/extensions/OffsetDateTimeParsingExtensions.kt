package javatimefun.offsetdatetime.extensions

import javatimefun.parseOrNull
import java.time.OffsetDateTime

/**
 * Works off of String representations of OffsetDateTime (ISO default: "2024-11-15T12:34:56+01:00").
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of OffsetDateTime.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return OffsetDateTime? Null means couldn't parse, else parsed OffsetDateTime.
 */
fun String.toOffsetDateTime(vararg formats: String?): OffsetDateTime? =
    parseOrNull(formats, { OffsetDateTime.parse(it) }, { text, formatter -> OffsetDateTime.parse(text, formatter) })

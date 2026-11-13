package javatimefun.zoneddatetime.extensions

import javatimefun.parseOrNull
import java.time.ZonedDateTime

/**
 * Works off of String representations of ZonedDateTime:
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this  String representation of ZonedDateTime.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return ZonedDateTime? Null means couldn't parse, else parsed ZonedDateTime.
 */
fun String.toZonedDateTime(vararg formats: String?): ZonedDateTime? =
    parseOrNull(formats, { ZonedDateTime.parse(it) }, { text, formatter -> ZonedDateTime.parse(text, formatter) })

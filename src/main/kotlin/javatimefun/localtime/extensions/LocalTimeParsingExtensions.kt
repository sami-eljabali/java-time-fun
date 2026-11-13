package javatimefun.localtime.extensions

import javatimefun.parseOrNull
import java.time.LocalTime

/**
 * Works off of String representations of time, without date, nor time zone.
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of LocalTime.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return LocalTime? Null means couldn't parse, else parsed LocalTime.
 */
fun String.toLocalTime(vararg formats: String?): LocalTime? =
    parseOrNull(formats, { LocalTime.parse(it) }, { text, formatter -> LocalTime.parse(text, formatter) })

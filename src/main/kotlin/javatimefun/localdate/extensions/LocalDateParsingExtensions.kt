package javatimefun.localdate.extensions

import javatimefun.parseOrNull
import java.time.LocalDate

/**
 * Works off of String representations of date, without time, nor time zone.
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of LocalDate.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return LocalDate? Null means couldn't parse, else parsed LocalDate.
 */
fun String.toLocalDate(vararg formats: String?): LocalDate? =
    parseOrNull(formats, { LocalDate.parse(it) }, { text, formatter -> LocalDate.parse(text, formatter) })

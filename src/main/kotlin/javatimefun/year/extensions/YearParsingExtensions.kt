package javatimefun.year.extensions

import javatimefun.parseOrNull
import java.time.Year

/**
 * Works off of String representations of Year (ISO default: "2024").
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of Year.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return Year? Null means couldn't parse, else parsed Year.
 */
fun String.toYear(vararg formats: String?): Year? =
    parseOrNull(formats, { Year.parse(it) }, { text, formatter -> Year.parse(text, formatter) })

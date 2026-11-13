package javatimefun.monthday.extensions

import javatimefun.parseOrNull
import java.time.MonthDay

/**
 * Works off of String representations of MonthDay (ISO default: "--12-25").
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of MonthDay.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return MonthDay? Null means couldn't parse, else parsed MonthDay.
 */
fun String.toMonthDay(vararg formats: String?): MonthDay? =
    parseOrNull(formats, { MonthDay.parse(it) }, { text, formatter -> MonthDay.parse(text, formatter) })

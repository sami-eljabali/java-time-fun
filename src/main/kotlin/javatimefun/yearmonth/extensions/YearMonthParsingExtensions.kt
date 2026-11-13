package javatimefun.yearmonth.extensions

import javatimefun.parseOrNull
import java.time.YearMonth

/**
 * Works off of String representations of YearMonth (ISO default: "2024-11").
 * When formats are provided, each format is attempted in-order until one succeeds.
 *
 * @param this String representation of YearMonth.
 * @param formats String? vararg of formats to attempt when parsing.
 * @return YearMonth? Null means couldn't parse, else parsed YearMonth.
 */
fun String.toYearMonth(vararg formats: String?): YearMonth? =
    parseOrNull(formats, { YearMonth.parse(it) }, { text, formatter -> YearMonth.parse(text, formatter) })

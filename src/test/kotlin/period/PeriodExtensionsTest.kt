package period

import javatimefun.period.extensions.days
import javatimefun.period.extensions.months
import javatimefun.period.extensions.times
import javatimefun.period.extensions.unaryMinus
import javatimefun.period.extensions.weeks
import javatimefun.period.extensions.years
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Period

class PeriodExtensionsTest {
    @Test
    fun `int and long extensions build the expected periods`() {
        assertEquals(Period.ofDays(3), 3.days)
        assertEquals(Period.ofWeeks(3), 3.weeks)
        assertEquals(Period.ofMonths(3), 3.months)
        assertEquals(Period.ofYears(3), 3.years)
        assertEquals(Period.ofMonths(3), 3L.months)
    }

    @Test
    fun `operators multiply and negate`() {
        assertEquals(Period.ofDays(6), 2.days * 3)
        assertEquals(Period.ofMonths(-2), -2.months)
    }
}

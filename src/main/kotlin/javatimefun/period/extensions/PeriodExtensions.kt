package javatimefun.period.extensions

import java.time.Period

val Int.days: Period get() = Period.ofDays(this)
val Int.weeks: Period get() = Period.ofWeeks(this)
val Int.months: Period get() = Period.ofMonths(this)
val Int.years: Period get() = Period.ofYears(this)

val Long.days: Period get() = Math.toIntExact(this).days
val Long.weeks: Period get() = Math.toIntExact(this).weeks
val Long.months: Period get() = Math.toIntExact(this).months
val Long.years: Period get() = Math.toIntExact(this).years

operator fun Period.times(multiplicand: Int): Period = this.multipliedBy(multiplicand)
operator fun Period.unaryMinus(): Period = this.negated()

package javatimefun.duration.extensions

import java.time.Duration

val Int.nanoseconds: Duration get() = this.toLong().nanoseconds
val Int.microseconds: Duration get() = this.toLong().microseconds
val Int.milliseconds: Duration get() = Duration.ofMillis(this.toLong())
val Int.seconds: Duration get() = Duration.ofSeconds(this.toLong())
val Int.minutes: Duration get() = Duration.ofMinutes(this.toLong())
val Int.hours: Duration get() = Duration.ofHours(this.toLong())
val Int.days: Duration get() = Duration.ofDays(this.toLong())

val Long.nanoseconds: Duration get() = Duration.ofNanos(this)
val Long.microseconds: Duration get() = Duration.ofNanos(Math.multiplyExact(this, 1_000L))
val Long.milliseconds: Duration get() = Duration.ofMillis(this)
val Long.seconds: Duration get() = Duration.ofSeconds(this)
val Long.minutes: Duration get() = Duration.ofMinutes(this)
val Long.hours: Duration get() = Duration.ofHours(this)
val Long.days: Duration get() = Duration.ofDays(this)

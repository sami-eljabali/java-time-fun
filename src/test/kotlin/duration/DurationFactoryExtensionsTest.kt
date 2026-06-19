package duration

import javatimefun.duration.extensions.days
import javatimefun.duration.extensions.hours
import javatimefun.duration.extensions.microseconds
import javatimefun.duration.extensions.milliseconds
import javatimefun.duration.extensions.minutes
import javatimefun.duration.extensions.nanoseconds
import javatimefun.duration.extensions.seconds
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.Duration

class DurationFactoryExtensionsTest {
    @Test
    fun `int extensions build the expected durations`() {
        assertEquals(Duration.ofNanos(5), 5.nanoseconds)
        assertEquals(Duration.ofNanos(5_000), 5.microseconds)
        assertEquals(Duration.ofMillis(5), 5.milliseconds)
        assertEquals(Duration.ofSeconds(5), 5.seconds)
        assertEquals(Duration.ofMinutes(5), 5.minutes)
        assertEquals(Duration.ofHours(5), 5.hours)
        assertEquals(Duration.ofDays(5), 5.days)
    }

    @Test
    fun `long extensions build the expected durations`() {
        assertEquals(Duration.ofNanos(2_000L), 2L.microseconds)
        assertEquals(Duration.ofMillis(2000L), 2000L.milliseconds)
        assertEquals(Duration.ofDays(2L), 2L.days)
    }
}

package localtime

import javatimefun.localtime.extensions.minusMillis
import javatimefun.localtime.extensions.plusMillis
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalTime

class LocalTimeMutatingTest {
    @Test
    fun `plusMillis adds milliseconds as nanos`() {
        val time = LocalTime.of(12, 0, 0, 0)
        val result = time.plusMillis(500)

        assertEquals(12, result.hour)
        assertEquals(0, result.minute)
        assertEquals(0, result.second)
        assertEquals(500_000_000, result.nano)
    }

    @Test
    fun `plusMillis rolls over into the next second`() {
        val time = LocalTime.of(12, 0, 0, 800_000_000)
        val result = time.plusMillis(500)

        assertEquals(1, result.second)
        assertEquals(300_000_000, result.nano)
    }

    @Test
    fun `minusMillis is the inverse of plusMillis`() {
        val time = LocalTime.of(12, 0, 0, 0)

        assertEquals(time, time.plusMillis(750).minusMillis(750))
    }

    @Test
    fun `plusMillis with negative argument behaves like minusMillis`() {
        val time = LocalTime.of(12, 0, 0, 0)

        assertEquals(time.minusMillis(250), time.plusMillis(-250))
    }

    @Test
    fun `plusMillis wraps around midnight`() {
        val time = LocalTime.of(23, 59, 59, 999_000_000)

        assertEquals(LocalTime.MIDNIGHT, time.plusMillis(1))
    }
}

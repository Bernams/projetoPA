package projeto.jsonObjects

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class JSONNullTest {
    @Test
    fun testNullCreation() {
        val jsonNull = JSONNull()
        assertEquals("null", jsonNull.toJSONString())
    }
}

package projeto.annotations

import JSONExclude
import org.junit.jupiter.api.Test
import projeto.JSONReflector
import projeto.jsonObjects.JSONBoolean
import projeto.jsonObjects.JSONNumber
import projeto.jsonObjects.JSONObject
import kotlin.test.assertEquals

class EEJSONExcludeTest {

    private val reflector = JSONReflector()

    @Test
    fun exclusionTest() {
        data class TestData(@JSONExclude val a: String, val b: Int, val c: Boolean)
        val obj = TestData("pa", 92448, true)
        val result = reflector.reflect(obj)
        assertEquals(JSONObject().apply {
            put("b", JSONNumber(92448))
            put("c", JSONBoolean(true))
        }.toJSONString(), result.toJSONString())
    }
}
package projeto.annotations

import JSONCustomKey
import JSONForceString
import org.junit.jupiter.api.Test
import projeto.JSONReflector
import projeto.jsonObjects.JSONBoolean
import projeto.jsonObjects.JSONNumber
import projeto.jsonObjects.JSONObject
import projeto.jsonObjects.JSONString
import kotlin.test.assertEquals

class JSONCustomKeyTest {


    private val reflector = JSONReflector()

    @Test
    fun forceStringTest() {
        data class TestData(val a: String,@JSONCustomKey("TESTE") val b: Int, val c: Boolean)
        val obj = TestData("pa", 92448, true)
        val result = reflector.reflect(obj)
        assertEquals(JSONObject().apply {
            put("a", JSONString("pa"))
            put("TESTE", JSONNumber(92448))
            put("c", JSONBoolean(true))
        }.toJSONString(), result.toJSONString())
    }
}
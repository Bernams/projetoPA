package projeto

import JSONCustomKey
import JSONExclude
import JSONForceString
import projeto.jsonObjects.*
import kotlin.test.Test
import kotlin.test.assertEquals

class JSONReflectorTest {
    enum class TestEnum { VALUE }
    private val reflector = JSONReflector()

    @Test
    fun nullObjectTest() {
        val result = reflector.reflect(null)
        assertEquals(JSONString(null).toJSONString(), result.toJSONString())
    }

    @Test
    fun stringTest() {
        val result = reflector.reflect("pa")
        assertEquals(JSONString("pa").toJSONString(), result.toJSONString())
    }

    @Test
    fun charTest() {
        val result = reflector.reflect('p')
        assertEquals(JSONString("p").toJSONString(), result.toJSONString())
    }

    @Test
    fun numberTest() {
        val result = reflector.reflect(92448)
        assertEquals(JSONNumber(92448).toJSONString(), result.toJSONString())
    }

    @Test
    fun BooleanTest() {
        val result = reflector.reflect(true)
        assertEquals(JSONBoolean(true).toJSONString(), result.toJSONString())
    }

    @Test
    fun enumTest() {
        val result = reflector.reflect(TestEnum.VALUE)
        assertEquals(JSONString("VALUE").toJSONString(), result.toJSONString())
    }

    @Test
    fun arrayTest() {
        val list = listOf("pa", 92448, true)
        val result = reflector.reflect(list)
        assertEquals(JSONArray().apply {
            add(JSONString("pa"))
            add(JSONNumber(92448))
            add(JSONBoolean(true))
        }.toJSONString(), result.toJSONString())
    }

    @Test
    fun mapTest() {
        val map = mapOf("a" to "pa", "b" to 92448, "c" to true)
        val result = reflector.reflect(map)
        assertEquals(JSONObject().apply {
            put("a", JSONString("pa"))
            put("b", JSONNumber(92448))
            put("c", JSONBoolean(true))
        }.toJSONString(), result.toJSONString())
    }

    @Test
    fun dataClassTest() {
        data class TestData(val a: String, val b: Int, val c: Boolean)
        val obj = TestData("pa", 92448, true)
        val result = reflector.reflect(obj)
        assertEquals(JSONObject().apply {
            put("a", JSONString("pa"))
            put("b", JSONNumber(92448))
            put("c", JSONBoolean(true))
        }.toJSONString(), result.toJSONString())
    }

}
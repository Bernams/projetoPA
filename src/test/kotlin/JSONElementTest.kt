import jsonObjects.JSONArray
import jsonObjects.JSONObject
import jsonObjects.JSONValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class JSONElementTest {

    @Test
    fun testJSONObject() {
        val jsonObject = JSONObject()
        val jsonValue = JSONValue("PA")
        val jsonValue2 = JSONValue("6.0")
        jsonObject.put("uc", jsonValue)
        jsonObject.put("ects", jsonValue2)

        assertEquals(2, jsonObject.size())
        assertEquals(jsonValue, jsonObject.get("uc"))
        assertEquals(jsonValue2, jsonObject.get("ects"))
    }

    @Test
    fun testJSONArray() {
        val jsonArray = JSONArray()
        val jsonValue = JSONValue("Dave Farley")
        val jsonValue2 = JSONValue("Martin Fowler")
        jsonArray.add(jsonValue)
        jsonArray.add(jsonValue2)

        assertEquals(2, jsonArray.size())
        assertEquals(jsonValue, jsonArray.get(0))
        assertEquals(jsonValue2, jsonArray.get(1))
    }

    @Test
    fun testJSONValue() {
        val stringValue = JSONValue("Hello, World!")
        val intValue = JSONValue("42")
        val doubleValue = JSONValue("3.14")
        val booleanValue = JSONValue("true")
        val nullValue = JSONValue("null")

        assertEquals("Hello, World!", stringValue.value)
        assertEquals(42, intValue.value)
        assertEquals(3.14, doubleValue.value)
        assertEquals(true, booleanValue.value)
        assertNull(nullValue.value)
    }
}

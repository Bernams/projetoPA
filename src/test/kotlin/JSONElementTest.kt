import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class JSONElementTest {

    @Test
    fun testJSONObject() {
        val jsonObject = JSONObject()
        jsonObject.put("uc", JSONValue("PA"))
        jsonObject.put("ects", JSONValue("6.0"))

        assertEquals(2, jsonObject.size())
        assertEquals(JSONValue("PA"), jsonObject.get("uc"))
        assertEquals(JSONValue("6.0"), jsonObject.get("ects"))
    }

    @Test
    fun testJSONArray() {
        val jsonArray = JSONArray()
        jsonArray.add(JSONValue("Dave Farley"))
        jsonArray.add(JSONValue("Martin Fowler"))

        assertEquals(2, jsonArray.size())
        assertEquals(JSONValue("Dave Farley"), jsonArray.get(0))
        assertEquals(JSONValue("Martin Fowler"), jsonArray.get(1))
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

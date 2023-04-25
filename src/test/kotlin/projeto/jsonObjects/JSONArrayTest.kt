package projeto.jsonObjects
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JSONArrayTest {

    @Test
    fun testAddElement() {
        val jsonArray = JSONArray()
        val jsonString = JSONString("Hello")
        jsonArray.add(jsonString)

        val element = jsonArray.get(0)
        assertEquals(jsonString, element)
    }

    @Test
    fun testRemoveElement() {
        val jsonArray = JSONArray()
        val jsonString = JSONString("Hello")
        val jsonNumber = JSONNumber(42)
        val jsonBoolean = JSONBoolean(true)
        jsonArray.add(jsonString)
        jsonArray.add(jsonNumber)
        jsonArray.add(jsonBoolean)

        jsonArray.removeAt(0)

        val element = jsonArray.get(0)
        assertEquals(jsonNumber, element)

        jsonArray.remove(jsonNumber)
        val element2 = jsonArray.get(0)
        assertEquals(jsonBoolean,element2)
    }

}

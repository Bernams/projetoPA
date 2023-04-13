package projeto.jsonObjects

import JSONFileCreator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import projeto.visitor.findObjectsWithProperty
import projeto.visitor.findValuesWithProperty
import projeto.visitor.validatePropertyValue
import kotlin.reflect.typeOf

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



    @Test
    fun pacoh() {
        val inscritos = JSONArray()

        val root = JSONObject()
        val jsonObject = JSONObject()
        val jsonObject2 = JSONObject()
        val jsonObject3 = JSONObject()
        val UC = JSONValue("PA")
        val ECTS = JSONValue("6.0")
        val DATA_EXAME = JSONValue("null")
        val NUMERO = JSONValue("101101")
        val NOME = JSONValue("Dave Farley")
        val INTERNACIONAL = JSONValue("true")
        val NUMERO2 = JSONValue("101102")
        val NOME2 = JSONValue("Martin Fowler")
        val INTERNACIONAL2 = JSONValue("true")
        val NUMERO3 = JSONValue("26503")
        val NOME3 = JSONValue("André Santos")
        val INTERNACIONAL3 = JSONValue("false")

        jsonObject.put("numero", NUMERO)
        jsonObject.put("nome", NOME)
        jsonObject.put("internacional", INTERNACIONAL)

        jsonObject2.put("numero", NUMERO2)
        jsonObject2.put("nome", NOME2)
        jsonObject2.put("internacional", INTERNACIONAL2)

        jsonObject3.put("numero", NUMERO3)
        jsonObject3.put("internacional", INTERNACIONAL3)

        inscritos.add(jsonObject)
        inscritos.add(jsonObject2)
        inscritos.add(jsonObject3)

        root.put("uc", UC)
        root.put("ects", ECTS)
        root.put("data-exame", DATA_EXAME)
        root.put("inscritos", inscritos)

        val visitor = findValuesWithProperty("numero")
        root.accept(visitor)

        val visitor2 = findObjectsWithProperty(listOf("numero","nome"))
        root.accept(visitor2)


        val visitor3 = validatePropertyValue("numero", typeOf(2))
        root.accept(visitor3)
        val creator = JSONFileCreator("src/test/resources/PA.json")
        creator.writeToFile(root)

    }
}

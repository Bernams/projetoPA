package projeto.jsonObjects

import JSONFileCreator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import projeto.visitor.FindObjectsWithProperty
import projeto.visitor.FindValuesWithProperty
import projeto.visitor.ValidatePropertyValue
import kotlin.reflect.typeOf

class JSONElementTest {

    @Test
    fun testJSONObject() {
        val jsonObject = JSONObject()
        val jsonValue = JSONString("PA")
        val jsonValue2 = JSONNumber(6.0)
        jsonObject.put("uc", jsonValue)
        jsonObject.put("ects", jsonValue2)

        assertEquals(2, jsonObject.size())
        assertEquals(jsonValue, jsonObject.get("uc"))
        assertEquals(jsonValue2, jsonObject.get("ects"))
    }

    @Test
    fun testJSONArray() {
        val jsonArray = JSONArray()
        val jsonValue = JSONString("Dave Farley")
        val jsonValue2 = JSONString("Martin Fowler")
        jsonArray.add(jsonValue)
        jsonArray.add(jsonValue2)

        assertEquals(2, jsonArray.size())
        assertEquals(jsonValue, jsonArray.get(0))
        assertEquals(jsonValue2, jsonArray.get(1))
    }

    @Test
    fun testJSONValue() {
        val stringValue = JSONString("Hello, World!")
        val intValue = JSONNumber(42)
        val doubleValue = JSONNumber(3.14)
        val booleanValue = JSONBoolean(true)

        assertEquals("Hello, World!", stringValue.s)
        assertEquals(42, intValue.s)
        assertEquals(3.14, doubleValue.s)
        assertEquals(true, booleanValue.s)
    }



    @Test
    fun pacoh() {
        val inscritos = JSONArray()

        val root = JSONObject()
        val jsonObject = JSONObject()
        val jsonObject2 = JSONObject()
        val jsonObject3 = JSONObject()
        val UC = JSONString("PA")
        val ECTS = JSONNumber(6.0)
        val DATA_EXAME = JSONBoolean(null)
        val NUMERO = JSONNumber(101101)
        val NOME = JSONString("Dave Farley")
        val INTERNACIONAL = JSONBoolean(true)
        val NUMERO2 = JSONNumber(101102)
        val NOME2 = JSONString("Martin Fowler")
        val INTERNACIONAL2 = JSONBoolean(true)
        val NUMERO3 = JSONNumber(26503)
        val NOME3 = JSONString("André Santos")
        val INTERNACIONAL3 = JSONBoolean(false)

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

        val visitor = FindValuesWithProperty("numero")
        root.accept(visitor)

        val visitor2 = FindObjectsWithProperty(listOf("numero","nome"))
        root.accept(visitor2)


        //val visitor3 = ValidatePropertyValue("numero", typeOf(2))
        //root.accept(visitor3)
        val creator = JSONFileCreator("src/test/resources/PA.json")
        creator.writeToFile(root)

    }
}

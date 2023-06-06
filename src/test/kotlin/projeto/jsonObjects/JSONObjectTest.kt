package projeto.jsonObjects

import JSONFileCreator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JSONObjectTest {

    @Test
    fun testJSONObject() {
        val jsonObject = JSONObject()
        val jsonString = JSONString("PA")
        val jsonNumber = JSONNumber(6.0)
        jsonObject.put("uc", jsonString)
        jsonObject.put("ects", jsonNumber)

        Assertions.assertEquals(2, jsonObject.size())
        Assertions.assertEquals(jsonString, jsonObject.get("uc"))
        Assertions.assertEquals(jsonNumber, jsonObject.get("ects"))
    }

    @Test
    fun testJSONObjectStructure() {
        val inscritos = JSONArray()

        val root = JSONObject()
        val jsonObject = JSONObject()
        val jsonObject2 = JSONObject()
        val jsonObject3 = JSONObject()
        val UC = JSONString("PA")
        val ECTS = JSONNumber(6.0)
        val DATA_EXAME = JSONNumber(null)
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
        jsonObject3.put("nome", NOME3)
        jsonObject3.put("internacional", INTERNACIONAL3)

        inscritos.add(jsonObject)
        inscritos.add(jsonObject2)
        inscritos.add(jsonObject3)

        root.put("uc", UC)
        root.put("ects", ECTS)
        root.put("data-exame", DATA_EXAME)
        root.put("inscritos", inscritos)


        val creator = JSONFileCreator("src/test/resources/PA.json")
        creator.writeToFile(root)

    }
}
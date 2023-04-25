package projeto.visitor

import org.junit.jupiter.api.Test
import projeto.jsonObjects.*
import projeto.visitor.Validations.FindObjectsWithProperty
import projeto.visitor.Validations.FindValuesWithProperty
import projeto.visitor.Validations.ValidateInscritosProperty
import projeto.visitor.Validations.ValidateNumeroProperty
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class VisitorTest {



    fun createJsonObject(): JSONObject{
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

        return root
    }


    @Test
    fun testFindObjectsWithProperty() {
        val jsonObject = createJsonObject()

        val visitor = FindObjectsWithProperty(listOf("numero","nome"))
        jsonObject.accept(visitor)
        assertEquals(visitor.getFoundValues().size, 3)

    }

    @Test
    fun testFindValuesWithProperty() {
        val jsonObject = createJsonObject()

        val visitor = FindValuesWithProperty("numero")
        jsonObject.accept(visitor)
        assertEquals(visitor.getFoundValues().size, 3)

    }
    @Test
    fun testValidateInscritos() {
        val jsonObject = createJsonObject()

        val visitor = ValidateInscritosProperty()
        jsonObject.accept(visitor)
        assertTrue(visitor.getValidation())


    }
    @Test
    fun testValidateNumero() {
        val jsonObject = createJsonObject()

        val visitor = ValidateNumeroProperty()
        jsonObject.accept(visitor)
        assertTrue(visitor.getValidation())
    }

}
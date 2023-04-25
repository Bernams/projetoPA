package projeto.jsonObjects

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JSONBooleanTest {

    @Test
    fun testBooleanCreation(){
        val bool = JSONBoolean(true)
        assertTrue(bool.bool)
        assertEquals("true", bool.toJSONString())
    }

}
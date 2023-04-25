package projeto.jsonObjects

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JSONNumberTest {

    @Test
    fun testIntCreation(){
        val number = JSONNumber(34)
        assertEquals(34,number.num)
        assertEquals("34", number.toJSONString())
    }

    @Test
    fun testDoubleCreation(){
        val number = JSONNumber(34.43)
        assertEquals(34.43,number.num)
        assertEquals("34.43", number.toJSONString())
    }

}
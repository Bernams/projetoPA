package projeto.jsonObjects

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JSONStringTest {

    @Test
    fun testStringCreation(){
        val string = JSONString("pacoh")
        assertEquals("pacoh",string.str)
        assertEquals("\"pacoh\"", string.toJSONString())
    }
}
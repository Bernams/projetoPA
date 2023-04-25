package projeto.visitor
import JSONVisitor
import projeto.jsonObjects.*

class ValidatePropertyValue<T>(private val property : String, private val expectedProperty : Class<T>) : JSONVisitor {

    override fun visit(jsonObject: JSONObject) {
        TODO("Not yet implemented")
    }

    override fun visit(jsonArray: JSONArray) {
        TODO("Not yet implemented")
    }

    override fun visit(jsonString: JSONString) {
        TODO("Not yet implemented")
    }

    override fun visit(jsonBoolean: JSONBoolean) {
        TODO("Not yet implemented")
    }

    override fun visit(jsonNumber: JSONNumber) {
        TODO("Not yet implemented")
    }


}
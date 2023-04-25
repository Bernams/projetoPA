package projeto.visitor.Validations

import JSONVisitor
import projeto.jsonObjects.*

class FindObjectsWithProperty(private val properties: List<String>) : JSONVisitor {

    private val found = mutableListOf<JSONObject>()

    override fun visit(jsonObject: JSONObject) {
        jsonObject.getProperties().containsAll(properties).let {
            if (it) {
                found.add(jsonObject)
            }
        }
    }

    override fun visit(jsonArray: JSONArray) {
    }

    override fun visit(jsonString: JSONString) {
    }

    override fun visit(jsonBoolean: JSONBoolean) {
    }

    override fun visit(jsonNumber: JSONNumber) {
    }

}
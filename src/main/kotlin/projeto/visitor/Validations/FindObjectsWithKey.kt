package projeto.visitor.Validations

import JSONVisitor
import projeto.jsonObjects.*

class FindObjectsWithKey(private val key: List<String>) : JSONVisitor {

    private val found = mutableListOf<JSONObject>()

    override fun visit(jsonObject: JSONObject) {
        jsonObject.getProperties().containsAll(key).let {
            if (it) {
                found.add(jsonObject)
            }
        }
    }

    fun getFound(): List<JSONObject> {
        return found
    }

    override fun visit(jsonArray: JSONArray) {
    }

    override fun visit(jsonString: JSONString) {
    }

    override fun visit(jsonBoolean: JSONBoolean) {
    }

    override fun visit(jsonNumber: JSONNumber) {
    }

    override fun visit(jsonNull: JSONNull) {
    }

}
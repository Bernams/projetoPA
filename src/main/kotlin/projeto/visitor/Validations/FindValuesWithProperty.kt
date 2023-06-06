package projeto.visitor.Validations

import JSONVisitor
import projeto.jsonObjects.*

class FindValuesWithProperty(private val property: String): JSONVisitor {
    private val found = mutableListOf<String>()

    override fun visit(jsonObject: JSONObject) {
        jsonObject.get(property)?.let { found.add(it.toJSONString()) }
    }

    fun getFoundValues(): List<String> {
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
package projeto.visitor.Validations

import JSONVisitor
import projeto.jsonObjects.*

class FindValuesWithKey(private val key: String): JSONVisitor {
    private val found = mutableListOf<String>()


    override fun visit(jsonObject: JSONObject) {
        jsonObject.get(key)?.let { found.add(it.toJSONString()) }
    }

    fun getFound(): List<String> {
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
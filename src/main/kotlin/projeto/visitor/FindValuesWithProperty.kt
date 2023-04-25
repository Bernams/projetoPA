package projeto.visitor

import JSONVisitor
import projeto.jsonObjects.*

class FindValuesWithProperty(private val property: String): JSONVisitor {
    private val found = mutableListOf<String>()


    override fun visit(jsonObject: JSONObject) {
        jsonObject.get(property)?.let { found.add(it.toJSONString()) }
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
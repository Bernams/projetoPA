package projeto.visitor.Validations

import JSONVisitor
import projeto.jsonObjects.*
import kotlin.reflect.KClass

class ValidateNumeroProperty : JSONVisitor {
    var isValid = true
    private val expectedType: KClass<*> = JSONNumber::class

    override fun visit(jsonObject: JSONObject) {
        val numeroElement = jsonObject.get("numero")
        if (numeroElement != null && numeroElement::class == expectedType) {
            val numeroValue = (numeroElement as JSONNumber).num
            if (numeroValue !is Int) {
                isValid = false
            }
        }
    }
    fun getValidation(): Boolean {
        return isValid
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
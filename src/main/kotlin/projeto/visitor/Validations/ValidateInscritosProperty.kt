package projeto.visitor.Validations

import JSONVisitor
import projeto.jsonObjects.*

class ValidateInscritosProperty : JSONVisitor {
    var isValid = true
    private var referenceStructure: Set<String>? = null

    override fun visit(jsonObject: JSONObject) {
        val inscritosElement = jsonObject.get("inscritos")
        if (inscritosElement is JSONArray) {
            inscritosElement.getAll().forEach { element ->
                if (element is JSONObject) {
                    if (referenceStructure == null) {
                        referenceStructure = element.getProperties()
                    } else if (element.getProperties() != referenceStructure) {
                        isValid = false
                    }
                } else {
                    isValid = false
                }
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

    override fun visit(jsonNull: JSONNull) {
    }
}

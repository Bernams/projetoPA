package projeto.ui

import View
import projeto.JSONReflector
import projeto.Model
import projeto.jsonObjects.JSONElement
import projeto.jsonObjects.JSONObject

class JSONEditorController(val jsonModel: Model) {

    fun editModel(obj : Any?, key: String) {
        jsonModel.modifyJSON(obj, key)
    }

    fun printAll() : String {
        return jsonModel.printJSON()
    }
}
package projeto.ui

import projeto.Model
import projeto.jsonObjects.*

class JSONEditorController(val jsonModel: Model) {

    fun handleAddNestedObject(context: JSONObject, key: String, obj: JSONObject) {
        jsonModel.addNestedObject(context, key, obj)
    }

    fun handleRemoveNestedObject(context: JSONObject) {
        jsonModel.removeNestedObject(context)
    }

    fun handleAddNestedArray(context: JSONObject, key: String, arr: JSONArray) {
        jsonModel.addNestedArray(context, key, arr)
    }

    fun handleAddNestedValue(context: JSONObject, key: String, value: JSONElement) {
        jsonModel.addNestedValue(context, key, value)
    }

    fun handleAddArrayNestedObject(context: JSONArray, jsonObject: JSONObject) {
        jsonModel.addArrayNestedObject(context, jsonObject)
    }

    fun handleAddArrayNestedArray(context: JSONArray, jsonObject: JSONArray) {
        jsonModel.addArrayNestedArray(context, jsonObject)
    }

    fun handleAddArrayValue(context: JSONArray, value: JSONElement) {
        jsonModel.addArrayValue(context, value)
    }

    fun printAll() : String {
        return jsonModel.printJSON()
    }

}
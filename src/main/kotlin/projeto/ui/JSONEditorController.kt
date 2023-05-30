package projeto.ui

import View
import projeto.JSONReflector
import projeto.Model
import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONElement
import projeto.jsonObjects.JSONObject

class JSONEditorController(val jsonModel: Model) {

    fun editModel(obj : Any?, key: String) {
        jsonModel.modifyJSON(obj, key)
    }

    fun addObject(jsonObject: JSONObject,key: String){
        jsonModel.addJsonObject(jsonObject, key)
    }

    fun addArray(jsonArray: JSONArray,key: String){
        jsonModel.addJsonArray(jsonArray, key)
    }

    fun printAll() : String {
        return jsonModel.printJSON()
    }
}
package projeto

import projeto.jsonObjects.JSONElement
import projeto.jsonObjects.JSONObject

class Model : Observable {
    override val observers = arrayListOf<Observer>()

    var jsonModel = JSONObject()

    fun modifyJSON(obj : Any?, key : String) {
        jsonModel.put(key, JSONReflector().reflect(obj))
    }

    override fun add(observer: Observer) {
        observers.add(observer)
    }

    override fun remove(observer: Observer) {
        observers.remove(observer)
    }

    override fun sendUpdateEvent() {
        observers.forEach{ it.update() }
    }

    fun printJSON() : String {
        return jsonModel.toJSONString()
    }

}
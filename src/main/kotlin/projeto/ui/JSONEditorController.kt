package projeto.ui

import View
import projeto.Model
import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONObject
import java.awt.Component
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import javax.swing.*

class JSONEditorController(val jsonModel: Model) {
    private val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()


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

    fun addCheckboxWidget(value : Boolean, label: String, view : View) : JPanel {
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(label))
            val checkbox = JCheckBox("", value)
            editModel(value, label)

            checkbox.addActionListener {
                editModel(checkbox.isSelected, label)
                view.update()
            }
            add(checkbox)

        }
    }

    fun addValueWidget(label: String, value: String, view :  View) : JPanel =

        JPanel().apply{
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            if(value.matches(regex)) {
                editModel(Integer.parseInt(value), label)
            } else {
                editModel(value, label)
            }
            add(JLabel(label))
            val text = JTextField(value)
            text.addFocusListener(object: FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    editModel(text.text, label)
                    view.update()
                }
            })
            add(text)
        }
}

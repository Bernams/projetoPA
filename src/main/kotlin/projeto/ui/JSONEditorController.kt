package projeto.ui

import View
import projeto.Model
import projeto.jsonObjects.*
import java.awt.Component
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import javax.swing.*
import kotlin.collections.ArrayDeque

class JSONEditorController(val jsonModel: Model) {
    private val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    private val contextStack: ArrayDeque<JSONObject> = ArrayDeque<JSONObject>().also { it.add(jsonModel.jsonModel) }

    fun addNestedObject(context: JSONObject, key: String, obj: JSONObject) {
        context.put(key, obj)
    }

    fun addNestedArray(context: JSONObject, key: String, arr: JSONArray) {
        context.put(key, arr)
    }

    fun addNestedValue(context: JSONObject, key: String, value: JSONElement) {
        context.put(key, value)
    }

    fun addArrayNestedObject(context: JSONArray, jsonObject: JSONObject) {
        context.add(jsonObject)
    }
    fun addArrayNestedArray(context: JSONArray, jsonObject: JSONArray) {
        context.add(jsonObject)
    }

    fun getCurrentContext(): JSONObject {
        return contextStack.last()
    }

    fun pushContext(jsonObject: JSONObject) {
        contextStack.add(jsonObject)
    }

    fun popContext() {
        if (contextStack.size > 1) {
            contextStack.removeLast()
        }
    }
    fun printAll() : String {
        return jsonModel.printJSON()
    }

    fun addCheckboxWidget(context: JSONObject, value: Boolean, label: String, view: View): JPanel {
        var newLabel = label
        var counter = 1
        while(context.getProperties().contains(newLabel)){
            newLabel = label.plus("($counter)")
            counter++
        }
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(label))
            val checkbox = JCheckBox("", value)
            addNestedValue(context, label, JSONBoolean(value))

            checkbox.addActionListener {
                addNestedValue(context, label, JSONBoolean(checkbox.isSelected))
                view.update()
            }
            add(checkbox)
        }
    }

    fun addArrayCheckboxWidget(context: JSONArray, value: Boolean, view: View): JPanel {
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            val checkbox = JCheckBox("", value)
            context.add(JSONBoolean(value))

            checkbox.addActionListener {
                context.add(JSONBoolean(checkbox.isSelected))
                view.update()
            }
            add(checkbox)
        }
    }

    fun addValueWidget(context: JSONObject, label: String, value: String, view: View): JPanel {
        var newLabel = label
        var counter = 1
        while(context.getProperties().contains(newLabel)){
            newLabel = label.plus("($counter)")
            counter++
        }
        return JPanel().apply {
            layout = FlowLayout(FlowLayout.LEADING)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            if (value.matches(regex)) {
                addNestedValue(context, newLabel, JSONNumber(Integer.parseInt(value)))
            } else {
                addNestedValue(context, newLabel, JSONString(value))
            }

            add(JLabel(newLabel))
            val text = JTextField(value)
            text.addFocusListener(object : FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    addNestedValue(context, newLabel, JSONString(text.text))
                    view.update()
                }
            })
            add(text)
        }
    }

    fun addArrayValueWidget(context: JSONArray, value: String, view: View): JPanel {

        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            if (value.matches(regex)) {
                context.add(JSONNumber(Integer.parseInt(value)))
            } else {
                context.add(JSONString(value))
            }

            val text = JTextField(value)
            text.addFocusListener(object : FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    context.add(JSONString(text.text))
                    view.update()
                }
            })
            add(text)
        }
    }

    fun addObjectWidget(key: String, jsonObject: JSONObject, view : View) : JPanel {
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val panel = JPanel()
            panel.layout = GridLayout()
            val scrollPane = JScrollPane(view.appPanel(jsonObject)).apply {
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                preferredSize = Dimension(200, 100)
            }
            panel.add(scrollPane)
            add(panel)
        }
    }

    fun addArrayWidget(key: String, jsonArray: JSONArray, view : View) : JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val panel = JPanel()
            panel.layout = GridLayout()
            val scrollPane = JScrollPane(view.arrayPanel(jsonArray)).apply {
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                preferredSize = Dimension(200, 100)

            }
            panel.add(scrollPane)
            add(panel)
        }



}

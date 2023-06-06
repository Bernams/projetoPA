import projeto.Model
import projeto.Observer
import projeto.jsonObjects.*
import projeto.ui.JSONEditorController
import java.awt.*
import java.awt.event.*
import javax.swing.*


fun main() {
    val model = Model()
    val controller = JSONEditorController(model)
    View(model, controller).open()
}

class View(val model : Model, val controller : JSONEditorController) : Observer {
    init {
        model.add(this)
    }
    val view = this
    val srcArea = JTextArea()
    val frame = JFrame("PA - JSON Object Editor").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = GridLayout(0, 2)
        size = Dimension(900, 900)

        val left = JPanel()
        left.layout = GridLayout()
        val scrollPane = JScrollPane(appPanel(model.jsonModel)).apply {
            horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
            verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        }
        left.add(scrollPane)
        add(left)

        val right = JPanel()
        right.layout = GridLayout()

        srcArea.tabSize = 2
        srcArea.text = controller.printAll()
        srcArea.isEditable = false
        right.add(srcArea)
        add(right)
    }

    override fun update() {
        srcArea.text = controller.printAll()
    }

    fun open() {
        frame.isVisible = true
    }

    fun appPanel(context: JSONObject): JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            // menu
            addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        val menu = JPopupMenu("Message")
                        val addValue = JButton("Add Value")
                        val addArray = JButton("Add Array")
                        val addObject = JButton("Add Object")
                        val undo = JButton("Undo")
                        val delete = JButton("Delete Component")

                        addValue.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            when (val value = JOptionPane.showInputDialog("Insert value:")) {
                                "true" -> {
                                    add(addCheckboxWidget(context, true, label))
                                    update()
                                }
                                "false" -> {
                                    add(addCheckboxWidget(context, false, label))
                                    update()
                                }
                                else -> {
                                    add(addValueWidget(context, label, value))
                                }
                            }
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addArray.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            val jsonArray = JSONArray()
                            add(addArrayWidget(context,label, jsonArray))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addObject.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            val jsonObject = JSONObject()
                            add(addObjectWidget(context, label, jsonObject))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        delete.addActionListener {
                            controller.handleRemoveNestedObject(context)
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        undo.addActionListener {

                        }

                        menu.add(addValue)
                        menu.add(addArray)
                        menu.add(addObject)
                        menu.add(undo)
                        menu.add(delete)

                        val location: Point = MouseInfo.getPointerInfo().location
                        val x: Double = location.getX() - frame.location.x
                        val y: Double = location.getY() - frame.location.y - 20
                        menu.show(this@apply, x.toInt(), y.toInt())
                    }
                }
            })
        }
    fun arrayPanel(context: JSONArray): JPanel =
        JPanel().apply {
            layout = BoxLayout(this,BoxLayout.Y_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            // menu
            addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        val menu = JPopupMenu("Message")
                        val addValue = JButton("Add Value")
                        val addArray = JButton("Add Array")
                        val addObject = JButton("Add Object")

                        val delete = JButton("Delete")

                        addValue.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            when (val value = JOptionPane.showInputDialog("Insert value:")) {
                                "true" -> {
                                    add(addArrayCheckboxWidget(context,label, true ))
                                    update()
                                }
                                "false" -> {
                                    add(addArrayCheckboxWidget(context, label,false))
                                    update()
                                }
                                else -> {
                                    add(addArrayValueWidget(context,label, value))
                                }
                            }
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addArray.addActionListener {
                            val jsonArray = JSONArray()
                            val label = JOptionPane.showInputDialog("Insert label:")
                            add(addArrayWidget(context,label, jsonArray))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addObject.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            val jsonObject = JSONObject()
                            add(addObjectWidget(context, label,jsonObject))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        menu.add(addValue)
                        menu.add(addArray)
                        menu.add(addObject)
                        menu.add(delete)

                        val location: Point = MouseInfo.getPointerInfo().location
                        val x: Double = location.getX() - frame.location.x
                        val y: Double = location.getY() - frame.location.y - 20
                        menu.show(this@apply, x.toInt(), y.toInt())
                    }
                }
            })
        }

    fun addArrayWidget(context: JSONElement, key: String, jsonArray: JSONArray) : JPanel {

        when(context){
            is JSONObject -> controller.handleAddNestedArray(context, key, jsonArray)
            is JSONArray -> controller.handleAddArrayNestedArray(context, jsonArray)
        }

        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val panel = JPanel()
            panel.layout = GridLayout()
            panel.preferredSize = Dimension(Integer.MAX_VALUE, 100)
            val scrollPane = JScrollPane(view.arrayPanel(jsonArray)).apply {
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                preferredSize = Dimension(Integer.MAX_VALUE, 100)
                maximumSize = Dimension(Integer.MAX_VALUE, 100)

            }
            panel.add(scrollPane)
            add(panel)
        }

    }

    fun addObjectWidget(context: JSONElement, key: String, jsonObject: JSONObject) : JPanel {
        when(context){
            is JSONObject -> controller.handleAddNestedObject(context, key, jsonObject)
            is JSONArray -> controller.handleAddArrayNestedObject(context, jsonObject)
        }
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val panel = JPanel()
            panel.layout = GridLayout()
            panel.preferredSize = Dimension(Integer.MAX_VALUE, 100)
            val scrollPane = JScrollPane(view.appPanel(jsonObject)).apply {
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
                preferredSize = Dimension(Integer.MAX_VALUE, 100)
                maximumSize = Dimension(Integer.MAX_VALUE, 100)
            }
            panel.add(scrollPane)
            add(panel)
        }
    }
    fun addCheckboxWidget(context: JSONObject, value: Boolean, label: String): JPanel {
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

            add(JLabel(newLabel))
            val checkbox = JCheckBox("", value)
            controller.handleAddNestedValue(context, newLabel, JSONBoolean(value))

            checkbox.addActionListener {
                controller.handleAddNestedValue(context, newLabel, JSONBoolean(checkbox.isSelected))
                view.update()
            }
            add(checkbox)
        }
    }

    fun addArrayCheckboxWidget(context: JSONArray,label: String, value: Boolean): JPanel {
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(label))
            val checkbox = JCheckBox("", value)
            context.add(JSONBoolean(value))

            checkbox.addActionListener {
                context.add(JSONBoolean(checkbox.isSelected))
                view.update()
            }
            add(checkbox)
        }
    }

    fun addValueWidget(context: JSONObject, label: String, value: String): JPanel {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
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

            if (value.matches(regex)) {
                controller.handleAddNestedValue(context, newLabel, JSONNumber(Integer.parseInt(value)))
            } else {
                controller.handleAddNestedValue(context, newLabel, JSONString(value))
            }

            add(JLabel(newLabel))
            val text = JTextField(value)
            text.maximumSize = Dimension(Integer.MAX_VALUE, 100)
            text.addFocusListener(object : FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    controller.handleAddNestedValue(context, newLabel, JSONString(text.text))
                    view.update()
                }
            })
            add(text)
        }
    }

    fun addArrayValueWidget(context: JSONArray, label: String,value: String): JPanel {
        val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()


        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(label))
            if (value.matches(regex)) {
                controller.handleAddArrayValue(context, JSONNumber(Integer.parseInt(value)))
            } else {
                controller.handleAddArrayValue(context, JSONString(value))
            }

            val text = JTextField(value)
            text.maximumSize = Dimension(Integer.MAX_VALUE, 100)
            text.addFocusListener(object : FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    context.add(JSONString(text.text))
                    view.update()
                }
            })
            add(text)
        }
    }

}







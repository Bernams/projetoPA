import projeto.Model
import projeto.Observer
import projeto.jsonObjects.JSONArray
import projeto.jsonObjects.JSONObject
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
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    val srcArea = JTextArea()
    val frame = JFrame("PA - JSON Object Editor").apply {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = GridLayout(0, 2)
        size = Dimension(900, 900)

        val left = JPanel()
        left.layout = GridLayout()
        val scrollPane = JScrollPane(appPanel()).apply {
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

    private fun appPanel(): JPanel =
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

                        addValue.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            when (val value = JOptionPane.showInputDialog("Insert value:")) {
                                "true" -> {
                                    add(CheckboxWidget(label, true))
                                }
                                "false" -> {
                                    add(CheckboxWidget(label, false))
                                }
                                else -> {
                                    add(ValueWidget(label, value))
                                }
                            }
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addArray.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            controller.addArray(JSONArray(), label)
                            add(ObjectWidget(label))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addObject.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            controller.addObject(JSONObject(), label)
                            add(ObjectWidget(label))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }
                        /*
                        val del = JButton("delete all")
                        del.addActionListener {
                            components.forEach {
                                remove(it)
                            }
                            menu.isVisible = false
                            revalidate()
                            frame.repaint()
                        }
                        */
                        menu.add(addValue)
                        menu.add(addArray)
                        menu.add(addObject)

                        val location: Point = MouseInfo.getPointerInfo().location
                        val x: Double = location.getX() - frame.location.x
                        val y: Double = location.getY() - frame.location.y - 20
                        menu.show(this@apply, x.toInt(), y.toInt());
                    }
                }
            })
        }

    fun ValueWidget(label: String, value: String) : JPanel =
        JPanel().apply{
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            if(value.matches(regex)) {
                controller.editModel(Integer.parseInt(value), label)
            } else {
                controller.editModel(value, label)
            }
            add(JLabel(label))
            val text = JTextField(value)
            text.addFocusListener(object: FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    controller.editModel(text.text, label)
                    update()
                }
            })
            add(text)
        }

    fun ObjectWidget(key: String) : JPanel =
        JPanel().apply{
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val panel = JPanel()
            panel.layout = GridLayout()
            val scrollPane = JScrollPane(appPanel()).apply {
                horizontalScrollBarPolicy = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
                verticalScrollBarPolicy = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
            }
            panel.add(scrollPane)
            add(panel)
        }

    fun CheckboxWidget(label: String, value: Boolean) : JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(label))
            val checkbox = JCheckBox("", value)
            controller.editModel(value, label)

            checkbox.addActionListener {
                controller.editModel(checkbox.isSelected, label)
                update()
            }
            add(checkbox)
        }

}







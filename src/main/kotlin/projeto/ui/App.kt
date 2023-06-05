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
                                    add(controller.addCheckboxWidget(context, true, label, view))
                                    update()
                                }
                                "false" -> {
                                    add(controller.addCheckboxWidget(context, false, label, view))
                                    update()
                                }
                                else -> {
                                    add(controller.addValueWidget(context, label, value, view))
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
                            controller.addNestedArray(context, label, jsonArray)
                            add(controller.addArrayWidget(label, jsonArray, view))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addObject.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            val jsonObject = JSONObject()
                            controller.addNestedObject(context, label, jsonObject)
                            add(controller.addObjectWidget(label, jsonObject, view))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        delete.addActionListener {

                        }

                        undo.addActionListener {

                            revalidate()
                            update()
                            frame.repaint()
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
            layout = FlowLayout(FlowLayout.CENTER)
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
                                    add(controller.addArrayCheckboxWidget(context, true,view ))
                                    update()
                                }
                                "false" -> {
                                    add(controller.addArrayCheckboxWidget(context, false, view))
                                    update()
                                }
                                else -> {
                                    add(controller.addArrayValueWidget(context, value, view))
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
                            controller.addArrayNestedArray(context,jsonArray)
                            add(controller.addArrayWidget(label, jsonArray, view))
                            menu.isVisible = false
                            revalidate()
                            update()
                            frame.repaint()
                        }

                        addObject.addActionListener {
                            val label = JOptionPane.showInputDialog("Insert label:")
                            val jsonObject = JSONObject()
                            controller.addArrayNestedObject(context, jsonObject)
                            add(controller.addObjectWidget(label,jsonObject, view))
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




}







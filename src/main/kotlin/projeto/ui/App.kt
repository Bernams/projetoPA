import java.awt.Checkbox
import java.awt.Component
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.*
import javax.swing.*


fun main() {
    Editor().open()
}

class Editor {
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
        val srcArea = JTextArea()
        srcArea.tabSize = 2
        srcArea.text = "TODO"
        srcArea.isEditable = false
        right.add(srcArea)
        add(right)
    }

    fun open() {
        frame.isVisible = true
    }

    fun appPanel(): JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(ValueWidget("A", "um"))
            add(CheckboxWidget("estrangeiro", true))

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
                            frame.repaint()
                        }

                        addArray.addActionListener {

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
                        menu.show(this@apply, 100, 100);
                    }
                }
            })
        }

    fun ValueWidget(key: String, value: String) : JPanel =
        JPanel().apply{
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val text = JTextField(value)
            text.addFocusListener(object: FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    println("Perdeu foco: ${text.text}")
                }
            })
            add(text)
        }

    fun CheckboxWidget(key: String, value: Boolean) : JPanel =
        JPanel().apply {
            layout = BoxLayout(this, BoxLayout.X_AXIS)
            alignmentX = Component.LEFT_ALIGNMENT
            alignmentY = Component.TOP_ALIGNMENT

            add(JLabel(key))
            val checkbox = JCheckBox("", value)

            checkbox.addFocusListener(object: FocusAdapter() {
                override fun focusLost(e: FocusEvent) {
                    println("Perdeu foco: ${checkbox.isSelected}")
                }
            })
            add(checkbox)
        }

}







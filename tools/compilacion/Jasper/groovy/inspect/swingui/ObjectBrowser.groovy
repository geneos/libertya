package groovy.inspect.swingui

import java.awt.*
import javax.swing.*
import groovy.swing.SwingBuilder
import groovy.inspect.Inspector

/**
A little GUI to show some of the Inspector capabilities.
Starting this script opens the ObjectBrowser on "some String".
Use it in groovysh or groovyConsole to inspect your object of
interest with
<code>
ObjectBrowser.inspect(myObject)
</code>.

@author Dierk Koenig
**/
class ObjectBrowser {
    
    def inspector
    def swing, frame, fieldTable, methodTable, itemTable, mapTable

    static void main(args) {
        inspect("some String")
    }
    static void inspect(objectUnderInspection){
        def browser = new ObjectBrowser()
        browser.inspector = new Inspector(objectUnderInspection)
        browser.run()
    }
    
    void run() {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        swing = new SwingBuilder()
        
        frame = swing.frame(title:'Groovy Object Browser', location:[200,200], size:[800,600],
                defaultCloseOperation:WindowConstants.DISPOSE_ON_CLOSE) {
                
            menuBar {
                menu(text:'Help') {
                    menuItem() {action(name:'About', closure: this.&showAbout)}
                }
            }
            panel() {
                borderLayout()
                panel(  name:"Class Info",
                        border: BorderFactory.createEmptyBorder(5,10,5,10),
                        constraints:BorderLayout.NORTH) {
                    flowLayout(alignment:FlowLayout.LEFT)
                    def props = inspector.classProps
                    def classLabel = '<html>' + props.join('<br>')
                    label(classLabel)
                }
                tabbedPane(constraints:BorderLayout.CENTER){
                    if (inspector.object instanceof Collection) {
	                    scrollPane(name: ' Collection data ') {
	                        itemTable = table() {
	                        	int i = 0
	                            def data = inspector.object.collect { val -> [i++, val] }
	                            tableModel(list:data) {
	                                closureColumn(header:'Index', read:{it[0]})
	                                closureColumn(header:'Value', read:{it[1]})
	                            }
	                        }
	                    }
                    }
                    if (inspector.object instanceof Map) {
	                    scrollPane(name: ' Map data ') {
	                        itemTable = table() {
	                        	int i = 0
	                            def data = inspector.object.collect { key, val -> [i++, key, val] }
	                            tableModel(list:data) {
	                                closureColumn(header:'Index', read:{it[0]})
	                                closureColumn(header:'Key',   read:{it[1]})
	                                closureColumn(header:'Value', read:{it[2]})
	                            }
	                        }
	                    }
                    }
                    scrollPane(name: ' Public Fields and Properties ') {
                        fieldTable = table() {
                            def data = Inspector.sort(inspector.publicFields.toList())
                            data.addAll(Inspector.sort(inspector.propertyInfo.toList()))
                            tableModel(list:data) {
                                closureColumn(header:'Name',        read:{it[Inspector.MEMBER_NAME_IDX]})
                                closureColumn(header:'Value',       read:{it[Inspector.MEMBER_VALUE_IDX]})
                                closureColumn(header:'Type',        read:{it[Inspector.MEMBER_TYPE_IDX]})
                                closureColumn(header:'Origin',      read:{it[Inspector.MEMBER_ORIGIN_IDX]})
                                closureColumn(header:'Modifier',    read:{it[Inspector.MEMBER_MODIFIER_IDX]})
                                closureColumn(header:'Declarer',    read:{it[Inspector.MEMBER_DECLARER_IDX]})
                            }
                        }
                    }
                    scrollPane(name:' (Meta) Methods ' ) {
                        methodTable = table() {
                            def data = Inspector.sort(inspector.methods.toList())
                            data.addAll(Inspector.sort(inspector.metaMethods.toList()))

                            tableModel(list:data) {
                                closureColumn(header:'Name',        read:{it[Inspector.MEMBER_NAME_IDX]})
                                closureColumn(header:'Params',      read:{it[Inspector.MEMBER_PARAMS_IDX]})
                                closureColumn(header:'Type',        read:{it[Inspector.MEMBER_TYPE_IDX]})
                                closureColumn(header:'Origin',      read:{it[Inspector.MEMBER_ORIGIN_IDX]})
                                closureColumn(header:'Modifier',    read:{it[Inspector.MEMBER_MODIFIER_IDX]})
                                closureColumn(header:'Declarer',    read:{it[Inspector.MEMBER_DECLARER_IDX]})
                                closureColumn(header:'Exceptions',  read:{it[Inspector.MEMBER_EXCEPTIONS_IDX]})
                            }
                        }
                    }
                }
            }
        }
        
        // Add a bit of formatting
        addSorter(itemTable)
        addSorter(mapTable)
        addSorter(fieldTable)
        addSorter(methodTable)
        
        frame.show()
        frame.toFront()
    }
    
    void addSorter(table) {
    	if (table != null) {
	    	def sorter = new TableSorter(table.model)
    		table.model = sorter
    		sorter.addMouseListenerToHeaderInTable(table)
    	}
    }
    
    void showAbout(EventObject evt) {
         def pane = swing.optionPane()
         // work around GROOVY-1048
         pane.setMessage('An interactive GUI to explore object capabilities.')
         def dialog = pane.createDialog(frame, 'About Groovy Object Browser')
         dialog.show()
    }
}

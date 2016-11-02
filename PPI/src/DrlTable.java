
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.tree.TreeCellEditor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.table.*;


public class DrlTable extends JTable 
{
  
  public DrlTable(TableModel dm) {
        super(dm, null, null);
        setDefaultRenderer( JComponent.class, new JComponentCellRenderer() );
        setDefaultEditor( JComponent.class, new JComponentCellEditor() );
    }
  
  public TableCellRenderer getCellRenderer(int row, int column) {
				TableColumn tableColumn = getColumnModel().getColumn(column);
				TableCellRenderer renderer = tableColumn.getCellRenderer();
				if (renderer == null) {
					Class c = getColumnClass(column);
					if( c.equals(Object.class) )
					{
						Object o = getValueAt(row,column);
						if( o != null )
							c = getValueAt(row,column).getClass();
					}
        
					renderer = getDefaultRenderer(c);
				}
				return renderer;
			}
  	public TableCellEditor getCellEditor(int row, int column) {
				TableColumn tableColumn = getColumnModel().getColumn(column);
				TableCellEditor editor = tableColumn.getCellEditor();
				if (editor == null) {
					Class c = getColumnClass(column);
					if( c.equals(Object.class) )
					{
						Object o = getValueAt(row,column);
						if( o != null )
							c = getValueAt(row,column).getClass();
					}
					
          editor = getDefaultEditor(c);
				}
				return editor;
			}

  public DrlTable()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    this.setIntercellSpacing(new Dimension(3, 3));
    this.setMaximumSize(new Dimension(10, 10));
    this.setPreferredSize(new Dimension(10, 10));
  }

			
		}
class JComponentCellRenderer implements TableCellRenderer
{
    public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int column) {
        return (JComponent)value;
    }
}
class RowColor extends DefaultTableCellRenderer 
{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(new java.awt.Color(255, 72, 72));
        return c;
    }
}
 class JComponentCellEditor implements TableCellEditor, TreeCellEditor,
Serializable {
	
	protected EventListenerList listenerList = new EventListenerList();
	transient protected ChangeEvent changeEvent = null;
	
	protected JComponent editorComponent = null;
	protected JComponent container = null;		// Can be tree or table
	
	
	public Component getComponent() {
		return editorComponent;
	}
	
	
	public Object getCellEditorValue() {
		return editorComponent;
	}
	
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}
	
	public boolean shouldSelectCell(EventObject anEvent) {
		if( editorComponent != null && anEvent instanceof MouseEvent
			&& ((MouseEvent)anEvent).getID() == MouseEvent.MOUSE_PRESSED )
		{
            Component dispatchComponent = SwingUtilities.getDeepestComponentAt(editorComponent, 3, 3 );
			MouseEvent e = (MouseEvent)anEvent;
			MouseEvent e2 = new MouseEvent( dispatchComponent, MouseEvent.MOUSE_RELEASED,
				e.getWhen() + 100000, e.getModifiers(), 3, 3, e.getClickCount(),
				e.isPopupTrigger() );
			dispatchComponent.dispatchEvent(e2); 
			e2 = new MouseEvent( dispatchComponent, MouseEvent.MOUSE_CLICKED,
				e.getWhen() + 100001, e.getModifiers(), 3, 3, 1,
				e.isPopupTrigger() );
			dispatchComponent.dispatchEvent(e2); 
		}
		return false;
	}
	
	public boolean stopCellEditing() {
		fireEditingStopped();
		return true;
	}
	
	public void cancelCellEditing() {
		fireEditingCanceled();
	}
	
	public void addCellEditorListener(CellEditorListener l) {
		listenerList.add(CellEditorListener.class, l);
	}
	
	public void removeCellEditorListener(CellEditorListener l) {
		listenerList.remove(CellEditorListener.class, l);
	}
	
	protected void fireEditingStopped() {
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==CellEditorListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((CellEditorListener)listeners[i+1]).editingStopped(changeEvent);
			}	       
		}
	}
	
	protected void fireEditingCanceled() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==CellEditorListener.class) {
				// Lazily create the event:
				if (changeEvent == null)
					changeEvent = new ChangeEvent(this);
				((CellEditorListener)listeners[i+1]).editingCanceled(changeEvent);
			}	       
		}
	}
	
	// implements javax.swing.tree.TreeCellEditor
	public Component getTreeCellEditorComponent(JTree tree, Object value,
		boolean isSelected, boolean expanded, boolean leaf, int row) {
		String         stringValue = tree.convertValueToText(value, isSelected,
			expanded, leaf, row, false);
		
		editorComponent = (JComponent)value;
		container = tree;
		return editorComponent;
	}
	
	// implements javax.swing.table.TableCellEditor
	public Component getTableCellEditorComponent(JTable table, Object value,
		boolean isSelected, int row, int column) {
		
		editorComponent = (JComponent)value;
		container = table;
		return editorComponent;
	}
	
} // End of class JComponentCellEditor 


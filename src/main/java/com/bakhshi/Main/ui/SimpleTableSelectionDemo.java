package com.bakhshi.Main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
public class SimpleTableSelectionDemo extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean DEBUG = false;
    private JTable table;
    
    private int selectedRow;



    public SimpleTableSelectionDemo(Object[][] data, String[] columns) {
        super(new GridLayout(1,0));
         table = new JTable(data, columns);
         table.setColumnSelectionAllowed(false);
      
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.setDefaultRenderer(String.class, centerRenderer);
        
        Color color=new Color(255, 255, 255);
        table.setBackground(color);
        table.getTableHeader().setFont(new Font("B Titr", Font.BOLD, 17));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setSize(50, 35);
        table.setRowHeight(25);
        table.getTableHeader().setResizingAllowed(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 40));
        table.setFont(new Font("B Nazanin", Font.BOLD, 17));

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableCellRenderer renderers = table.getDefaultRenderer(String.class);
        ((javax.swing.JLabel) renderers).setHorizontalAlignment(SwingConstants.RIGHT);
        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setColumnHeader(new JViewport() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -261989302690318393L;

			@Override public Dimension getPreferredSize() {
              Dimension d = super.getPreferredSize();
              d.height = 40;
              return d;
            }
          });

        //Add the scroll pane to this panel.
        add(scrollPane);
        this.setBackground(Color.MAGENTA);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    
	public int getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(int selectedRow) {
		this.selectedRow = selectedRow;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	
    
    
  
}


           
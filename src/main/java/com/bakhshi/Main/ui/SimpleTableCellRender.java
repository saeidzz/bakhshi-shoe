package com.bakhshi.Main.ui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class SimpleTableCellRender extends DefaultTableCellRenderer {
	  /**
	 * 
	 */
	private static final long serialVersionUID = -6013073103829569303L;
	/**
	 * 
	 */

	public Component getTableCellRendererComponent(JTable table,
              Object value,
              boolean isSelected,
              boolean hasFocus,
              int row,
              int column) {
Component c = 
super.getTableCellRendererComponent(table, value,
       isSelected, hasFocus,
       row, column);

c.setFont(new Font("Arial" +
		"", Font.BOLD, 17));

return c;
 }
}
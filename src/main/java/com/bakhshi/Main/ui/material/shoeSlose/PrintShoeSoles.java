package com.bakhshi.Main.ui.material.shoeSlose;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.bakhshi.Main.Model.materials.shoeSoles.ShoeSoles;
import com.bakhshi.Main.ui.Container;
import com.bakhshi.Main.ui.SimpleTableSelectionDemo;
import com.bakhshi.Main.ui.WorkWithPdf;

public class PrintShoeSoles {

	private JFrame frame;
	SimpleTableSelectionDemo panel_2;
	int selectedRow;
	int selectedColumn;
	JPanel panel_1;
	Object[][] data;

	public PrintShoeSoles(List<ShoeSoles> shoeSoles) {
		initialize(shoeSoles);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final  List<ShoeSoles> shoeSoles) {
		frame = new JFrame();
		frame.setResizable(false);
frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
		PrintShoeSoles.class.getResource("/com/bakhshi/Main/ui/icon.jpg")));
frame.setBounds(100, 100, 796, 904);
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setVisible(true);

JPanel panel = new JPanel();
frame.getContentPane().add(panel, BorderLayout.CENTER);

 panel_1 = new JPanel();
 panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
panel_1.setBackground(Color.WHITE);

String[] columnNames = new String[] { " توسط ", "شماره فاکتور",
		" مبلغ کل ", " فی ", " تعداد ", " سایز ", " کد زیره ",
		" تاریخ ", " ردیف " };

data = new Object[shoeSoles.size()][9];

int i = 0;
for (ShoeSoles s : shoeSoles) {
	data[i] = new Object[] { s.getSavedBy(),
			s.getFactorNumber(),
			String.format("%,.0f",s.getWholePrice()),
			String.format("%,.0f", s.getUnitPrice()), s.getNumber(),
			s.getSize(), s.getCode(), s.getDate().toString(), i };
	i++;
}

panel_2 = new SimpleTableSelectionDemo(data, columnNames);
panel_2.getTable().setBorder(new LineBorder(new Color(0, 0, 0)));
panel_2.getTable().setForeground(Color.BLACK);
panel_2.getTable().setBackground(Color.WHITE);
panel_2.setForeground(Color.WHITE);
panel_2.setBackground(Color.WHITE);
panel_2.setSize(600, 200);
panel_2.setBorder(null);

JLabel label_5 = new JLabel("گروه تولیدی زیره کفش بخشی");
label_5.setFont(new Font("Tahoma", Font.BOLD, 30));
label_5.setBackground(Color.WHITE);

JLabel lblNewLabel = new JLabel(
		new ImageIcon(
				Container.class
						.getResource("icon30.jpg")));

JLabel lblNewLabel_1 = new JLabel("آدرس :سه راه آدران-شهرک قلعه میر-بعد از آتش نشانی - خیابان شهید مفتح-خیبان درختی سمت راست-اولین کارخانه سمت چپ-پلاک 1");
lblNewLabel_1.setFont(new Font("B Nazanin", Font.BOLD, 16));

JLabel label_11 = new JLabel("تلفن :56863214                فکس :56860589");
label_11.setFont(new Font("B Nazanin", Font.BOLD, 16));
JLabel label_12 = new JLabel("آدرس دفتر مرکزی :تهران -منطقه 17 - خیابان سجاد جنوبی -نرسیده به میدان بهاران -پلاک 40 -فروشگاه بخشی");
label_12.setFont(new Font("B Nazanin", Font.BOLD, 16));
JLabel label_13 = new JLabel("تلفن :66207393            فکس :66310023");
label_13.setFont(new Font("B Nazanin", Font.BOLD, 16));
GroupLayout gl_panel_1 = new GroupLayout(panel_1);
gl_panel_1.setHorizontalGroup(
	gl_panel_1.createParallelGroup(Alignment.TRAILING)
		.addGroup(gl_panel_1.createSequentialGroup()
			.addGap(153)
			.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(135, Short.MAX_VALUE))
		.addGroup(gl_panel_1.createSequentialGroup()
			.addGap(346)
			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(388, Short.MAX_VALUE))
		.addGroup(gl_panel_1.createSequentialGroup()
			.addContainerGap()
			.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
				.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblNewLabel_1)
				.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 628, GroupLayout.PREFERRED_SIZE)))
		.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
);
gl_panel_1.setVerticalGroup(
	gl_panel_1.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_panel_1.createSequentialGroup()
			.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
			.addGap(18)
			.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addComponent(label_12)
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addComponent(label_13)
			.addGap(13)
			.addComponent(lblNewLabel_1)
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(label_11)
			.addContainerGap())
);
panel_1.setLayout(gl_panel_1);

JButton btnNewButton = new JButton("حذف مورد انتخاب شده از جدول");
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
     if(shoeSoles.get(selectedRow)!=null){
    	 shoeSoles.remove(shoeSoles.get(selectedRow));
    	 new PrintShoeSoles(shoeSoles);
    	 frame.dispose();
    	 
     }else{
			JOptionPane.showMessageDialog(frame, "لطفا یک سطر را انتخاب نمایید !!");

     }
	}
});
btnNewButton.setBackground(Color.WHITE);
btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);

JButton button = new JButton("چاپ فاکتور");
button.setFont(new Font("Tahoma", Font.BOLD, 13));
button.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		 PrinterJob pjob = PrinterJob.getPrinterJob();
		 Paper paper = new Paper();
		 
		    double margin = 0;
		    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
		        - margin * 2);
		 PageFormat preformat = pjob.defaultPage();
		 preformat.setOrientation(PageFormat.LANDSCAPE);
		 PageFormat postformat = pjob.pageDialog(preformat);
		 //If user does not hit cancel then print.
		 if (preformat != postformat) {
		     //Set print component  
		     pjob.setPrintable(new WorkWithPdf(panel_1), postformat);
		     if (pjob.printDialog()) {
		         try {
					pjob.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		     }
		 }
	}
});
button.setBackground(Color.WHITE);
button.setHorizontalAlignment(SwingConstants.TRAILING);
GroupLayout gl_panel = new GroupLayout(panel);
gl_panel.setHorizontalGroup(
	gl_panel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_panel.createSequentialGroup()
			.addGap(101)
			.addComponent(button)
			.addPreferredGap(ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
			.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
			.addGap(147))
		.addGroup(gl_panel.createSequentialGroup()
			.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 786, GroupLayout.PREFERRED_SIZE)
			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
);
gl_panel.setVerticalGroup(
	gl_panel.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_panel.createSequentialGroup()
			.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
				.addComponent(button, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(ComponentPlacement.RELATED)
			.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE))
);
panel.setLayout(gl_panel);

JTable table = panel_2.getTable();
ListSelectionModel colSM = table.getColumnModel().getSelectionModel();
colSM.addListSelectionListener(new ListSelectionListener() {
	public void valueChanged(ListSelectionEvent e) {
		// Ignore extra messages.
		if (e.getValueIsAdjusting())
			return;

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (lsm.isSelectionEmpty()) {
			System.out.println("No columns are selected.");
		} else {
			selectedColumn = lsm.getMinSelectionIndex();
			// System.out.println("Column " + selectedCol
			// + " is now selected.");
		}
	}
});

ListSelectionModel rowSM = table.getSelectionModel();
rowSM.addListSelectionListener(new ListSelectionListener() {
	public void valueChanged(ListSelectionEvent e) {
		// Ignore extra messages.
		if (e.getValueIsAdjusting())
			return;

		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (lsm.isSelectionEmpty()) {
			System.out.println("No rows are selected.");
		} else {
			selectedRow = lsm.getMinSelectionIndex();
			System.out.println("Row " + selectedRow
					+ " is now selected.");
		}
	}
});
}
}

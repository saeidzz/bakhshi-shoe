package com.bakhshi.Main.ui.evaFactor;

import java.util.List;

import javax.swing.JFrame;

import com.bakhshi.Main.Model.Customer;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.factor.EVASaleFactor;
import com.bakhshi.Main.ui.Container;
import com.bakhshi.Main.ui.SimpleTableCellRender;
import com.bakhshi.Main.ui.SimpleTableSelectionDemo;
import com.bakhshi.Main.ui.WorkWithPdf;
import com.bakhshi.Main.ui.pvcFactor.PVCprintFactor;

import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.border.LineBorder;

public class EVaprintFactor {

	private JFrame frame;
	private JTextField numberf;
	private JTextField datef;
	private JTextField phoneNF;
	private JTextField namef;
	SimpleTableSelectionDemo panel_2;
	private JTextField totalPrice;
	int selectedRow;
	int selectedColumn;
	JPanel panel_1;

	public EVaprintFactor(Customer customer, List<EVASaleFactor> factors) {
		initialize(customer, factors);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Customer customer,final List<EVASaleFactor> factors){
			frame = new JFrame();
			frame.setResizable(false);
	frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
			PVCprintFactor.class.getResource("/com/bakhshi/Main/ui/icon.jpg")));
	frame.setBounds(100, 100, 796, 950);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	frame.setVisible(true);

	JPanel panel = new JPanel();
	frame.getContentPane().add(panel, BorderLayout.CENTER);

	 panel_1 = new JPanel();
	 panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
	panel_1.setBackground(Color.WHITE);

	JLabel label = new JLabel("فاکتور فروش");
	label.setFont(new Font("Tahoma", Font.BOLD, 24));
	label.setBackground(Color.WHITE);

	numberf = new JTextField();
	numberf.setFont(new Font("B Nazanin", Font.BOLD, 19));
	numberf.setColumns(10);
	numberf.setBackground(Color.WHITE);
	numberf.setText(factors.get(0).getId()+"");

	datef = new JTextField();
	datef.setFont(new Font("B Nazanin", Font.BOLD, 19));
	datef.setColumns(10);
	datef.setBackground(Color.WHITE);
	datef.setText(new MyDate().toString());

	JLabel label_1 = new JLabel("تاریخ :");
	label_1.setFont(new Font("Tahoma", Font.BOLD, 17));
	label_1.setBackground(Color.WHITE);

	JLabel label_2 = new JLabel("شماره :");
	label_2.setFont(new Font("Tahoma", Font.BOLD, 17));
	label_2.setBackground(Color.WHITE);

	phoneNF = new JTextField();
	phoneNF.setFont(new Font("B Nazanin", Font.BOLD, 19));
	phoneNF.setColumns(10);
	phoneNF.setBackground(Color.WHITE);
	phoneNF.setText(customer.getPhoneNumber());

	JLabel label_3 = new JLabel("تلفن :");
	label_3.setFont(new Font("Tahoma", Font.BOLD, 17));
	label_3.setBackground(Color.WHITE);

	namef = new JTextField();
	namef.setFont(new Font("B Nazanin", Font.BOLD, 19));
	namef.setColumns(10);
	namef.setBackground(Color.WHITE);
	namef.setText(customer.getName());

	JLabel label_4 = new JLabel("نام :");
	label_4.setFont(new Font("Tahoma", Font.BOLD, 17));
	label_4.setBackground(Color.WHITE);

	String[] columns = new String[] { "قیمت کل", "فی", "تعداد", "سایز","نوع کالا", "شماره فاکتور", "ردیف" };
	Object[][] data = new Object[factors.size()][7];

	int i = 0;
	double total = 0;
	for (EVASaleFactor factor : factors) {
		data[i] = new Object[] {
				String.format("%,.0f", factor.getWholePrice()),
				String.format("%,.0f", factor.getUnitPrice()),
				String.format("%.0f", factor.getNumber()),
				factor.getSize(), factor.getProductModel(), factor.getId(),i

		};
		total += factor.getWholePrice();
		i++;
	}

	panel_2 = new SimpleTableSelectionDemo(data, columns);
	panel_2.getTable().setBorder(new LineBorder(new Color(0, 0, 0)));
	panel_2.getTable().setForeground(Color.BLACK);
	panel_2.getTable().setBackground(Color.WHITE);
	panel_2.setForeground(Color.WHITE);
	panel_2.setBackground(Color.WHITE);
	panel_2.setSize(600, 200);
	panel_2.setBorder(null);
	panel_2.getTable().getColumnModel().getColumn(4).setCellRenderer(new SimpleTableCellRender());


	JLabel label_5 = new JLabel("گروه تولیدی زیره کفش بخشی");
	label_5.setFont(new Font("Tahoma", Font.BOLD, 30));
	label_5.setBackground(Color.WHITE);

	JLabel label_6 = new JLabel(
			"کلیه ی کالای فوق صحیح و سالم به رویت خریدار رسیده و تحویل گردیده ");
	label_6.setFont(new Font("B Nazanin", Font.BOLD, 18));
	label_6.setBackground(Color.WHITE);

	JLabel label_7 = new JLabel(
			"و هیچ گونه مرجوعی از طرف فروشنده مورد پذیرش نیست");
	label_7.setFont(new Font("B Nazanin", Font.BOLD, 18));
	label_7.setBackground(Color.WHITE);

	JLabel label_8 = new JLabel("امضا خریدار");
	label_8.setFont(new Font("B Nazanin", Font.BOLD, 18));

	JLabel label_9 = new JLabel("امضا فروشنده");
	label_9.setFont(new Font("B Nazanin", Font.BOLD, 18));

	totalPrice = new JTextField();
	totalPrice.setFont(new Font("B Nazanin", Font.BOLD, 18));
	totalPrice.setColumns(10);
	totalPrice.setBackground(Color.WHITE);
	totalPrice.setText(String.format("%,.0f", total));
	
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
	
	JLabel label_10 = new JLabel("قیمت کل :");
	label_10.setFont(new Font("Tahoma", Font.BOLD, 17));
	label_10.setBackground(Color.WHITE);
	GroupLayout gl_panel_1 = new GroupLayout(panel_1);
	gl_panel_1.setHorizontalGroup(
		gl_panel_1.createParallelGroup(Alignment.TRAILING)
			.addGroup(gl_panel_1.createSequentialGroup()
				.addContainerGap()
				.addComponent(lblNewLabel)
				.addGap(111)
				.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(143, Short.MAX_VALUE))
			.addGroup(gl_panel_1.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(numberf, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addComponent(datef, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
								.addGap(161))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(label_1)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(phoneNF)
							.addComponent(namef, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
						.addGap(62)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(label_4)
							.addComponent(label_3))
						.addGap(41))
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap(457, Short.MAX_VALUE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)))
				.addGap(87))
			.addGroup(gl_panel_1.createSequentialGroup()
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 789, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(232, Short.MAX_VALUE))
			.addGroup(gl_panel_1.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 391, Short.MAX_VALUE)
								.addComponent(label_7))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(totalPrice, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 456, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)))))
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(58)
						.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
						.addComponent(label_8)))
				.addGap(262))
			.addGroup(gl_panel_1.createSequentialGroup()
				.addContainerGap(23, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
					.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblNewLabel_1)
					.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE))
				.addGap(249))
	);
	gl_panel_1.setVerticalGroup(
		gl_panel_1.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel_1.createSequentialGroup()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblNewLabel))
					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
				.addGap(1)
				.addComponent(label, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
					.addComponent(namef, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addComponent(numberf, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_4))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
					.addComponent(phoneNF, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addComponent(datef, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(5)
						.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addComponent(totalPrice, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel_1.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(90)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_9)
							.addComponent(label_8))))
				.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
				.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(label_13)
				.addPreferredGap(ComponentPlacement.RELATED)
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
         if(factors.get(selectedRow)!=null){
        	 factors.remove(factors.get(selectedRow));
        	 new EVaprintFactor(customer,factors);
        	 frame.dispose();
        	 
         }else{
				JOptionPane.showMessageDialog(frame, "لطفا یک سطر را انتخاب نمایید !!");

         }
		}
	});
	btnNewButton.setBackground(Color.WHITE);
	btnNewButton.setHorizontalAlignment(SwingConstants.TRAILING);

	JButton button = new JButton("چاپ فاکتور");
	button.setBackground(Color.WHITE);
	button.setFont(new Font("Tahoma", Font.BOLD, 13));
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			 PrinterJob pjob = PrinterJob.getPrinterJob();
			 Paper paper = new Paper();
			 
			    double margin = 0;
			    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
			        - margin * 2);
		
			 PageFormat preformat = pjob.defaultPage();
			 preformat.setPaper(paper);
			 preformat.setOrientation(PageFormat.PORTRAIT);
			 
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

	button.setMnemonic(KeyEvent.VK_B); // Shortcut: Alt + Enter	button.setBackground(Color.WHITE);
	
	button.setHorizontalAlignment(SwingConstants.TRAILING);
	GroupLayout gl_panel = new GroupLayout(panel);
	gl_panel.setHorizontalGroup(
		gl_panel.createParallelGroup(Alignment.LEADING)
			.addGroup(gl_panel.createSequentialGroup()
				.addGap(90)
				.addComponent(button)
				.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
				.addGap(170))
			.addGroup(gl_panel.createSequentialGroup()
				.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 794, Short.MAX_VALUE)
				.addContainerGap())
	);
	gl_panel.setVerticalGroup(
		gl_panel.createParallelGroup(Alignment.TRAILING)
			.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
				.addGap(4))
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

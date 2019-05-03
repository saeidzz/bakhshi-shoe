package com.bakhshi.Main.ui.offEmployee;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.bakhshi.Main.Model.Employee;
import com.bakhshi.Main.Model.Off;
import com.bakhshi.Main.data_access.CapsulDao;
import com.bakhshi.Main.data_access.ContactDao;
import com.bakhshi.Main.data_access.CustomerDao;
import com.bakhshi.Main.data_access.EmployeeDao;
import com.bakhshi.Main.data_access.FrameDao;
import com.bakhshi.Main.data_access.MaterialDao;
import com.bakhshi.Main.data_access.OffDao;
import com.bakhshi.Main.data_access.ProPertyDao;
import com.bakhshi.Main.data_access.ProductionfDao;
import com.bakhshi.Main.data_access.ShoeSolesDao;
import com.bakhshi.Main.data_access.StoredShoeSoleDao;
import com.bakhshi.Main.data_access.factors.EvaFactorDao;
import com.bakhshi.Main.data_access.factors.PvcFactorDao;
import com.bakhshi.Main.data_access.factors.SaleFactorDao;
import com.bakhshi.Main.ui.DataBaseBackupAndRestore;
import com.bakhshi.Main.ui.SearchPanel;
import com.bakhshi.Main.ui.SimpleTableSelectionDemo;
import com.bakhshi.Main.ui.WorkWithPdf;
import com.bakhshi.Main.ui.contact.Contacts;
import com.bakhshi.Main.ui.customer.Customers;
import com.bakhshi.Main.ui.dailyProduct.productions;
import com.bakhshi.Main.ui.employee.Employyes;
import com.bakhshi.Main.ui.evaFactor.EVAFactors;
import com.bakhshi.Main.ui.factor.Factors;
import com.bakhshi.Main.ui.material.capsul.CapsulList;
import com.bakhshi.Main.ui.material.frame.Frames;
import com.bakhshi.Main.ui.material.material.MaterialsList;
import com.bakhshi.Main.ui.material.shoeSlose.shoeSoles;
import com.bakhshi.Main.ui.pvcFactor.PVCFactors;
import com.bakhshi.Main.ui.storeHouse.Properties;
import com.bakhshi.Main.ui.storeHouse.shoeSole.StoredShoeSoles;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Offs {
	private JFrame frame;
	int selectedRow = -1;
	int selectedColumn;
	Object[][] data;
	SimpleTableSelectionDemo panel;
	private JTextField employeeName;
	private JTextField employeeMelliCode;
	private JTextField employeeCostractDate;

	/**
	 * Create the application.
	 */
	public Offs(List<Off> offs, Employee employee) {
		initialize(offs, employee);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final List<Off> offs, final Employee employee) {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				Offs.class.getResource("/com/bakhshi/Main/ui/icon.jpg")));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);

		String[] columnNames = new String[] { "تاریخ مرخصی ", "شماره", "ردیف" };

		data = new Object[offs.size()][3];

		int i = 0;
		for (Off off : offs) {
			data[i] = new Object[] {  off.getOffDay().toString(), off.getId(),i };
			i++;
		}

		panel = new SimpleTableSelectionDemo(data, columnNames);
		panel.getTable().setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.getTable().setForeground(Color.BLACK);
		panel.getTable().setBackground(Color.WHITE);
		panel.setForeground(Color.DARK_GRAY);
		panel.setBackground(Color.DARK_GRAY);
		panel.setSize(600, 200);
		panel.setBorder(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);

		employeeName = new JTextField();
		employeeName.setEditable(false);
		employeeName.setFont(new Font("Tahoma", Font.BOLD, 16));
		employeeName.setText(employee.getName());
		employeeName.setColumns(10);

		employeeMelliCode = new JTextField();
		employeeMelliCode.setEditable(false);
		employeeMelliCode.setFont(new Font("Tahoma", Font.BOLD, 16));
		employeeMelliCode.setText(employee.getMelliCode());
		employeeMelliCode.setColumns(10);

		employeeCostractDate = new JTextField();
		employeeCostractDate.setEditable(false);
		employeeCostractDate.setFont(new Font("Tahoma", Font.BOLD, 16));
		employeeCostractDate.setText(employee.getContractDate().toString());
		employeeCostractDate.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel_2
						.createSequentialGroup()
						.addGap(110)
						.addComponent(employeeCostractDate,
								GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
						.addGap(70)
						.addComponent(employeeMelliCode,
								GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
						.addGap(78)
						.addComponent(employeeName, GroupLayout.DEFAULT_SIZE,
								195, Short.MAX_VALUE).addGap(34)));
		gl_panel_2
				.setVerticalGroup(gl_panel_2
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																employeeMelliCode,
																GroupLayout.PREFERRED_SIZE,
																48,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																employeeCostractDate,
																GroupLayout.PREFERRED_SIZE,
																48,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																employeeName,
																GroupLayout.PREFERRED_SIZE,
																48,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		panel_2.setLayout(gl_panel_2);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 891,
						Short.MAX_VALUE)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addComponent(panel_2,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 891,
						Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 429,
								Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 91,
								GroupLayout.PREFERRED_SIZE)));

		JButton btnNewButton = new JButton("حذف مرخصی انتخاب شده");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (offs.get(selectedRow) != null) {
					OffDao.delete(offs.get(selectedRow).getId());
					frame.dispose();
					new Offs(OffDao.findByEmployeeIdId(employee), employee);
				} else {
					JOptionPane.showMessageDialog(frame,
							"لطفا یک مرخصی را انتخاب نمایید !!");
				}

			}
		});

		JButton button = new JButton("اضافه کردن مرخصی ");
		button.setBackground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 15));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AddNewOff(employee);
				frame.dispose();

			}
		});

		JButton button_1 = new JButton("به روز رسانی مرخصی  ");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (offs.get(selectedRow) != null) {
					
					new EditOffs(offs.get(selectedRow));
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(frame,
							"لطفا یک مرخصی را انتخاب نمایید !!");
				}
			}
		});
		button_1.setBackground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton button_2 = new JButton("چاپ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 PrinterJob pjob = PrinterJob.getPrinterJob();
				 PageFormat preformat = pjob.defaultPage();
				 preformat.setOrientation(PageFormat.LANDSCAPE);
				 PageFormat postformat = pjob.pageDialog(preformat);
				 //If user does not hit cancel then print.
				 if (preformat != postformat) {
				     //Set print component  
				     pjob.setPrintable(new WorkWithPdf(panel), postformat);
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
		button_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		button_2.setBackground(Color.WHITE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 281, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);

		JTable table = panel.getTable();
		/*
		 * 
		 * TableColumn column=table.getColumnModel().getColumn(4);;
		 * column.setPreferredWidth(400); ListSelectionModel colSM =
		 * table.getColumnModel().getSelectionModel();
		 * colSM.addListSelectionListener(new ListSelectionListener() { public
		 * void valueChanged(ListSelectionEvent e) { // Ignore extra messages.
		 * if (e.getValueIsAdjusting()) return;
		 * 
		 * ListSelectionModel lsm = (ListSelectionModel) e.getSource(); if
		 * (lsm.isSelectionEmpty()) {
		 * System.out.println("No columns are selected."); } else {
		 * selectedColumn = lsm.getMinSelectionIndex(); //
		 * System.out.println("Column " + selectedCol // + " is now selected.");
		 * } } });
		 */

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

		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.setBackground(Color.WHITE);
		menuBar.setToolTipText("منو");
		frame.setJMenuBar(menuBar);

		JMenu menu_1 = new JMenu("فایل");
		menu_1.setBackground(Color.WHITE);
		menu_1.setForeground(Color.BLACK);
		menu_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(menu_1);

		JMenuItem exitItem = new JMenuItem("خروج از برنامه");
		exitItem.setFont(new Font("SansSerif", Font.BOLD, 16));
		exitItem.setForeground(Color.BLACK);
		exitItem.setBackground(Color.WHITE);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});

		menu_1.add(exitItem);

		JMenuItem exportDb = new JMenuItem("خروجی از پایگاه داده");
		exportDb.setBackground(Color.WHITE);
		exportDb.setFont(new Font("Segoe UI", Font.BOLD, 16));
		exitItem.setFont(new Font("SansSerif", Font.BOLD, 16));
		exitItem.setForeground(Color.BLACK);
		exitItem.setBackground(Color.WHITE);
		exportDb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				  try {
			            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			        } catch (Exception e) {
			            e.printStackTrace();
			        } 
				JFileChooser fileChooser = new JFileChooser();
				int retval = fileChooser.showSaveDialog(frame);
				if (retval == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					if (file == null) {
						return;
					}
					if (!file.getName().toLowerCase().endsWith("sql")) {
						System.out.println();
						String path = file.getAbsolutePath() + ".sql";
						try {
							DataBaseBackupAndRestore.backUp(path);
						} catch (SQLException | URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
		});

		menu_1.add(exportDb);
		JMenuItem importDb = new JMenuItem("بازگردانی پایگاه داده");
		importDb.setBackground(Color.WHITE);
		importDb.setFont(new Font("Segoe UI", Font.BOLD, 16));
		importDb.setFont(new Font("SansSerif", Font.BOLD, 16));
		importDb.setForeground(Color.BLACK);
		importDb.setBackground(Color.WHITE);
		importDb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				  try {
			            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			        } catch (Exception e) {
			            e.printStackTrace();
			        } 
	            final JFileChooser fc = new JFileChooser(); 
	            int returnVal = fc.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	 File file = fc.getSelectedFile();
	                //This is where a real application would open the file.
	            	 System.out.println(file);
	                try {
						DataBaseBackupAndRestore.restore(file.getAbsolutePath());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            } else {
	                System.out.println("Open command cancelled by user.");
	            }
	            System.out.println(returnVal);
	        }
			
		});

		menu_1.add(importDb);
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setForeground(Color.BLACK);
		horizontalGlue.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue);

		JMenu mnNewMenu_5 = new JMenu("جستجو");
		mnNewMenu_5.setForeground(Color.BLACK);
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnNewMenu_5);

		JMenuItem mntmNewMenuItem = new JMenuItem("پنل جستجو");
		mntmNewMenuItem.setBackground(Color.WHITE);
		mntmNewMenuItem.setFont(new Font("SansSerif", Font.BOLD, 16));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new SearchPanel();
				frame.dispose();
			}
		});
		mnNewMenu_5.add(mntmNewMenuItem);

		Component horizontalGlue_6 = Box.createHorizontalGlue();
		horizontalGlue_6.setForeground(Color.BLACK);
		horizontalGlue_6.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue_6);

		JMenu mnNewMenu = new JMenu("دفترچه تلفن");
		mnNewMenu.setBackground(Color.WHITE);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu);

		JMenuItem menuItem_2 = new JMenuItem("لیست مخاطبین");
		menuItem_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_2.setForeground(Color.BLACK);
		menuItem_2.setBackground(Color.WHITE);
		menuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new Contacts(ContactDao.read());
				frame.dispose();
			}
		});
		mnNewMenu.add(menuItem_2);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		horizontalGlue_1.setForeground(Color.BLACK);
		horizontalGlue_1.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue_1);

		JMenu mnNewMenu_1 = new JMenu("انبار");
		mnNewMenu_1.setBackground(Color.WHITE);
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.WHITE);
		mnNewMenu_1.add(separator); // SEPARATOR
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_1.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_1);

		JMenuItem menuItem_5 = new JMenuItem("انبار کالا");
		menuItem_5.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_5.setForeground(Color.BLACK);
		menuItem_5.setBackground(Color.WHITE);
		menuItem_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				new Properties(ProPertyDao.read());
				frame.dispose();
			}
		});
		mnNewMenu_1.add(menuItem_5);
		
		JMenuItem menuItem_7 = new JMenuItem("انبار زیره");
		menuItem_7.setForeground(Color.BLACK);
		menuItem_7.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_7.setBackground(Color.WHITE);
		menuItem_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				new StoredShoeSoles(StoredShoeSoleDao.read());
				frame.dispose();
			}
		});
		mnNewMenu_1.add(menuItem_7);
		

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		horizontalGlue_2.setForeground(Color.BLACK);
		horizontalGlue_2.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue_2);

		JMenu mnNewMenu_2 = new JMenu("ورودی خروجی");
		mnNewMenu_2.setBackground(Color.WHITE);
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_2.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_2);

		JMenuItem menuItem = new JMenuItem("مواد ");
		menuItem.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem.setForeground(Color.BLACK);
		menuItem.setBackground(Color.WHITE);
		mnNewMenu_2.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new MaterialsList(MaterialDao.read());
				frame.dispose();
			}
		});
		
		JMenuItem menuItem_13 = new JMenuItem("کپسول  ");
		menuItem_13.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_13.setBackground(Color.WHITE);
		menuItem_13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new CapsulList(CapsulDao.read());
				frame.dispose();

			}
		});
		mnNewMenu_2.add(menuItem_13);
		
		JMenuItem menuItem_14 = new JMenuItem("قالب ");
		menuItem_14.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_14.setBackground(Color.WHITE);
		menuItem_14.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new Frames(FrameDao.read());
				frame.dispose();
			}
		});
		
		mnNewMenu_2.add(menuItem_14);
		
		JMenuItem menuItem_15 = new JMenuItem("زیره  ");
		menuItem_15.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_15.setBackground(Color.WHITE);
		menuItem_15.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new shoeSoles(ShoeSolesDao.read());
				frame.dispose();
			}
		});
		mnNewMenu_2.add(menuItem_15);

		Component horizontalGlue_3 = Box.createHorizontalGlue();
		horizontalGlue_3.setForeground(Color.BLACK);
		horizontalGlue_3.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue_3);

		JMenu mnNewMenu_3 = new JMenu("افراد");
		mnNewMenu_3.setBackground(Color.WHITE);
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_3.setForeground(Color.BLACK);
		menuBar.add(mnNewMenu_3);

		JMenuItem menuItem_1 = new JMenuItem("مشتری ها");
		menuItem_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_1.setForeground(Color.BLACK);
		menuItem_1.setBackground(Color.WHITE);
		menuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new Customers(CustomerDao.read());
				frame.dispose();

			}
		});
		mnNewMenu_3.add(menuItem_1);

		JMenuItem menuItem_3 = new JMenuItem("کارگران");
		menuItem_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_3.setForeground(Color.BLACK);
		menuItem_3.setBackground(Color.WHITE);
		menuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new Employyes(EmployeeDao.read());
				frame.dispose();

			}
		});
		mnNewMenu_3.add(menuItem_3);

		Component horizontalGlue_5 = Box.createHorizontalGlue();
		horizontalGlue_5.setForeground(Color.BLACK);
		horizontalGlue_5.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue_5);

		JMenu menu = new JMenu("تولیدات روزانه");
		menu.setBackground(Color.WHITE);
		menu.setForeground(Color.BLACK);
		menu.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.add(menu);

		JMenuItem menuItem_4 = new JMenuItem("مدیریت تولیدات");
		menuItem_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_4.setForeground(Color.BLACK);
		menuItem_4.setBackground(Color.WHITE);
		menuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new productions(ProductionfDao.read());
				frame.dispose();
			}
		});
		menu.add(menuItem_4);

		Component horizontalGlue_4 = Box.createHorizontalGlue();
		horizontalGlue_4.setForeground(Color.BLACK);
		horizontalGlue_4.setBackground(Color.WHITE);
		menuBar.add(horizontalGlue_4);

		JMenu mnNewMenu_4 = new JMenu("فاکتور ها");
		mnNewMenu_4.setBackground(Color.WHITE);
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		mnNewMenu_4.setForeground(Color.BLACK);

		menuBar.add(mnNewMenu_4);

		JMenuItem mntmPvc_1 = new JMenuItem("لیست فاکتور PVC");
		mntmPvc_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		mntmPvc_1.setForeground(Color.BLACK);
		mntmPvc_1.setBackground(Color.WHITE);
		mntmPvc_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new PVCFactors(PvcFactorDao.read());
				frame.dispose();

			}
		});
		mnNewMenu_4.add(mntmPvc_1);

		JMenuItem mntmEvr = new JMenuItem("EVA لیست فاکتور ");
		mntmEvr.setFont(new Font("SansSerif", Font.BOLD, 16));
		mntmEvr.setForeground(Color.BLACK);
		mntmEvr.setBackground(Color.WHITE);
		mntmEvr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new EVAFactors(EvaFactorDao.read());
				frame.dispose();
			}
		});
		mnNewMenu_4.add(mntmEvr);

		JMenuItem menuItem_6 = new JMenuItem("لیست فاکتور فروش ");
		menuItem_6.setFont(new Font("SansSerif", Font.BOLD, 16));
		menuItem_6.setForeground(Color.BLACK);
		menuItem_6.setBackground(Color.WHITE);
		menuItem_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				
				new Factors(SaleFactorDao.read());
				frame.dispose();
			}
		});
		mnNewMenu_4.add(menuItem_6);

	

		frame.setSize(956, 701);
	}

}

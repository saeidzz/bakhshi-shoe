package com.bakhshi.Main.ui.evaFactor;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;

import com.bakhshi.Main.Model.Customer;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.factor.EVASaleFactor;
import com.bakhshi.Main.data_access.CapsulDao;
import com.bakhshi.Main.data_access.ContactDao;
import com.bakhshi.Main.data_access.CustomerDao;
import com.bakhshi.Main.data_access.EmployeeDao;
import com.bakhshi.Main.data_access.FrameDao;
import com.bakhshi.Main.data_access.MaterialDao;
import com.bakhshi.Main.data_access.MyDateDao;
import com.bakhshi.Main.data_access.ProPertyDao;
import com.bakhshi.Main.data_access.ProductionfDao;
import com.bakhshi.Main.data_access.ShoeSolesDao;
import com.bakhshi.Main.data_access.StoredShoeSoleDao;
import com.bakhshi.Main.data_access.factors.EvaFactorDao;
import com.bakhshi.Main.data_access.factors.PvcFactorDao;
import com.bakhshi.Main.data_access.factors.SaleFactorDao;
import com.bakhshi.Main.ui.DataBaseBackupAndRestore;
import com.bakhshi.Main.ui.SearchPanel;
import com.bakhshi.Main.ui.contact.Contacts;
import com.bakhshi.Main.ui.customer.Customers;
import com.bakhshi.Main.ui.dailyProduct.productions;
import com.bakhshi.Main.ui.employee.Employyes;
import com.bakhshi.Main.ui.factor.Factors;
import com.bakhshi.Main.ui.material.capsul.CapsulList;
import com.bakhshi.Main.ui.material.frame.Frames;
import com.bakhshi.Main.ui.material.material.MaterialsList;
import com.bakhshi.Main.ui.material.shoeSlose.shoeSoles;
import com.bakhshi.Main.ui.pvcFactor.PVCFactors;
import com.bakhshi.Main.ui.storeHouse.Properties;
import com.bakhshi.Main.ui.storeHouse.shoeSole.StoredShoeSoles;

public class EditEvaFactor {


	private JFrame frame;
	private JTextField EvaModelField;
	private JTextField yearF;
	private JTextField monthF;
	private JTextField dayF;
	private JTextField EvaNumber;
	private JTextField EvaUnitPrice;
	JComboBox<String> customerCombo;
	List<Customer>  customers;
	private JTextField sizeF;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public EditEvaFactor(EVASaleFactor evaSaleFactor) {
		initialize(evaSaleFactor);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final EVASaleFactor evaSaleFactor) {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddNewEVAFactor.class.getResource("/com/bakhshi/Main/ui/icon.jpg")));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);

		   customerCombo = new JComboBox<>();
			customerCombo.setFont(new Font("B Nazanin", Font.BOLD, 18));
			customerCombo.setBackground(Color.WHITE);
			customers=CustomerDao.read();
			
			for (Customer customer : customers) {
				customerCombo.addItem(customer.getName());
			}

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
		);
		
		EvaModelField = new JTextField();
		EvaModelField.setFont(new Font("Arial", Font.BOLD, 18));
		EvaModelField.setText(evaSaleFactor.getProductModel());
		EvaModelField.setColumns(10);
		
		yearF = new JTextField();
		yearF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		yearF.setText(evaSaleFactor.getDate().getYear()+"");
		yearF.setColumns(10);
		
		monthF = new JTextField();
		monthF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		monthF.setText(evaSaleFactor.getDate().getMonth()+"");
		monthF.setColumns(10);
		
		dayF = new JTextField();
		dayF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		dayF.setText(evaSaleFactor.getDate().getDay()+"");
		dayF.setColumns(10);
		
		EvaNumber = new JTextField();
		EvaNumber.setFont(new Font("B Nazanin", Font.BOLD, 18));
		EvaNumber.setText(String.format("%.0f", evaSaleFactor.getNumber()));
		EvaNumber.setColumns(10);
		
		EvaUnitPrice = new JTextField();
		EvaUnitPrice.setFont(new Font("B Nazanin", Font.BOLD, 18));
		EvaUnitPrice.setText(String.format ("%.0f", evaSaleFactor.getUnitPrice()));
		EvaUnitPrice.setColumns(10);
		
		JLabel label_2 = new JLabel("فی :");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label_3 = new JLabel("تعداد :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label_4 = new JLabel("تاریخ :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label_5 = new JLabel("مدل :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label_6 = new JLabel("انتخاب مشتری :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton saveBtn = new JButton("ذخیره");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String model=EvaModelField.getText();
				String day=dayF.getText().trim();
				String month=monthF.getText().trim();
				String year=yearF.getText().trim();
				String EvNumber=EvaNumber.getText().trim();
				String EvaUnitPrie=EvaUnitPrice.getText().trim();
				Customer c=customers.get(customerCombo.getSelectedIndex());
				System.out.println(customerCombo.getSelectedIndex());
				String size=sizeF.getText();
				if (model.equals("")||day.equals("")||month.equals("")||
						year.equals("")||EvNumber.equals("")||EvaUnitPrie.equals("")||c==null) {
					JOptionPane.showMessageDialog(frame,
							"تمام ورودی ها باید مقدار داشته باشند!!");
				}else{
					MyDate date=new MyDate(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day));
					long res=MyDateDao.ExistDate(date);
					if(res!=-1){
						date.setId(res);
					}else{
						MyDateDao.create(date);

					}
					EVASaleFactor evaSaleFactor1=new EVASaleFactor(c, model, Double.parseDouble(EvaUnitPrie),Double.parseDouble(EvNumber), date,size);
					evaSaleFactor1.setId(evaSaleFactor.getId());
			        EvaFactorDao.create(evaSaleFactor1);
			        new EVAFactors(EvaFactorDao.read());
			        frame.dispose();
				}
			}
		});
		
		saveBtn.setToolTipText("ذخیره");
		saveBtn.setForeground(Color.BLACK);
		saveBtn.setBackground(Color.WHITE);
		saveBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		sizeF = new JTextField();
		sizeF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		sizeF.setColumns(10);
		sizeF.setText(evaSaleFactor.getSize());
		
		JLabel label = new JLabel("سایز :");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		//String[] bookTitles = new String[] {"Effective Java", "Head First Java",
            //    "Thinking in Java", "Java for Dummies"};
	 

				
		
		
//add to the parent container (e.g. a JFrame):
//get the selected item:
//String selectedBook = (String) comboBox.getSelectedItem();
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(165)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(sizeF, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(yearF, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(monthF, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(dayF, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(EvaNumber, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(EvaUnitPrice, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addGap(18)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(customerCombo, 0, 243, Short.MAX_VALUE)
									.addComponent(EvaModelField, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))))
					.addGap(198))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(180)
					.addComponent(saveBtn, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
					.addGap(417))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerCombo, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(EvaModelField, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(yearF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(monthF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(EvaNumber, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(EvaUnitPrice, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(sizeF, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
					.addGap(27)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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


	
		
		frame.setSize(804,652);
	}

}

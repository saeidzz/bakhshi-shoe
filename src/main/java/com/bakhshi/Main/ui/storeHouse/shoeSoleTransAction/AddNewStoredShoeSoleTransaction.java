package com.bakhshi.Main.ui.storeHouse.shoeSoleTransAction;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.storeHouse.storedShoeSole.StoredShoeSole;
import com.bakhshi.Main.Model.storeHouse.storedShoeSole.StoredShoeSoleTransaction;
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
import com.bakhshi.Main.data_access.StoredShoeSoleTransactionDao;
import com.bakhshi.Main.data_access.factors.EvaFactorDao;
import com.bakhshi.Main.data_access.factors.PvcFactorDao;
import com.bakhshi.Main.data_access.factors.SaleFactorDao;
import com.bakhshi.Main.ui.DataBaseBackupAndRestore;
import com.bakhshi.Main.ui.SearchPanel;
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

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class AddNewStoredShoeSoleTransaction {

	private JFrame frame;
	private JTextField NumberF;
	private JTextField shoeSoleCodeF;
	private JTextField yearf;
	private JTextField monthF;
	private JTextField dayf;
	JComboBox<String> comboBox;
	private JTextField shoeSoleColorF;
	private JTextField shoeSoleSizeF;

	public AddNewStoredShoeSoleTransaction(StoredShoeSole shoeSole) {
		initialize(shoeSole);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final StoredShoeSole shoeSole) {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				AddNewStoredShoeSoleTransaction.class
						.getResource("/com/bakhshi/Main/ui/icon.jpg")));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE));

		NumberF = new JTextField();
		NumberF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		NumberF.setColumns(10);

		JLabel label_5 = new JLabel("تعداد جابه جایی :");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel label_6 = new JLabel("کد زیره :");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 15));

		JButton saveBtn = new JButton("ذخیره");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int number = Integer.parseInt(NumberF.getText());
				int year = Integer.parseInt(yearf.getText().trim());
				int month = Integer.parseInt(monthF.getText().trim());
				int day = Integer.parseInt(dayf.getText());
                int inOrOut = comboBox.getSelectedIndex();
             
                

				if (number == 0 || year == 0
						|| month == 0 || day == 0) {
					JOptionPane.showMessageDialog(frame,
							"ورودی ها باید مقدار داشته باشند !!");
				} else {
					MyDate date = new MyDate(year, month, day);
					long res = MyDateDao.ExistDate(date);
					if (res != -1) {
						date.setId(res);
					} else {
						MyDateDao.create(date);
					}

					if (inOrOut == 0) {
						int numb = shoeSole.getNumber();
						numb += number;
						shoeSole.setNumber(numb);
						StoredShoeSoleDao.create(shoeSole);
					} else {
						int numb = shoeSole.getNumber();
						numb -= number;
						shoeSole.setNumber(numb);
						StoredShoeSoleDao.create(shoeSole);
					}
					
					StoredShoeSoleTransaction storedShoeSoleTransaction=new StoredShoeSoleTransaction(shoeSole,date,comboBox.getSelectedItem()+"",number);
				    StoredShoeSoleTransactionDao.create(storedShoeSoleTransaction);
				    
					
					
					new StoredShoeSolesTransActions(StoredShoeSoleTransactionDao.findByShoeSole(shoeSole),shoeSole);
					frame.dispose();
				}
			}
		});
		saveBtn.setToolTipText("ذخیره");
		saveBtn.setForeground(Color.BLACK);
		saveBtn.setBackground(Color.WHITE);
		saveBtn.setFont(new Font("Tahoma", Font.BOLD, 16));

		shoeSoleCodeF = new JTextField();
		shoeSoleCodeF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		shoeSoleCodeF.setColumns(10);
		shoeSoleCodeF.setText(shoeSole.getCode());
		shoeSoleCodeF.setEditable(false);

		MyDate d = new MyDate();

		yearf = new JTextField();
		yearf.setFont(new Font("B Nazanin", Font.BOLD, 18));
		yearf.setColumns(10);
		yearf.setText(d.getYear() + "");

		monthF = new JTextField();
		monthF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		monthF.setColumns(10);
		monthF.setText(d.getMonth() + "");

		dayf = new JTextField();
		dayf.setFont(new Font("B Nazanin", Font.BOLD, 18));
		dayf.setColumns(10);
		dayf.setText(d.getDay() + "");

		JLabel label = new JLabel("تاریخ جا به جایی :");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));

		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("B Nazanin", Font.BOLD, 18));
		comboBox.setBackground(Color.WHITE);
		comboBox.addItem("ورود به انبار");
		comboBox.addItem("خروج از انبار");

		JLabel label_1 = new JLabel("نوع جا به جایی :");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		shoeSoleColorF = new JTextField();
		shoeSoleColorF.setText((String) null);
		shoeSoleColorF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		shoeSoleColorF.setEditable(false);
		shoeSoleColorF.setColumns(10);
		shoeSoleColorF.setText(shoeSole.getColor());
		
		
		shoeSoleSizeF = new JTextField();
		shoeSoleSizeF.setText((String) null);
		shoeSoleSizeF.setFont(new Font("B Nazanin", Font.BOLD, 18));
		shoeSoleSizeF.setEditable(false);
		shoeSoleSizeF.setColumns(10);
		shoeSoleSizeF.setText(shoeSole.getSize());
		
		JLabel label_3 = new JLabel("رنگ زیره :");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel label_4 = new JLabel("سایز زیره :");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));

		// add to the parent container (e.g. a JFrame):
		// get the selected item:
		// String selectedBook = (String) comboBox.getSelectedItem();

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(163)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(shoeSoleCodeF, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addGap(198))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBox, 0, 171, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(yearf, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(monthF, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dayf, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
								.addComponent(NumberF, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addGap(197))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(shoeSoleColorF, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
								.addComponent(shoeSoleSizeF, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
							.addGap(11)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_3, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
							.addGap(198))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(187)
					.addComponent(saveBtn, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
					.addGap(410))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(shoeSoleCodeF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(shoeSoleColorF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(shoeSoleSizeF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(NumberF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(monthF, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(dayf, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(yearf, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(136))
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

	
	
		frame.setSize(723, 584);
	}
}

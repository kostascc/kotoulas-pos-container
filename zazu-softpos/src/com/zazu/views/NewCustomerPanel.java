/*******************************************************************************
 * Copyright (C) 2018 Konstantinos Chatzis - All Rights Reserved
 * 
 * Licensed Under:
 * Creative Commons Attribution-NoDerivatives 4.0 International Public License
 *  
 * You must give appropriate credit, provide a link to the license, and indicate 
 * if changes were made. You may do so in any reasonable manner, but not in 
 * any way that suggests the licensor endorses you or your use. If you remix, 
 * transform, or build upon the material, you may not distribute the modified material. 
 * 
 * Konstantinos Chatzis <kachatzis@ece.auth.gr>
 ******************************************************************************/

package com.zazu.views;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import com.zazu.models.CardLevel;
//import com.zazu.models.CartProduct;
import com.zazu.models.Customer;
import com.zazu.models.Store;
import com.zazu.utils.Api.GetClient;
import com.zazu.utils.Api.PostClient;

import javax.swing.JLabel;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
/*
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
*/
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.text.DateFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
//import java.awt.Panel;
//import java.awt.Button;
import java.awt.Choice;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewCustomerPanel extends JPanel {
	private JTextField txtAddress;
	private JTextField txtCity;
	private JTextField txtPostalCode;
	private JTextField txtCountry;
	private JTextField txtMobilePhone;
	private JTextField txtHomePhone;
	private JTextField txtEmail;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtCard;
	private JLabel lblWarning;
	private JLabel lblErrorCard;
	private JLabel lblErrorMobilePhone;
	private JLabel lblErrorLandlinePhone;
	private Choice choiceSendSms;
	private Choice choiceSendEmail;
	private Choice choiceDay;
	private Choice choiceMonth;
	private Choice choiceYear;
	private Choice choiceIndividual;
	private Choice choiceProfessional;
	private Choice choiceCardIsPrinted;
	private JButton btnRegister;
	
	private Customer customer;
	private Store store;
	
	private MainView mainView;
	

	/**
	 * Create the panel.
	 */
	public NewCustomerPanel(final MainView mainView) {
		this.mainView = mainView;
		

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				mainView.resetLockTimer();
			}
		});
		
		
		mainView.resetLockTimer();
		
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		
		JLabel label_3 = new JLabel("Κινητό τηλέφωνο");
		label_3.setBounds(12, 12, 122, 15);
		
		txtMobilePhone = new JTextField();
		txtMobilePhone.setBounds(12, 35, 166, 19);
		txtMobilePhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtMobilePhone.getText().length() < 3) {
					txtMobilePhone.setText("+30");
				}
				txtMobilePhone.setCaretPosition(3);
			}
		});
		txtMobilePhone.setColumns(10);
		txtMobilePhone.setText("+30");
		
		JLabel lblNewLabel_1 = new JLabel("Σταθερό τηλέφωνο");
		lblNewLabel_1.setBounds(12, 60, 134, 15);
		
		txtHomePhone = new JTextField();
		txtHomePhone.setBounds(12, 83, 166, 19);
		txtHomePhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtHomePhone.getText().length() < 3) {
					txtHomePhone.setText("+30");
				}
				txtHomePhone.setCaretPosition(3);
			}
		});
		txtHomePhone.setColumns(10);
		txtHomePhone.setText("+30");
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(12, 114, 127, 15);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(12, 131, 156, 19);
		txtEmail.setColumns(10);
		
		JLabel lblSdfs = new JLabel("SMS");
		lblSdfs.setBounds(209, 12, 99, 15);
		
		JLabel lblEmail_1 = new JLabel("Ενημερώσεις");
		lblEmail_1.setBounds(197, 110, 111, 15);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(209, 35, 99, 19);
		
		choiceSendSms = new Choice();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 86, Short.MAX_VALUE)
				.addGap(0, 86, Short.MAX_VALUE)
				.addComponent(choiceSendSms, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
				.addComponent(choiceSendSms, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(197, 131, 99, 19);
		
		choiceSendEmail = new Choice();
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGap(0, 99, Short.MAX_VALUE)
				.addGap(0, 86, Short.MAX_VALUE)
				.addGap(0, 86, Short.MAX_VALUE)
				.addComponent(choiceSendEmail, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGap(0, 19, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
				.addGap(0, 27, Short.MAX_VALUE)
				.addComponent(choiceSendEmail, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
		);
		panel_8.setLayout(gl_panel_8);
		
		lblErrorMobilePhone = new JLabel("");
		lblErrorMobilePhone.setBounds(176, 35, 17, 17);
		lblErrorMobilePhone.setVisible(false);
		try {
		    Image img = ImageIO.read(getClass().getResource("/setting_error.png"));
		    img = img.getScaledInstance( 17, 17,  java.awt.Image.SCALE_SMOOTH ) ;
		    lblErrorMobilePhone.setIcon(new ImageIcon(img));
		 } catch (Exception ex) {
		    System.out.println(ex);
		 }
		
		lblErrorLandlinePhone = new JLabel("");
		lblErrorLandlinePhone.setBounds(176, 83, 17, 17);
		lblErrorLandlinePhone.setVisible(false);
		try {
		    Image img = ImageIO.read(getClass().getResource("/setting_error.png"));
		    img = img.getScaledInstance( 17, 17,  java.awt.Image.SCALE_SMOOTH ) ;
		    lblErrorLandlinePhone.setIcon(new ImageIcon(img));
		 } catch (Exception ex) {
		    System.out.println(ex);
		 }
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		
		JLabel label_4 = new JLabel("Όνομα");
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		
		JLabel label_5 = new JLabel("Επίθετο");
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		
		JLabel label_7 = new JLabel("Ημερομηνία Γέννησης");
		
		JPanel panel_5 = new JPanel();
		
		choiceDay = new Choice();
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 86, Short.MAX_VALUE)
				.addComponent(choiceDay, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addComponent(choiceDay, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
		);
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		
		choiceMonth = new Choice();
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 86, Short.MAX_VALUE)
				.addComponent(choiceMonth, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addComponent(choiceMonth, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
		);
		panel_6.setLayout(gl_panel_6);
		
		JPanel panel_7 = new JPanel();
		
		choiceYear = new Choice();
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 86, Short.MAX_VALUE)
				.addComponent(choiceYear, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_7.setVerticalGroup(
			gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGap(0, 27, Short.MAX_VALUE)
				.addComponent(choiceYear, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
		);
		panel_7.setLayout(gl_panel_7);
		
		JPanel panel_10 = new JPanel();
		
		Choice choice = new Choice();
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGap(0, 99, Short.MAX_VALUE)
				.addGap(0, 99, Short.MAX_VALUE)
				.addGap(0, 99, Short.MAX_VALUE)
				.addComponent(choice, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGap(0, 19, Short.MAX_VALUE)
				.addGap(0, 21, Short.MAX_VALUE)
				.addGap(0, 21, Short.MAX_VALUE)
				.addComponent(choice, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		panel_10.setLayout(gl_panel_10);
		
		JLabel label_9 = new JLabel("Ιδιώτης");
		
		JPanel panel_11 = new JPanel();
		
		choiceIndividual = new Choice();
		//choiceIndividual.setVisible(false);
		GroupLayout gl_panel_11 = new GroupLayout(panel_11);
		gl_panel_11.setHorizontalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addComponent(choiceIndividual, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
		);
		gl_panel_11.setVerticalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addComponent(choiceIndividual, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		panel_11.setLayout(gl_panel_11);
		
		JLabel label_10 = new JLabel("Επαγγελματίας");
		
		JPanel panel_13 = new JPanel();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
							.addComponent(label_4)
							.addComponent(label_5)
							.addComponent(label_7)
							.addComponent(txtLastName, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
							.addComponent(txtFirstName))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(14))
								.addComponent(label_10, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
						.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_9)
						.addComponent(label_10))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_13, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_11, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		choiceProfessional = new Choice();
		GroupLayout gl_panel_13 = new GroupLayout(panel_13);
		gl_panel_13.setHorizontalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addComponent(choiceProfessional, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
		);
		gl_panel_13.setVerticalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addComponent(choiceProfessional, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_13.setLayout(gl_panel_13);
		panel_2.setLayout(gl_panel_2);
		
		btnRegister = new JButton("Εγγραφή");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerBtnPressed();
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(new Color(51, 153, 255));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		
		JLabel label_6 = new JLabel("Κάρτα");
		label_6.setBounds(12, 12, 44, 15);
		
		txtCard = new JTextField();
		txtCard.setBounds(12, 35, 98, 19);
		txtCard.setColumns(10);
		
		JLabel lblCardIsPrinted = new JLabel("Εκτυπωμένη");
		lblCardIsPrinted.setBounds(139, 12, 88, 15);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBounds(139, 35, 88, 19);
		
		choiceCardIsPrinted = new Choice();
		GroupLayout gl_panel_12 = new GroupLayout(panel_12);
		gl_panel_12.setHorizontalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addComponent(choiceCardIsPrinted, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
		);
		gl_panel_12.setVerticalGroup(
			gl_panel_12.createParallelGroup(Alignment.LEADING)
				.addComponent(choiceCardIsPrinted, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		panel_12.setLayout(gl_panel_12);
		
		lblErrorCard = new JLabel("");
		lblErrorCard.setBounds(109, 35, 17, 17);
		lblErrorCard.setVisible(false);
		try {
		    Image img = ImageIO.read(getClass().getResource("/setting_error.png"));
		    img = img.getScaledInstance( 17, 17,  java.awt.Image.SCALE_SMOOTH ) ;
		    lblErrorCard.setIcon(new ImageIcon(img));
		 } catch (Exception ex) {
		    System.out.println(ex);
		 }
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		
		lblWarning = new JLabel("Εσφαλμένη είσοδος στοιχείων");
		lblWarning.setVisible(false);
		lblWarning.setForeground(new Color(204, 0, 0));
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addComponent(lblWarning, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
					.addGap(24))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addComponent(lblWarning)
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel_9.setLayout(gl_panel_9);
		try {
		    Image img = ImageIO.read(getClass().getResource("/setting_error.png"));
		    img = img.getScaledInstance( 17, 17,  java.awt.Image.SCALE_SMOOTH ) ;
		 } catch (Exception ex) {
		    System.out.println(ex);
		 }
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
							.addGap(40)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addGap(15)
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		panel_3.setLayout(null);
		panel_3.add(txtCard);
		panel_3.add(label_6);
		panel_3.add(lblErrorCard);
		panel_3.add(panel_12);
		panel_3.add(lblCardIsPrinted);
		panel_1.setLayout(null);
		panel_1.add(label_3);
		panel_1.add(lblErrorMobilePhone);
		panel_1.add(lblNewLabel_1);
		panel_1.add(lblErrorLandlinePhone);
		panel_1.add(txtHomePhone);
		panel_1.add(txtMobilePhone);
		panel_1.add(panel_4);
		panel_1.add(lblSdfs);
		panel_1.add(txtEmail);
		panel_1.add(lblEmail);
		panel_1.add(lblEmail_1);
		panel_1.add(panel_8);
		
		JLabel label = new JLabel("Διεύθυνση");
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		
		JLabel label_1 = new JLabel("Πόλη");
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		
		txtPostalCode = new JTextField();
		txtPostalCode.setColumns(10);
		
		JLabel label_2 = new JLabel("Τ.Κ.");
		
		JLabel lblNewLabel = new JLabel("Χώρα");
		
		txtCountry = new JTextField();
		txtCountry.setColumns(10);
		txtCountry.setText("Ελλάδα");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addComponent(label)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(txtPostalCode, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
						.addComponent(lblNewLabel)
						.addComponent(txtCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPostalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtCountry, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		initComboBoxes();
		
		//lblWarning.setVisible(false);

	}
	
	
	private void resetErrors() {
		lblErrorCard.setVisible(false);
		lblErrorMobilePhone.setVisible(false);
		lblErrorLandlinePhone.setVisible(false);
		lblWarning.setVisible(false);
		
		
	}
	
	
	
	/**
	 * Register Button Pressed.
	 */
	private void registerBtnPressed() {
		
		resetErrors();
		
		if (   txtFirstName.getText().length()<2 
			|| txtLastName.getText().length()<2
			/*|| (txtMobilePhone.getText().length()<8 && txtHomePhone.getText().length()<8)
			|| txtCard.getText().length()<3*/
		)
		{
			
			lblWarning.setVisible(true);
		
		} else { 
			lblWarning.setVisible(false); 
			lockScreen();
			executeRegister(); 
		}
	}
	
	/**
	 * Locks Screen functions.
	 */
	private void lockScreen() {
		btnRegister.setEnabled(false);
	}
	
	/**
	 * Unlocks Screen Functions
	 */
	private void unlockScreen() {
		btnRegister.setEnabled(true);
	}
	
	/**
	 * Initializes Combo boxes.
	 */
	private void initComboBoxes() {
		// Day Combo
		for (int i=1; i<=31; i++) {
			choiceDay.add(Integer.toString(i));
		}
	
		// Month Combo
		for (int i=1; i<13; i++) {
			choiceMonth.addItem(Integer.toString(i));
		}
		
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		
		// Year Combo
		for (int i=currYear; i>=1940; i--) {
			choiceYear.addItem(Integer.toString(i));
		}
		
		// SendSms Combo
		choiceSendSms.add("ΝΑΙ");
		choiceSendSms.add("ΟΧΙ");
		
		// SendEmail Combo
		choiceSendEmail.add("ΝΑΙ");
		choiceSendEmail.add("ΟΧΙ");
		
		// IsIndividual Combo
		choiceIndividual.add("ΝΑΙ");
		choiceIndividual.add("ΟΧΙ");
		
		// IsProfessional Combo
		choiceProfessional.add("ΟΧΙ");
		choiceProfessional.add("ΝΑΙ");
		
		// CardIsPrinted Combo
		choiceCardIsPrinted.add("ΝΑΙ");
		choiceCardIsPrinted.add("ΟΧΙ");
	}
	
	/**
	 * Sets Customer's First and Last name.
	 */
	private void setNames() {
		customer.first_name = txtFirstName.getText();
		customer.last_name = txtLastName.getText();
	}
	
	/**
	 * Sets CUstomer's Mobile and Landline Phones.
	 */
	private void setPhones() {
		if (txtHomePhone.getText().length() > 7) 
			customer.phone_home = txtHomePhone.getText();
		if (txtMobilePhone.getText().length() > 7)
			customer.phone_mobile = txtMobilePhone.getText();
	}
	
	/**
	 * Sest Customer's full Address.
	 */
	private void setAddress() {
		if (!txtAddress.getText().isEmpty())
			customer.address = txtAddress.getText();
		if (!txtCountry.getText().isEmpty())
			customer.country = txtCountry.getText();
		if (!txtPostalCode.getText().isEmpty())
			customer.postal_code = Integer.parseInt( txtPostalCode.getText() );
		if (!txtCity.getText().isEmpty())
			customer.city = txtCity.getText();
	}
	
	/**
	 * Sets Registration Store, based on current object.
	 */
	private void setStore() {
		customer.registration_store_id = this.store.store_id;
	}
	
	/**
	 * Sets Customer's Marketing selections,
	 * Send Email, Send SMS, and Individual state. 
	 */
	private void setMarketing() {
		customer.send_email = choiceBoolean( choiceSendEmail );
		customer.send_sms = choiceBoolean( choiceSendSms );
		customer.is_individual = choiceBoolean( choiceIndividual );
	}
	
	/**
	 * Sets other selections about the Customer,
	 * such as Enabled.
	 */
	private void setOther() {
		customer.is_enabled = true;
	}
	
	private void setCard() {
		customer.card = Integer.parseInt( txtCard.getText() );
		customer.is_card_printed = choiceBoolean( choiceCardIsPrinted );
	}
	
	private void setBirthday() {
		String birthday = "";
		birthday = choiceYear.getSelectedItem() + "-" + choiceMonth.getSelectedItem() + "-" + choiceDay.getSelectedItem();
		customer.birthday = birthday;
	}
	
	private void setSelections() {
		customer.is_professional = choiceBoolean( choiceProfessional );
		customer.is_individual = choiceBoolean( choiceIndividual );
	}
	
	/**
	 * Check the uniqueness of the card.
	 * @return
	 */
	private boolean checkUniqueCard() {
		
		GetClient getClient = new GetClient();
		getClient.setModel(new Customer());
		getClient.setFilter("(card=" + txtCard.getText() + ")&limit=1");
		getClient.execute();
		
		if(getClient.getResponseCode()==200 && !(getClient.getObject().isEmpty()) ) {
			/*List<Customer> customerList = (List) getClient.getObject();
			if(!customerList.isEmpty()) {*/
				System.out.println("  Unique Card Error");
				uniqueCardError();
				return false;
			//}
		}
		
		return true;
	}
	
	private void uniqueCardError() {
		lblErrorCard.setVisible(true);
		lblErrorCard.paintImmediately(lblErrorCard.getVisibleRect());
	}
	
	
	private void cardTextError() {
		lblErrorCard.setVisible(true);
		lblErrorCard.paintImmediately(lblErrorCard.getVisibleRect());
	}
	
	private void mobilePhoneError() {
		lblErrorMobilePhone.setVisible(true);
		lblErrorMobilePhone.paintImmediately(lblErrorMobilePhone.getVisibleRect());
	}
	
	private void landlinePhoneError() {
		lblErrorLandlinePhone.setVisible(true);
		lblErrorLandlinePhone.paintImmediately(lblErrorLandlinePhone.getVisibleRect());
	}
	
	
	private boolean checkTxtCard() {
		trimInteger( txtCard );
		
		if (txtCard.getText().length()<4 || txtCard.getText().length()>12) {
			System.out.println("  Card Field Error");
			cardTextError();
			return false;
		}
		
		System.out.println("  Card Check Successfull!");
		return true;
	}
	
	
	private boolean checkPhones() {
		
		trimInteger( txtHomePhone );
		trimInteger( txtMobilePhone );
		
		int phoneCount = 0;
		
		if (/*txtMobilePhone.getText().length()<8 ||*/ txtMobilePhone.getText().length()>7) {
			System.out.println("  Checking Mobile Phone");
			phoneCount++;
			if ( ! checkPhoneNumber( txtMobilePhone ) ) {
				mobilePhoneError();
				System.out.println("  Mobile Phone Error");
				return false;
			}
		}else {
			System.out.println("  Skipping Mobile Phone");
		}
		if (/*txtHomePhone.getText().length()<8 ||*/ txtHomePhone.getText().length()>7) {
			//System.out.println("NewCustomerPanel: Invalid Home Phone Field.");
			System.out.println("  Checking Landline Phone");
			phoneCount++;
			if ( ! checkPhoneNumber( txtHomePhone ) ) {
				landlinePhoneError();
				System.out.println("  Landline Phone Error");
				return false;
			}
		} else {
			System.out.println("  Skipping Landline Phone");
		}
		
		if (phoneCount<1)
			return false;
			
		System.out.println("  Phone Check Successfull!");
		return true;
		
	}
	
	
	private boolean checkPhoneNumber( JTextField text ) {
		 
		boolean result = true;
		char[] number = text.getText().toCharArray();
		
		//System.out.println( number[0] );
		
		if ( number[0] != '+')
			result = false;
		
		/*if ( number[1] != '3' && number['0'] != '0' )
			result = false;*/
		if (number.length < 8)
			result = false;
		
		if (!result) {
			text.setText("");
			return false;
		}
		
		return true;
	}
	
	
	private boolean checkTxtEmail() {
		
		System.out.println("  Email Check Successfull!");
		return true;
	}
	
	
	private boolean checkBirthday() {
		
		int choiceCount = 0;
		
		if (!choiceDay.getSelectedItem().isEmpty()) {
			choiceCount++;
		}
		if (!choiceMonth.getSelectedItem().isEmpty()) {
			choiceCount++;
		}
		if (!choiceYear.getSelectedItem().isEmpty()) {
			choiceCount++;
		}
		
		System.out.print("  Birthday Check (" + choiceCount + ") ");
		
		if (choiceCount!=3 && choiceCount!=0) {
			System.out.println("");
			return false;
		}
		
		System.out.print("Successfull!");
		return true;
	}
	
	
	private boolean preRegistrationCheck() {
		
		System.out.println(" Pre-Registration Check Running");
		
		return (
				(checkTxtCard() && checkUniqueCard()) && (checkPhones() && checkTxtEmail()) && checkBirthday()
				);
	}
	
	private void showInvalidFields() {
		lblWarning.setVisible(true);
		System.out.println("NewCustomerPanel: Invalid Fields.");
	}
	
	private void executeRegister() {
		
		lockScreen();
		
		if (preRegistrationCheck()) {
		
			customer = new Customer();
			
			setNames();
			setPhones();
			setAddress();
			setMarketing();
			setCard();
			setOther();
			setFirstCardLevel();
			setRegistrationDateTime();
			setStore();
			setBirthday();
			setSelections();
			
			postCustomer();
			
		} else {
			
			showInvalidFields();
			
		}
		
		unlockScreen();
		
	}
	
	/**
	 * Set first Card Level for the new customer
	 */
	private void setFirstCardLevel() {
		GetClient getClient = new GetClient();
		getClient.setModel( new CardLevel() );
		getClient.setFilter( "is_enabled=1&sort=position&limit=1" );
		getClient.execute();
		
		if (getClient.getResponseCode() == 200) {
			List<CardLevel> cardLevelResponse = (List) getClient.getObject();
			for ( CardLevel cardLevel : cardLevelResponse ) {
				customer.card_level_id = cardLevel.card_level_id;
			}
		}
	}
	
	/**
	 * POST customer
	 */
	private void postCustomer() {
		PostClient postClient = new PostClient();
		postClient.setObject( customer );
		postClient.execute();
		
		if ( postClient.getResponseCode()==200 ) {
			List<Customer> customerList = (List) postClient.getObject();
			for (Customer rCustomer : customerList) {
				System.out.println("New Customer: " + rCustomer.first_name);
				rCustomer = getRegisteredCustomer( rCustomer );
				registrationSuccessful( rCustomer );
			}
		} else {
			registrationUnsuccessful();
		}
	}
	
	
	private Customer getRegisteredCustomer( Customer customer ) {
		Customer rCustomer = new Customer();
		
		GetClient getClient = new GetClient();
		getClient.setModel( rCustomer );
		getClient.setFilter( "(customer_id="+customer.customer_id+")&limit=1" );
		getClient.execute();
		
		if (getClient.getResponseCode()==200) {
			List<Customer> customerResponseList = (List) getClient.getObject();
			if(!customerResponseList.isEmpty()) {
				for (Customer customerResponse : customerResponseList ) {
					rCustomer = customerResponse;
				}
			}
		}
		
		return rCustomer;
	}
	
	
	private void registrationSuccessful( Customer customer ) {
		System.out.println("NewCustomerPanel: Successful Registration.");
		//Customer customer = getRegisteredCustomer();
		mainView.switchToNewTransactionPanelWithCustomer( customer );
	}
	
	private void registrationUnsuccessful() {
		System.out.println("NewCustomerPanel: Unsuccessful Registration.");
	}
	
	private void setRegistrationDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		customer.registration_date = dateFormat.format(date);
		
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date time = new Date();
		//System.out.println(dateFormat.format(time));
		customer.registration_time = timeFormat.format(time);
	}
	
	/**
	 * Trims JTextField text to only integer.
	 * @param jTextField
	 */
	private void trimInteger( JTextField jTextField ) {
		jTextField.setText( jTextField.getText().replaceAll("[^\\d.+]", "") );
	}
	
	/**
	 * Returns a boolean value based on the choice field.
	 * @param choice
	 * @return boolean
	 */
	private boolean choiceBoolean( Choice choice ) {
		
		if( choice.getSelectedItem().equals("ΝΑΙ") ) {
			return true;
		}
		
		if( choice.getSelectedItem().equals("ΟΧΙ") ) {
			return false;
		}
		
		return false;
		
	}
	
	
	public void setStore(Store store) {
		this.store = store;
	}
}

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import com.zazu.models.Customer;
import com.zazu.models.CustomerSearchItem;
import com.zazu.utils.Api.GetClient;
//import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

//import javax.swing.JTable;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.lang3.StringUtils;

import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
//import javax.swing.UIManager;

public class FindCustomerPanel extends JPanel {
	private JTextField txtSearch;
	private JList listCustomers;
	private JButton btnNewTransaction;
	
	private List<CustomerSearchItem> customerList;
	private CustomerSearchItem[] customerJList;

	private MainView mainView;
	private JScrollPane scrollPane;
	
	/**
	 * Create the panel.
	 */
	public FindCustomerPanel(MainView mainView) {
		
		this.mainView = mainView;
		
		mainView.resetLockTimer();
		
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 153, 255));
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
		);
		
		listCustomers = new JList();
		scrollPane.setViewportView(listCustomers);
		listCustomers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCustomers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				refreshView();
			}
		});
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSearchPressed();
			}
		});
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(new Color(51, 153, 255));
		
		btnNewTransaction = new JButton("Συναλλαγή");
		btnNewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnNewTransactionPressed();
			}
		});
		btnNewTransaction.setForeground(Color.WHITE);
		btnNewTransaction.setBackground(new Color(51, 153, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
					.addComponent(btnNewTransaction, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewTransaction, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		initView();
	}
	
	
	private void initView() {
		this.btnNewTransaction.setEnabled(false);
	}
	
	private void btnSearchPressed() {
		if (!txtSearch.getText().isEmpty()) {
			search();
		}
	}
	
	private void btnNewTransactionPressed() {
		openNewTransaction( (CustomerSearchItem) listCustomers.getSelectedValue() );
	}
	
	
	private String getSearchFilter() {
		String filter = "";
		String searchText = txtSearch.getText();
		List<String> stringFields = new ArrayList<String>();
		List<String> intFields = new ArrayList<String>();
		stringFields.add("first_name");
		stringFields.add("last_name");
		stringFields.add("phone_mobile");
		stringFields.add("phone_home");
		stringFields.add("email1");
		stringFields.add("email2");
		intFields.add("card");
		
		for (String stringField : stringFields) {
			try {
				filter += URLEncoder.encode("(" + stringField + " LIKE %" + searchText + "%)or", "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if ( StringUtils.isNumericSpace( searchText ) && (searchText.length()<=9)) {
			for (String intField : intFields) {
				try {
					filter += URLEncoder.encode("(" + intField + " = " + Integer.parseInt( searchText ) + ")or", "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		
		filter += "()";
		
		filter = filter.replace("or()", "");
		
		filter += "&limit=200";
		
		return filter;
	}
	
	
	private List<CustomerSearchItem> searchGetResponse() {
		List<CustomerSearchItem> customerSearchList = new ArrayList<CustomerSearchItem>();
		
		GetClient getClient = new GetClient();
		getClient.setModel( new Customer() );
		getClient.setFilter( getSearchFilter() );
		getClient.execute();
		
		List<Customer> customerResponseList = (List) getClient.getObject();
		
		if (getClient.getResponseCode()==200) {
			if (!customerResponseList.isEmpty()) {
				for (Customer customer : customerResponseList) {
					customerSearchList.add( new CustomerSearchItem( customer ) );
				}
			}
		}
		
		System.out.println("FindCustomerPanel: Initialized CustomerSearchList (size:" + customerSearchList.size() + ").");
		
		return customerSearchList;
	}
	
	private void search() {
		
		//List<CustomerSearchItem> customerResponseList = (List) searchGetResponse();
		this.customerList = (List) searchGetResponse();
		
		refreshList();
	}
	
	
	
	private void refreshList() {
		customerJList = new CustomerSearchItem[ customerList.size() ];	
		
		for (int i=0; i<this.customerList.size(); i++) {
			customerJList[i] = this.customerList.get(i);
		}
		
		listCustomers.setListData(customerJList);
	}
	
	
	private void refreshView() {
		if (!listCustomers.isSelectionEmpty()) {
			if (listCustomers.getSelectedValue() != null)
				btnNewTransaction.setEnabled(true);
		} else {
			btnNewTransaction.setEnabled(false);
		}
	}
	
	private void openNewTransaction(CustomerSearchItem customerSearchItem) {
		this.mainView.switchToNewTransactionPanelWithCustomer( customerSearchItem.customer );
	}
}

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
//import javax.swing.BoxLayout;
//import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
//import java.awt.Button;
import javax.swing.JButton;
//import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.zazu.models.Customer;
import com.zazu.models.Device;
import com.zazu.models.Good;
import com.zazu.models.Manager;
import com.zazu.models.Store;
import com.zazu.utils.TransactionHandler;
import com.zazu.utils.Api.GetClient;

//import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

import com.zazu.models.*;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
//import com.zazu.utils.ImportantObjectChecker;


/*
 * TODO: Simplify Gift List population. Some functions 
 * should not run.
 */

public class NewTransactionPanel extends JPanel {
	private JTextField txtCard;
	private JList listProducts;
	private JList listCart;
	private JList listGifts;
	private JSpinner spinnerQuantity;
	private JLabel lblCard;
	private JLabel lblPoints;
	private JButton btnExecuteTransaction; 
	private JButton btnCancelTransaction;
	private JButton btnAddCartProduct;
	private JButton btnAddCartGift;
	private JButton btnRemoveCartProduct;
	private JButton btnCustomerInfo;
	private JLabel lblSum;
	private JLabel lblGiftCredits;
	
	private List<Good> productList;
	private List<Good> giftList;
	private List<CartProduct> cartList;
	
	private Good[] productJList;
	private Good[] giftJList;
	private CartProduct[] cartProductJList;

	private Customer customer;
	private Manager manager;
	private Store store;
	private Device device;
	
	private MainView mainView;
	
	int giftCredits = 0;
	
	
	/**
	 * Create the panel.
	 */
	public NewTransactionPanel(final MainView mainView) {
		this.mainView = mainView;
		
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mainView.resetLockTimer();
			}
		});
		
		
		mainView.resetLockTimer();
		
		
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_5 = new GroupLayout(panel_5);
		gl_panel_5.setHorizontalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
		);
		gl_panel_5.setVerticalGroup(
			gl_panel_5.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_5.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		listGifts = new JList();
		listGifts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(listGifts);
		listGifts.setFont(new Font("Dialog", Font.BOLD, 14));
		listGifts.setBackground(UIManager.getColor("Button.background"));
		panel_5.setLayout(gl_panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_5, 0, 0, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_4, 0, 0, Short.MAX_VALUE))
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addComponent(panel, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, 0, 0, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
						.addComponent(panel_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addGap(92))
		);
		
		btnAddCartGift = new JButton("ΠΡΟΣΘΗΚΗ");
		btnAddCartGift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addGiftToCart();
			}
		});
		btnAddCartGift.setForeground(Color.WHITE);
		btnAddCartGift.setBackground(new Color(102, 204, 102));
		
		JLabel lblNewLabel_1 = new JLabel("Δώρα");
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddCartGift)
						.addComponent(lblNewLabel_1))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAddCartGift, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(128, Short.MAX_VALUE))
		);
		panel_6.setLayout(gl_panel_6);
		
		JLabel label_5 = new JLabel("Σύνολο:");
		label_5.setFont(new Font("Dialog", Font.BOLD, 15));
		
		lblSum = new JLabel("0,00 €");
		lblSum.setFont(new Font("Dialog", Font.BOLD, 15));
		
		btnExecuteTransaction = new JButton("Ολοκλήρωση");
		btnExecuteTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				executeTransaction();
			}
		});
		btnExecuteTransaction.setForeground(Color.WHITE);
		btnExecuteTransaction.setBackground(new Color(102, 204, 102));
		btnExecuteTransaction.setFont(new Font("Dialog", Font.BOLD, 14));
		
		btnCancelTransaction = new JButton("Ακύρωση");
		btnCancelTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cancelTransaction();
			}
		});
		btnCancelTransaction.setForeground(Color.WHITE);
		btnCancelTransaction.setBackground(new Color(204, 0, 0));
		
		lblGiftCredits = new JLabel("0 Π.");
		lblGiftCredits.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGiftCredits.setFont(new Font("Dialog", Font.BOLD, 15));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(btnExecuteTransaction, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelTransaction))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblSum, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblGiftCredits, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(lblSum, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGiftCredits, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCancelTransaction, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
						.addComponent(btnExecuteTransaction, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(32))
		);
		panel_4.setLayout(gl_panel_4);
		
		txtCard = new JTextField();
		txtCard.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCard.setColumns(10);
		
		JButton btnLoadCard = new JButton("Επιλογή");
		btnLoadCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadCustomer();
			}
		});
		btnLoadCard.setForeground(Color.WHITE);
		btnLoadCard.setBackground(new Color(51, 153, 255));
		
		JLabel label_1 = new JLabel("Πόντοι:");
		
		lblCard = new JLabel("12345678");
		
		lblPoints = new JLabel("48");
		
		JLabel label_4 = new JLabel("Πελάτης");
		
		btnCustomerInfo = new JButton("?");
		btnCustomerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCustomerInfoDialog();
			}
		});
		btnCustomerInfo.setForeground(Color.WHITE);
		btnCustomerInfo.setBackground(new Color(51, 153, 255));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCard, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnLoadCard, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblPoints, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblCard, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCustomerInfo)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtCard, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnLoadCard, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblCard)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1)
								.addComponent(lblPoints)))
						.addComponent(btnCustomerInfo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		listCart = new JList();
		listCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(listCart);
		listCart.setFont(new Font("Dialog", Font.BOLD, 14));
		listCart.setBackground(UIManager.getColor("DesktopIcon.background"));
		panel_1.setLayout(gl_panel_1);
		
		btnRemoveCartProduct = new JButton("ΑΦΑΙΡΕΣΗ");
		btnRemoveCartProduct.setPreferredSize(new Dimension(112, 25));
		btnRemoveCartProduct.setMinimumSize(new Dimension(112, 25));
		btnRemoveCartProduct.setMaximumSize(new Dimension(112, 25));
		btnRemoveCartProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeCartProduct();
			}
		});
		btnRemoveCartProduct.setForeground(Color.WHITE);
		btnRemoveCartProduct.setBackground(new Color(204, 0, 0));
		
		btnAddCartProduct = new JButton("ΠΡΟΣΘΗΚΗ");
		btnAddCartProduct.setActionCommand("ΠΡΟΣΘΗΚΗ");
		btnAddCartProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProductToCart();
			}
		});
		btnAddCartProduct.setForeground(Color.WHITE);
		btnAddCartProduct.setBackground(new Color(102, 204, 102));
		
		spinnerQuantity = new JSpinner();
		spinnerQuantity.setModel(new SpinnerNumberModel(new Float(0), new Float(0), new Float(9999), new Float(1)));
		spinnerQuantity.setFont(new Font("Dialog", Font.BOLD, 14));
		
		JButton btnReloadProducts = new JButton("ΕΝΗΜΕΡΩΣΗ");
		btnReloadProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReloadProducts.setForeground(Color.WHITE);
		btnReloadProducts.setBackground(new Color(51, 153, 255));
		btnReloadProducts.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("€");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel label_2 = new JLabel("Ποσό");
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label_2)
							.addContainerGap(99, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(spinnerQuantity, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(btnRemoveCartProduct, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addComponent(btnReloadProducts, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(btnAddCartProduct, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(28)
					.addComponent(label_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(spinnerQuantity, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(btnAddCartProduct, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemoveCartProduct, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(53)
					.addComponent(btnReloadProducts, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		listProducts = new JList();
		scrollPane.setViewportView(listProducts);
		listProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProducts.setFont(new Font("Dialog", Font.BOLD, 14));
		listProducts.setBackground(UIManager.getColor("DesktopIcon.background"));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		initProducts();
		initGifts();
		initCart();
		initCustomer();
		initView();
		refreshView();
		
	}
	
	
	private void refreshView() {
		// Refresh Execution Button
		if(this.customer.customer_id>0 && this.cartList.size()>0) {
			this.btnExecuteTransaction.setEnabled(true);
		}
		
		refreshSum();
	}
	
	
	private void refreshSum() {
		Float sum = (float) 0.0;
		
		for ( CartProduct cartProduct : cartList ) {
			if ( !cartProduct.good.is_gift ) {
				sum += cartProduct.quantity;
			}
		}
		
		this.lblSum.setText(sum + " €");
		
		this.lblGiftCredits.setText( giftCredits + " Π." );
	}
	
	private void initView() {
		btnExecuteTransaction.setEnabled(false);
	}
	
	
	private void showCustomerInfoDialog() {
		CustomerInfoDialog customerInfo = new CustomerInfoDialog( customer );
		customerInfo.setVisible(true);
	}
	
	private void initCustomer() {
		this.customer = new Customer();
		lblCard.setText("-");
		lblPoints.setText("-");
		btnCustomerInfo.setEnabled(false);
		
		refreshCustomer();
	}
	
	
	/**
	 * Load Customer button
	 */
	private void loadCustomer() {
		
		String card = txtCard.getText();
		
		GetClient getClient = new GetClient();
		getClient.setModel( new Customer() );
		getClient.setFilter("(card=" + card + ")and(is_enabled=1)&limit=1");
		getClient.execute();
		
		System.out.println("Cards Found: " + ((List) getClient.getObject()).size());
		
		this.customer = new Customer();
		
		List<Customer> customerList = (List) getClient.getObject();
		if(!customerList.isEmpty()) {
			for (Customer customerResponse : customerList) {
				this.customer = customerResponse;
				System.out.println("Card Found: " + customerResponse.card);
			}
		}
		
		refreshCustomer();
		
		refreshView();
		
	}
	
	
	private void refreshCustomer() {
		if (this.customer.customer_id>0) {
			loadGifts();
			refreshListGift();
			this.txtCard.setText("");
			lblCard.setText( this.customer.first_name + " " + this.customer.last_name + "(" + Integer.toString(this.customer.card) + ")" );
			lblPoints.setText( Integer.toString(this.customer.credit) );
			btnCustomerInfo.setEnabled(true);
		} else {
			emptyListGift();
			this.txtCard.setText("");
			lblCard.setText("-");
			lblPoints.setText("-");
			btnCustomerInfo.setEnabled(false);
		}
	}
	
	
	private void removeCartProduct() {
		
		if (!listCart.isSelectionEmpty()) {
			int selectedCartProduct = listCart.getSelectedIndex();
			
			//CartProduct[] cartProduct = (CartProduct[]) listCart.getSelectedValues();
			
			if( cartList.get(selectedCartProduct).good.is_gift ) {
				removeGiftFromCart();
			} else {
			
				cartList.remove(selectedCartProduct);
			
				/*CartProduct selectedCartProduct = (CartProduct) listCart.getSelectedValue();
				cartList.remove(selectedCartProduct);*/
				refreshListCart();
			}
		
			refreshView();
		}
	}
	
	
	private void removeGiftFromCart() {
		int selectedCartGift = listCart.getSelectedIndex();
		//CartProduct[] selectedCartGift = (CartProduct[]) listCart.getSelectedValue();
		
		// Update Gift Credits
		giftCredits -= (cartList.get(selectedCartGift).good).credits_cost;
		if (giftCredits<0)
			giftCredits = 0;
		
		// Add to Gift dataset
		//giftList.add(cartList.get(selectedCartGift).good);
		// refresh gift list
		refreshListGift();
		// Remove from dataset
		cartList.remove(selectedCartGift);
		// refresh cart
		refreshListCart();
		
		// refresh view
		refreshView();
	}
	
	
	private void refreshListCart() {
		cartProductJList = new CartProduct [ cartList.size() ];
		
		for (int i=0; i<cartList.size(); i++) {
			cartProductJList[i] = cartList.get(i);
		}
		
		listCart.setListData( cartProductJList );
	}
	
	
	private void initCart() {
		cartList = new ArrayList<CartProduct>();
	}
	
	
	private void addToCart( CartProduct cartProduct ) {
		cartList.add( cartProduct );
		
		cartProductJList = new CartProduct[ cartList.size() ];
		for (int i=0; i<cartList.size(); i++) {
			cartProductJList[i] = cartList.get(i);
		}
		
		listCart.setListData( cartProductJList );
	}
	
	
	private void addProductToCart() {
		if (!listProducts.isSelectionEmpty()) {
			
			Good selectedGood = (Good) listProducts.getSelectedValue();
			Float quantity = (Float) spinnerQuantity.getValue();
			CartProduct cartProduct = new CartProduct( selectedGood, quantity );
			if(quantity>0.0)
				addToCart( cartProduct );
		
			refreshView();
			
		}
	}
	
	
	private void addGiftToCart() {
		if(!listGifts.isSelectionEmpty()) {
			
			Good selectedGift = (Good) listGifts.getSelectedValue();
			selectedGift.unit = "("+selectedGift.credits_cost+")";
			Float quantity = (float) 1.0;
			CartProduct cartProduct = new CartProduct( selectedGift, quantity );
			addToCart( cartProduct );
			//giftList.remove( listGifts.getSelectedValue() );
			
			// Update Gift Credits
			giftCredits += (selectedGift).credits_cost;
			if (giftCredits<0) {
				giftCredits = 0;
			}
			
			refreshListGift();
		
			refreshView();
			
		}
	}
	
	
	private void emptyListGift() {
		System.out.println("NewTransactionPanel: EmptyListGift().");
		giftJList = new Good[ 0 ];
		listGifts.setListData(giftJList);
		giftCredits = 0;
	}
	
	private void refreshListGift() {
		
		//loadGifts();
		
		System.out.println("NewTransactionPanel: RefreshListGift() (size:"+giftList.size()+").");
			
			giftJList = new Good[ giftList.size() ];
			
			if (!giftList.isEmpty()) {
				
				String[] stringGiftList = new String[ giftList.size() ];
				
				for(int i=0; i<giftList.size(); i++) {
					giftJList[i] = giftList.get(i);
					giftJList[i].unit = "("+String.valueOf(giftJList[i].credits_cost)+")";
				}
			} else {
				//emptyListGift();
			}
			
			listGifts.setListData(giftJList);
		
	}
	
	
	/**
	 * Initialize Product List
	 */
	private void initProducts() {
		
		System.out.println("NewTransactionView: initProducts started.");
		
		GetClient getClient = new GetClient();
		getClient.setModel( new Good() );
		getClient.setFilter("(is_enabled=1)and(is_gift=0)&limit=1000");
		getClient.execute();
		
		productList = (List) getClient.getObject();
		
		productJList = new Good[ productList.size() ];
		
		if (!productList.isEmpty()) {
			
			for(int i=0; i<productList.size(); i++)
				productJList[i] = productList.get(i);
			
		}
		
		listProducts.setListData(productJList);
		
		
	}
	
	
	private void loadGifts() {
		if (this.customer!=null && this.customer.customer_id>0) {
			System.out.println("NewTransactionPanel: LoadGifts.");
			
			GetClient getClient = new GetClient();
			getClient.setModel( new Good() );
			getClient.setFilter(
					"(is_enabled=1)and(is_gift=1)and"
					+ "(card_level_id=" + this.customer.card_level_id + ")and"
					+ "(min_credits<" + this.customer.credit + ")and"
					+ "(credits_cost<" + this.customer.credit + ")"
					+ "&limit=300");
			getClient.execute();
			
			giftList = (List) getClient.getObject();
			
			giftJList = new Good[ giftList.size() ];
			
			if (!giftList.isEmpty()) {
				
				for(int i=0; i<giftList.size(); i++)
					giftJList[i] = giftList.get(i);
				
			}
			
			listGifts.setListData(giftJList);
		} else {
			emptyListGift();
		}
	}
	
	/**
	 * Initialize Gift List
	 */
	private void initGifts() {
		
		
		if (this.customer!=null && this.customer.customer_id>0) {
			
			loadGifts();
			
		} else {
			giftList = new ArrayList<Good>();
			emptyListGift();
		}
	}
	
	/**
	 * Cancel Transaction and dispose window
	 */
	private void cancelTransaction() {
		this.setVisible(false);
	}
	
	/**
	 * Use a Transaction Handler to execute the transaction
	 */
	private void executeTransaction() {
		
		// Check before executing
		if( btnExecuteTransaction.isEnabled()
			&& this.customer.customer_id>0
			&& this.customer.is_enabled == true
			&& this.customer.is_deleted == false) {
			
			// Lock functions that may interrupt the transaction
			lockScreen();
			
			System.out.println();
			
			// Create TransactionHandler
			TransactionHandler transactionHandler = new TransactionHandler();
			transactionHandler
				.setCustomer( this.customer )
				.setManager( this.manager )
				.setStore ( this.store )
				.setDevice ( this.device )
				.setCartList ( this.cartList )
				.handle();
			if( transactionHandler.getResult() ) {
				transactionSuccessful();
			} else {
				transactionUnsuccessful();
			}
			
			
		}
	}
	
	
	private void lockScreen() {
		btnExecuteTransaction.setEnabled(false);
		btnCancelTransaction.setEnabled(false);
		listProducts.setEnabled(false);
		listGifts.setEnabled(false);
		listCart.setEnabled(false);
		btnAddCartProduct.setEnabled(false);
		btnRemoveCartProduct.setEnabled(false);
		btnAddCartGift.setEnabled(false);
	}
	
	
	private void unlockScreen() {
		btnExecuteTransaction.setEnabled(true);
		btnCancelTransaction.setEnabled(true);
		listProducts.setEnabled(true);
		listGifts.setEnabled(true);
		listCart.setEnabled(true);
		btnAddCartProduct.setEnabled(true);
		btnRemoveCartProduct.setEnabled(true);
		btnAddCartGift.setEnabled(true);
	}
	
	private void transactionUnsuccessful() {
		// Show Unsuccessful Transaction Panel
		UnsuccessfulTransactionDialog unsuccessfulTransaction = new UnsuccessfulTransactionDialog();
		unsuccessfulTransaction.setVisible(true);
		
		// Unlock transaction panel
		unlockScreen();
		
		System.out.println("NewTransactionPanel: Transaction Unsuccessful, Unlocked Screen.");
	}
	
	private void transactionSuccessful() {
		// Show Successful Transaction Panel
		/*JDialog successDialog = new JDialog();
		successDialog.setTitle("Επιτυχημένη Συναλλαγή");
		successDialog.setVisible(true);
		*/
		// Hide this transaction panel
		cancelTransaction();
		SuccessfulTransactionDialog successfulTransaction = new SuccessfulTransactionDialog();
		successfulTransaction.setVisible(true);
		
		System.out.println("NewTransactionPanel: TransactionSuccessful. Continuing");
	}
	
	
	public NewTransactionPanel setManager( Manager manager ) { this.manager = manager; return this; }
	public NewTransactionPanel setDevice( Device device ) { this.device = device; return this; }
	public NewTransactionPanel setStore( Store store ) { this.store = store; return this; }
	public NewTransactionPanel setCustomer( Customer customer ) { this.customer = customer; refreshCustomer(); return this; }
}





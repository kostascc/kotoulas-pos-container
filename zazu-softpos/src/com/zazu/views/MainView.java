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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import com.zazu.models.*;
import com.zazu.utils.ImportantObjectChecker;
import com.zazu.utils.LockTimer;
import java.awt.Insets;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.HierarchyEvent;


/**
 * MainView Contains Most of the views and functions.
 * It contains a sidebar with buttons, and a panel 
 * which loads the JPanels.
 */
public class MainView extends JFrame {

	private JPanel contentPane;
	
	private Manager manager;
	private Store store;
	private Device device;
	
	Panel panelChildContainer = new Panel();
	JPanel jPanelChildContainer = new JPanel();
	
	LockTimer lockTimer;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//@Override
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	/**
	 * Create the MainView Frame.
	 */
	public MainView() {
		/*addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent arg0) {
				System.out.println("Hierarchy Changed");
				resetLockTimer();
			}
		});
		addContainerListener(new ContainerAdapter() {
			@Override
			public void componentAdded(ContainerEvent arg0) {
				System.out.println("Container Added");
				resetLockTimer();
			}
			@Override
			public void componentRemoved(ContainerEvent e) {
				System.out.println("Component Removed");
				resetLockTimer();
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("MOUSE CLICKED");
				resetLockTimer();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				System.out.println("MOUSE MOVED");
				resetLockTimer();
			}
		});
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				System.out.println("FOCUS GAINED");
				resetLockTimer();
			}
		});*/
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setTitle("Loyalty POS - Kotoulas Energy S.A.");
		setType(Type.UTILITY);
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 548);
		contentPane = new JPanel();
		contentPane.setAlignmentY(0.0f);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panelLeftNavigation = new JPanel();
		panelLeftNavigation.setBorder(null);
		
		panelChildContainer.setBackground(Color.GRAY);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelLeftNavigation, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panelChildContainer, GroupLayout.PREFERRED_SIZE, 729, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panelLeftNavigation, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panelChildContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		panelChildContainer.setLayout(null);
		jPanelChildContainer.setBackground(Color.WHITE);
		
		
		jPanelChildContainer.setBounds(0, 0, 717, 511);
		panelChildContainer.add(jPanelChildContainer);
		panelLeftNavigation.setLayout(null);
		
		
		/*
		JButton btnNewTransaction = new JButton("Συναλλαγή");
		btnNewTransaction.setForeground(Color.WHITE);
		btnNewTransaction.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		btnNewTransaction.setBackground(new Color(51, 153, 255));
		try {
		    Image img = ImageIO.read(getClass().getResource("resources/transaction_button.jpg"));
		    btnNewTransaction.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		btnNewTransaction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showNewTransactionPanel();
			}
		});
		btnNewTransaction.setBounds(10, 20, 146, 39);
		panelLeftNavigation.add(btnNewTransaction);
		
		
		
		JButton btnFindCustomer = new JButton("Εύρεση Πελάτη");
		btnFindCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showFindCustomerPanel();
			}
		});
		btnFindCustomer.setForeground(Color.WHITE);
		btnFindCustomer.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		btnFindCustomer.setBackground(new Color(51, 153, 255));
		btnFindCustomer.setBounds(10, 81, 134, 104);
		try {
		    Image img = ImageIO.read(getClass().getResource("/customer_search_button.bmp"));
		    img = img.getScaledInstance( 156, 114,  java.awt.Image.SCALE_SMOOTH ) ;
		    btnFindCustomer.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		panelLeftNavigation.add(btnFindCustomer);
		
		
		
		Button btnNewCustomer = new Button("Νέος Πελάτης");
		btnNewCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showNewCustomerPanel();
			}
		});
		btnNewCustomer.setForeground(Color.WHITE);
		btnNewCustomer.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		btnNewCustomer.setBackground(new Color(51, 153, 255));
		btnNewCustomer.setBounds(10, 246, 146, 39);
		panelLeftNavigation.add(btnNewCustomer);
		
		Button btnExit = new Button("Έξοδος");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 15));
		btnExit.setBackground(new Color(51, 153, 255));
		btnExit.setBounds(10, 432, 136, 39);
		panelLeftNavigation.add(btnExit);
		*/
		
		Panel panelBlueBackground = new Panel();
		panelBlueBackground.setBackground(UIManager.getColor("ColorChooser.foreground"));
		panelBlueBackground.setBounds(0, 0, 156, 511);
		panelLeftNavigation.add(panelBlueBackground);
		
		JButton btnNewTransaction = new JButton("");
		btnNewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showNewTransactionPanel();
			}
		});
		btnNewTransaction.setMargin(new Insets(0, 0, 0, 0));
		try {
		    Image img = ImageIO.read(getClass().getResource("/transaction_button.bmp"));
		    img = img.getScaledInstance( 130, 80,  java.awt.Image.SCALE_SMOOTH ) ;
		    btnNewTransaction.setIcon(new ImageIcon(img));
		 } catch (Exception ex) {
		    System.out.println(ex);
		 }
		
		JButton btnNewCustomer = new JButton("");
		btnNewCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNewCustomerPanel();
			}
		});
		try {
		    Image img = ImageIO.read(getClass().getResource("/new_customer_button.bmp"));
		    img = img.getScaledInstance( 130, 80,  java.awt.Image.SCALE_SMOOTH ) ;
		    btnNewCustomer.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		JButton btnFindCustomer = new JButton("");
		btnFindCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFindCustomerPanel();
			}
		});
		try {
		    Image img = ImageIO.read(getClass().getResource("/customer_search_button.bmp"));
		    img = img.getScaledInstance( 130, 80,  java.awt.Image.SCALE_SMOOTH ) ;
		    btnFindCustomer.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		JButton btnExit = new JButton("");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		try {
		    Image img = ImageIO.read(getClass().getResource("/exit_button.bmp"));
		    img = img.getScaledInstance( 130, 36,  java.awt.Image.SCALE_SMOOTH ) ;
		    btnExit.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		JLabel lblNavLogo = new JLabel("");
		try {
		    Image img = ImageIO.read(getClass().getResource("/nav_logo.bmp"));
		    img = img.getScaledInstance( 156, 100,  java.awt.Image.SCALE_SMOOTH ) ;
		    lblNavLogo.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		JLabel lblAuthorLogo = new JLabel("");
		try {
		    Image img = ImageIO.read(getClass().getResource("/nav_loginet_logo.bmp"));
		    img = img.getScaledInstance( 130, 38,  java.awt.Image.SCALE_SMOOTH ) ;
		    lblAuthorLogo.setIcon(new ImageIcon(img));
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		
		
		
		GroupLayout gl_panelBlueBackground = new GroupLayout(panelBlueBackground);
		gl_panelBlueBackground.setHorizontalGroup(
			gl_panelBlueBackground.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNavLogo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
				.addGroup(gl_panelBlueBackground.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelBlueBackground.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnExit, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 132, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelBlueBackground.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnNewTransaction, 0, 0, Short.MAX_VALUE)
							.addComponent(btnNewCustomer, 0, 0, Short.MAX_VALUE)
							.addComponent(btnFindCustomer, GroupLayout.PREFERRED_SIZE, 132, Short.MAX_VALUE)))
					.addContainerGap(12, Short.MAX_VALUE))
				.addGroup(gl_panelBlueBackground.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAuthorLogo, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelBlueBackground.setVerticalGroup(
			gl_panelBlueBackground.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBlueBackground.createSequentialGroup()
					.addComponent(lblNavLogo, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewTransaction, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewCustomer, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFindCustomer, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
					.addComponent(lblAuthorLogo, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelBlueBackground.setLayout(gl_panelBlueBackground);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(10, 300, 100, 100);
		panelLeftNavigation.add(canvas);
		contentPane.setLayout(gl_contentPane);
		
		
		//showNewTransactionPanel();
		
		lockTimer = new LockTimer( this );
		
		resetLockTimer();
	}
	
	
	/**
	 * Switch to a 'New Transaction Panel' with a given customer.
	 * @param Customer customer
	 */
	public void switchToNewTransactionPanelWithCustomer(Customer customer) {
		System.out.println("MainView: Opening New Transaction Panel with set Customer (" + customer.customer_id + ").");
		
		refreshView();
		
		System.out.println( this.manager );
		System.out.println("MainView: showNewTransactionPanel");
		
		NewTransactionPanel newTransactionPanel = new NewTransactionPanel(this);
		newTransactionPanel
			.setManager( this.manager )
			.setDevice( this.device )
			.setStore( this.store )
			.setCustomer( customer );
		setChildContainer(newTransactionPanel);
	}
	
	/**
	 * Show a 'New Transaction Panel',
	 * only with objects defined in this class.
	 */
	private void showNewTransactionPanel(){
		refreshView();
		
		System.out.println( this.manager );
		System.out.println("MainView: showNewTransactionPanel");
		
		NewTransactionPanel newTransactionPanel = new NewTransactionPanel(this);
		newTransactionPanel
			.setManager( this.manager )
			.setDevice( this.device )
			.setStore( this.store );
		setChildContainer(newTransactionPanel);
	}
	
	/**
	 * Show a 'New Customer Panel'.
	 */
	private void showNewCustomerPanel() {
		refreshView();
		
		System.out.println("MainView: showNewCustomerPanel");
		
		NewCustomerPanel newCustomerPanel = new NewCustomerPanel(this);
		newCustomerPanel.setStore( this.store );
		setChildContainer(newCustomerPanel);
	}
	
	/**
	 * Show a 'Find Customer Panel'.
	 */
	private void showFindCustomerPanel() {
		refreshView();
		
		System.out.println("MainView: showFindCustomerPanel");
		
		JPanel findCustomerPanel = new FindCustomerPanel(this);
		setChildContainer(findCustomerPanel);
	}
	
	/**
	 * Set a new JPanel into the Panel Container.
	 * @param JPanel jPanel
	 */
	private void setChildContainer( JPanel jPanel ) {
		jPanelChildContainer.removeAll();
		jPanelChildContainer.setLayout( new BorderLayout() );
		jPanelChildContainer.add( jPanel , BorderLayout.NORTH );
		jPanelChildContainer.repaint();
		jPanelChildContainer.validate();
	}
	
	/**
	 * Checks the validity of global object,
	 * provided by this class to it's children.
	 */
	private void refreshView() {
		ImportantObjectChecker checker = new ImportantObjectChecker();
		checker
			.setManager( this.manager )
			.setOnFailSendToLogin( true )
			.setMainView( this )
			.check();
	}
	
	/**
	 * Dispose this screen and go to Login
	 */
	public void logout() {
		LoginView loginView = new LoginView();
		loginView.setVisible(true);
		dispose();
	}
	
	
	public void resetLockTimer() {
		lockTimer.reset();
	}
	
	
	/**
	 * Set Connected Manager.
	 * @param manager
	 */
	public void setManager(Manager manager) { this.manager = manager; /*System.out.println("Manager: " + manager.name);*/ }
	
	/**
	 * Set Current Store.
	 * @param store
	 */
	public void setStore(Store store) { this.store = store; /*System.out.println("Store: " + store.code);*/ }
	
	/**
	 * Set Current Device.
	 * @param device
	 */
	public void setDevice(Device device) { this.device = device; /*System.out.println("Device: " + device.code);*/ }
}

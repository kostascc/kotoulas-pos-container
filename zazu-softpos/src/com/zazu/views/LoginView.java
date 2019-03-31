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
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.JDesktopPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import com.zazu.utils.LoginHandler;
import com.zazu.utils.Api.GetClient;
import com.zazu.models.Manager;
import com.zazu.models.Device;
import com.zazu.models.Store;
import com.zazu.views.MainView;
import com.zazu.utils.POSUpdater;
import java.util.List;
import java.util.ArrayList;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import java.awt.SystemColor;
import java.awt.Choice;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.IOException;



public class LoginView extends JFrame {
	
	private String versionString = "v18.12.16.0"; 
	public JPasswordField txtPassword = new JPasswordField();
	JLabel lblConnecting;
	JLabel lblVersion;
	Button btnLogin;
	Button btnExit;
	JButton btnUpdate;
	
	private Choice choiceDevice;
	private Choice choiceStore;
	private Choice choiceUsername;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public LoginView() {
		
		//readVersion();
		
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				//updateExe();
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				//updateExe();
			}
			@Override
			public void windowOpened(WindowEvent e) {
				//updateExe();
			}
		});
		getContentPane().setBackground(Color.WHITE);
		setTitle("Kotoulas Energy S.A.");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 541, 367);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		txtPassword.setForeground(UIManager.getColor("MenuItem.selectionForeground"));
		
		//TextField txtPassword = new TextField();
		txtPassword.setBounds(241, 116, 226, 27);
		getContentPane().add(txtPassword);
		
		btnExit = new Button("Exit");
		btnExit.setActionCommand("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(51, 153, 255));
		btnExit.setBounds(72, 242, 120, 35);
		getContentPane().add(btnExit);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(102, 255, 51));
		panel.setBounds(0, 0, 541, 40);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		Label label_2 = new Label("Kotoulas Energy S.A.");
		label_2.setAlignment(Label.CENTER);
		panel.add(label_2);
		label_2.setFont(new Font("Dialog", Font.BOLD, 16));
		label_2.setForeground(Color.WHITE);
		
		btnLogin = new Button("Login");
		btnLogin.setActionCommand("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginPressed();
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(102, 255, 51));
		btnLogin.setBounds(334, 242, 120, 35);
		getContentPane().add(btnLogin);
		
		Label label = new Label("Username");
		//label.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setBounds(72, 81, 150, 21);
		getContentPane().add(label);
		
		Label label_1 = new Label("Password");
		//label_1.setFont(new Font("Dialog", Font.BOLD, 13));
		label_1.setForeground(Color.BLACK);
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(72, 118, 163, 21);
		getContentPane().add(label_1);
		
		Label label_3 = new Label("Store ID");
		//label_3.setFont(new Font("Dialog", Font.BOLD, 13));
		label_3.setForeground(Color.BLACK);
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(72, 153, 120, 21);
		getContentPane().add(label_3);
		
		Label label_4 = new Label("Device ID");
		//label_4.setFont(new Font("Dialog", Font.BOLD, 13));
		label_4.setForeground(Color.BLACK);
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(72, 188, 120, 21);
		getContentPane().add(label_4);
		
		lblConnecting = new JLabel("Connecting...");
		lblConnecting.setVisible(false);
		lblConnecting.setForeground(Color.GRAY);
		lblConnecting.setBounds(334, 282, 120, 15);
		getContentPane().add(lblConnecting);
		
		lblVersion = new JLabel(versionString);
		lblVersion.setFont(new Font("Dialog", Font.BOLD, 9));
		lblVersion.setForeground(SystemColor.controlDkShadow);
		lblVersion.setBackground(Color.WHITE);
		lblVersion.setBounds(72, 283, 120, 15);
		getContentPane().add(lblVersion);
		
		
		choiceStore = new Choice();
		choiceStore.setBounds(241, 153, 226, 21);
		getContentPane().add(choiceStore);
		
		choiceDevice = new Choice();
		choiceDevice.setBounds(241, 188, 226, 21);
		getContentPane().add(choiceDevice);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(241, 186, 226, 23);
		getContentPane().add(panel_2);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 173, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 23, Short.MAX_VALUE)
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(241, 153, 226, 23);
		getContentPane().add(panel_1);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 173, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 23, Short.MAX_VALUE)
		);
		panel_1.setLayout(gl_panel_1);
		
		choiceUsername = new Choice();
		choiceUsername.setBounds(241, 81, 226, 21);
		getContentPane().add(choiceUsername);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(241, 81, 226, 21);
		getContentPane().add(panel_3);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 202, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGap(0, 21, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateExe();
			}
		});
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(51, 153, 255));
		btnUpdate.setBounds(72, 303, 86, 15);
		getContentPane().add(btnUpdate);
		
		
		loadCombos();
		
		SwingUtilities.updateComponentTreeUI(this);

		invalidate();
		validate();
		repaint();
		
		//updateExe();
		//POSUpdater.clean();
	}
	
	
	private void loadCombos() {
		loadUsernames();
		loadStores();
		loadDevices();
	}
	
	
	private void loadUsernames() {
		List<Manager> managers = fetchManagers();
		if (managers.isEmpty())
			return;
		
		for (Manager manager : managers) {
			this.choiceUsername.add( manager.dashboard_login );
		}
	}
	
	private void loadStores() {
		List<Store> stores = fetchStores();
		if (stores.isEmpty())
			return;
		
		for (Store store : stores) {
			this.choiceStore.add( store.code );
		}
	}
	
	
	private void loadDevices() {
		List<Device> devices = fetchDevices();
		if (devices.size()<=0)
			return;
		
		for (Device device : devices) {
			this.choiceDevice.add( device.code);
		}
	}
	
	
	private List<Manager> fetchManagers(){
		GetClient getClient = new GetClient();
		getClient.setModel( new Manager() );
		getClient.setFilter( "(is_enabled=1)and(is_employee=1)&limit=100" );
		getClient.execute();
		
		if (getClient.getResponseCode()==200 && getClient.getObject().size()>0) {
			System.out.println("  Managers Fetched: " + getClient.getObject().size());
			return (List)getClient.getObject();
		}
		
		return (new ArrayList<Manager>());
	}
	
	
	private List<Device> fetchDevices(){
		GetClient getClient = new GetClient();
		getClient.setModel( new Device() );
		getClient.setFilter( "(is_enabled=1)&limit=100" );
		getClient.execute();
		
		if (getClient.getResponseCode()==200 && getClient.getObject().size()>0) {
			System.out.println("  Devices Fetched: " + getClient.getObject().size());
			return (List)getClient.getObject();
		}
		
		return (new ArrayList<Device>());
	}
	
	
	private List<Store> fetchStores(){
		GetClient getClient = new GetClient();
		getClient.setModel( new Store() );
		getClient.setFilter( "(is_enabled=1)&limit=100" );
		getClient.execute();
		
		if (getClient.getResponseCode()==200 && getClient.getObject().size()>0) {
			System.out.println("  Stores Fetched: " + getClient.getObject().size());
			return (List)getClient.getObject();
		}
		
		return (new ArrayList<Store>());
	}
	
	
	private LoginHandler setLoginHandler() {
		//String username = txtUsername.getText();
		String username = choiceUsername.getSelectedItem();
		String password = txtPassword.getText();
		String storeCode = choiceStore.getSelectedItem();
		//String storeCode = txtStore.getText();
		String deviceCode = choiceDevice.getSelectedItem();
		//String deviceCode = txtDevice.getText();
		
		clearLoginFields();
		
		LoginHandler loginHandler = new LoginHandler();
		loginHandler
			.setUsername(username)
			.setPassword(password)
			.setDeviceCode(deviceCode)
			.setStoreCode(storeCode)
			.execute();
		
		return loginHandler;
	}
	
	
	private void loginPressed() {
		
		this.connecting();
		
		System.out.println("LoginView: Login Pressed ("+ choiceUsername.getSelectedItem() + "/" + txtPassword.getText() +")");
		
		// Set Login Handler & fields and Execute
		LoginHandler loginHandler = setLoginHandler();
		
		System.out.println("LoginView: LoginHandlerResponse: "+loginHandler.getResponse()+".");
		
		// If successful, open Login into Main View
		if (loginHandler.getResponse()) {
			openMainView( loginHandler ) ;
		}
		
		this.stoppedConnecting();
		
	}
	
	
	private void openMainView(LoginHandler loginHandler) {
		System.out.println("LoginView: Login Successful.");
		// Get Login Objects
		Device device = loginHandler.getDevice();
		Store store = loginHandler.getStore();
		Manager manager = loginHandler.getManager();
		
		// Create Main View
		MainView mainView = new MainView();
		// Pass Login Objects
		mainView.setManager( manager );
		mainView.setDevice( device );
		mainView.setStore( store );
		// Switch to MainView
		mainView.setVisible(true);
		// Dispose this window
		dispose();
	}
	
	
	private void clearLoginFields() {
		txtPassword.setText("");
	}
	
	
	private Device getDevice() {
		Device device = new Device();
		return device;
	}
	
	
	private Store getStore() {
		Store store = new Store();
		return store;
	}
	
	
	private void connecting() {
		this.lblConnecting.setVisible(true);
		lblConnecting.paintImmediately(lblConnecting.getVisibleRect());
		this.btnLogin.setEnabled(false);
		this.choiceUsername.setEnabled(false);
		this.txtPassword.setEnabled(false);
		this.choiceDevice.setEnabled(false);
		this.choiceStore.setEnabled(false);
	}
	
	
	private void stoppedConnecting() {
		this.lblConnecting.setVisible(false);
		lblConnecting.paintImmediately(lblConnecting.getVisibleRect());
		this.btnLogin.setEnabled(true);
		this.choiceUsername.setEnabled(true);
		this.txtPassword.setEnabled(true);
		this.choiceDevice.setEnabled(true);
		this.choiceStore.setEnabled(true);
	}
	
	
	private void updateExe() {
		this.lblVersion.setText("Updating...");
		lblVersion.paintImmediately(lblVersion.getVisibleRect());
		
		updatingLock();
		
		//POSUpdater.update();
		UpdatingView updatingView = new UpdatingView();
		updatingView.setVisible(true);
		
		// Run Update
		POSUpdater.update();
		
		/*updatingUnlock();
		
		this.lblVersion.setText( versionString );
		lblVersion.paintImmediately(lblVersion.getVisibleRect());*/
		
		//restartAfterUpdateNeeded();
	}
	
	private void updatingLock() {
		this.choiceUsername.setEnabled(false);
		this.txtPassword.setEnabled(false);
		this.choiceDevice.setEnabled(false);
		this.choiceStore.setEnabled(false);
		this.btnLogin.setEnabled(false);
		this.btnExit.setEnabled(false);
	}
	
	private void updatingUnlock() {
		this.choiceUsername.setEnabled(true);
		this.txtPassword.setEnabled(true);
		this.choiceDevice.setEnabled(true);
		this.choiceStore.setEnabled(true);
		this.btnLogin.setEnabled(true);
		this.btnExit.setEnabled(true);
	}
	
	private void restartAfterUpdateNeeded() {
		this.lblVersion.setText("Restart Required!");
		this.btnUpdate.setText("Exit");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
	}
	
	/**
	 * Read version from POM file
	 */
	private void readVersion() {
		this.versionString = "XX.XX.XX.XX";
		MavenXpp3Reader reader = new MavenXpp3Reader();
		org.apache.maven.model.Model model;
		try {
			model = reader.read(new FileReader("pom.xml"));
			//System.out.println(model.getId());
			//System.out.println(model.getGroupId());
			//System.out.println(model.getArtifactId());
			//System.out.println(model.getVersion());
			this.versionString = model.getVersion();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}

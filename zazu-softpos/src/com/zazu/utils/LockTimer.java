package com.zazu.utils;


import java.awt.Frame;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.zazu.views.MainView;

public class LockTimer {
	
	private long delay = 4*60*1000L;
	Timer timer;
	TimerTask tTask;
	MainView mainView;
	
	public LockTimer(MainView mainView){
		this.mainView = mainView;
		set();
	}
	
	
	
	private void set() {
		tTask = new TimerTask() {
			public void run() {
	            System.out.println(">LockTimer performed on: " + new Date() + "n" +
	              "Thread's name: " + Thread.currentThread().getName());
	            action();
	        }
		};
		timer = new Timer("LockTimer");
	    timer.schedule(tTask, delay);
	}
	
	public void reset() {
		System.out.println(">LockTimer Reset");
		timer.cancel();
		timer.purge();
		set();
	}
	
	
	private void action() {
		mainView.logout();
		JOptionPane.showMessageDialog(new Frame(), "Αποσύνδεση λόγω αδράνειας!");
	}
	
	
}

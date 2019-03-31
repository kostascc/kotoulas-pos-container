package com.zazu.utils;

import java.awt.Frame;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
	
	
public final class POSUpdater {
	
	static final String executable_url = "https://www.dropbox.com/s/4yhwl8nm6p7jtf4/POS%20Updater%20-%20Kotoulas%20Energy.exe?dl=1";
	static final String executable_filename = "POS Updater - Kotoulas Energy.exe";
	
	public static void update() {
		System.out.println("Updater Started.");
		
		downloadExecutable();
		startNewExecutable();
		exitCurrentExecutable();
	}
	
	
	private static void downloadExecutable() {
		
		try (
			BufferedInputStream in = new BufferedInputStream(new URL( executable_url ).openStream()) ;
			FileOutputStream fileOutputStream = new FileOutputStream( executable_filename )) {
			System.out.println( "Executable: " + executable_filename );
			System.out.println( "URL: " + executable_url );
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
			
			fileOutputStream.flush();
			fileOutputStream.close();
			System.out.println("File Downloaded!");
		} catch (MalformedURLException e) {
			  JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		  } catch (RuntimeException e) {
			  JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		  }
		
	}
	
	private static void startNewExecutable() {
		try {
			//Runtime.getRuntime().exec( "\"" + executable_filename + "\"", null, new File( "\"" + System.getProperty("user.dir") + "\""));
			//Runtime.getRuntime().exec( new String[] { "cmd /c \"" + executable_filename + "\"", "-noupdate" } );
			Runtime.getRuntime().exec( "cmd /c \"" + executable_filename + "\" -noupdate");
		} catch (MalformedURLException e) {
			  JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		  } catch (IOException e) {
			  JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		  } catch (RuntimeException e) {
			  JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		  }
	}
	
	private static void exitCurrentExecutable() {
		System.exit(0);
	}
	
	
	public static void clean() {
		System.out.println("Cleaning Last Update.");
		File file = new File( executable_filename );
		file.delete();
	}

}

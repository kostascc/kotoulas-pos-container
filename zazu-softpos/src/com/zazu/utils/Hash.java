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
package com.zazu.utils;

import java.awt.Frame;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import com.zazu.config.*;

public class Hash {
	
	EncryptionConfig encryptionConfig = new EncryptionConfig();
	
	private String word = "";
	private String hash = "";
	
	private static boolean DEBUG = false;
	
	public Hash(){;}

	public Hash setWord(String word){ this.word = word; return this; }
	
	public String getHash() { hash(); return this.hash; }
	
	private void hash() { 
		
		try {
			String word = hashInner(this.word);
			this.hash = hashOutter(word);
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(new Frame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace(); 
		}
		
		
	}
	
	private String hashInner(String word) throws NoSuchAlgorithmException {
		
		for (int i=0; i<encryptionConfig.ROUNDS; i++) {
			MessageDigest md = MessageDigest.getInstance(encryptionConfig.INNER_ALGORITHM);
	        md.update((word+encryptionConfig.PASSWORD_INNER_SALT.toString().trim()).getBytes());
	        
	        byte byteData[] = md.digest();
	        
	        // Convert to HEX
	        StringBuffer sb = new StringBuffer();
	        for (int j = 0; j < byteData.length; j++) {
	         sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        if(DEBUG) System.out.println("Hex format : " + sb.toString());
	        word = sb.toString().trim();
		}
		
		return word;
		
	}
	
	private String hashOutter(String word) throws NoSuchAlgorithmException {
		
		for (int i=0; i<encryptionConfig.ROUNDS; i++) {
			MessageDigest md = MessageDigest.getInstance(encryptionConfig.OUTTER_ALGORITHM);
	        md.update((word+encryptionConfig.PASSWORD_OUTTER_SALT.toString().trim()).getBytes());
	        
	        byte byteData[] = md.digest();
	        
	        // Convert to HEX
	        StringBuffer sb = new StringBuffer();
	        for (int j = 0; j < byteData.length; j++) {
	         sb.append(Integer.toString((byteData[j] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        if (DEBUG) System.out.println("Hex format : " + sb.toString());
	        word = sb.toString().trim();
		}
		
		return word;
		
	}
	
}

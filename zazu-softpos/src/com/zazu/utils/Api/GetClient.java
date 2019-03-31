/*
 * Copyrights (C) 2018 Genie
 */

package com.zazu.utils.Api;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import com.zazu.models.*;


public class GetClient extends Client {
	
	public GetClient () { super(); }
	
	
	public void execute() {

		  try {
			  
			String urlFilter = "";
			if (this.filter.length()>0) urlFilter = this.filter;

			URL url = new URL(this.baseUrl + this.modelName + "?filter=" + urlFilter + "&fields=" + fields);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("X-DreamFactory-API-Key", this.apiKey);
			
			System.out.println( url );
			System.out.println("Filter: " + this.filter);
			//System.out.println("Response: " + conn.getResponseCode());
			
			this.responseCode = conn.getResponseCode();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output, response="";
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
				response += output;
			}
			
			response = response.trim();
			response += "{{end}}";
			setData(response);
			dataToObject();
			
			conn.disconnect();
			
			if (DEBUG) System.out.println( response );

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

}

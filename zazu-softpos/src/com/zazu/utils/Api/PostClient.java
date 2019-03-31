/*
 * Copyrights (C) 2018 Genie
 */

package com.zazu.utils.Api;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

import com.zazu.models.*;


public class PostClient extends Client {
	
	public PostClient () { super(); }
	
	
	
	public void execute() {

		  try {

			URL url = new URL(this.baseUrl + this.modelName);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			//conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("X-DreamFactory-API-Key", this.apiKey);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			//conn.setDoInput(true);
			
			System.out.println("URL:" + this.baseUrl + this.modelName);
						
			OutputStream os = conn.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(this.data);
            osw.flush();
            osw.close();
            os.close();
			
            this.responseCode = conn.getResponseCode();
            
			System.out.println("Response: " + conn.getResponseCode());

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output, response = "";
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				response += output;
			}
			
			response = response.trim();
			response += "{{end}}";
			setData(response);
			dataToObject();
			
			conn.disconnect();
			
			
			// Get id
			int id = -1;
			
			

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

package com.KudosApp.views;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
//import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// Registration Page
public class ThirdPresenter {
	@FXML
	private View third;
	@FXML
	private Label label;
	@FXML 
	private Label testLabel;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passWordField;
	
	
	// Registration Send
	@FXML
	String buttonClick() throws SQLException, NoSuchAlgorithmException {
		String login_url = "http://kudosapp.org/kudosLibb/login.php";
		try {
			//MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			String user_name = userNameField.getText();
			String passwordEntered = passWordField.getText();
			//messageDigest.update(passwordEntered.getBytes());
			//String encryptedPass = new String(messageDigest.digest());
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")
			+"&" +URLEncoder.encode("passwordEntered","UTF-8")+"="+URLEncoder.encode(passwordEntered,"UTF-8");
			bufferedWriter.write(post_data);
			bufferedWriter.flush();
			bufferedWriter.close();
			outputStream.close();
			InputStream inputStream = httpURLConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
			String result="";
			String line="";
			while((line = bufferedReader.readLine())!= null) {
				result += line;
				}
			String r2 = result;
			testLabel.setText(String.valueOf(r2));
			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
		}
	// Go Back
	@FXML
	void goBack(){
		MobileApplication.getInstance().switchView("Fourth View");
	}
	}



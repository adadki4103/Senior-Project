package com.gluonapplication.views;
// Login for Brand Users

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
import java.sql.SQLException;
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class BrandPresenter {
	@FXML
	private View brand;
	@FXML 
	private Label testLabel;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passWordField;
	String r2; 
	String r3;
	public static String brandName;
	public String user_name;
	public String passwordEntered;
	
	public void initialize() { 
    	brand.setShowTransitionFactory(BounceInUpTransition::new);
        brand.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
     
            }});
	}
// Go Back
	@FXML
	void goBack(){
		MobileApplication.getInstance().switchView("Landing View");
    }
// Check DB for Brand User & Pass
	@FXML
	String buttonClick() throws SQLException {
		String login_url = "http://kudosapp.org/kudosLibb/checkBrandUser2.php";
		try {
			user_name = userNameField.getText();
			passwordEntered = passWordField.getText();
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
			r2 = result;
			bufferedReader.close();
			inputStream.close();
			httpURLConnection.disconnect();
			if(r2.equals("Pass")){
				buttonClick2();
				MobileApplication.getInstance().switchView("Resolve View");
			}
			else if(r2.equals("Fail")){
				testLabel.setText("User Does Not Exist");
			}
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
	}
// Gets the Brand Name of the User Logging in.
	@FXML
	String buttonClick2() throws SQLException {
		String login_url = "http://kudosapp.org/kudosLibb/checkBrandUser.php";
		try {
			user_name = userNameField.getText();
			passwordEntered = passWordField.getText();
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
			r3 = result;
			brandName = String.valueOf(r3);
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

}

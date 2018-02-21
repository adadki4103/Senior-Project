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
import java.sql.SQLException;
import com.gluonhq.charm.glisten.animation.BounceInUpTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// Brand user Log in page
public class EleventhPresenter {
	@FXML
	private View primary;
	@FXML 
	private Label testLabel;
	@FXML
	private TextField userNameField;
	@FXML
	private PasswordField passWordField;
	String r2; 
	String r3;
	public static String brandName; // brandName of that brandUser
	public String user_name;
	public String passwordEntered;
	//public static String loggedInName;
	
	public void initialize() { 
    	primary.setShowTransitionFactory(BounceInUpTransition::new);
        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
     
            }});
	}
	// Go Back
	@FXML
	void goBack(){
		MobileApplication.getInstance().switchView("Fourth View");
    }
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
				MobileApplication.getInstance().switchView("Twelth View");
				System.out.println("Exist");
			}
			else if(r2.equals("Fail")){
				System.out.println("Not there");
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
			System.out.println("Brand: "+brandName);
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
	}

}



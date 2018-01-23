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
import com.gluonhq.charm.glisten.mvc.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ThirdPresenter {

	   @FXML
	    private View third;
	   
	   
	    @FXML
	    private Label label;
	    
	    @FXML
	    private TextField userNameField;
	    
	    @FXML
	    String buttonClick() throws SQLException {
	    	 String login_url = "http://kudosapp.org/kudosLibb/login.php";
	             try {
	                 String user_name = userNameField.getText();
	                 URL url = new URL(login_url);
	                 HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
	                 httpURLConnection.setRequestMethod("POST");
	                 httpURLConnection.setDoOutput(true);
	                 httpURLConnection.setDoInput(true);
	                 OutputStream outputStream = httpURLConnection.getOutputStream();
	                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
	                 String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8");
	                // +"&" +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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



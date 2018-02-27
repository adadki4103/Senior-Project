package com.gluonapplication.views;
// Give a Gift Controller

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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.mysql.jdbc.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GivePresenter {

    @FXML
    private View give;
    @FXML
    private ComboBox<String> brandCombo;
    @FXML
    private ComboBox<String> itemCombo;
	@FXML
	private TextField toSendName;
	@FXML
	private Label errorLabel;
	String r3;
	@FXML
	private Button sendItemButton;
    ObservableList<String> brandList = FXCollections.observableArrayList();
    ObservableList<String> itemList = FXCollections.observableArrayList();
    public static String itemChosen = "nullItem";
    public static String nameGiven = "nullName";

    public void initialize() {
        give.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setVisible(false);
                appBar.setTitleText("Give a Gift");
                try {
            		brandCombo.getSelectionModel().clearSelection();
            		brandCombo.getItems().clear();
            		itemCombo.getSelectionModel().clearSelection();
            		itemCombo.getItems().clear();
					brandToCombo();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            }});}
    // Populate Brand Combo Box from DB
    private String brandToCombo() throws SQLException{    	
    	String login_url = "http://kudosapp.org/kudosLibb/populateBrand.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
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
			String[] splitted = result.split("\\.");
			for(int i=0; i < splitted.length;i++){
				brandList.add(splitted[i]);
			}
			brandCombo.setItems(brandList);
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
    }
    // Get brandCombo Selection. Use it to populate itemCombo
    @FXML
    private String getBrandBox() throws SQLException, NoSuchAlgorithmException{
    	itemCombo.getItems().clear();
		String value = brandCombo.getValue();
		String login_url = "http://kudosapp.org/kudosLibb/whatItem.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("value","UTF-8")+"="+URLEncoder.encode(value,"UTF-8");
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
			String[] splitted = result.split("\\.");
			for(int i=0; i < splitted.length;i++){
				itemList.add(splitted[i]);
			}
			itemCombo.setItems(itemList);
			return result;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					}
		return null;
		}
    // Checks for User. Displays Error Message if they don't exist.
    @FXML
    private String checkForUser(){
    	String nameToCheck = toSendName.getText();
    	itemChosen = itemCombo.getValue(); 
		nameGiven = toSendName.getText();
		String login_url = "http://kudosapp.org/kudosLibb/doesExist.php";
		try {
			URL url = new URL(login_url);
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			String post_data = URLEncoder.encode("nameToCheck","UTF-8")+"="+URLEncoder.encode(nameToCheck,"UTF-8");
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
			errorLabel.setText(String.valueOf(r3));
			boolean isMyComboBoxEmpty = itemCombo.getSelectionModel().isEmpty();
			boolean nameExist = false;
			String str = toSendName.getText();
			String noName ="User Name Does Not Exist";
			String yesName = "User Name Exist";
			String errorMessage = "Please Select an Item";
			if(StringUtils.isEmptyOrWhitespaceOnly(str) == true){
				errorMessage = "Name cannot be blank";
			}
			if(r3.equals(noName) || toSendName.getText().isEmpty()){
				nameExist = false;
				errorMessage = "User Name Does Not Exist";
			}
			else if(r3.equals(yesName)){
				nameExist = true;
			}
			if(isMyComboBoxEmpty == false && nameExist == true && r3.equals(yesName) && StringUtils.isEmptyOrWhitespaceOnly(str) == false){
				itemChosen = itemCombo.getValue(); 
				nameGiven = toSendName.getText();
				MobileApplication.getInstance().switchView("Pay View");
			}
			else if(isMyComboBoxEmpty == true || nameExist == false){
				errorLabel.setText(String.valueOf(errorMessage));
				if(toSendName.getText().isEmpty()){
					errorMessage = "Please Enter a Name";
					errorLabel.setText(String.valueOf(errorMessage));}
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
    public static String giveItemName(){
    	return itemChosen;
    }
    public static String giveRecName(){
    	return nameGiven;
    }

    
}
